import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() {
  }

  public saveLocalStorage(loginResponse) {
    this.clearStorage()
    localStorage.setItem('jwt', loginResponse.jwt);
    localStorage.setItem('roles', JSON.stringify(loginResponse.roles));
    localStorage.setItem('username', loginResponse.username);
    localStorage.setItem('employeeCode', loginResponse.employeeCode);
  }

  public saveSessionStorage(loginResponse) {
    this.clearStorage()
    sessionStorage.setItem('jwt', loginResponse.jwt);
    sessionStorage.setItem('roles', JSON.stringify(loginResponse.roles));
    sessionStorage.setItem('username', loginResponse.username);
    sessionStorage.setItem('employeeCode', loginResponse.employeeCode);
  }

  public getJwt(): string {
    if (localStorage.getItem('jwt') != null) {
      return localStorage.getItem('jwt');
    } else {
      return sessionStorage.getItem('jwt');
    }
  }

  public getUsername(): string {
    if (localStorage.getItem('username') != null) {
      return localStorage.getItem('username');
    } else {
      return sessionStorage.getItem('username');
    }
  }

  public getEmployeeCode(): string {
    if (localStorage.getItem('employeeCode') != null) {
      return localStorage.getItem('employeeCode');
    } else {
      return sessionStorage.getItem('employeeCode');
    }
  }

  public getRoles(): string {
    if (localStorage.getItem('roles') != null) {
      return localStorage.getItem('roles');
    } else {
      return sessionStorage.getItem('roles');
    }
  }

  public clearStorage(){
    localStorage.clear();
    sessionStorage.clear();
  }
}
