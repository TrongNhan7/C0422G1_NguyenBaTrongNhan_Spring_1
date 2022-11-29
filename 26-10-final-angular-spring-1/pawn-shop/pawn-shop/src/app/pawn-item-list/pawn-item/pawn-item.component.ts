import { Component, OnInit } from '@angular/core';
import {PawnType} from "../../model/pawn/pawn-type";
import {FormControl, FormGroup} from "@angular/forms";
import {PawnItemService} from "../../service/pawn-item.service";
import {PawnTypeService} from "../../service/pawn-type.service";
import {ToastrService} from "ngx-toastr";
import {PawnItem} from "../../model/pawn/pawn-item";

@Component({
  selector: 'app-pawn-item',
  templateUrl: './pawn-item.component.html',
  styleUrls: ['./pawn-item.component.css']
})
export class PawnItemComponent implements OnInit {
  pawnTypes: PawnType[] = [];
  pawnItems: any;
  searchForm: FormGroup;
  pawnItemDetail: any;

  pawnItemPage: any;
  itemName: string = "";
  typePawn: string = "";
  page = 0;
  totalPage: number = 0;
  pageSelect: number[] = [];

  constructor(private pawnItemService: PawnItemService,
              private pawnTypeService: PawnTypeService,
              private toast: ToastrService) {
    this.searchForm = new FormGroup({
      pawnType: new FormControl(''),
      name: new FormControl('')
    })
  }

  ngOnInit(): void {
    // this.searchPawnItem()
    this.getAllPawnItem();
    this.getAllPawnType();
  }

  getAllPawnItem() {
    this.pawnItemService.getAllPawnItem(this.typePawn, this.itemName, this.page).subscribe(pawnItems => {
      this.pawnItems = pawnItems.content;
      this.totalPage = pawnItems.totalPages;
      for (let i = 0; i < this.totalPage; i++) {
        this.pageSelect.push(i);
      }
    });
  }

  getAllPawnType() {
    this.pawnTypeService.getAllPawnType().subscribe(pawnTypes => {
      this.pawnTypes = pawnTypes;
    });
  }

  updateStatusContract(idContract) {
    this.pageSelect.splice(0, this.totalPage);
    console.log(idContract);
    if (idContract != null) {
      this.pawnItemService.updateStatusContract(idContract).subscribe(n => {
        this.toast.success("Thành công!");
        this.getAllPawnItem();
      }, error => {
        console.error(error)
      })
    }

  }

  getDetailModal(pawnItems: PawnItem) {
    this.pawnItemDetail = pawnItems;
    console.log(this.pawnItemDetail);
  }

  searchPawnItem() {
    console.log(this.searchForm.value)
    this.page = 0;
    this.pawnItemService.getAllPawnItem(this.searchForm.value.pawnType, this.searchForm.value.name, this.page).subscribe(pawnItem => {
      if (pawnItem == null) {
        this.pawnItems = null
      } else {
        this.pawnItems = pawnItem.content;
        this.pageSelect = [];
        this.totalPage = pawnItem.totalPages;
        for (let i = 0; i < this.totalPage; i++) {
          this.pageSelect.push(i);
        }
      }

    });
  }

  previous() {
    this.page = this.page - 1;
    this.pawnItemService.getAllPawnItem(this.typePawn, this.itemName, this.page).subscribe(pawnItem => {
      if (pawnItem == null) {
        this.pawnItemPage = [];
      } else {
        this.pageSelect = [];
        this.pawnItemPage = pawnItem.content;
      }
    });
    this.getAllPawnItem();
  }

  next() {
    this.page = this.page + 1;
    this.pawnItemService.getAllPawnItem(this.typePawn, this.itemName, this.page).subscribe(pawnItem => {
      if (pawnItem == null) {
        this.pawnItemPage = [];
      } else {
        this.pageSelect = [];
        this.pawnItemPage = pawnItem.content;
      }
    });
    this.getAllPawnItem();
  }

  changePage(pageNow: number) {
    this.page = pageNow;
    this.pawnItemService.getAllPawnItem(this.typePawn, this.itemName, this.page).subscribe(pawnItem => {
      if (pawnItem == null) {
        this.pawnItemPage = [];
      } else {
        this.pageSelect = [];
        this.pawnItemPage = pawnItem.content;
      }
    });
    this.getAllPawnItem();
  }
}
