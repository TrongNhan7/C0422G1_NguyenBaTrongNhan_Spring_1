import {PawnType} from "./pawn-type";

export interface PawnItem {
  id?: number;
  name?: string;
  pawnType?: PawnType;
  status?: number;
}
