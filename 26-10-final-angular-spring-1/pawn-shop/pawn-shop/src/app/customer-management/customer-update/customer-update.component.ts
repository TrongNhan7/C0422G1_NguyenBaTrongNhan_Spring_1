import {Component, Inject, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {District} from "../../model/address/district";
import {City} from "../../model/address/city";
import {CustomerService} from "../../service/customer.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {AngularFireStorage} from "@angular/fire/storage";
import {ToastrService} from "ngx-toastr";
import {finalize} from "rxjs/operators";

@Component({
  selector: 'app-customer-update',
  templateUrl: './customer-update.component.html',
  styleUrls: ['./customer-update.component.css']
})
export class CustomerUpdateComponent implements OnInit {
  fileList: any = "";
  links: any[] = [];
  idCustomer: number = 0;
  idCity: number = 0;
  customerForm: FormGroup;
  districtList: District[] = [];
  cityList: City[] = [];
  urlList: any[] = [];
  rootLength = 0;

  constructor(private customerService: CustomerService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              @Inject(AngularFireStorage) private storage: AngularFireStorage,
              private toast: ToastrService) {

  }

  validateDateOfBirth(startDate: AbstractControl) {
    let temp = startDate.value.split("-");
    let now = new Date();
    if (now.getFullYear() - temp[0] > 18 && now.getFullYear() - temp[0] < 120) {
      return null;
    }
    return {'invalidStartDate': true}
  }


  ngOnInit(): void {
    this.customerForm = new FormGroup({
      id: new FormControl(''),
      name: new FormControl('',
        [Validators.required, Validators.pattern("^([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ]{1})" +
          "([a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)" +
          "((\\s{1}([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ]{1})" +
          "[a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+))+$")]),
      code: new FormControl(''),
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern("^([0-9]{10})$")]),
      dateOfBirth: new FormControl('', [Validators.required, this.validateDateOfBirth]),
      email: new FormControl('', [Validators.required, Validators.email]),
      gender: new FormControl('', [Validators.required]),
      idCard: new FormControl('', [Validators.required, Validators.pattern("^([0-9]{9})$")]),
      imgUrl: new FormControl('', [Validators.required]),
      status: new FormControl(true),
      address: new FormGroup({
        id: new FormControl(),
        street: new FormControl('', [Validators.required]),
        district: new FormGroup({
          id: new FormControl('',),
          prefix: new FormControl(''),
          city: new FormGroup({
            id: new FormControl('', Validators.required),
            name: new FormControl()
          })
        }),
      })
    });

    this.activatedRoute.paramMap.subscribe((paraMap: ParamMap) => {
      this.idCustomer = +paraMap.get("id");
      this.customerService.findCustomerById(Number(this.idCustomer)).subscribe(idForUpdate => {
          this.customerForm.patchValue(idForUpdate);
          this.urlList = idForUpdate.imgUrl.split(',');
          this.urlList.pop();
          this.rootLength = this.urlList.length;
        },
        error => {
        },
        () => {
          this.customerService.getAllDistrict(this.customerForm.value.address.district.city.id).subscribe(district => {
            this.districtList = district;
          })
        })
    })

    this.findAllCities()
  }

  findAllCities() {
    this.customerService.getAllCity().subscribe(city => {
      this.cityList = city
    })
  }

  update() {
    if (this.customerForm.valid) {
      let address = this.customerForm.value.address;
      let customerFormNew = this.customerForm.value;
      this.idCity = this.customerForm.value.address.district.city;
      this.customerService.getCityById(this.customerForm.value.city)
      console.log(this.customerForm.value.address.district.city)

      this.customerService.saveAddress(address).subscribe(addresses => {
        customerFormNew.address= addresses
      })
      this.handleFiles().then(() => {
        customerFormNew.imgUrl += this.fileList
        this.customerService.updateCustomer(customerFormNew).subscribe(
          value => {
            this.router.navigateByUrl("customer-list")
            this.toast.success('Chỉnh sửa thành công')
          },
          error => this.toast.error('Chỉnh sửa thất bại')
        );
      })
    } else {
      console.log(this.customerForm.errors)
      this.toast.error('Vui lòng điền đúng thông tin')
    }
  }

  changeCity(target: any) {
    this.customerService.getAllDistrict(target.value).subscribe(district => {
      this.districtList = district;
    })
  }

  show(event: any) {
    this.urlList.length = this.rootLength;
    this.links = event.target.files;
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
