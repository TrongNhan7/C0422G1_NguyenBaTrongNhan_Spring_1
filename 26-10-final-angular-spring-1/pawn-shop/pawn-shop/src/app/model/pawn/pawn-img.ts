import {PawnItem} from "./pawn-item";

export interface PawnImg {
  id?:number;
  imgUrl?:string;
  pawnItem?:PawnItem;
  statusDelete?: number;
}
