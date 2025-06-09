export interface RedemptionKitIonicHelperPlugin {
  initializeAndLaunch(options: { apiKey: string, customerID: string, sessionTimeout: number }): Promise<{ value: string }>;
  
}
