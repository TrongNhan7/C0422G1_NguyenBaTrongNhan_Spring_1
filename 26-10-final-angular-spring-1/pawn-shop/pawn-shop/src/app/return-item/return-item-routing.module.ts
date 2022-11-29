import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ReturnItemComponent} from "./return-item/return-item.component";
import {CheckCanActiveService} from "../service/check-can-active.service";


const routes: Routes = [
  {path: 'return-item', component: ReturnItemComponent, canActivate: [CheckCanActiveService]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReturnItemRoutingModule { }
