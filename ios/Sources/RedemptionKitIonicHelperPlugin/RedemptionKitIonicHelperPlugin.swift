import Foundation
import Capacitor
import RedemptionKit

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(RedemptionKitIonicHelperPlugin)
public class RedemptionKitIonicHelperPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "RedemptionKitIonicHelperPlugin"
    public let jsName = "RedemptionKitIonicHelper"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "initializeAndLaunch", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = RedemptionKitIonicHelper()

    @objc func initializeAndLaunch(_ call: CAPPluginCall) {
//         let value = call.getString("value") ?? ""
//        
//        DispatchQueue.main.async {
//            let vc = CustomViewController(sso: value)
//            self.bridge?.viewController?.present(vc, animated: true)
//        }
//        
//        call.resolve([
//            "value": value
//        ])
        
        Task {
            guard let apiKey = call.getString("apiKey") else {
                let error = LRRedemptionKitError.invalidInput(inputInformation: "apiKey")
                call.reject(error.localizedDescription, nil, error, nil)
                return
            }
            
            guard let vc = self.bridge?.viewController else {
                let error = LRRedemptionKitError.unknownError
                call.reject(error.localizedDescription, nil, error, nil)
                return
            }
            
            guard let customerID = call.getString("customerID") else {
                let error = LRRedemptionKitError.invalidInput(inputInformation: "customerID")
                call.reject(error.localizedDescription, nil, error, nil)
                return
            }
            
            var timeOut: Int? = nil
            
            if let sessionTimeout = call.getString("sessionTimeout"), let time = Int(sessionTimeout) {
                timeOut = time
            }
            
            do {
                try await LRRedemptionKit.initializeAndLaunch(
                    context: vc,
                    apiKey: apiKey,
                    handler: self,
                    customerID: customerID,
                    sessionTimeout: timeOut
                )
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
}

extension RedemptionKitIonicHelperPlugin: LRRedemptionKitHandler {
    func sdkLoggedOut() {
        print("\(#function)")
        self.notifyListeners("sdkLogoutEvent", data: [:])
    }
    
    func sdkSessionNotifier(isSessionValid: Bool) {
        print("\(#function) : isSessionValid : \(isSessionValid)")
        self.notifyListeners("sdkSessionNotifierEvent", data: ["isSessionValid": isSessionValid])
    }
}

//class CustomViewController: UIViewController, LRSDKDelegate {
//    func isUserAlive(isUserActive: Bool) {
//        
//    }
//    
//    func userLogout() {
//        
//    }
//    
//    func backToHome() {
//        
//    }
//    
//    func configurationMpinCallBack(orderId: String, requestId: String) {
//        
//    }
//    
//    let sso: String
//    
//    init(sso: String) {
//        self.sso = sso
//        super.init(nibName: nil, bundle: nil)
//    }
//    
//    required init?(coder: NSCoder) {
//        fatalError("init(coder:) has not been implemented")
//    }
//    
//    override func viewDidLoad() {
//        super.viewDidLoad()
//        
//        LRSDKConfigurator.launchSDK(ssoToken: sso, delegate: self)
//    }
//}
