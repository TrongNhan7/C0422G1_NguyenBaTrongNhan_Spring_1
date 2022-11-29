import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../service/login.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {ShareDataService} from "../../service/share-data.service";
import {TokenStorageService} from "../../service/token-storage.service";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit, OnDestroy {
  emailForm: FormGroup;

  constructor(private loginService: LoginService,
              private toastr: ToastrService,
              private router: Router,
              private data: ShareDataService,
              private tokenStorageService: TokenStorageService) {
    this.emailForm = new FormGroup({
      email: new FormControl("",[Validators.maxLength(30),Validators.required])
    })
    if (tokenStorageService.getUsername() != undefined){
      data.changeLoginStatus(false)
    }
  }

  ngOnInit(): void {
  }

  sendEmail() {
    this.loginService.sendMail(this.emailForm.value.email).subscribe(next => {
      this.router.navigateByUrl('')
      this.toastr.success('Hãy kiểm tra email của bạn', 'Gửi mail thành công', {
        extendedTimeOut: 1500,
        timeOut: 3000
      });
    },error => {
      this.toastr.error(error.error, 'Gửi mail thất bại', {
        extendedTimeOut: 1500,
        timeOut: 3000
      });
    })
  }

  ngOnDestroy(): void {
    this.data.changeLoginStatus(true)
  }
}
