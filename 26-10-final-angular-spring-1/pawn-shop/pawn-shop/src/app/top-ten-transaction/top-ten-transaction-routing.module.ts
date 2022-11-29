import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TopTenTransactionComponent} from "./top-ten-transaction/top-ten-transaction.component";
import {CheckCanActiveService} from "../service/check-can-active.service";


const routes: Routes = [
  {path: 'top-ten-transaction', component: TopTenTransactionComponent, canActivate: [CheckCanActiveService]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TopTenTransactionRoutingModule { }
