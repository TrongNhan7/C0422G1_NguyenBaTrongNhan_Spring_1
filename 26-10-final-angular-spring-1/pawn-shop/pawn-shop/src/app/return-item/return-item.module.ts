import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ReturnItemRoutingModule} from './return-item-routing.module';
import {ReturnItemComponent} from './return-item/return-item.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [ReturnItemComponent],
  exports: [
    ReturnItemComponent
  ],
  imports: [
    CommonModule,
    ReturnItemRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ReturnItemModule {
}
