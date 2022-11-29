import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LiquidationContractComponent } from './liquidation-contract.component';

describe('LiquidationContractComponent', () => {
  let component: LiquidationContractComponent;
  let fixture: ComponentFixture<LiquidationContractComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LiquidationContractComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LiquidationContractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
