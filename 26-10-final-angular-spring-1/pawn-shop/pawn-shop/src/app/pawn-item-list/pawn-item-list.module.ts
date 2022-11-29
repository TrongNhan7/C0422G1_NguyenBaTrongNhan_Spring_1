import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PawnItemListRoutingModule } from './pawn-item-list-routing.module';
import { PawnItemComponent } from './pawn-item/pawn-item.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
    declarations: [PawnItemComponent],
    exports: [
        PawnItemComponent
    ],
    imports: [
        CommonModule,
        PawnItemListRoutingModule,
        ReactiveFormsModule
    ]
})
export class PawnItemListModule { }
