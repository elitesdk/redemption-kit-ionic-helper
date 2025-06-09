import { WebPlugin } from '@capacitor/core';

import type { RedemptionKitIonicHelperPlugin } from './definitions';

export class RedemptionKitIonicHelperWeb extends WebPlugin implements RedemptionKitIonicHelperPlugin {
  async initializeAndLaunch(options: { apiKey: string, customerID: string, sessionTimeout: number }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return {value: ""};
  }
}
