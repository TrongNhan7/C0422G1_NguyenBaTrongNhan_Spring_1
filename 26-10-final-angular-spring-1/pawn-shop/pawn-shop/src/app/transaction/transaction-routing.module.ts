import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TransactionHistoryComponent} from "./transaction-history/transaction-history.component";
import {UpdateContractComponent} from "./update-contract/update-contract.component";
import {CheckCanActiveService} from "../service/check-can-active.service";


const routes: Routes = [
  {path: 'transaction-history', component: TransactionHistoryComponent, canActivate: [CheckCanActiveService]},
  {path: 'update-contract/:id', component: UpdateContractComponent, canActivate: [CheckCanActiveService]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransactionRoutingModule { }
