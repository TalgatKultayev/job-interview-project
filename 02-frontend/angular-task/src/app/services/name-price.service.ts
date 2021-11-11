import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { NamePrice } from '../common/name-price';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NamePriceService {

  private namePriceUrl = 'http://localhost:8080/api/productPrices';

  constructor(private httpClient : HttpClient) { }

  getNamePrices() : Observable<NamePrice[]> {
    return this.httpClient.get<NamePrice[]>(this.namePriceUrl).pipe(
      map(response => response)
    );
  }
}

interface ResponseNamePrice {
  namePrices: NamePrice[]
}
