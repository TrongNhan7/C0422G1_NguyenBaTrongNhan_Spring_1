import { Component, OnInit } from '@angular/core';
import {Contract} from "../../model/finance/contract";
import {ExpectedContractService} from "../../service/expected-contract.service";
import {Chart, registerables} from "chart.js";
import {ExportExcelService} from "../../service/export-excel.service";

Chart.register(...registerables);

function get_day_of_time(endDate: string, startDate: string) {
  let endDateHd = new Date(endDate);
  let startDateHd = new Date(startDate);
  let ms1 = endDateHd.getTime();
  let ms2 = startDateHd.getTime();
  return Math.ceil((ms1 - ms2) / (24 * 60 * 60 * 1000));
}


@Component({
  selector: 'app-expected-contract',
  templateUrl: './expected-contract.component.html',
  styleUrls: ['./expected-contract.component.css']
})
export class ExpectedContractComponent implements OnInit {

  expectedContractList: Contract[] = [];
  expectedContractOnlyList: Contract[] = [];
  startReturnDate = "";
  endReturnDate = "";
  page = 0;
  totalPage = 0;
  pageSelect: number[] = [];
  labelList = [];
  profitData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
  contract?: Contract;
  totalProfit = 0;
  myChart: Chart;
  validateEndDate: number;
  validateStartDate: number;
  header = ["Id", "Type", "Status", "Return date", "Liquid price", "Rate", "Item price", "Start date", "End date"
    , "Customer", "Code", "Employee", "Pawn item", "Profit"];

  constructor(
    private expectedContractService: ExpectedContractService,
    private exportExcelService: ExportExcelService
  ) {
  }

  ngOnInit(): void {
    this.getAllExpectedContract();
  }

  private getAllExpectedContract() {
    this.expectedContractService.getListExpectedContractByDate(this.startReturnDate, this.endReturnDate).subscribe(next => {
      if (next === null) {
        return this.expectedContractOnlyList = [];
      }
      this.expectedContractOnlyList = next;
      console.log(this.expectedContractOnlyList)
      for (let i of this.expectedContractOnlyList) {
        let day = get_day_of_time(i.endDate, i.startDate);
        i.profit = day * (i.itemPrice * (i.interestRate) / 100);
        this.totalProfit += i.profit;
        let a = i.startDate.slice(5, 7);
        console.log(a);
        if (this.labelList.length === 0) {
          this.labelList.push(a);
        } else {
          for (let k of this.labelList) {
            if (this.labelList.indexOf(a) === -1) {
              this.labelList.push(a);
            }
          }
        }
        console.log(this.labelList);
      }
      this.labelList.sort();
      console.log(this.labelList);
      for (let n = 0; n < this.expectedContractOnlyList.length; n++) {
        for (let m = 0; m < this.labelList.length; m++) {
          if (this.expectedContractOnlyList[n].startDate.slice(5, 7) == this.labelList[m]) {
            this.profitData[m] += this.expectedContractOnlyList[n].profit;
            console.log(this.profitData[m]);
          }
        }
      }
      console.log("data" + this.profitData);
    });
    this.expectedContractService.findExpectedContractByDate(this.startReturnDate, this.endReturnDate, this.page).subscribe(contracts => {
      if (contracts === null) {
        return this.expectedContractList = [];
      }
      this.expectedContractList = contracts.content;
      this.totalPage = contracts.totalPages;
      this.pageSelect.splice(0, this.totalPage);
      for (let i = 0; i < this.totalPage; i++){
        this.pageSelect.push(i);
      }
      for (let j of this.expectedContractList) {
        let day = get_day_of_time(j.endDate, j.startDate);
        j.profit = day * (j.itemPrice * (j.interestRate) / 100);
      }
    });
  }

  search() {
    this.page = 0;
    this.totalProfit = 0;
    this.labelList = [];
    this.profitData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    this.expectedContractService.getListExpectedContractByDate(this.startReturnDate, this.endReturnDate).subscribe(next => {
      if (next === null) {
        return this.expectedContractOnlyList = [];
      }
      this.expectedContractOnlyList = next;
      console.log(this.expectedContractOnlyList)
      for (let i of this.expectedContractOnlyList) {
        let day = get_day_of_time(i.endDate, i.startDate);
        i.profit = day * (i.itemPrice * (i.interestRate) / 100);
        this.totalProfit += i.profit;
        let a = i.startDate.slice(5, 7);
        if (this.labelList.length === 0) {
          this.labelList.push(a);
        } else {
          for (let k of this.labelList) {
            if (this.labelList.indexOf(a) === -1) {
              this.labelList.push(a);
            }
          }
        }
        console.log(this.labelList);
      }
      this.labelList.sort();
      for (let n = 0; n < this.expectedContractOnlyList.length; n++) {
        for (let m = 0; m < this.labelList.length; m++) {
          if (this.expectedContractOnlyList[n].startDate.slice(5, 7) == this.labelList[m]) {
            this.profitData[m] += this.expectedContractOnlyList[n].profit;
            console.log(this.profitData[m]);
          }
        }
      }
      console.log("data" + this.profitData);
    });

    this.expectedContractService.findExpectedContractByDate(this.startReturnDate, this.endReturnDate, this.page).subscribe(contracts => {
      if (contracts === null) {
        this.expectedContractList = [];
        this.pageSelect = [];
      }
      this.expectedContractList = contracts.content;
      this.totalPage = contracts.totalPages;
      this.pageSelect = [];
      for (let i = 0; i < this.totalPage; i++) {
        this.pageSelect.push(i);
      }
      for (let j of this.expectedContractList) {
        let day = get_day_of_time(j.endDate, j.startDate);
        j.profit = day * (j.itemPrice * (j.interestRate) / 100);
      }
    });
  }

