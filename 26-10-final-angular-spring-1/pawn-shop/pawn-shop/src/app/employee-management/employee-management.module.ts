import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EmployeeManagementRoutingModule} from './employee-management-routing.module';
import {EmployeeManagementComponent} from './employee-management/employee-management.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


@NgModule({
  declarations: [EmployeeManagementComponent],
  exports: [
    EmployeeManagementComponent
  ],
  imports: [
    CommonModule,
    EmployeeManagementRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule
  ]
})
export class EmployeeManagementModule {
}
