import {Component, OnInit} from '@angular/core';
import {Contract} from "../../model/finance/contract";
import {CompleteContractService} from "../../service/complete-contract.service";
import {Chart, registerables} from "chart.js";
import {ExportExcelService} from "../../service/export-excel.service";
import {log} from "util";

Chart.register(...registerables);

function get_day_of_time(returnDate: string, startDate: string) {
  let returnDateHd = new Date(returnDate);
  let startDateHd = new Date(startDate);
  let ms1 = returnDateHd.getTime();
  let ms2 = startDateHd.getTime();
  return Math.ceil((ms1 - ms2) / (24 * 60 * 60 * 1000));
}

@Component({
  selector: 'app-complete-contract',
  templateUrl: './complete-contract.component.html',
  styleUrls: ['./complete-contract.component.css']
})
export class CompleteContractComponent implements OnInit {
  completeContractList: Contract[] = [];
  completeContractOnlyList: Contract[] = [];
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

  constructor(private completeContractService: CompleteContractService,
              private exportExcelService: ExportExcelService) {
  }

  ngOnInit(): void {
    this.getAllCompleteContract();
  }

  private getAllCompleteContract() {
    this.completeContractService.getListCompleteContractByDate(this.startReturnDate, this.endReturnDate).subscribe(next => {
      if (next === null) {
        return this.completeContractOnlyList = [];
      }
      this.completeContractOnlyList = next;
      console.log(this.completeContractOnlyList)
      for (let i of this.completeContractOnlyList) {
        let day = get_day_of_time(i.returnDate, i.startDate);
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
      for (let n = 0; n < this.completeContractOnlyList.length; n++) {
        for (let m = 0; m < this.labelList.length; m++) {
          if (this.completeContractOnlyList[n].startDate.slice(5, 7) == this.labelList[m]) {
            this.profitData[m] += this.completeContractOnlyList[n].profit;
            console.log(this.profitData[m]);
          }
        }
      }
      console.log("data" + this.profitData);
    });

    this.completeContractService.findCompleteContractByDate(this.startReturnDate, this.endReturnDate, this.page).subscribe(contracts => {
      this.completeContractList = contracts.content;
      this.totalPage = contracts.totalPages;
      console.log("------")
      console.log(this.completeContractList)
      this.pageSelect.splice(0, this.totalPage);
      for (let i = 0; i < this.totalPage; i++) {
        this.pageSelect.push(i);
      }
      for (let j of this.completeContractList) {
        let day = get_day_of_time(j.returnDate, j.startDate);
        j.profit = day * (j.itemPrice * (j.interestRate) / 100);
      }
    });
  }

  search() {
    this.page = 0;
    this.totalProfit = 0;
    this.labelList = [];
    this.profitData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

    this.completeContractService.getListCompleteContractByDate(this.startReturnDate, this.endReturnDate).subscribe(next => {
      if (next === null) {
        return this.completeContractOnlyList = [];
      }
      this.completeContractOnlyList = next;
      console.log(this.completeContractOnlyList)
      for (let i of this.completeContractOnlyList) {
        let day = get_day_of_time(i.returnDate, i.startDate);
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
      for (let n = 0; n < this.completeContractOnlyList.length; n++) {
        for (let m = 0; m < this.labelList.length; m++) {
          if (this.completeContractOnlyList[n].startDate.slice(5, 7) == this.labelList[m]) {
            this.profitData[m] += this.completeContractOnlyList[n].profit;
            console.log(this.profitData[m]);
          }
        }
      }
      console.log("data" + this.profitData);
    });

    this.completeContractService.findCompleteContractByDate(this.startReturnDate, this.endReturnDate, this.page).subscribe(contracts => {
      if (contracts === null) {
        this.completeContractList = [];
        this.pageSelect = [];
      }
      this.completeContractList = contracts.content;
      this.totalPage = contracts.totalPages;
      this.pageSelect = [];
      for (let i = 0; i < this.totalPage; i++) {
        this.pageSelect.push(i);
      }
      for (let j of this.completeContractList) {
        let day = get_day_of_time(j.returnDate, j.startDate);
        j.profit = day * (j.itemPrice * (j.interestRate) / 100);
      }
      console.log(this.pageSelect);
    }, error => {
      alert(error.error);
      this.startReturnDate = "";
      this.endReturnDate = "";
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
            type: 'color',
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
        }

      }
    });
  }


  detailContract(id: number) {
    this.completeContractService.findContractById(id).subscribe(contract => {
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
      data: this.completeContractOnlyList,
    };
    this.exportExcelService.exportExcel(reportData);
  }

  previousPage() {
    this.pageSelect.splice(0, this.totalPage);
    this.page = this.page - 1;
    this.completeContractService.findCompleteContractByDate(this.startReturnDate, this.endReturnDate, this.page).subscribe(contracts => {
      if (contracts == null) {
        this.completeContractList = [];
      } else {
        this.completeContractList = contracts.content;
        this.totalPage = contracts.totalPages;
        for (let i = 0; i < this.totalPage; i++) {
          this.pageSelect.push(i);
        }
      }
      for (let i of this.completeContractList) {
        let day = get_day_of_time(i.endDate, i.startDate);
        i.profit = day * (i.itemPrice * (i.interestRate) / 100);
      }
    });
  }

  changePage(pageNow: number) {
    this.page = pageNow;
    this.pageSelect.splice(0, this.totalPage);
    this.completeContractService.findCompleteContractByDate(this.startReturnDate, this.endReturnDate, this.page).subscribe(contracts => {
      if (contracts == null) {
        this.completeContractList = [];
      } else {
        this.completeContractList = contracts.content;
        this.totalPage = contracts.totalPages;
        for (let i = 0; i < this.totalPage; i++) {
          this.pageSelect.push(i);
        }
      }
      for (let i of this.completeContractList) {
        let day = get_day_of_time(i.endDate, i.startDate);
        i.profit = day * (i.itemPrice * (i.interestRate) / 100);
      }
    });
  }

  nextPage() {
    this.pageSelect.splice(0, this.totalPage);
    this.page = this.page + 1;
    this.completeContractService.findCompleteContractByDate(this.startReturnDate, this.endReturnDate, this.page).subscribe(contracts => {
      if (contracts == null) {
        this.completeContractList = [];
      } else {
        this.completeContractList = contracts.content;
        this.totalPage = contracts.totalPages;
        for (let i = 0; i < this.totalPage; i++) {
          this.pageSelect.push(i);
        }
      }
      for (let i of this.completeContractList) {
        let day = get_day_of_time(i.endDate, i.startDate);
        i.profit = day * (i.itemPrice * (i.interestRate) / 100);
      }
    });
  }
}