import {Component, Inject, OnInit} from '@angular/core';
import {Employee} from "../../model/employee/employee";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EmployeeService} from "../../service/employee.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {finalize} from "rxjs/operators";
import {AngularFireStorage} from "@angular/fire/storage";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit {
  employee: any;
  employeeForm: FormGroup;
  img: any = '';
  username

  constructor(private employeeService: EmployeeService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              @Inject(AngularFireStorage) private storage: AngularFireStorage,
              private toastrService: ToastrService) {
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.username = paramMap.get('username');
      this.employeeService.findByUser(this.username).subscribe(next => {
        this.employee = next;

        this.employeeForm = new FormGroup({
          id: new FormControl(this.employee.id),
          userName: new FormControl(this.username),
          name: new FormControl(this.employee.name,[Validators.required,Validators.pattern('^([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ]{1})" +\n' +
            '          "([a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)" +\n' +
            '          "((\\\\s{1}([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ]{1})" +\n' +
            '          "[a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+))+$')]),
          email: new FormControl(this.employee.email,[Validators.required,Validators.email]),
          address: new FormControl(this.employee.address,[Validators.required]),
          phoneNumber: new FormControl(this.employee.phoneNumber,[Validators.required,Validators.pattern('^0[0-9]{9}$')]),
          idCard: new FormControl(this.employee.idCard,[Validators.required,Validators.pattern('^[0-9]{12}$')]),
          dateOfBirth: new FormControl(this.employee.dateOfBirth,[Validators.required]),
          gender: new FormControl(this.employee.gender),
          imgUrl: new FormControl(this.employee.imgUrl)
        });
      });
    });
  }

  uploadFile() {
    return new Promise((resolve, reject) => {
        const nameImg = Date.now() + this.img.name;
        const fileRef = this.storage.ref(nameImg);
        this.storage.upload(nameImg, this.img).snapshotChanges().pipe(
          finalize(() => {
            fileRef.getDownloadURL().subscribe((url) => {
              this.employeeForm.patchValue({img: url});
              resolve(true);
              this.employeeForm.value.imgUrl = url;
            });
          })
        ).subscribe();
      }
    );
  }

  ngOnInit(): void {
  }

  submit() {
    if (this.img){
      this.uploadFile().then(() => {
        const employee = this.employeeForm.value;
        this.employeeService.update(employee).subscribe(next => {
          this.router.navigateByUrl('/employee-information');
          this.toastrService.success("Chỉnh sửa thông tin cá nhân thành công","Chỉnh sửa thông tin")
        });
      })
    }else {
      this.employeeService.update(this.employeeForm.value).subscribe(next => {
        this.router.navigateByUrl('/employee-information');
        this.toastrService.success("Chỉnh sửa thông tin cá nhân thành công","Chỉnh sửa thông tin")
      });
    }
  }

  showImg($event: any) {
    this.img = $event.target.files[0];
  }
}
