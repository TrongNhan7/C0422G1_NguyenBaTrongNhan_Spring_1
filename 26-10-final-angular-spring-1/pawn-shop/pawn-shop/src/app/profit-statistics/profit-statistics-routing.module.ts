import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompleteContractComponent} from "./complete-contract/complete-contract.component";
import {ExpectedContractComponent} from "./expected-contract/expected-contract.component";
import {CommonModule} from "@angular/common";
import {LiquidationContractComponent} from "./liquidation-contract/liquidation-contract.component";
import {CustomerAddComponent} from "../customer-management/customer-add/customer-add.component";
import {CheckCanActiveService} from "../service/check-can-active.service";

const routes: Routes = [
  {path: 'complete-contract', component: CompleteContractComponent, canActivate: [CheckCanActiveService]},
  {path: 'expected-contract', component: ExpectedContractComponent, canActivate: [CheckCanActiveService]},
  {path: 'liquidation-contract', component: LiquidationContractComponent, canActivate: [CheckCanActiveService]}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfitStatisticsRoutingModule {
}
