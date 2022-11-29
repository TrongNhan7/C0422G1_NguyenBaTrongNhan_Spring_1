import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {TokenStorageService} from './token-storage.service';

@Injectable({
  providedIn: "root"
})
export class LoginService {

  constructor(private httpClient: HttpClient, private jwtService: TokenStorageService) {
  }

  public login(loginRequest) {
    console.log(loginRequest);
    return this.httpClient.post('http://localhost:8080/api/public/login', {
      username: loginRequest.username,
      password: loginRequest.password
    });
  }

  public sendMail(email: string) {
    return this.httpClient.post('http://localhost:8080/api/public/forgot-password', email);
  }

  public resetPassword(newPassword: string, jwt: string) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + jwt
      })
    }

    return this.httpClient.patch('http://localhost:8080/api/employee/reset-password', newPassword, httpOptions);
  }
}

