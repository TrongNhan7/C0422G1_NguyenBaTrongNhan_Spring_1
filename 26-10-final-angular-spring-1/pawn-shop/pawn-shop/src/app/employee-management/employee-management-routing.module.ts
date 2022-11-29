import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmployeeManagementComponent} from "./employee-management/employee-management.component";
import {CustomerAddComponent} from "../customer-management/customer-add/customer-add.component";
import {CheckCanActiveService} from "../service/check-can-active.service";
import {CheckAdminService} from "../service/check-admin.service";


const routes: Routes = [
  {path: 'employee-management', component: EmployeeManagementComponent, canActivate: [CheckAdminService]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeManagementRoutingModule { }
