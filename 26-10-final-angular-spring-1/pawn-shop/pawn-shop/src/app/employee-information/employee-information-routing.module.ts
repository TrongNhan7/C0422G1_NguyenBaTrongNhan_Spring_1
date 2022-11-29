import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EmployeeInformationComponent} from "./employee-information/employee-information.component";
import {EmployeeUpdateComponent} from "./employee-update/employee-update.component";
import {CustomerAddComponent} from "../customer-management/customer-add/customer-add.component";
import {CheckCanActiveService} from "../service/check-can-active.service";


const routes: Routes = [
  {path: 'employee-information', component: EmployeeInformationComponent, canActivate: [CheckCanActiveService]},
  {path: 'employee-update/:username', component: EmployeeUpdateComponent, canActivate: [CheckCanActiveService]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeInformationRoutingModule { }
