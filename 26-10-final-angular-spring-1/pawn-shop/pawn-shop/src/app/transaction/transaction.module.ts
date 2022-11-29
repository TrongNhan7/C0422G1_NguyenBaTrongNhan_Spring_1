import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionRoutingModule } from './transaction-routing.module';
import { TransactionHistoryComponent } from './transaction-history/transaction-history.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { UpdateContractComponent } from './update-contract/update-contract.component';


@NgModule({
  declarations: [TransactionHistoryComponent, UpdateContractComponent],
  exports: [
    TransactionHistoryComponent,
    UpdateContractComponent
  ],
  imports: [
    CommonModule,
    TransactionRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class TransactionModule { }
