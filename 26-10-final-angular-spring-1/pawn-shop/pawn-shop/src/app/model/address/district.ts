import {City} from "./city";

export interface District {
  id?:number;
  name?:string;
  prefix?:string;
  city?: City;
}
