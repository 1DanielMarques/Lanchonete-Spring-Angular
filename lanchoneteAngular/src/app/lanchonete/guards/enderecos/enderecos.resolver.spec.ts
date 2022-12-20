import { TestBed } from '@angular/core/testing';

import { EnderecosResolver } from './enderecos.resolver';

describe('EnderecosResolver', () => {
  let resolver: EnderecosResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(EnderecosResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
