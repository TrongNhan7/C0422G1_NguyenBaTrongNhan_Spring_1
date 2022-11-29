import {QuickCustomerDto} from './quick-customer-dto';
import {QuickPawnItemDto} from './quick-pawn-item-dto';

export interface QuickContractDto {
  id?: number;
  quickCustomerDto?: QuickCustomerDto;
  quickPawnItemDto?: QuickPawnItemDto;
}
