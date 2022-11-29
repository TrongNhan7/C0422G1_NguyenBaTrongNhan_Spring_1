import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TopTenTransactionRoutingModule } from './top-ten-transaction-routing.module';
import { TopTenTransactionComponent } from './top-ten-transaction/top-ten-transaction.component';


@NgModule({
    declarations: [TopTenTransactionComponent],
    exports: [
        TopTenTransactionComponent
    ],
    imports: [
        CommonModule,
        TopTenTransactionRoutingModule
    ]
})
export class TopTenTransactionModule { }
