import {PawnItem} from "./pawn-item";
import {Customer} from "./customer";
import {Employee} from "./employee";

export interface Contract {
  id?: number;

  code?: string;

  itemPrice?: number;

  interestRate?: number;

  startDate?: string;

  endDate?: string;

  returnDate?: string;

  liquidationPrice?: number;

  type?: boolean;

  profit?: number;

  pawnItem?: PawnItem;

  customer?: Customer;

  employee?: Employee;
}
