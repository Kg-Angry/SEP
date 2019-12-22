import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PaymentFormComponent} from './payment-form/payment-form.component';
import {UspesnoComponent} from './uspesno/uspesno.component';
import {NeuspesnoComponent} from './neuspesno/neuspesno.component';

const routes: Routes = [
  {
    path: 'banka/:paymentId',
    component: PaymentFormComponent
  },{
    path: 'uspesno',
    component: UspesnoComponent
  },{
    path: 'neuspesno',
    component: NeuspesnoComponent
  }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
