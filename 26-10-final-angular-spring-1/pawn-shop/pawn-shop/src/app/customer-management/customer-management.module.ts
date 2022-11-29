import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CustomerManagementRoutingModule} from './customer-management-routing.module';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CustomerAddComponent } from './customer-add/customer-add.component';
import { CustomerUpdateComponent } from './customer-update/customer-update.component';


@NgModule({
  declarations: [CustomerListComponent, CustomerAddComponent, CustomerUpdateComponent],
  exports: [
    CustomerListComponent,
    CustomerAddComponent,
    CustomerUpdateComponent
  ],
  imports: [
    CommonModule,
    CustomerManagementRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class CustomerManagementModule {
}
