import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NewsRoutingModule } from './news-routing.module';
import { NewsAddComponent } from './news-add/news-add.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { NewsListComponent } from './news-list/news-list.component';


@NgModule({
  declarations: [NewsAddComponent, NewsListComponent],
  exports: [
    NewsAddComponent,
    NewsListComponent
  ],
  imports: [
    CommonModule,
    NewsRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class NewsModule { }
