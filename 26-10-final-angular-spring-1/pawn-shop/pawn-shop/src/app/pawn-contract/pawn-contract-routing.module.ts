import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ContractAddComponent} from "./contract-add/contract-add.component";
import {CheckCanActiveService} from "../service/check-can-active.service";


const routes: Routes = [
  {path: 'contract-add', component: ContractAddComponent, canActivate: [CheckCanActiveService]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PawnContractRoutingModule { }
