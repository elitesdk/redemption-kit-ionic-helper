import { registerPlugin } from '@capacitor/core';
const RedemptionKitIonicHelper = registerPlugin('RedemptionKitIonicHelper', {
    web: () => import('./web').then((m) => new m.RedemptionKitIonicHelperWeb()),
});
export * from './definitions';
export { RedemptionKitIonicHelper };
//# sourceMappingURL=index.js.map