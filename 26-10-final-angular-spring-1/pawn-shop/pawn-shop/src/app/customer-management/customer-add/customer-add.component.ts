import {Component, Inject, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {District} from "../../model/address/district";
import {City} from "../../model/address/city";
import {CustomerService} from "../../service/customer.service";
import {Router} from "@angular/router";
import {finalize} from "rxjs/operators";
import {AngularFireStorage} from "@angular/fire/storage";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-customer-add',
  templateUrl: './customer-add.component.html',
  styleUrls: ['./customer-add.component.css']
})
export class CustomerAddComponent implements OnInit {
  idCity: number = 0;
  fileList: any = "";
  links: any[] = [];
  urlList: any[] = [];

  customerForm: FormGroup = new FormGroup({
    name: new FormControl('',
      [Validators.required, Validators.pattern("^([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ]{1})" +
        "([a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)" +
        "((\\s{1}([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ]{1})" +
        "[a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+))+$")]),
    code: new FormControl(''),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern("^((0)[0-9]{9})$")]),
    dateOfBirth: new FormControl('', [Validators.required, this.validateDateOfBirth]),
    email: new FormControl('', [Validators.required, Validators.email]),
    gender: new FormControl('0', [Validators.required]),
    idCard: new FormControl('', [Validators.required, Validators.pattern("^([0-9]{9})$")]),
    imgUrl: new FormControl('', [Validators.required]),
    status: new FormControl(true),
    address: new FormGroup({
      id: new FormControl(),
      street: new FormControl("", [Validators.required]),
      district: new FormControl("0", [Validators.required]),
      city: new FormControl("0", [Validators.required]),
    })
  })


  validateDateOfBirth(startDate: AbstractControl) {
    let temp = startDate.value.split("-");
    let now = new Date();
    if (now.getFullYear() - temp[0] > 18 && now.getFullYear() - temp[0] < 120) {
      return null;
    }
    return {'invalidStartDate': true}
  }

  districtList: District[] = [];
  cityList: City[] = [];

  constructor(private customerService: CustomerService, private router: Router,
              @Inject(AngularFireStorage) private storage: AngularFireStorage,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.findAllCities();
  }

  findAllCities() {
    this.customerService.getAllCity().subscribe(city => {
      this.cityList = city;
    });
  }

  create() {
    if (this.customerForm.valid) {
      let address = this.customerForm.value.address;
      let customerFormNew = this.customerForm.value;
      this.idCity = this.customerForm.value.address.district.city;
      this.customerService.getCityById(this.customerForm.value.city)
      console.log(this.customerForm.value.address.district.city)

      this.customerService.saveAddress(address).subscribe(addresses => {
        customerFormNew.address = addresses
      })
      this.handleFiles().then(() => {
        customerFormNew.imgUrl = this.fileList
        this.customerService.createNewCustomer(customerFormNew).subscribe(
          value => {
            this.router.navigateByUrl("customer-list");
            this.toast.success('Thêm mới thành công')
          },
          error => this.toast.error('Thêm mới thất bại')
        );
      })
    } else {
      console.log(this.customerForm)
      this.toast.error('Vui lòng nhập đúng thông tin')
    }
  }

  changeCity(target: any) {
    this.customerService.getAllDistrict(target.value).subscribe(district => {
      this.districtList = district;
    })
  }

  show(event: any) {
    this.links = event.target.files;
    console.log(this.links)
    for (let file of event.target.files) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
// tslint:disable-next-line:variable-name
      reader.onload = (_event) => {
        this.urlList.push(reader.result);
      };
    }
  }

  uploadFile(img: any) {
    return new Promise((resolve, reject) => {
      const nameImg = Date.now() + img.name;
      const fileRef = this.storage.ref(nameImg);
      this.storage.upload(nameImg, img).snapshotChanges().pipe(
        finalize(() => {
          fileRef.getDownloadURL().subscribe((url) => {
            this.customerForm.patchValue({img: url});
            resolve(true)
            this.fileList += url + ",";
          });
        })
      ).subscribe()
    });
  }

  async handleFiles() {
    for (let i = 0; i < this.links.length; i++) {
      await this.uploadFile(this.links[i])
    }
  }
  resetForm(){
    history.go()
  }
}
