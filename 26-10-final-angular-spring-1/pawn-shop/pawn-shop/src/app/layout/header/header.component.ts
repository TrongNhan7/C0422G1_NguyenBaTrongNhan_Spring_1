import {Component, HostListener, OnInit} from '@angular/core';
import {ShareDataService} from "../../service/share-data.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  navbarFixed: boolean = false;

  @HostListener('window:scroll', ['$event']) onscroll() {
    if (window.scrollY > 100) {
      this.navbarFixed = true;
    } else {
      this.navbarFixed = false;
    }
  }

  isLogin: boolean;
  subscription: Subscription;


  constructor(private data: ShareDataService,
              private tokenStorageService: TokenStorageService,
              private router: Router) {

  }

  ngOnInit(): void {
    this.subscription = this.data.currentEmployeeStatus.subscribe(status => {
      this.isLogin = status
    })
  }

  logout() {
    this.tokenStorageService.clearStorage()
    this.data.changeLoginStatus(false)
    this.data.changeIsEmployeeStatus(false)
    window.location.reload()
  }
}
