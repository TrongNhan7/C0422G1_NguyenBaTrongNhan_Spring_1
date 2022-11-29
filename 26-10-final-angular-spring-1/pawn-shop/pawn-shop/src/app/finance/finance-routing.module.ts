import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FinanceComponent} from "./finance/finance.component";
import {CheckAdminService} from "../service/check-admin.service";


const routes: Routes = [
  {path: 'finance', component: FinanceComponent, canActivate: [CheckAdminService]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FinanceRoutingModule { }