  click() {
    if (this.myChart != null) this.myChart.destroy();
    let labelString = [];
    for (let i of this.labelList) {
      if (i == "01") {
        labelString.push("Tháng 01");
        continue;
      }
      if (i == "02") {
        labelString.push("Tháng 02");
        continue;
      }
      if (i == "03") {
        labelString.push("Tháng 03");
        continue;
      }
      if (i == "04") {
        labelString.push("Tháng 04");
        continue;
      }
      if (i == "05") {
        labelString.push("Tháng 05");
        continue;
      }
      if (i == "06") {
        labelString.push("Tháng 06");
        continue;
      }
      if (i == "07") {
        labelString.push("Tháng 07");
        continue;
      }
      if (i == "08") {
        labelString.push("Tháng 08");
        continue;
      }
      if (i == "09") {
        labelString.push("Tháng 09");
        continue;
      }
      if (i == "10") {
        labelString.push("Tháng 10");
        continue;
      }
      if (i == "11") {
        labelString.push("Tháng 11");
        continue;
      }
      if (i == "12") {
        labelString.push("Tháng 12");
      }
    }
    this.myChart = new Chart("myChart", {
      type: 'bar',
      data: {
        labels: labelString,
        datasets: [{
          label: 'Lợi nhuận theo tháng',
          data: this.profitData,
          borderColor: [
            'rgba(255, 26, 104, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)',
            'rgba(0, 0, 0, 1)'
          ],
        }]
      },
      options: {
        animations: {
          backgroundColor: {
            type:'color',
            duration: 5000,
            easing: 'linear',
            from: 'orange',
            to: 'pink',
            loop: true
          }
        },
        scales: {
          y: {
            title: {
              display: true,
              text: '$ Tiền lãi $'
            }
          },
          x: {
            title: {
              display: true,
              text: '# Tháng #'
            }
          }
        },

      }
    });
  }

  detailContract(id: number) {
    this.expectedContractService.findContractById(id).subscribe(contract => {
      this.contract = contract;
      console.log(contract)
    })
  }

  validateStart() {
    this.validateStartDate = 0;
    if (get_day_of_time(this.endReturnDate, this.startReturnDate) < 0) {
      this.validateStartDate = 1;
    }
  }

  validateEnd() {
    this.validateEndDate = 0;
    if (get_day_of_time(this.endReturnDate, this.startReturnDate) < 0) {
      this.validateEndDate = 1;
    }
    console.log(get_day_of_time(this.endReturnDate, this.startReturnDate));
  }

  exportToExcel() {
    let reportData = {
      title: 'Báo cáo sales',
      data: this.expectedContractOnlyList,

    };
    this.exportExcelService.exportExcel(reportData);
  }


  previousPage() {
    this.pageSelect.splice(0, this.totalPage);
    this.page = this.page - 1;
    this.expectedContractService.findExpectedContractByDate(this.startReturnDate, this.endReturnDate, this.page).subscribe(contracts => {
      if (contracts == null) {
        this.expectedContractList = [];
      } else {
        this.expectedContractList = contracts.content;
        this.totalPage = contracts.totalPages;
        for (let i = 0; i < this.totalPage; i++){
          this.pageSelect.push(i);
        }
      }
      for (let i of this.expectedContractList) {
        let day = get_day_of_time(i.endDate, i.startDate);
        i.profit = day * (i.itemPrice * (i.interestRate) / 100);
      }
    });
  }

  changePage(pageNow: number) {
    this.page = pageNow;
    this.pageSelect.splice(0, this.totalPage);
    this.expectedContractService.findExpectedContractByDate(this.startReturnDate, this.endReturnDate, this.page).subscribe(contracts => {
      if (contracts == null) {
        this.expectedContractList = [];
      } else {
        this.expectedContractList = contracts.content;
        this.totalPage = contracts.totalPages;
        for (let i = 0; i < this.totalPage; i++){
          this.pageSelect.push(i);
        }
      }
      for (let i of this.expectedContractList) {
        let day = get_day_of_time(i.endDate, i.startDate);
        i.profit = day * (i.itemPrice * (i.interestRate) / 100);
      }
    });
  }

  nextPage() {
    this.pageSelect.splice(0, this.totalPage);
    this.page = this.page + 1;
    this.expectedContractService.findExpectedContractByDate(this.startReturnDate, this.endReturnDate, this.page).subscribe(contracts => {
      if (contracts == null) {
        this.expectedContractList = [];
      } else {
        this.expectedContractList = contracts.content;
        this.totalPage = contracts.totalPages;
        for (let i = 0; i < this.totalPage; i++){
          this.pageSelect.push(i);
        }
      }
      for (let i of this.expectedContractList) {
        let day = get_day_of_time(i.endDate, i.startDate);
        i.profit = day * (i.itemPrice * (i.interestRate) / 100);
      }
    });
  }
}
