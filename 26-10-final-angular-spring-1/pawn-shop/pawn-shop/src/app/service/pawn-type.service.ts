import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class PawnTypeService {

  private URL_PAWN_TYPE = 'http://localhost:8080/api/employee/pawnTypeRest'
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

  getAllPawnType(): Observable<any> {
    return this.http.get(this.URL_PAWN_TYPE + '/list',this.httpOptions);
  }

  findAll(): Observable<any>{
    return this.http.get("http://localhost:8080/api/public/contract/pawnType",this.httpOptions);
  }
}
