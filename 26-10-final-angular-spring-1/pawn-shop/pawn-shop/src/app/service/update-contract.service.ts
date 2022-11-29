import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Contract} from "../model/contract/contract";
import {Customer} from "../model/customer/customer";
import {PawnType} from "../model/pawn/pawn-type";
import {TokenStorageService} from "./token-storage.service";
import {PawnImg} from "../model/pawn/pawn-img";

@Injectable({
  providedIn: 'root'
})
export class UpdateContractService {
  token = '';
  httpOptions: any;

  API_URL = 'http://localhost:8080/';
  constructor(private http: HttpClient, private jwtService: TokenStorageService) {
    this.token = jwtService.getJwt();
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.token
      })
    }; }

  findById(id: number): Observable<Contract> {
    // @ts-ignore
    return this.http.get<Contract>(this.API_URL + 'api/employee/contracts/contract/' + id,this.httpOptions);
  }
  updateContract(contract: Contract): Observable<Contract> {
    // @ts-ignore
    return this.http.patch<Contract>(this.API_URL + 'api/employee/contracts/update-contract', contract,this.httpOptions);
  }
  getCustomer(): Observable<Customer[]> {
    // @ts-ignore
    return this.http.get<Customer[]>(this.API_URL + 'api/employee/getCustomerToEdit',this.httpOptions);
  }

  getPawnType(): Observable<PawnType[]> {
    // @ts-ignore
    return this.http.get<PawnType[]>(this.API_URL + 'api/employee/contracts/pawntypelist',this.httpOptions);
  }

  getPawmImg(): Observable<PawnImg[]> {
    // @ts-ignore
    return this.http.get<PawnImg[]>(this.API_URL + `api/employee/contracts/pawnImg/list`,this.httpOptions);
  }
}
