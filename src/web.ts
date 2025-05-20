import { WebPlugin } from '@capacitor/core';

import type { RedemptionKitIonicHelperPlugin } from './definitions';

export class RedemptionKitIonicHelperWeb extends WebPlugin implements RedemptionKitIonicHelperPlugin {
  async initializeAndLaunch(options: { value: string, name: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
