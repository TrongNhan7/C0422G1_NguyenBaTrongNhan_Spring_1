import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompleteContractComponent } from './complete-contract.component';

describe('CompleteContractComponent', () => {
  let component: CompleteContractComponent;
  let fixture: ComponentFixture<CompleteContractComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompleteContractComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompleteContractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
