import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ShareDataService {

  private isLogin = new BehaviorSubject(false);
  private isEmployee = new BehaviorSubject(false)
  private isAdmin = new BehaviorSubject(false)

  currentLoginStatus = this.isLogin.asObservable();
  currentEmployeeStatus = this.isEmployee.asObservable()
  currentAdminStatus = this.isAdmin.asObservable()

  constructor() {
  }

  changeLoginStatus(status: boolean){
    this.isLogin.next(status)
  }

  changeIsEmployeeStatus(status: boolean){
    this.isEmployee.next(status)
  }

  changeIsAdminStatus(status: boolean){
    this.isAdmin.next(status)
  }
}
