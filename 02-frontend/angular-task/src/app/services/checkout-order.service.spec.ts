import { TestBed } from '@angular/core/testing';

import { CheckoutOrderService } from './checkout-order.service';

describe('CheckoutOrderService', () => {
  let service: CheckoutOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckoutOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
