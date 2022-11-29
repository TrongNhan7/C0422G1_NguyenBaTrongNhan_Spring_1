import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LiquidationComponent} from "./liquidation/liquidation.component";
import {CustomerAddComponent} from "../customer-management/customer-add/customer-add.component";
import {CheckCanActiveService} from "../service/check-can-active.service";


const routes: Routes = [
  {path: 'liquidation', component: LiquidationComponent, canActivate: [CheckCanActiveService]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LiquidationRoutingModule { }
