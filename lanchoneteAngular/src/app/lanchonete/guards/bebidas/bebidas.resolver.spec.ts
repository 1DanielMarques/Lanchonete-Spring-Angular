import { TestBed } from '@angular/core/testing';

import { BebidasResolver } from './bebidas.resolver';

describe('BebidasResolver', () => {
  let resolver: BebidasResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(BebidasResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
