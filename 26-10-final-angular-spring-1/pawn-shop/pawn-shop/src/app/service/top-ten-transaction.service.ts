import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Contract} from "../model/contract/contract";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class TopTenTransactionService {

  API_URL = 'http://localhost:8080/';
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

  getAll(): Observable<Contract[]> {
    // @ts-ignore
    return this.http.get<Contract[]>(this.API_URL + `api/employee/contracts/top10Contract`,this.httpOptions);
  }
}
