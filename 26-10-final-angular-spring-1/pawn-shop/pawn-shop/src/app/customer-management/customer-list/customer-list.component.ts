import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../../service/customer.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  customerPage: any;
  name: string = "";
  page: number;
  totalPage: number = 0;
  pageSelect: number[] = [];
  size: number = 4;
  idModal: number;//xóa
  nameModal: string;//xóa
  imgUrlModal: string// ảnh đẩy lên modal
  reset = [];//reset modal

  constructor(private customerService: CustomerService,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.searchNameCustomer();
  }

  //xóa

  elementDelete(id: number, name: string, imgUrl: string) {
    this.idModal = id;
    this.nameModal = name;
    this.imgUrlModal = imgUrl
    console.log(this.idModal)
  }

  deleteCustomer() {
    this.customerService.deleteCustomer(this.idModal).subscribe(() => {
      this.toastrService.success("Bạn đã xóa thành công");
      this.ngOnInit();
    }, error => {
      console.log(error)
    }, () => {

    })
  }

  searchNameCustomer() {
    this.page = 0;
    this.customerService.getAllCustomer(this.name, this.page).subscribe(customer => {
      if (customer == null) {
        this.customerPage = [];
      } else {
        this.pageSelect = [];
        this.customerPage = customer.content;
        this.totalPage = customer.totalPages;
        for (let i = 0; i < this.totalPage; i++) {
          this.pageSelect.push(i);
        }
      }
    });
  }

  previous() {
    this.page = this.page - 1;
    this.customerService.getAllCustomer(this.name, this.page).subscribe(customer => {
      if (customer == null) {
        this.customerPage = [];
      } else {
        this.pageSelect = [];
        this.customerPage = customer.content;
        this.totalPage = customer.totalPages;
        for (let i = 0; i < this.totalPage; i++) {
          this.pageSelect.push(i);
        }
      }
    });
  }

  next() {
    this.page = this.page + 1;
    this.customerService.getAllCustomer(this.name, this.page).subscribe(customer => {
      if (customer == null) {
        this.customerPage = [];
      } else {
        this.pageSelect = [];
        this.customerPage = customer.content;
        this.totalPage = customer.totalPages;
        for (let i = 0; i < this.totalPage; i++) {
          this.pageSelect.push(i);
        }
      }
    });
  }

  changePage(pageNow: number) {
    this.page = pageNow;
    this.customerService.getAllCustomer(this.name, this.page).subscribe(customer => {
      if (customer == null) {
        this.customerPage = [];
      } else {
        this.pageSelect = [];
        this.customerPage = customer.content;
        this.totalPage = customer.totalPages;
        for (let i = 0; i < this.totalPage; i++) {
          this.pageSelect.push(i);
        }
      }
    });
  }

  resetModal() {
    this.reset = [];
  }
}
