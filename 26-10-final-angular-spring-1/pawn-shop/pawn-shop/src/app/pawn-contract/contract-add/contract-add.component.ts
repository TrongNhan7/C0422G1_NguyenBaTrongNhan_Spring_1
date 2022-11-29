import {Component, OnInit} from '@angular/core';
import {Customer} from "../../model/customer/customer";
import {PawnType} from "../../model/pawn/pawn-type";
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {Contract} from "../../model/contract/contract";
import {PawnImg} from "../../model/pawn/pawn-img";
import {finalize} from "rxjs/operators";
import {formatDate} from "@angular/common";
import {AngularFireStorage} from "@angular/fire/storage";
import {ContractService} from "../../service/contract.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {Employee} from "../../model/employee/employee";

function startDate() {
  let startDate = new Date();
  return startDate.getFullYear() + "-" + Number(startDate.getMonth() + 1) + "-" + startDate.getDate();
}

@Component({
  selector: 'app-contract-add',
  templateUrl: './contract-add.component.html',
  styleUrls: ['./contract-add.component.css']
})
export class ContractAddComponent implements OnInit {
  customerList: Customer[] = [];
  p: number = 1;
  pawnTypeList: PawnType[] = [];
  customer: Customer;
  contractForm: FormGroup;
  img: any = '';
  idCus: number = 0;
  checkImgSize = false;
  regexImg = false;
  isExits = false;
  money: any;
  urlListDisplayHtml: any[] = [];
  urlListToCreate: string[] = [];
  fileList = '';
  selectedFile: File = null;
  selectedFileList: File[] = [];
  maxLengthUrlInDb = 0;
  isLoading = false;
  employee: any

  constructor(private contractService: ContractService,
              private toast: ToastrService,
              private storage: AngularFireStorage,
              private tokenStorageService: TokenStorageService) {
    this.contractService.findAllPawnType().subscribe(data => {
      this.pawnTypeList = data;
    });
  }

  ngOnInit(): void {
    this.employee = {
      appUser: {
        username: this.tokenStorageService.getUsername()
      }
    }
    this.getAll();
    this.contractForm = new FormGroup({
        id: new FormControl(''),
        code: new FormControl('HD-' + this.tokenStorageService.getEmployeeCode() + '-'),
        itemPrice: new FormControl('', [Validators.required, this.validateItemPrice, Validators.pattern("^[-]*[0-9]*$")]),
        interestRate: new FormControl('', [Validators.required, Validators.min(0.2), Validators.max(0.4)]),
        startDate: new FormControl(startDate()),
        endDate: new FormControl('', [Validators.required, this.validateEndDate]),
        returnDate: new FormControl(null),
        liquidationPrice: new FormControl(null),
        type: new FormControl(false),
        status: new FormControl(0),
        customer: new FormControl(),
        employee: new FormControl(this.employee),
        pawnItem: new FormGroup({
          id: new FormControl(''),
          name: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(100)]),
          status: new FormControl(1),
          pawnType: new FormControl('', [Validators.required]),
          pawnImg: new FormControl('', [Validators.required])
        })
      }
    );
  }

  validateItemPrice(itemPrice: AbstractControl) {
    let temp = itemPrice.value;
    if (temp < 0) {
      return {'invalidItemPrice': true}
    }
    return null;
  }

  validateEndDate(endDate: AbstractControl) {
    let temp = endDate.value.split("-");
    let now = new Date();
    if (temp[0] <= now.getFullYear() && temp[1] <= now.getMonth() + 1 && temp[2] <= now.getDate()) {
      return {'invalidEndDate': true}
    }
    return null;
  }

  getAll() {
    this.contractService.findAllCustomer().subscribe(data => {
      this.customerList = data;
    });
  }

  getId(id: number, cus: Customer) {
    this.contractForm.get('customer').patchValue(cus);
    this.customer = cus;
  }

  search(idCard: string) {
    if (idCard === '') {
      this.getAll();
    } else {
      this.contractService.search(idCard).subscribe(customers => {
        this.customerList = customers;
      });
    }
  }

  create() {
    this.isLoading = true;
    let pawnItem = this.contractForm.value.pawnItem;
    let contractNew = this.contractForm.value;
    this.contractService.createPawnItem(pawnItem).subscribe(pawnItems => {
        contractNew.pawnItem.id = pawnItems.id;
      }, error => {
      },
      () => {
        this.contractService.createContract(contractNew).subscribe(() => {

          this.toast.success("Thêm mới thành công", "Thông báo")
          this.ngOnInit()
        }, error => {
          history.go(0),
            this.toast.error("Thêm mới thất bại", "Thông báo");
          this.isLoading = false;
        }, () => {
          this.isLoading = false;
        })
      })
  }

  getCurrentDateTime(): string {
    return formatDate(new Date(), 'yyyy-MM-dd hh:mm:ss', 'en-US');
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0];
    if (this.selectedFile.size > 9000000) {
      this.checkImgSize = true;
      this.regexImg = false;
      this.regexImg = false;
      return;
    }

    if (!this.selectedFile.name.match('^.*\\.(jpg|JPG|png|PNG)$')) {
      this.checkImgSize = false;
      this.regexImg = true;
      this.isExits = false;
      return;
    }
    this.selectedFileList.push(this.selectedFile);
    this.urlListDisplayHtml = [];
    for (let i = 0; i < this.selectedFileList.length; i++) {
      let reader = new FileReader();
      reader.readAsDataURL(this.selectedFileList[i]);

      // tslint:disable-next-line:variable-name
      reader.onload = (_event) => {
        this.urlListDisplayHtml.push(reader.result);
      };
    }
  }

  uploadFile(img: any) {
    return new Promise((resolve, reject) => {
      const nameImg = this.getCurrentDateTime() + img.name;
      const fileRef = this.storage.ref(nameImg);
      this.storage.upload(nameImg, img).snapshotChanges().pipe(
        finalize(() => {
          fileRef.getDownloadURL().subscribe((url) => {
            resolve(true)
            this.fileList += url + ",";
          });
        })
      ).subscribe()
    });
  }

  async handleFiles() {
    for (let i = 0; i < this.selectedFileList.length; i++) {
      await this.uploadFile(this.selectedFileList[i])
    }
  }

  save() {
    this.handleFiles().then(() => {
      this.urlListToCreate = this.fileList.split(',');
      const contract: Contract = this.contractForm.value;
      const pawnImgList: PawnImg[] = [];
      for (let i = 0; i < this.urlListToCreate.length - 1; i++) {
        let pawnImg: PawnImg = {
          'imgUrl': this.urlListToCreate[i]
        }
        pawnImgList.push(pawnImg);
      }
      contract.pawnItem.pawnImg = pawnImgList;
      console.log(contract);
      this.contractService.createPawnItem(contract.pawnItem).subscribe(
        pawnItems => {
          contract.pawnItem.id = pawnItems.id;
        }, error => {
        },
        () => {
          this.contractService.createContract(contract).subscribe(() => {
            history.go()
            this.toast.success("Thêm mới thành công", "Thông báo")
          }, error => {
            this.toast.error("Thêm mới thất bại", "Thông báo")
          })
        })
    })

  }

  // chooseFile() {
  //   if (!this.selectedFile || this.selectedFile.name === ''){
  //     this.isExits = true;
  //     this.checkImgSize = false;
  //     this.regexImg = false;
  //   }
  // }

  reset() {
    history.go()
  }
}
