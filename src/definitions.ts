export interface RedemptionKitIonicHelperPlugin {
  initialize(options: { apiKey: string, customerID: string, sessionTimeout?: number }): Promise<{ value: string }>;
  launch(options?: { redirection?: Object }): Promise<{ value: string }>;
  initializeAndLaunch(options: { apiKey: string, customerID: string, sessionTimeout?: number }): Promise<{ value: string }>;
  getPointBalance(options?: { kind: string }): Promise<{ value: string }>;
  getTransactionHistory(options: { transactionType?: string, startDate?: number, endDate?: number }): Promise<{ value: string }>;
  getRedemptionOptions(): Promise<{ value: string }>;
  getBanners(options: { moduleName?: string }): Promise<{ value: string }>;
  getGiftVouchers(): Promise<{ value: string }>;
  getOfferCategories(): Promise<{ value: string }>;
  getOffers(options: { type: string }): Promise<{ value: string }>;
  getBillPayCategories(): Promise<{ value: string }>;
  searchProducts(options: { type: string, keyword: string }): Promise<{ value: string }>;
  clearSession(): Promise<{ value: string }>;
}
