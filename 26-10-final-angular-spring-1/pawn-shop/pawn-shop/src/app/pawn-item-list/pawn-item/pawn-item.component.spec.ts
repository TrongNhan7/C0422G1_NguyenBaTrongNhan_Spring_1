import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PawnItemComponent } from './pawn-item.component';

describe('PawnItemComponent', () => {
  let component: PawnItemComponent;
  let fixture: ComponentFixture<PawnItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PawnItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PawnItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
