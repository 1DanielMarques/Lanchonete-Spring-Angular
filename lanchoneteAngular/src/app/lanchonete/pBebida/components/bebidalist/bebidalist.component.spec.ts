import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BebidalistComponent } from './bebidalist.component';

describe('BebidalistComponent', () => {
  let component: BebidalistComponent;
  let fixture: ComponentFixture<BebidalistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BebidalistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BebidalistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
