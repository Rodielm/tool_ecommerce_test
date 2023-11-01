import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule, formatDate } from '@angular/common';
import { PriceService } from '../services/price.service';
import { CreatePrice } from '../price/interfaces/create.price.interface';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-form.component.html',
  styleUrls: ['./create-form.component.css'],
})
export class CreateFormComponent {
  @Output()
  // change to response
  updateEvent = new EventEmitter<any[]>();
  params: CreatePrice = {
    brandId: '0',
    productId: '0',
    priceList: '0',
    priority: 1,
    price: 0,
    startDate: '',
    endDate: '',
  };

  inputValidation = {} as {
    [key: string]: boolean;
  };

  constructor(private priceService: PriceService) {
    Object.keys(this.params).forEach((key) => {
      this.inputValidation[key] = false;
    });
  }

  onSubmit() {
    const keys: (keyof CreatePrice)[] = Object.keys(
      this.params
    ) as (keyof CreatePrice)[];

    const isInvalid = keys.some((key) => {
      const value = this.params[key];
      this.inputValidation[key] = value === '' || value === 0 || value === '0';
      return this.inputValidation[key];
    });

    if (isInvalid) {
      return;
    }

    const formattedDate = (date: string) =>
      formatDate(date, 'yyyyMMddHHmmss', 'en-US');

    const paramsReq = {
      brandId: parseInt(this.params.brandId),
      productId: parseInt(this.params.productId),
      priceList: parseInt(this.params.priceList),
      price: this.params.price,
      priority: this.params.priority,
      startDate: formattedDate(this.params.startDate),
      endDate: formattedDate(this.params.endDate),
    };

    if (paramsReq.startDate > paramsReq.endDate) {
      this.inputValidation['startDate'] = true;
      return;
    }

    // const test = {
    //   brandId: 2,
    //   productId: '3',
    //   priceList: '2',
    //   priority: 2,
    //   price: 100,
    //   startDate: '20231016092300',
    //   endDate: '20231026092300',
    // };

    this.priceService.createPrice(paramsReq).subscribe({
      next: (res) => {
        console.log('POST request successful:', res);
        this.updateEvent.emit();
      },
      error: (err) => {
        console.log('POST request failed:', err);
      },
    });
  }
}
