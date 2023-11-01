import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { TabComponent } from 'src/common/tabs/tab.component';
import { TabsComponent } from 'src/common/tabs/tabs.component';
import { SearchFormComponent } from '../search-form/search-form.component';
import { CreateFormComponent } from '../create-form/create-form.component';
import { HttpClientModule } from '@angular/common/http';
import { PriceService } from '../services/price.service';
import { ResponsePrice } from './interfaces/response.price.interface';

@Component({
  selector: 'app-price',
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    TabsComponent,
    TabComponent,
    SearchFormComponent,
    CreateFormComponent,
  ],
  templateUrl: './price.component.html',
  styleUrls: ['./price.component.css'],
})
export class PriceComponent implements OnInit {
  resultsPrice: ResponsePrice[] = [];

  // @ViewChild(SearchFormComponent)
  // private searchComponent!: SearchFormComponent;

  constructor(private priceService: PriceService) {}

  ngOnInit(): void {
    this.getPrices();
  }

  receiveDataFromChild(data: ResponsePrice) {
    console.log('This search data', data);
    if (data === null) {
      this.resultsPrice = [];
    } else {
      this.resultsPrice = [data];
    }
  }

  updatePrices() {
    this.getPrices();
  }

  getPrices() {
    this.priceService.getPrices().subscribe((res) => {
      console.log('data...', res);
      this.resultsPrice = [...res];
    });
  }
}
