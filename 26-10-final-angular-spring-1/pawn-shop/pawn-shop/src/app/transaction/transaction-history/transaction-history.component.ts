import { Component, OnInit } from '@angular/core';
import {TransactionListService} from "../../service/transaction-list.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {
  contractsPage: any;
  customerName: string = "";
  pawnItemName: string = "";
  typeContract: string = "";
  startDate: string;
  endDate: string;
  statusContract: string = "";
  page: number = 0;
  totalPage: number = 0;
  pageSelect: number[] = [];
  contract: any = {};

  constructor(private transactionListService: TransactionListService,
              private router: Router,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.onSearchPageContract();
  }

  onSearchPageContract() {
    this.page = 0;
    this.pageSelect.splice(0, this.totalPage);
    return this.transactionListService.getPageContract(
      this.page, this.customerName, this.pawnItemName, this.typeContract, this.startDate, this.endDate, this.statusContract
    ).subscribe(contracts => {
      if (contracts == null) {
        this.contractsPage = [];
      } else {
        this.pageSelect = [];
        this.contractsPage = contracts.content;
        this.totalPage = contracts.totalPages;
        for (let i = 0; i < this.totalPage; i++){
          this.pageSelect.push(i);
        }
      }
    });
  }

  previousPage() {
    this.pageSelect.splice(0, this.totalPage);
    this.page = this.page - 1;
    return this.transactionListService.getPageContract(
      this.page, this.customerName, this.pawnItemName, this.typeContract, this.startDate, this.endDate, this.statusContract
    ).subscribe(contracts => {
      if (contracts == null) {
        this.contractsPage = [];
      } else {
        this.contractsPage = contracts.content;
        this.totalPage = contracts.totalPages;
        for (let i = 0; i < this.totalPage; i++){
          this.pageSelect.push(i);
        }
      }
    });
  }

  nextPage() {
    this.pageSelect.splice(0, this.totalPage);
    this.page = this.page + 1;
    return this.transactionListService.getPageContract(
      this.page, this.customerName, this.pawnItemName, this.typeContract, this.startDate, this.endDate, this.statusContract
    ).subscribe(contracts => {
      if (contracts == null) {
        this.contractsPage = [];
      } else {
        this.contractsPage = contracts.content;
        this.totalPage = contracts.totalPages;
        for (let i = 0; i < this.totalPage; i++){
          this.pageSelect.push(i);
        }
      }
    });
  }

  changePage(pageNow: number) {
    this.page = pageNow;
    this.pageSelect.splice(0, this.totalPage);
    return this.transactionListService.getPageContract(
      this.page, this.customerName, this.pawnItemName, this.typeContract, this.startDate, this.endDate, this.statusContract
    ).subscribe(contracts => {
      if (contracts == null) {
        this.contractsPage = [];
      } else {
        this.contractsPage = contracts.content;
        this.totalPage = contracts.totalPages;
        for (let i = 0; i < this.totalPage; i++){
          this.pageSelect.push(i);
        }
      }
    });
  }

  getContractById(id: number){
    return this.transactionListService.getContractDetail(id).subscribe(ct => {
      this.contract = ct;
    }, error => {
      console.log(error);
    })
  }

  deleteContract(id: number) {
    return this.transactionListService.deleteContract(id).subscribe(() => {
      this.onSearchPageContract();
      this.toastr.success("Xóa thành công");
    }, error => {
      this.toastr.error("Xóa thất bại");
    })
  }
}
