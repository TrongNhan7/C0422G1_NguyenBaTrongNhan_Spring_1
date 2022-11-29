import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Contract} from '../model/contract/contract';
import {Observable} from 'rxjs';
import {Customer} from "../model/customer/customer";
import {PawnType} from "../model/pawn/pawn-type";
import {PawnItem} from "../model/pawn/pawn-item";
import {TokenStorageService} from "./token-storage.service";

const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  token = '';
  httpOptions: any;

  constructor(private http: HttpClient, private jwtService: TokenStorageService) {
    this.token = jwtService.getJwt();
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.token
      })
    };
  }

  getAllPaginationAndSearch(index: number, code: string, customerName: string, pawnItem: string, startDate: string): Observable<Contract[]> {
    // @ts-ignore
    return this.http.get<Contract[]>(API_URL + '/api/employee/contracts/listPage?page=' + index + '&code='
      + code + '&customerName=' + customerName + '&pawnItem=' + pawnItem + '&startDate=' + startDate, this.httpOptions);
  }

  getAllNotPagination(): Observable<Contract[]> {
    // @ts-ignore
    return this.http.get<Contract[]>(API_URL + '/api/employee/contracts/listNotPagination',this.httpOptions);
  }

  returnItem(id: number, email: string, customerName: string, liquidationPrice: string, pawnItem: string, returnDate: Date): Observable<Contract> {
    // @ts-ignore
    return this.http.get<Contract>(API_URL + '/api/employee/contracts/returnItem/'
      + id + '?email=' + email + '&customerName=' + customerName + '&liquidationPrice=' + liquidationPrice + '&pawnItem=' + pawnItem + '&returnDate' + returnDate, this.httpOptions);
  }

  findAllCustomer(): Observable<Customer[]> {
    // @ts-ignore
    return this.http.get<Customer[]>('http://localhost:8080/api/employee/customer/list',this.httpOptions);
  }

  findAllPawnType(): Observable<PawnType[]> {
    // @ts-ignore
    return this.http.get<PawnType[]>('http://localhost:8080/api/employee/contracts/pawntypelist',this.httpOptions);
  }

  search(idCard: string): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/employee/findCustomerByIdCard?idCard=${idCard}`,this.httpOptions);
  }

  createPawnItem(pawnItem: PawnItem): Observable<PawnItem>{
    // @ts-ignore
    return this.http.post<PawnItem>("http://localhost:8080/api/employee/pawnItem/addPawnItem" , pawnItem,this.httpOptions);
  }


  createContract(value: any) {
    return this.http.post('http://localhost:8080/api/employee/contracts/create', value,this.httpOptions);
  }

}

