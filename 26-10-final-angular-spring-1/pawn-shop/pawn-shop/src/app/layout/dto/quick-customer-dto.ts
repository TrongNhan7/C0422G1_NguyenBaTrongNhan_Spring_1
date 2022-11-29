import {QuickAddressDto} from './quick-address-dto';

export interface QuickCustomerDto {
  id?: number;
  name?: string;
  phoneNumber?: string;
  quickAddressDto?: QuickAddressDto;
}
