import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Contract} from '../model/finance/contract';
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class CompleteContractService {
  private URL_COMPLETE_CONTRACT = 'http://localhost:8080/api/employee/interestRest/complete-contract';
  token = '';
  httpOptions: any;

  constructor(private http: HttpClient,private jwtService: TokenStorageService) {
    this.token = jwtService.getJwt();
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.token
      })
    };

  }

  findCompleteContractByDate(startReturnDate: string, endReturnDate: string, page: number): Observable<any> {
    return this.http.get(this.URL_COMPLETE_CONTRACT + '?page=' + page +
      '&startReturnDate=' + startReturnDate + '&endReturnDate=' + endReturnDate,this.httpOptions);
  }

  findContractById(id: number): Observable<Contract> {
    // @ts-ignore
    return this.http.get<Contract>('http://localhost:8080/api/employee/interestRest' + '/' + id,this.httpOptions);
  }

  getListCompleteContractByDate(startReturnDate: string, endReturnDate: string): Observable<any> {
    return this.http.get('http://localhost:8080/api/employee/interestRest/complete-contract/list' +
      '?startReturnDate=' + startReturnDate + '&endReturnDate=' + endReturnDate,this.httpOptions);
  }
}
