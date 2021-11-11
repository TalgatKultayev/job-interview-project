import { ProductCheckoutComponent } from '../components/product-checkout/product-checkout.component';

export class CheckoutOrder {
  id!:number;
  productName!: string;
  productPrice!: number;
  productQuantity!: number;
  uniqueOrderId!: string;
  constructor(
    id:number,
    productName:string,
    productPrice:number,
    productQuantity:number,
    uniqueOrderId:string) {

    this.id = id;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productQuantity = productQuantity;
    this.uniqueOrderId = uniqueOrderId;
  }
}
