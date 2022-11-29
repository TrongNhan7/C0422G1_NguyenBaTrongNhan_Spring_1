import { Component, OnInit } from '@angular/core';
import {Finance} from "../../model/finance/finance";
import {FinanceService} from "../../service/finance.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-finance',
  templateUrl: './finance.component.html',
  styleUrls: ['./finance.component.css']
})
export class FinanceComponent implements OnInit {

  finances: Finance[] = [];
  investments: number;
  profits: number;

  constructor(private financeService: FinanceService,
              private route: Router) { }

  ngOnInit(): void {
    this.getAllFinance();
    this.getInvestment();
    this.getExpectedProfit();
  }

  getAllFinance() {
    this.financeService.getAllFinance().subscribe(finances => {
      this.finances = finances;
    });
  }

  getInvestment() {
    this.financeService.getInvestment().subscribe(investments => {
      this.investments = investments;
    });
  }

  getExpectedProfit() {
    this.financeService.getExpectedProfit().subscribe(profits => {
      this.profits = profits;
    });
  }
}
