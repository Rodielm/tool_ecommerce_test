import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PriceService } from '../services/price.service';
import { SearchPrice } from '../price/interfaces/search.price.interface';
import { FormsModule } from '@angular/forms';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-search-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css'],
})
export class SearchFormComponent {
  @Output()
  // change to response
  resultsEvent = new EventEmitter<any>();
  params: SearchPrice = {
    appDate: '',
    brandId: 0,
    productId: 0,
  };

  inputValidation = {} as {
    [key: string]: boolean;
  };

  constructor(private priceService: PriceService) {
    console.log('This init....', Object.keys(this.params));
    Object.keys(this.params).forEach((key) => {
      this.inputValidation[key] = false;
    });
  }

  onSubmit() {
    const keys: (keyof SearchPrice)[] = Object.keys(
      this.params
    ) as (keyof SearchPrice)[];

    const isInvalid = keys.some((key) => {
      const value = this.params[key];
      this.inputValidation[key] = value === '' || value === 0;
      return this.inputValidation[key];
    });

    if (isInvalid) {
      return;
    }

    /////

    const formattedDate = formatDate(
      this.params.appDate,
      'yyyyMMddHHmmss',
      'en-US'
    );

    console.log('Get Data', formattedDate);

    const paramsReq = {
      appDate: formattedDate,
      brandId: this.params.brandId,
      productId: this.params.productId,
    };

    this.priceService.searchPrice(paramsReq).subscribe((resp) => {
      console.log('Get Data', resp);
      this.resultsEvent.emit(resp);
    });
  }
}
