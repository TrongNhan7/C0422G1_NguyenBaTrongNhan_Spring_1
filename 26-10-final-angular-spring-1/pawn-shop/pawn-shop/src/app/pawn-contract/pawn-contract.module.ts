import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PawnContractRoutingModule } from './pawn-contract-routing.module';
import { ContractAddComponent } from './contract-add/contract-add.component';
import {ReactiveFormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";


@NgModule({
    declarations: [ContractAddComponent],
    exports: [
        ContractAddComponent
    ],
  imports: [
    CommonModule,
    PawnContractRoutingModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ]
})
export class PawnContractModule { }
