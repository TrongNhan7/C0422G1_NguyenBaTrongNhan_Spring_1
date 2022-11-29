import { Injectable } from '@angular/core';
import {TokenStorageService} from "./token-storage.service";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CheckAdminService implements CanActivate{

  constructor(private tokenStorageService: TokenStorageService,
              private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log(this.tokenStorageService.getRoles())
    if (this.tokenStorageService.getRoles() == '["ROLE_ADMIN"]'){
      return true;
    }
    this.router.navigateByUrl('/contract-add')
    return false;
  }
}
