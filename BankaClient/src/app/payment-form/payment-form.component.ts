import { Component, OnInit } from '@angular/core';
import {Card} from '../core/model/card.model';
import {PaymentService} from '../core/services/payment.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css']
})
export class PaymentFormComponent implements OnInit {

  card: Card = new Card();

  constructor(private payService: PaymentService, private route: ActivatedRoute ) { }

  ngOnInit() {
    this.card.paymentId = parseInt(this.route.snapshot.paramMap.get('paymentId'));
  }
  sendCard(card: Card) {
    console.log(this.card);
    this.payService.sendCard(card).subscribe(
      data => {
        location.href = data.statusTransakcije;
      } , error => {
        alert(error.error.message);
    }
    );
  }
}
