import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PaymentFormComponent } from './payment-form/payment-form.component';
import { AppRoutingModule } from './app-routing.module';
import { UspesnoComponent } from './uspesno/uspesno.component';
import { NeuspesnoComponent } from './neuspesno/neuspesno.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { GreskaComponent } from './greska/greska.component';

@NgModule({
  declarations: [
    AppComponent,
    PaymentFormComponent,
    UspesnoComponent,
    NeuspesnoComponent,
    GreskaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
