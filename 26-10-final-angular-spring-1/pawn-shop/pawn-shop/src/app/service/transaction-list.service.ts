import { Injectable } from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from "../../environments/environment";
import {TokenStorageService} from "./token-storage.service";

const API_URL = `${environment.apiUrl}`;
@Injectable({
  providedIn: 'root'
})
export class TransactionListService {
  token = '';
  httpOptions: any;
  constructor(private httpClient: HttpClient, private  jwtService: TokenStorageService) {
    this.token = jwtService.getJwt();
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.token
      })
    };
  }

  getPageContract(page:number, customerName: string, pawnItemName: string,
                  type: string, startDate: string,
                  endDate: string, status: string): Observable<any>{
    if (customerName == "#"
      || customerName == "%"
      || customerName == "^"
      || customerName == "&"
      || customerName == "+"
      || customerName == "_"
      || customerName == "{}"
      || pawnItemName == "#"
      || pawnItemName == "%"
      || pawnItemName == "^"
      || pawnItemName == "&"
      || pawnItemName == "+"
      || pawnItemName == "_"
      || pawnItemName == "{}" ){
        customerName = "?";
        pawnItemName = "?";
    }
    if (startDate == null){
      startDate = "0000-00-00";
    }
    if (endDate == null){
      endDate = "2032-01-01";
    }
    return this.httpClient.get<any>(API_URL + "/api/employee/contracts?page="+ page + "&customerName=" + customerName +
    "&pawnItemName=" + pawnItemName + "&type=" + type + "&startDate=" + startDate + "&endDate=" + endDate + "&status=" + status,this.httpOptions);
  }


  getContractDetail(id: number): Observable<any>{
    return this.httpClient.get<any>(`${API_URL}/api/employee/contracts/${id}`,this.httpOptions);
  }

  deleteContract(id: number): Observable<any>{
    return this.httpClient.patch<any>(`${API_URL}/api/employee/contracts/${id}`, null,this.httpOptions);
  }
}
