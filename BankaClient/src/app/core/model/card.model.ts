export class Card {
  pan: string;
  securityCode: string;
  cardHolderName: string;
  datumVazenja: string;
  merchantUsername: string;
  paymentId:number;
  constructor() {
    this.cardHolderName = '';
    this.pan = '';
    this.securityCode = '';
    this.datumVazenja = '';
    this.merchantUsername = '';
    this.paymentId = null;
  }
}

