import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Request } from 'src/app/common/request';
import { CheckoutOrderService } from 'src/app/services/checkout-order.service';
import { ProductCheckoutComponent } from '../product-checkout/product-checkout.component';

@Component({
  selector: 'app-validation',
  templateUrl: './validation.component.html',
  styleUrls: ['./validation.component.css'],
})
export class ValidationComponent implements OnInit {
  updateRequest!: Request;
  productName!: string;
  productPrice!: number;
  productQuantity!: number;
  id!: number;

  storage: Storage = sessionStorage;
  constructor(private router : Router) {
    let data = JSON.parse(this.storage.getItem('updateRequest')!);

    if (data != null) {
      this.updateRequest = data;
    }

    console.log('Arrived at validation ' + JSON.stringify(this.updateRequest));

    this.updateValues();
  }

  updateValues() {
    this.productName = this.updateRequest.checkoutOrder.productName;
    console.log("Name is " + this.productName);
    this.productPrice = this.updateRequest.checkoutOrder.productPrice;
    console.log("Price is " + this.productPrice);
    this.productQuantity = this.updateRequest.checkoutOrder.productQuantity;
    console.log("Quant is " + this.productQuantity);
    this.id = this.updateRequest.checkoutOrder.id;
    console.log("ID is " + this.id);
  }

  ngOnInit(): void {
  }
  newClick(){
    this.storage.removeItem('updateRequest');
    this.router.navigateByUrl('/checkout');
  }
  updateClick(){
      this.router.navigateByUrl("/checkout");
    }
}
