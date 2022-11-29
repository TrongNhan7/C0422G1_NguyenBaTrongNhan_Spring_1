import {PawnType} from "./pawn-type";
import {PawnImg} from "./pawn-img";

export interface PawnItem {
  id?:number;
  name?:string;
  status?:boolean;
  pawnType?:PawnType;
  pawnImg?: PawnImg[];
}
