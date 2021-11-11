import { TestBed } from '@angular/core/testing';

import { NamePriceService } from './name-price.service';

describe('NamePriceService', () => {
  let service: NamePriceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NamePriceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
