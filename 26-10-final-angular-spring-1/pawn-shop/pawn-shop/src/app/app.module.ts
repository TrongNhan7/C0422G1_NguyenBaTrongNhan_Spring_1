import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ProfitStatisticsModule} from "./profit-statistics/profit-statistics.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {EmployeeInformationModule} from "./employee-information/employee-information.module";
import {environment} from "../environments/environment.prod";
import {AngularFireStorageModule} from "@angular/fire/storage";
import {AngularFireModule} from "@angular/fire";
import {NewsModule} from "./news/news.module";
import {LiquidationModule} from "./liquidation/liquidation.module";
import {ToastrModule} from "ngx-toastr";
import {CustomerManagementModule} from "./customer-management/customer-management.module";
import {ReturnItemModule} from "./return-item/return-item.module";
import {EmployeeManagementModule} from "./employee-management/employee-management.module";
import {LayoutModule} from "./layout/layout.module";
import {LoginModule} from "./login/login.module";
import {TransactionModule} from "./transaction/transaction.module";
import {PawnContractModule} from "./pawn-contract/pawn-contract.module";
import {FinanceModule} from "./finance/finance.module";
import {PawnItemListModule} from "./pawn-item-list/pawn-item-list.module";
import {TopTenTransactionModule} from "./top-ten-transaction/top-ten-transaction.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NgxUiLoaderHttpModule, NgxUiLoaderModule} from "ngx-ui-loader";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ProfitStatisticsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    EmployeeInformationModule,
    AngularFireStorageModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    NewsModule,
    LiquidationModule,
    ToastrModule.forRoot({
      timeOut: 2500,
      progressBar: true,
      preventDuplicates: true,
      closeButton: true
    }),
    CustomerManagementModule,
    ReturnItemModule,
    EmployeeManagementModule,
    LayoutModule,
    LoginModule,
    TransactionModule,
    PawnContractModule,
    FinanceModule,
    PawnItemListModule,
    TopTenTransactionModule,
    BrowserAnimationsModule,
    NgxUiLoaderModule.forRoot({
      "bgsColor": "#ff3300",
      "bgsOpacity": 0.5,
      "bgsPosition": "bottom-right",
      "bgsSize": 60,
      "bgsType": "ball-spin-clockwise",
      "blur": 5,
      "delay": 0,
      "fastFadeOut": true,
      "fgsColor": "#f37214",
      "fgsPosition": "center-center",
      "fgsSize": 60,
      "fgsType": "ball-scale-multiple",
      "gap": 24,
      "logoPosition": "center-center",
      "logoSize": 120,
      "logoUrl": "",
      "masterLoaderId": "master",
      "overlayBorderRadius": "0",
      "overlayColor": "rgba(40, 40, 40, 0.8)",
      "pbColor": "red",
      "pbDirection": "ltr",
      "pbThickness": 3,
      "hasProgressBar": true,
      "text": "",
      "textColor": "#FFFFFF",
      "textPosition": "center-center",
      "maxTime": -1,
      "minTime": 300
    }),
    NgxUiLoaderHttpModule.forRoot({
      showForeground: true
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
