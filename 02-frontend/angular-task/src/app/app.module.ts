import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProductCheckoutComponent } from './components/product-checkout/product-checkout.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { ValidationComponent } from './components/validation/validation.component';

const routes : Routes = [
  {path:'validation', component: ValidationComponent},
  {path:'checkout', component: ProductCheckoutComponent},
  { path: '', redirectTo: '/checkout', pathMatch: 'full' },
  { path: '**', redirectTo: '/checkout', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    ProductCheckoutComponent,
    ValidationComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    ReactiveFormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
