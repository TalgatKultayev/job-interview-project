import { Component, OnInit } from '@angular/core';
import { NamePrice } from 'src/app/common/name-price';
import { NamePriceService } from 'src/app/services/name-price.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormGroupName,
  Validators,
} from '@angular/forms';
import { Product } from 'src/app/common/product';
import { BehaviorSubject, ReplaySubject, Subject } from 'rxjs';
import { Router } from '@angular/router';
import { CheckoutOrderService } from 'src/app/services/checkout-order.service';
import { Request } from 'src/app/common/request';
import { CheckoutOrder } from 'src/app/common/checkout-order';

@Component({
  selector: 'app-product-checkout',
  templateUrl: './product-checkout.component.html',
  styleUrls: ['./product-checkout.component.css'],
})
export class ProductCheckoutComponent implements OnInit {
  productGroup!: FormGroup;
  storage: Storage = sessionStorage;
  updateRequest!: Request;

  iPrice: Subject<number> = new BehaviorSubject<number>(0);
  iQuantity: Subject<number> = new BehaviorSubject<number>(0);

  namePrices: NamePrice[] = [];
  products: Product[] = [];
  nameOfProduct: string = '';

  totalQuantity: number = 0;
  totalPrice: number = 0;

  selectedLevel: string = '';

  ////////////////////////////////////////

  constructor(
    private namePriceService: NamePriceService,
    private formBuilder: FormBuilder,
    private router: Router,
    private checkoutOrderService: CheckoutOrderService
  ) {}

  ngOnInit(): void {
    let data = JSON.parse(this.storage.getItem('updateRequest')!);

    this.productGroup = this.formBuilder.group({
      product: this.formBuilder.group({
        productName: new FormControl('', [
          Validators.required,
          Validators.pattern('^[a-zA-Z._ -]+$'),
        ]),

        productQuantity: new FormControl('', [
          Validators.required,
          Validators.pattern('^[0-9]{1,13}$'),
        ]),

        productPrice: new FormControl('', [
          Validators.required,
          Validators.pattern('^[0-9]+(.[0-9][0-9]?)?$'),
        ]),
      }),
    });

    //populate name-price array
    this.namePriceService.getNamePrices().subscribe((data) => {
      this.namePrices = data;
    });

    this.reviewProductDetails();
    //this.selected();

    if (data != null) {
      this.updateRequest = data;

      /////NURBOLGA///////////////////////////////////////////////////////////
      this.productGroup
        .get('product.productName')
        ?.setValue(this.updateRequest.checkoutOrder.productName);
      this.productGroup
        .get('product.productPrice')
        ?.setValue(this.updateRequest.checkoutOrder.productPrice.toFixed(2));
      this.productGroup
        .get('product.productQuantity')
        ?.setValue(this.updateRequest.checkoutOrder.productQuantity);
        /////NURBOLGA///////////////////////////////////////////////////////////////
    }
  }

  //method called upon clicking submit button, JSON data can be retrieved
  onSubmit() {
    console.log('Handling the submit button');
    if (this.productGroup.invalid) {
      this.productGroup.markAllAsTouched();
      return;
    }
    // console.log(this.productGroup.get('product')!.value);

    //set up purchase

    //populate request - checkoutOrder(aka product)
    let request = new Request();
    request.checkoutOrder = this.productGroup.controls['product'].value;

    console.log("JSON of request is following: " + JSON.stringify(request));
    console.log("Update request is " + JSON.stringify(this.updateRequest));
    if (this.updateRequest != null) {
      request.checkoutOrder.id = this.updateRequest.checkoutOrder.id;
      request.checkoutOrder.uniqueOrderId =
        this.updateRequest.checkoutOrder.uniqueOrderId;
    }
    //call REST API via checkoutOrderService
    if (!this.updateRequest) {
      this.checkoutOrderService.placeOrder(request).subscribe({
        next: (response) => {
          this.updateRequest = response;
          this.storage.setItem(
            'updateRequest',
            JSON.stringify(this.updateRequest)
          );
          alert(
            `Your order has been received.\nOrder tracking number: ${response.checkoutOrder.uniqueOrderId}`
          );
          this.updateRequest = response;
          this.storage.setItem(
            'updateRequest',
            JSON.stringify(this.updateRequest)
          );
          // console.log('updateRequest is' + JSON.stringify(this.updateRequest.checkoutOrder.id));

          //reset cart
          this.resetData();
        },
        error: (err) => {
          alert(`There was an error: ${err.message}`);
        },
      });
    } else {
      this.checkoutOrderService.updateOrder(request).subscribe({
        next: (response) => {
          this.updateRequest = response;
          this.storage.setItem(
            'updateRequest',
            JSON.stringify(this.updateRequest)
          );
          alert(
            `Your order has been received.\nOrder tracking number: ${response.checkoutOrder.uniqueOrderId}`
          );

          // console.log('updateRequest is' + JSON.stringify(this.updateRequest.checkoutOrder.id));

          //reset cart
          this.resetData();
        },
        error: (err) => {
          alert(`There was an error: ${err.message}`);
        },
      });
    }
  }
  //reset data after submission
  resetData() {
    //reset form data
    this.productGroup.reset();
    this.router.navigateByUrl('validation');
    //navigate to order info page, session memory applied there, update link there
  }

  //user enters value, it instantly displayed in total quantity field and used to calculate price for selected product
  copyValueEntered(event: any) {
    let currentQuantityValue: number = 0;
    currentQuantityValue = event.target.value;
    this.iQuantity.next(currentQuantityValue);
  }

  //get selected name through event, then search in namePrices for appropriate price for given name

  selected() {
    let currentPriceValue: number = 0;
    let selectedProductName: string = this.selectedLevel;
    console.log('Selected name is ' + selectedProductName);
    this.nameOfProduct = selectedProductName;
    for (let i = 0; i < this.namePrices.length; i++) {
      if (this.namePrices[i].productName === selectedProductName) {
        currentPriceValue = this.namePrices[i].productPrice;
        this.iQuantity.subscribe((data) => {
          this.iPrice.next(currentPriceValue * data);
        });
      }
    }
  }

  ////////////////////////////
  //Subscribe for instant data renewal
  reviewProductDetails() {
    this.iQuantity.subscribe((data) => (this.totalQuantity = data));

    this.iPrice.subscribe(
      (data) => (
        (this.totalPrice = data),
        this.productGroup.get('product.productPrice')?.setValue(data.toFixed(2))
      )
    );
  }

  //getter methods used for validation
  get productProductName() {
    return this.productGroup.get('product.productName');
  }
  get productProductQuantity() {
    return this.productGroup.get('product.productQuantity');
  }
  get productProductPrice() {
    return this.productGroup.get('product.productPrice');
  }
}
