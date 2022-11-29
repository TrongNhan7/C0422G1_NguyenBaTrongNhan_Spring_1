import { Component, OnInit } from '@angular/core';
import {Contract} from "../../model/contract/contract";
import {TopTenTransactionService} from "../../service/top-ten-transaction.service";

@Component({
  selector: 'app-top-ten-transaction',
  templateUrl: './top-ten-transaction.component.html',
  styleUrls: ['./top-ten-transaction.component.css']
})
export class TopTenTransactionComponent implements OnInit {
  contractList: Contract[];
  noContent = false;


  constructor(private topTen: TopTenTransactionService) {
  }
  ngOnInit(): void {
    this.topTen.getAll().subscribe(
      value => {
        this.contractList = value;
        if (value.length === 0) {
          this.noContent = true;
        }
      }
    );
  }
}
