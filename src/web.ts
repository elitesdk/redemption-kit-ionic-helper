import { WebPlugin } from '@capacitor/core';

import type { RedemptionKitIonicHelperPlugin } from './definitions';

export class RedemptionKitIonicHelperWeb extends WebPlugin implements RedemptionKitIonicHelperPlugin {
  async initialize(options: { apiKey: string, customerID: string, sessionTimeout: number }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async launch(options: { redirection: Object }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async initializeAndLaunch(options: { apiKey: string, customerID: string, sessionTimeout: number }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async getPointBalance(options: { kind: string }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async getTransactionHistory(options: { transactionType: string, startDate: number, endDate: number }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async getRedemptionOptions(options: { }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async getBanners(options: { moduleName: string }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async getGiftVouchers(options: { }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async getOfferCategories(options: { }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async getOffers(options: { type: string }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async getBillPayCategories(options: { }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async searchProducts(options: { type: string, keyword: string }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
  async clearSession(options: { }): Promise<{ value: string }>{
    console.log('options: '+options);
    return {value: ""};
  }
}
