import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {PawnItemComponent} from "./pawn-item/pawn-item.component";
import {CustomerAddComponent} from "../customer-management/customer-add/customer-add.component";
import {CheckCanActiveService} from "../service/check-can-active.service";


const routes: Routes = [
  {path: 'pawn-item', component: PawnItemComponent, canActivate: [CheckCanActiveService]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PawnItemListRoutingModule {
}
