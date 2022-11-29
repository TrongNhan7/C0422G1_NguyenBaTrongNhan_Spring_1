import {Address} from "../address/address";

export interface Customer {
  id?:number;
  name?:string;
  code?:string;
  phoneNumber?:string;
  dateOfBirth?:string;
  email?:string;
  gender?:boolean;
  idCard?:string;
  imgUrl?:string;
  status?:boolean;
  address?:Address;
}
