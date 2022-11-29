import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Employee} from '../model/employee/employee';
import {Observable} from 'rxjs';
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  token = '';
  httpOptions: any;

  constructor(private http: HttpClient, private  jwtService: TokenStorageService) {
    this.token = jwtService.getJwt();
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.token
      })
    };
  }

  findByUser(user: string) {
    return this.http.get('http://localhost:8080/api/employee/findUser/' + user,this.httpOptions);
  }

  update(employee: Employee): Observable<Employee> {
    // @ts-ignore
    return this.http.patch<Employee>('http://localhost:8080/api/employee/update', employee,this.httpOptions);
  }
}
