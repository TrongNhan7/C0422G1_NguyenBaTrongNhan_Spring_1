import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpectedContractComponent } from './expected-contract.component';

describe('ExpectedContractComponent', () => {
  let component: ExpectedContractComponent;
  let fixture: ComponentFixture<ExpectedContractComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpectedContractComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpectedContractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
