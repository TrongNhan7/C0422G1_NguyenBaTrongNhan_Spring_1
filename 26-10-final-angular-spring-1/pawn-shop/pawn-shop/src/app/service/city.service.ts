import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {City} from '../model/address/city';
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<City[]> {
    return this.httpClient.get<City[]>("http://localhost:8080/api/public/cities");
  }
}
