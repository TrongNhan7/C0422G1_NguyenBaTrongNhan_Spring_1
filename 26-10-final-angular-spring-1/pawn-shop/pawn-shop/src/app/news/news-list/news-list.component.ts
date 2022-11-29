import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {formatDate} from '@angular/common';
import {ToastrService} from 'ngx-toastr';
import {News} from "../../model/news/news";
import {NewsService} from "../../service/news.service";
import {TokenStorageService} from "../../service/token-storage.service";

declare var $: any;

@Component({
  selector: 'app-news-list',
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.css']
})
export class NewsListComponent implements OnInit {
  newsList: News[] = [];
  idModal: number;
  titleModal: string;
  searchForm: FormGroup;
  searchTitleForm: FormGroup;
  delete = [];
  totalPage: number = 0;
  pageSelect: number[] = [];
  firstDate: string = '0001-01-01';
  lastDate: string = '9000-01-01';
  titleSearch: string = '';
  number: number = 0;
  isEmployee: boolean;

  constructor(private newsService: NewsService, private toast: ToastrService,
              private tokenStorageService: TokenStorageService) {

  }

  ngOnInit(): void {
    this.number = 0;
    this.getAllNewsList(this.number, this.firstDate, this.lastDate, this.titleSearch);
    this.getFormSearch();
    this.isEmployee = this.tokenStorageService.getEmployeeCode() != undefined;
  }

  getFormSearch() {
    this.searchForm = new FormGroup({
      firstDate: new FormControl('', [this.checkDateNow]),
      lastDate: new FormControl('', [Validators.required])
    }, this.checkDateBefore);
    this.searchTitleForm = new FormGroup({
      titleSearch: new FormControl('', [Validators.required, Validators.maxLength(100), Validators.pattern(/^(\s+\S+\s*)*(?!\s).*$/)]),
    });
  }

  getAllNewsList(page: number, firstDate, lastDate, titleSearch) {
    this.newsService.getAllNews(page, firstDate, lastDate, titleSearch).subscribe(data => {
      // @ts-ignore
      this.newsList = data.content;
      if (this.newsList.length !== 0) {
        // @ts-ignore
        this.totalPage = data.totalPages;
        // @ts-ignore
        this.pageSelect = new Array(data.totalPage);
        // @ts-ignore
        this.number = data.number;
        for (let i = 0; i < this.totalPage; i++) {
          this.pageSelect.push(i);
        }
      }
    });
  }

  elementDelete(id: number, title: string) {
    this.idModal = id;
    this.titleModal = title;
  }

  dateSearch() {
    this.firstDate = this.searchForm.value.firstDate;
    this.lastDate = this.searchForm.value.lastDate;
    if (this.firstDate != "" && this.lastDate == "") {
      this.toast.warning("Vui lòng nhập đầy đủ khoảng thời gian")
    } else if (this.firstDate == "" && this.lastDate != "") {
      this.toast.warning("Vui lòng nhập đầy đủ khoảng thời gian")
    } else if (this.firstDate == "" && this.lastDate == "") {
      this.getAllNewsList(0, this.firstDate, this.lastDate, this.titleSearch);
    } else if (this.searchForm.valid) {
      this.getAllNewsList(this.number , this.firstDate, this.lastDate, this.titleSearch);
    } else {
      return null;
    }

  }

  searchTitle() {
    const searchTitle = this.searchTitleForm.value.titleSearch;
    this.newsService.searchTheLast(searchTitle).subscribe(data => {
      // @ts-ignore
      this.newsList = data.content;
    }, error => {
      this.toast.warning("Không có dữ liệu được tìm thấy")
    });
  }

  deleteTitle() {
    this.newsService.deleteNews(this.idModal).subscribe(() => {
      this.ngOnInit();
      this.toast.success("Xóa thành công", "Thông báo")
    }, error => {
      this.toast.warning("Xóa thất bại", "Thông báo")
    })
  }

  resetModal() {
    this.delete = [];
  }

  previousPage() {
    this.pageSelect.splice(0, this.totalPage);
    this.number = this.number - 1;
    this.getAllNewsList(this.number, this.firstDate, this.lastDate, this.titleSearch);
  }

  nextPage() {
    this.pageSelect.splice(0, this.totalPage);
    this.number = this.number + 1;
    this.getAllNewsList(this.number, this.firstDate, this.lastDate, this.titleSearch);

  }

  changePage(i: number) {
    this.pageSelect.splice(0, this.totalPage);
    this.getAllNewsList(i, this.firstDate, this.lastDate, this.titleSearch);
  }

  checkDateNow(form: AbstractControl) {
    let dateForm = new Date(form.value);
    let dateNow = new Date();
    if (dateNow.getDate() < dateForm.getDate()) {
      return {dateNow: true};
    } else {
      return null;
    }

  }

  checkDateBefore(form: AbstractControl) {
    let firstDate = new Date(form.value.firstDate);
    let lastDate = new Date(form.value.lastDate);
    if (firstDate.getTime() >= lastDate.getTime()) {
      return {beforeDate: true};
    } else {
      return null;
    }
  }

}
