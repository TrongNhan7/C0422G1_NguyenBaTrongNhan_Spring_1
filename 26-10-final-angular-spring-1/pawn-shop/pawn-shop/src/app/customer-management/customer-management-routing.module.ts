import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CustomerAddComponent} from "./customer-add/customer-add.component";
import {CustomerListComponent} from "./customer-list/customer-list.component";
import {CustomerUpdateComponent} from "./customer-update/customer-update.component";
import {CheckCanActiveService} from "../service/check-can-active.service";


const routes: Routes = [
  {path: 'customer-add', component: CustomerAddComponent, canActivate: [CheckCanActiveService]},
  {path: 'customer-list', component: CustomerListComponent, canActivate: [CheckCanActiveService]},
  {path: 'customer-update/:id', component: CustomerUpdateComponent, canActivate: [CheckCanActiveService]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerManagementRoutingModule {
}
