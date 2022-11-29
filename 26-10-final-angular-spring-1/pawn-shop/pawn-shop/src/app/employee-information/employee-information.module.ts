import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeInformationRoutingModule } from './employee-information-routing.module';
import { EmployeeInformationComponent } from './employee-information/employee-information.component';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
    declarations: [EmployeeInformationComponent, EmployeeUpdateComponent],
  exports: [
    EmployeeInformationComponent,
    EmployeeUpdateComponent
  ],
  imports: [
    CommonModule,
    EmployeeInformationRoutingModule,
    ReactiveFormsModule
  ]
})
export class EmployeeInformationModule { }
