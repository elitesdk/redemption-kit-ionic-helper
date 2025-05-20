import { registerPlugin } from '@capacitor/core';

import type { RedemptionKitIonicHelperPlugin } from './definitions';

const RedemptionKitIonicHelper = registerPlugin<RedemptionKitIonicHelperPlugin>('RedemptionKitIonicHelper', {
  web: () => import('./web').then((m) => new m.RedemptionKitIonicHelperWeb()),
});

export * from './definitions';
export { RedemptionKitIonicHelper };
