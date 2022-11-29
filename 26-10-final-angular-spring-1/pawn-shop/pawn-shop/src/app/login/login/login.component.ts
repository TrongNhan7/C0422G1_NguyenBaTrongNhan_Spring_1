import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../service/login.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {ShareDataService} from "../../service/share-data.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formLogin: FormGroup;
  isLogin: boolean;
  subscription: Subscription;

  constructor(private loginService: LoginService,
              private tokenStorageService: TokenStorageService,
              private toastr: ToastrService,
              private router: Router,
              private data: ShareDataService) {
    if (tokenStorageService.getRoles() != undefined) {
      router.navigateByUrl('')
    }
    this.formLogin = new FormGroup({
        username: new FormControl('', [Validators.maxLength(30), Validators.required]),
        password: new FormControl('', [Validators.maxLength(30), Validators.required]),
        rememberMe: new FormControl()
      }
    );
  }

  ngOnInit(): void {
    this.data.changeLoginStatus(false)
    this.subscription = this.data.currentLoginStatus.subscribe(status => this.isLogin = status)
  }

  login() {
    let loginRequest = this.formLogin.value;
      this.loginService.login(loginRequest).subscribe(loginResponse => {
      this.data.changeLoginStatus(true)
      this.data.changeIsEmployeeStatus(true)
      if (loginRequest.rememberMe) {
        this.tokenStorageService.saveLocalStorage(loginResponse);
      } else {
        this.tokenStorageService.saveSessionStorage(loginResponse);
      }
      this.tokenStorageService.getRoles() == 'ROLE_ADMIN' ? this.data.changeIsAdminStatus(true) : this.data.changeIsAdminStatus(false)

      this.router.navigateByUrl("contract-add")
      this.toastr.success('Chào ' + this.tokenStorageService.getUsername(), 'Đăng nhập thành công', {
        extendedTimeOut: 1500,
        timeOut: 3000
      });
    }, error => {
      console.log(error);
      this.toastr.error("Tài khoản hoặc mật khẩu không đúng", 'Đăng nhập không thành công', {
        extendedTimeOut: 1500,
        timeOut: 3000
      });
    });
  }
}
