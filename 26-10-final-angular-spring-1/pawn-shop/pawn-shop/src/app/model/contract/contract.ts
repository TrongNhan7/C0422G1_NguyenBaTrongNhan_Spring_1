import {Customer} from "../customer/customer";
import {PawnItem} from "../pawn/pawn-item";
import {Employee} from "../employee/employee";

export interface Contract {
  id?:number;
  code?:string;
  itemPrice?:number;
  interestRate?:number;
  startDate?:string;
  endDate?:string;
  returnDate?:string;
  liquidationPrice?:number;
  customer?:Customer;
  type?:boolean;
  status?:number;
  pawnItem?:PawnItem;
  employee?:Employee;
}
