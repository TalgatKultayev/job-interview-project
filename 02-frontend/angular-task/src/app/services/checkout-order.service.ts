import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Request } from '../common/request';

@Injectable({
  providedIn: 'root',
})
export class CheckoutOrderService {
  private requestUrl = 'http://localhost:8080/api/checkoutOrders';
  constructor(private httpClient: HttpClient) {}
  placeOrder(request: Request): Observable<any> {
    return this.httpClient.post<Request>(this.requestUrl, request);
  }
  updateOrder(request: Request): Observable<any> {
    return this.httpClient.put<Request>(this.requestUrl, request);
  }
}
