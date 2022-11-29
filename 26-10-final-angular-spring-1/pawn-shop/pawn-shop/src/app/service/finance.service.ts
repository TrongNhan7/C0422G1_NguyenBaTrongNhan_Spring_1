import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Finance} from "../model/finance/finance";
import {TokenStorageService} from "./token-storage.service";


@Injectable({
  providedIn: 'root'
})
export class FinanceService {

  private URL_FINANCE = 'http://localhost:8080/api/admin/finance'
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

  getAllFinance(): Observable<Finance[]> {
    // @ts-ignore
    return this.http.get<Finance[]>(this.URL_FINANCE,this.httpOptions);
  }

  getInvestment(): Observable<number> {
    // @ts-ignore
    return this.http.get<number>('http://localhost:8080/api/admin/finance/investment',this.httpOptions);
  }

  getExpectedProfit(): Observable<number> {
    // @ts-ignore
    return this.http.get<number>('http://localhost:8080/api/admin/finance/profit',this.httpOptions);
  }

}
