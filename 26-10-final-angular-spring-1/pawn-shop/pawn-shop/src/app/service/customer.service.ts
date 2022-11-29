import {Injectable} from '@angular/core';
import {Customer} from "../model/customer/customer";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {City} from "../model/address/city";
import {District} from "../model/address/district";
import {Address} from "../model/address/address";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  token = '';
  httpOptions: any;

  private URL_CUSTOMER = 'http://localhost:8080/api/employee/customer'

  constructor(private http: HttpClient, private  jwtService: TokenStorageService) {
    this.token = jwtService.getJwt();
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.token
      })
    };
  }

  getAllCustomer(name: string, page: number): Observable<any> {
    return this.http.get<any>(this.URL_CUSTOMER + "?page=" + page + "&name=" + name, this.httpOptions)
  }

  deleteCustomer(id: any): Observable<Customer> {
    console.log(id)
    // @ts-ignore
    return this.http.patch<Customer>(this.URL_CUSTOMER + '/' + id,"",this.httpOptions);
  }

  createNewCustomer(customer: Customer) {
    return this.http.post('http://localhost:8080/api/employee/customer/goCreateCustomer', customer, this.httpOptions);
  }

  getAllCity(): Observable<City[]> {
    // @ts-ignore
    return this.http.get<City[]>('http://localhost:8080/api/public/cities', this.httpOptions);
  }

  getAllDistrict(id: number): Observable<District[]> {
    // @ts-ignore
    return this.http.get<District[]>('http://localhost:8080/api/public/districts?city=' + id, this.httpOptions);
  }

  getCityById(id: number): Observable<City> {
    // @ts-ignore
    return this.http.get('http://localhost:8080/api/public/cities/findCityById?id=' + id, this.httpOptions);
  }

  saveAddress(address: Address) {
    return this.http.post<Address>('http://localhost:8080/api/employee/customer/saveAddress', address, this.httpOptions);
  }

  findCustomerById(id: number): Observable<Customer> {
    // @ts-ignore
    return this.http.get<Customer>('http://localhost:8080/api/employee/findCustomerById?id=' + id, this.httpOptions);
  }

  updateCustomer(customer: Customer) {
    return this.http.patch('http://localhost:8080/api/employee/customer/goUpdateCustomer', customer, this.httpOptions);
  }
}

