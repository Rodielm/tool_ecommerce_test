import { Component } from '@angular/core';
import { PriceComponent } from './price/price.component';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [PriceComponent],
})
export class AppComponent {
  title = 'toolEcommerce-app';
}
