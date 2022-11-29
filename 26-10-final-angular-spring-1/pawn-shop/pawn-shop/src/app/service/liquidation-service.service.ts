import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class LiquidationServiceService {
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

  findAllPawnItem(object: any, page: number): Observable<any> {
    console.log(this.token)
    return this.httpClient.get(`http://localhost:8080/api/employee/liquidation/pawnItem/list?namePawnItem=${object.namePawnItem}&idPawnType=${object.idPawnType}&price=${object.price}&page=${page}`,this.httpOptions)
  }

  findAllPawnType():Observable<any>{
    return this.httpClient.get(`http://localhost:8080/api/employee/liquidation/pawnType/list`,this.httpOptions);
  }

  updateLiquidation(object:any):Observable<any>{
    console.log(object)
    return this.httpClient.patch("http://localhost:8080/api/employee/liquidation/update",object,this.httpOptions);
  }

  findImgUrl(idPawnItem: number): Observable<string[]> {
    // @ts-ignore
    return this.httpClient.get<string[]>("http://localhost:8080/api/employee/liquidation/pawn-img/"+ idPawnItem,this.httpOptions);
  }
}
