import { Component, OnInit } from '@angular/core';
import {PawnItemDto} from "./dto/pawn-item-dto";
import {PawnTypeDto} from "./dto/pawn-type-dto";
import {LiquidationDto} from "./dto/liquidation-dto";
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {LiquidationServiceService} from "../../service/liquidation-service.service";

@Component({
  selector: 'app-liquidation',
  templateUrl: './liquidation.component.html',
  styleUrls: ['./liquidation.component.css']
})
export class LiquidationComponent implements OnInit {
  date = new Date().toDateString();

  pawnItemLiquidation: PawnItemDto;

  pawnTypeList: PawnTypeDto[] = [];

  priceValidate: number = 10;

  imgUrl: string[] = [];

  p: number = 1;

  searchPawnItem = {
    namePawnItem: '',
    idPawnType: '',
    price: '',
  }

  liquidation: LiquidationDto;
  formLiquidation: FormGroup;

  price = 0;
  idPawnItem = 0;

  search: FormGroup;
  page: number = 0;
  totalPage: number = 0;
  pageSelect: number[] = [];
  pawnItemPage: any;

  constructor(private liquidationServiceService: LiquidationServiceService,
              private toast: ToastrService,
              private router: Router) {
    this.search = new FormGroup({
      namePawnItem: new FormControl(''),
      idPawnType: new FormControl(''),
      price: new FormControl('')
    });

    this.findAllPawnItem(this.searchPawnItem);
    this.findAllPawnType();
    this.formLiquidation = new FormGroup({
      liquidationPrice: new FormControl(this.price, [Validators.min(this.priceValidate)])
    });
  }


  buildForm() {
    this.formLiquidation = new FormGroup({
      liquidationPrice: new FormControl(this.price, [Validators.min(this.priceValidate)])
    });
  }

  ngOnInit(): void {
  }

  findAllPawnItem(object: any) {
    this.liquidationServiceService.findAllPawnItem(object,this.page).subscribe(pawnItems => {
      this.pawnItemPage = pawnItems.content;
      this.totalPage = pawnItems.totalPages;
      for (let i = 0; i < this.totalPage; i++){
        this.pageSelect.push(i);
      }
    }, error => {
      console.log(error);
    });
  }

  findAllPawnType() {
    this.liquidationServiceService.findAllPawnType().subscribe(n => {
      this.pawnTypeList = n;
    }, error => {
      console.log(error);
    })
  }

  changeNamePawnItem() {
    this.searchPawnItem = this.search.value;
    this.findAllPawnItem(this.searchPawnItem);
  }

  findPawnItemById(pawnIten1: PawnItemDto) {
    this.pawnItemLiquidation = pawnIten1;
    this.price = pawnIten1.itemPrice * 2 * 70 / 100;
    this.priceValidate = pawnIten1.itemPrice * 2 * 70 / 100;
    this.idPawnItem = pawnIten1.idPawnItem;
    this.liquidationServiceService.findImgUrl(pawnIten1.idPawnItem).subscribe(urls => {
      this.imgUrl = urls;
    })
    this.buildForm();
  }

  get liquidationPrice() {
    return this.formLiquidation.get('liquidationPrice');
  }

  updateLiquidation() {
    this.liquidation = this.formLiquidation.value;
    this.liquidation.idPawnItem = this.idPawnItem;
    console.log(this.formLiquidation.valid)
    if (this.liquidation.idPawnItem != null) {
      if (this.formLiquidation.valid) {
        console.log(this.liquidation)
        this.liquidationServiceService.updateLiquidation(this.liquidation).subscribe(n => {
          this.imgUrl = []
          this.toast.success("Thanh lý thành công!");
          this.formLiquidation.reset();
          this.pawnItemLiquidation = null;
          // this.router.navigateByUrl("liquidation");
          this.findAllPawnItem(this.searchPawnItem);
        }, error => {
          console.log(error);
        });
      }
    } else {
      this.toast.warning("Chưa chọn đồ thanh lý!");
    }
  }

  previousPage() {
    this.pageSelect.splice(0, this.totalPage);
    this.page = this.page - 1;
    this.findAllPawnItem(this.search.value)
  }

  nextPage() {
    this.pageSelect.splice(0, this.totalPage);
    this.page = this.page + 1;
    this.findAllPawnItem(this.search.value)
  }

  changePage(pageNow: number) {
    this.page = pageNow;
    this.pageSelect.splice(0, this.totalPage);
    this.findAllPawnItem(this.search.value)
  }

  resetForm() {
    this.imgUrl = [];
    this.pawnItemLiquidation = null;
  }
}
