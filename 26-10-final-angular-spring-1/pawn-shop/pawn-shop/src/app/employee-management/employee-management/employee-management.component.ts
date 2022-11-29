import { Component, OnInit } from '@angular/core';
import {Employee} from "../../model/employee/employee";
import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeListService} from "../../service/employee-list.service";
import {ToastrService} from "ngx-toastr";
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-employee-management',
  templateUrl: './employee-management.component.html',
  styleUrls: ['./employee-management.component.css']
})
export class EmployeeManagementComponent implements OnInit {
  employee: Employee[] = [];
  formSearch: FormGroup;
  searchName = '';
  searchCode = '';
  number = 0;
  totalPages = 0;
  checkNext: boolean;
  checkPreview: boolean;
  checkSpecialCharacterName: boolean;
  size: number;
  sizePage: number;
  element: number;
  totalElements: number;
  page = 0;
  pageSelect: number[] = [];
  ids: number;
  codeModal: string;
  nameModal: string;
  idModal: number;

  constructor(private employeeService: EmployeeListService, private toast: ToastrService, private title: Title) {
    this.title.setTitle('Quản Lý Nhân Viên');
  }

  ngOnInit(): void {
    this.createForm();
    this.getAllEmployees(this.searchName, this.searchCode, this.page);
  }

  getAllEmployees(searchKeyWordName: string, searchKeyWordCode: string, pageable: number) {
    this.pageSelect.splice(0, this.totalPages);
    if (this.formSearch.value.searchName === 'null' || this.formSearch.value.searchCode === 'null'
      && this.formSearch.value.searchName === ' ' || this.formSearch.value.searchCode === ' '
      && this.formSearch.value.searchName === '#' || this.formSearch.value.searchCode === '#'
      && this.formSearch.value.searchName === '%' || this.formSearch.value.searchCode === '%'
      && this.formSearch.value.searchName === '^' || this.formSearch.value.searchCode === '^'
      && this.formSearch.value.searchName === '&' || this.formSearch.value.searchCode === '&'
    ) {

      this.employee = [];
      return;
    }
    this.employeeService.getAllEmployee(searchKeyWordName, searchKeyWordCode, pageable).subscribe((value: any) => {
      if (value != null) {
        this.employee = value.content;
        this.number = value.number;
        this.totalPages = value.totalPages;
        this.checkNext = !value.last;
        this.checkPreview = !value.first;
        this.sizePage = value.size;
        this.totalElements = value.totalElements;
        for (let i = 0; i < this.totalPages; i++) {
          this.pageSelect.push(i);
        }
      } else {
        this.employee = [];
      }
    });
  }

  createForm() {
    this.formSearch = new FormGroup({
      typeSearch: new FormControl()
    });
  }

  searchCodeName() {
    this.pageSelect.splice(0, this.totalPages);
    this.searchName = this.formSearch.value.typeSearch.trim();
    this.searchCode = this.formSearch.value.typeSearch.trim();
    if (this.formSearch.value.searchName === 'null' || this.formSearch.value.searchCode === 'null'
      && this.formSearch.value.searchName === ' ' || this.formSearch.value.searchCode === ' '
      && this.formSearch.value.searchName === '#' || this.formSearch.value.searchCode === '#'
      && this.formSearch.value.searchName === '%' || this.formSearch.value.searchCode === '%'
      && this.formSearch.value.searchName === '^' || this.formSearch.value.searchCode === '^'
      && this.formSearch.value.searchName === '&' || this.formSearch.value.searchCode === ''
    ) {
    }
    this.getAllEmployees(this.searchName, this.searchCode, this.page);
  }

  goPrevious() {
    this.pageSelect.splice(0, this.totalPages);
    if (this.page > 0) {
      this.page--;
    }
    this.getAllEmployees(this.searchName, this.searchCode, this.page);
  }

  goNext() {
    this.pageSelect.splice(0, this.totalPages);
    if (this.page < this.totalPages - 1) {
      this.page++;
    }
    this.getAllEmployees(this.searchName, this.searchCode, this.page);
  }

  getModal(id: number, code: string, name: string) {
    this.codeModal = code;
    this.nameModal = name;
    this.idModal = id;
  }

  deleteId() {
    if (this.employee.length === 1 && this.sizePage !== 0) {
      this.totalPages = this.totalPages - 1;
    }
    this.employeeService.deleteEmployee(this.idModal).subscribe(() => {
    }, error => {
    }, () => {
      this.toast.success('Xóa thành công');
      this.ngOnInit();
    });
  }

  changePage(pageNow: number) {
    this.pageSelect.splice(0, this.totalPages);
    this.page = pageNow;
    this.getAllEmployees(this.searchName, this.searchCode, this.page);
  }
}
