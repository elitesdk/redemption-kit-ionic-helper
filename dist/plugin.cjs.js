'use strict';

var core = require('@capacitor/core');

const RedemptionKitIonicHelper = core.registerPlugin('RedemptionKitIonicHelper', {
    web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.RedemptionKitIonicHelperWeb()),
});

class RedemptionKitIonicHelperWeb extends core.WebPlugin {
    async initialize(options) {
        console.log('options: ' + options);
        return { value: "" };
    }
    async launch(options) {
        console.log('options: ' + options);
        return { value: "" };
    }
    async initializeAndLaunch(options) {
        console.log('options: ' + options);
        return { value: "" };
    }
    async getPointBalance(options) {
        console.log('options: ' + options);
        return { value: "" };
    }
    async getTransactionHistory(options) {
        console.log('options: ' + options);
        return { value: "" };
    }
    async getRedemptionOptions() {
        console.log('options: ');
        return { value: "" };
    }
    async getBanners(options) {
        console.log('options: ' + options);
        return { value: "" };
    }
    async getGiftVouchers() {
        console.log('options: ');
        return { value: "" };
    }
    async getOfferCategories() {
        console.log('options: ');
        return { value: "" };
    }
    async getOffers(options) {
        console.log('options: ' + options);
        return { value: "" };
    }
    async getBillPayCategories() {
        console.log('options: ');
        return { value: "" };
    }
    async searchProducts(options) {
        console.log('options: ' + options);
        return { value: "" };
    }
    async clearSession() {
        console.log('options: ');
        return { value: "" };
    }
}

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    RedemptionKitIonicHelperWeb: RedemptionKitIonicHelperWeb
});

exports.RedemptionKitIonicHelper = RedemptionKitIonicHelper;
//# sourceMappingURL=plugin.cjs.js.map
