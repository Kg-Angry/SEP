import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Card} from '../model/card.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }

  sendCard(card: Card): Observable<any> {
    return this.http.post('https://localhost:8088/checkPan', card);
  }
}
