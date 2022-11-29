import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopTenTransactionComponent } from './top-ten-transaction.component';

describe('TopTenTransactionComponent', () => {
  let component: TopTenTransactionComponent;
  let fixture: ComponentFixture<TopTenTransactionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopTenTransactionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopTenTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
