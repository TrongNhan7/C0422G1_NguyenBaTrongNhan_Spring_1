import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {City} from '../model/address/city';
import {HttpClient} from '@angular/common/http';
import {District} from '../model/address/district';

@Injectable({
  providedIn: 'root'
})
export class DistrictService {

  constructor(private httpClient: HttpClient) {
  }

  findAll(city: number): Observable<District[]> {
    return this.httpClient.get<District[]>('http://localhost:8080/api/public/districts?city=' + city);
  }
}
