export interface RedemptionKitIonicHelperPlugin {
  initializeAndLaunch(options: { value: string, name: string }): Promise<{ value: string }>;
  
}
