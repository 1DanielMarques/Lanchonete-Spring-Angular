import { TestBed } from '@angular/core/testing';

import { LanchesResolver } from './lanches.resolver';

describe('LanchesResolver', () => {
  let resolver: LanchesResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(LanchesResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
