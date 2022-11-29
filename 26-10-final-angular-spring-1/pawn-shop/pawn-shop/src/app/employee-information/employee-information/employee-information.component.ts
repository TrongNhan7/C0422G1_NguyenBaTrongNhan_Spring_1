import { Component, OnInit } from '@angular/core';
import {Employee} from "../../model/employee/employee";
import {EmployeeService} from "../../service/employee.service";
import {TokenStorageService} from "../../service/token-storage.service";

@Component({
  selector: 'app-employee-information',
  templateUrl: './employee-information.component.html',
  styleUrls: ['./employee-information.component.css']
})
export class EmployeeInformationComponent implements OnInit {
  employeeDetail: any;
  imgLoad: any = '';
  username: string;

  constructor(private employeeService: EmployeeService, private tokenStorageService: TokenStorageService) {
    this.username = this.tokenStorageService.getUsername()
  }

  getInfoEmployee(user: string) {
    this.employeeService.findByUser(user).subscribe(next => {
      console.log(this.employeeDetail);
      this.employeeDetail = next;
      this.imgLoad = this.employeeDetail.imgUrl;
      console.log(this.imgLoad);
    });
  }

  ngOnInit(): void {
    this.getInfoEmployee(this.username)
  }
}
