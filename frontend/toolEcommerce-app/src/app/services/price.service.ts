import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SearchPrice } from '../price/interfaces/search.price.interface';
import { Observable } from 'rxjs';
import { ResponsePrice } from '../price/interfaces/response.price.interface';

@Injectable({
  providedIn: 'root',
})
export class PriceService {
  private apiUrl = 'http://localhost:8081/api/v1/prices';

  constructor(private http: HttpClient) {}

  getPrices(): Observable<ResponsePrice[]> {
    return this.http.get<ResponsePrice[]>(this.apiUrl);
  }

  searchPrice(params: SearchPrice): Observable<any> {
    const { appDate, brandId, productId } = params;
    const httpParams = new HttpParams()
      .set('appDate', appDate)
      .set('brandId', brandId.toString())
      .set('productId', productId.toString());
    return this.http.get(`${this.apiUrl}/search`, { params: httpParams });
  }

  createPrice(params: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, params);
  }
}
