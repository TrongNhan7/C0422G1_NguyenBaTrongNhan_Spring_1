import {Component, OnDestroy, OnInit} from '@angular/core';
import {ShareDataService} from "./service/share-data.service";
import {Subscription} from "rxjs";
import {TokenStorageService} from "./service/token-storage.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy{
  title = 'pawn-shop';
  isLogin: boolean = false;
  subscription: Subscription;
  isEmployee: boolean;
  constructor(private data: ShareDataService,
              private tokenStorageService: TokenStorageService) {

  }

  ngOnInit(): void {
    this.subscription = this.data.currentLoginStatus.subscribe(status => this.isLogin = status)
    if (this.tokenStorageService.getUsername() != undefined){
     this.data.changeIsEmployeeStatus(true)
      this.data.changeLoginStatus(true)
    }
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
