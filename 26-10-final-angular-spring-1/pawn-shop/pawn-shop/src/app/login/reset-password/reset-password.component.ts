import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../service/login.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {TokenStorageService} from "../../service/token-storage.service";
import {Component, OnDestroy, OnInit} from "@angular/core";
import {ShareDataService} from "../../service/share-data.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit, OnDestroy {
  jwt: string;
  resetPasswordForm: FormGroup;

  constructor(private loginService: LoginService,
              private activatedRoute: ActivatedRoute,
              private toastr: ToastrService,
              private tokenStorageService: TokenStorageService,
              private router: Router,
              private data: ShareDataService) {

    if (tokenStorageService.getUsername() != undefined){
      data.changeLoginStatus(false)
    }
  }

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.jwt = paramMap.get("jwt")
    })

    this.resetPasswordForm = new FormGroup({
      newPassword: new FormControl("", [Validators.required, Validators.maxLength(30)]),
      confirmPassword: new FormControl("", [Validators.required, Validators.maxLength(30)])
    }, this.compare)
  }

  resetPassword() {
    this.loginService.resetPassword(this.resetPasswordForm.value.newPassword, this.jwt).subscribe(loginResponse => {
      this.tokenStorageService.saveSessionStorage(loginResponse)
      this.data.changeLoginStatus(true)
      this.data.changeIsEmployeeStatus(true)
      this.router.navigateByUrl("contract-add")
      this.toastr.success('Chào ' + this.tokenStorageService.getUsername(), 'Đặt lại mật khẩu thành công', {
        extendedTimeOut: 1500,
        timeOut: 3000
      });
    },error => {
      this.toastr.error(error.error, 'Đặt lại mật khẩu thất bại', {
        extendedTimeOut: 1500,
        timeOut: 3000
      });
    })
  }

  compare(resetPasswordForm: AbstractControl) {
    return resetPasswordForm.value.newPassword === resetPasswordForm.value.confirmPassword ? null : {passwordNotMatch: true}
  }

  ngOnDestroy(): void {
    this.data.changeLoginStatus(true)
  }
}
