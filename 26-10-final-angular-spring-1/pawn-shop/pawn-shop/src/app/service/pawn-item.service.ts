import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class PawnItemService {

  private URL_PAWN = 'http://localhost:8080/api/employee/pawnItemRest'
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

  getAllPawnItem(itemName: string, pawnName: string, page: number): Observable<any> {
    return this.http.get<any>(this.URL_PAWN + "?page=" + page + "&itemName=" + itemName + "&pawnName=" + pawnName,this.httpOptions);
  }

  updateStatusContract(idContract: number) {
    return this.http.get(`http://localhost:8080/api/employee/pawnItemRest/updateStatusContract/` + idContract,this.httpOptions);
  }

}
