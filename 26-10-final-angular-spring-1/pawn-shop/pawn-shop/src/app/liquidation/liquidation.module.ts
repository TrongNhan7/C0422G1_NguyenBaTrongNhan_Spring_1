import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LiquidationRoutingModule} from './liquidation-routing.module';
import {LiquidationComponent} from './liquidation/liquidation.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [LiquidationComponent],
  exports: [
    LiquidationComponent
  ],
  imports: [
    CommonModule,
    LiquidationRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class LiquidationModule {
}
