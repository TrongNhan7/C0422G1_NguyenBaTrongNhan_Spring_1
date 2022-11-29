import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from "../../environments/environment";
import {Employee} from "../model/employee/employee";
import {TokenStorageService} from "./token-storage.service";


const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class EmployeeListService {
  token = '';
  httpOptions: any;

  constructor(private httpClient: HttpClient, private jwtService: TokenStorageService) {
    this.token = jwtService.getJwt();
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.token
      })
    };
  }

  getAllEmployee(searchKeyWordName: string, searchKeyWordCode: string, pageable: number) {
    return this.httpClient.get(API_URL + '/api/employee/list?name=' + searchKeyWordName +
      '&employeeCode=' + searchKeyWordCode + '&page=' + pageable, this.httpOptions);
  }

  deleteEmployee(id: number): Observable<Employee> {
    console.log(this.httpClient)
    console.log(id)
    // @ts-ignore
    return this.httpClient.patch<Employee>('http://localhost:8080/api/employee/delete/'+ id,"", this.httpOptions);
  }
}
