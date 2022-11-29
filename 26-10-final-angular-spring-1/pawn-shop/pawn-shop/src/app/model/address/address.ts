import {District} from "./district";
import {City} from "./city";

export interface Address {
  id?: number;
  street?: string;
  district?:District;
}
