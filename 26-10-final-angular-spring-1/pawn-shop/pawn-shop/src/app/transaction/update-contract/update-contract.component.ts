import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {PawnType} from "../../model/pawn/pawn-type";
import {Customer} from "../../model/customer/customer";
import {UpdateContractService} from "../../service/update-contract.service";
import {AngularFireStorage} from "@angular/fire/storage";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {Contract} from "../../model/contract/contract";
import {formatDate} from "@angular/common";
import {PawnImg} from "../../model/pawn/pawn-img";
import {finalize} from "rxjs/operators";
import {TokenStorageService} from "../../service/token-storage.service";

@Component({
  selector: 'app-update-contract',
  templateUrl: './update-contract.component.html',
  styleUrls: ['./update-contract.component.css']
})
export class UpdateContractComponent implements OnInit {
  contractForm: FormGroup;
  pawnTypeList: PawnType[];
  customerList: Customer[];

  checkImgSize = false;
  regexImg = false;
  isExits = false;

  urlListDisplayHtml: PawnImg[] = [];
  urlListToCreate: string[] = [];
  fileList = '';
  selectedFile: File = null;
  selectedFileList: File[] = [];
  buttonBook = true;
  maxLengthUrlInDb = 0;
  deleteCount = 0;
  isLoading = false;
  itemPrice: number = 0;
  money: any;

  constructor(private updateService: UpdateContractService,
              private activatedRoute: ActivatedRoute,
              private storage: AngularFireStorage,
              private router: Router,
              private toast: ToastrService,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.contractForm = new FormGroup({
      id: new FormControl(''),
      code: new FormControl(''),
      customer: new FormControl(''),
      pawnItem: new FormGroup({
        id: new FormControl(''),
        name: new FormControl('', [Validators.required]),
        status: new FormControl(''),
        pawnType: new FormControl(''),
        pawnImg: new FormControl('')
      }),
      itemPrice: new FormControl('', [Validators.required, Validators.min(0)]),
      interestRate: new FormControl('', [Validators.required, Validators.min(0.2), Validators.max(0.4)]),
      startDate: new FormControl(''),
      endDate: new FormControl('', [Validators.required]),
      returnDate: new FormControl(''),
      liquidationPrice: new FormControl(''),
      employee: new FormControl(''),
      type: new FormControl(''),
      status: new FormControl('')
    }, this.checkDateEnd);

    const id = this.activatedRoute.snapshot.params.id;

    this.updateService.findById(Number(id)).subscribe(
      value => {
        if (value.code.split("-").length == 1){
          value.code = 'HD-'+ this.tokenStorageService.getEmployeeCode()+ "-" + value.code;
        }
        let employee = {
          appUser: {
            username: this.tokenStorageService.getUsername()
          }
        }
        this.contractForm.patchValue(value);
        this.contractForm.patchValue({employee: employee})
        this.urlListDisplayHtml = value.pawnItem.pawnImg.slice();
        this.maxLengthUrlInDb = value.pawnItem.pawnImg.length;
      }
    );
    this.updateService.getCustomer().subscribe(
      value => {
        this.customerList = value;
      }
    );
    this.updateService.getPawnType().subscribe(
      value => {
        this.pawnTypeList = value;
      }
    );
    this.updateService.getPawmImg().subscribe(value => {
      this.contractForm.patchValue({pawnImg: value});
    });
  }

  compareWithId(item1, item2) {
    return item1 && item2 && item1.id === item2.id;
  }

  checkDateEnd(abstractControl: AbstractControl): any {
    const start = new Date(abstractControl.value.startDate);
    const now = new Date(abstractControl.value.endDate);
    console.log(now);
    if (now > start) {
      return null;
    } else if (now < start) {
      return {checkDate: true};
    } else {
      return null;
    }
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

    if (!this.selectedFile.name.match('^.*\\.(jpg|JPG|png|PNG|jpeg)$')) {
      this.checkImgSize = false;
      this.regexImg = true;
      this.isExits = false;
      return;
    }
    this.selectedFileList.push(this.selectedFile);
    // doc file va in ra man hinh
    const reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);

    // tslint:disable-next-line:variable-name
    reader.onload = (_event) => {
      const pawnImg: PawnImg = {
        imgUrl: String(reader.result),
        statusDelete: 0
      };
      this.urlListDisplayHtml.push(pawnImg);
    };
  }

  delete(index: number) {
    if (index < this.maxLengthUrlInDb) {
      this.urlListDisplayHtml[index].statusDelete = 1;
      this.contractForm.value.pawnItem.pawnImg[index].statusDelete = 1;
    } else {
      this.urlListDisplayHtml[index].statusDelete = 1;
      this.selectedFileList.splice(index - this.maxLengthUrlInDb, 1);
    }
  }

  uploadFile(img: any) {
    return new Promise((resolve, reject) => {
      const nameImg = this.getCurrentDateTime() + img.name;
      const fileRef = this.storage.ref(`pawn-shop/` + nameImg);
      this.storage.upload(`pawn-shop/` + nameImg, img).snapshotChanges().pipe(
        finalize(() => {
          fileRef.getDownloadURL().subscribe((url) => {
            resolve(true);
            this.fileList += url + ',';
          });
        })
      ).subscribe();
    });
  }

  async handleFiles() {
    for (const item of this.selectedFileList) {
      await this.uploadFile(item);
    }
  }

  save() {
    if (this.contractForm.invalid) {
      this.contractForm.markAllAsTouched();
    } else {
      // this.isLoading = true;
      this.handleFiles().then(() => {
        this.urlListToCreate = this.fileList.split(',');
        const contract: Contract = this.contractForm.value;
        for (let i = 0; i < this.urlListToCreate.length - 1; i++) {
          const pawnImg: PawnImg = {
            imgUrl: this.urlListToCreate[i]
          };
          contract.pawnItem.pawnImg.push(pawnImg);
        }
        this.updateService.updateContract(contract).subscribe(
          value => {
            history.back();
            this.toast.success('Chỉnh sửa thành công');
          }, error => {
            this.toast.error('Chỉnh sửa thất bại');
            // this.isLoading = false;
          });
      });
    }
  }
}
