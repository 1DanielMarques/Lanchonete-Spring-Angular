import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LanchesListComponent } from './lanches-list.component';

describe('LanchesListComponent', () => {
  let component: LanchesListComponent;
  let fixture: ComponentFixture<LanchesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LanchesListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LanchesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
