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
        CAPPluginMethod(name: "initialize", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "launch", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "initializeAndLaunch", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getPointBalance", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getTransactionHistory", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getRedemptionOptions", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getBanners", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getGiftVouchers", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getOfferCategories", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getOffers", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getBillPayCategories", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "searchProducts", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "clearSession", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = RedemptionKitIonicHelper()

    @objc
    func initialize(_ call: CAPPluginCall) {
        Task {
            guard let apiKey = call.getString("apiKey") else {
                let error = LRRedemptionKitError.invalidInput(inputInformation: "apiKey")
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
                try await LRRedemptionKit.initialize(
                    apiKey: apiKey,
                    handler: self,
                    customerID: customerID,
                    sessionTimeout: timeOut
                )
                call.resolve([
                    "value": "Success"
                ])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func launch(_ call: CAPPluginCall) {
        Task {
            var redirectionObj: LRRedirectionDetails?
            if let obj = call.getObject("redirection") {
                //Go via redirection
                
                guard let type = obj["type"] as? String, let typeEnum = LRRedirectionType(rawValue: type) else {
                    let error = LRRedemptionKitError.invalidInput(inputInformation: "type")
                    call.reject(error.localizedDescription, nil, error, nil)
                    return
                }
                
                let title = obj["title"] as? String
                let value = obj["value"] as? String
                redirectionObj = LRRedirectionDetails(type: typeEnum, title: title, value: value)
            }
            
            guard let vc = self.bridge?.viewController else {
                let error = LRRedemptionKitError.unknownError
                call.reject(error.localizedDescription, nil, error, nil)
                return
            }
            
            do {
                try await LRRedemptionKit.launch(
                    context: vc,
                    redirection: redirectionObj
                )
                call.resolve([
                    "value": "Success"
                ])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func initializeAndLaunch(_ call: CAPPluginCall) {
        Task {
            guard let apiKey = call.getString("apiKey") else {
                let error = LRRedemptionKitError.invalidInput(inputInformation: "apiKey")
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
            
            guard let vc = self.bridge?.viewController else {
                let error = LRRedemptionKitError.unknownError
                call.reject(error.localizedDescription, nil, error, nil)
                return
            }
            
            do {
                try await LRRedemptionKit.initializeAndLaunch(
                    context: vc,
                    apiKey: apiKey,
                    handler: self,
                    customerID: customerID,
                    sessionTimeout: timeOut
                )
                call.resolve([
                    "value": "Success"
                ])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func getPointBalance(_ call: CAPPluginCall) {
        Task {
            do {
                let response = try await LRRedemptionKit.getPointBalance(kind: call.getString("kind"))
                call.resolve(["value": response])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func getTransactionHistory(_ call: CAPPluginCall) {
        Task {
            do {
                var transactionTypeEnum: LRTransactionType
                if let transactionType = call.getString("transactionType"), let enumType = LRTransactionType(rawValue: transactionType) {
                    transactionTypeEnum = enumType
                } else {
                    transactionTypeEnum = .all
                }
                
                var startDate: Date?
                if let startDateInt = call.getInt("startDate") {
                    startDate = Date(timeIntervalSince1970: TimeInterval(startDateInt))
                }
                
                var endDate: Date?
                if let endDateInt = call.getInt("endDate") {
                    endDate = Date(timeIntervalSince1970: TimeInterval(endDateInt))
                }
                
                let response = try await LRRedemptionKit.getTransactionHistory(
                    transactionType: transactionTypeEnum,
                    startDate: startDate,
                    endDate: endDate
                )
                call.resolve(["value": response])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func getRedemptionOptions(_ call: CAPPluginCall) {
        Task {
            do {
                let response = try await LRRedemptionKit.getRedemptionOptions()
                call.resolve(["value": response])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func getBanners(_ call: CAPPluginCall) {
        Task {
            do {
                if let moduleName = call.getString("moduleName") {
                    let response = try await LRRedemptionKit.getBanners(
                        moduleName: moduleName
                    )
                    call.resolve(["value": response])
                } else {
                    let response = try await LRRedemptionKit.getBanners()
                    call.resolve(["value": response])
                }
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func getGiftVouchers(_ call: CAPPluginCall) {
        Task {
            do {
                let response = try await LRRedemptionKit.getGiftVouchers()
                call.resolve(["value": response])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func getOfferCategories(_ call: CAPPluginCall) {
        Task {
            do {
                let response = try await LRRedemptionKit.getOfferCategories()
                call.resolve(["value": response])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func getOffers(_ call: CAPPluginCall) {
        Task {
            do {
                guard let typeString = call.getString("type"), let type = LROfferType(rawValue: typeString) else {
                    let error = LRRedemptionKitError.invalidInput(inputInformation: "type")
                    call.reject(error.localizedDescription, nil, error, nil)
                    return
                }
                
                let response = try await LRRedemptionKit.getOffers(
                    type: type
                )
                call.resolve(["value": response])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func getBillPayCategories(_ call: CAPPluginCall) {
        Task {
            do {
                let response = try await LRRedemptionKit.getBillPayCategories()
                call.resolve(["value": response])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func searchProducts(_ call: CAPPluginCall) {
        Task {
            guard let typeString = call.getString("type"), let type = LRSearchType(rawValue: typeString) else {
                let error = LRRedemptionKitError.invalidInput(inputInformation: "type")
                call.reject(error.localizedDescription, nil, error, nil)
                return
            }
            
            guard let keyword = call.getString("keyword") else {
                let error = LRRedemptionKitError.invalidInput(inputInformation: "keyword")
                call.reject(error.localizedDescription, nil, error, nil)
                return
            }
            
            do {
                let response = try await LRRedemptionKit.searchProducts(
                    type: type,
                    keyword: keyword
                )
                call.resolve(["value": response])
            } catch {
                call.reject(error.localizedDescription, nil, error, nil)
            }
        }
    }
    
    @objc
    func clearSession(_ call: CAPPluginCall) {
        LRRedemptionKit.clearSession()
        call.resolve([
            "value": "Success"
        ])
    }
}

extension RedemptionKitIonicHelperPlugin: LRRedemptionKitHandler {
    public func sdkLoggedOut() {
        print("\(#function)")
        self.notifyListeners("sdkLogoutEvent", data: [:])
    }
    
    public func sdkSessionNotifier(isSessionValid: Bool) {
        print("\(#function) : isSessionValid : \(isSessionValid)")
        self.notifyListeners("sdkSessionNotifierEvent", data: ["isSessionValid": isSessionValid])
    }
}
