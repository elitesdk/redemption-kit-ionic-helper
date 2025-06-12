package com.loylty.plugins.elitesdk;

import android.text.TextUtils;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.rewardz.redemptionkit.LRRedemptionKit;
import com.rewardz.redemptionkit.emum.LROfferType;
import com.rewardz.redemptionkit.emum.LRProductType;
import com.rewardz.redemptionkit.emum.LRRedirectionType;
import com.rewardz.redemptionkit.emum.LRSearchType;
import com.rewardz.redemptionkit.emum.LRTransactionType;
import com.rewardz.redemptionkit.interfaces.LRRedemptionKitHandler;
import com.rewardz.redemptionkit.interfaces.LrErrorCallback;
import com.rewardz.redemptionkit.interfaces.LrSdkCallback;
import com.rewardz.redemptionkit.models.LRRedirectionDetails;

import java.text.SimpleDateFormat;
import java.util.Date;

@CapacitorPlugin(name = "RedemptionKitIonicHelper")
public class RedemptionKitIonicHelperPlugin extends Plugin implements LRRedemptionKitHandler {

    String value = "value";

    @PluginMethod
    public void initialize(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().initialize(getActivity(), call.getString("apiKey"), RedemptionKitIonicHelperPlugin.this, call.getString("customerID"), call.getInt("sessionTimeout", 0), new LrErrorCallback() {
            @Override
            public void onSuccess() {
                call.resolve();
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));

    }

    @PluginMethod
    public void launch(PluginCall call) {
        JSObject jsObject = call.getObject("redirection");
        LRRedirectionDetails lrRedirectionDetails;
        if (jsObject != null) {
            lrRedirectionDetails = new LRRedirectionDetails();
            lrRedirectionDetails.setType(getLRRedirectionType(call.getString("type")));
            lrRedirectionDetails.setTitle(call.getString("title"));
            lrRedirectionDetails.setValue(call.getString("value"));
        } else lrRedirectionDetails = null;


        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().launch(getActivity(), lrRedirectionDetails, new LrErrorCallback() {
            @Override
            public void onSuccess() {
                call.resolve();
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    @PluginMethod
    public void initializeAndLaunch(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().initializeAndLaunch(getActivity(), call.getString("apiKey"), RedemptionKitIonicHelperPlugin.this, call.getString("customerID"), call.getInt("sessionTimeout", 0), new LrErrorCallback() {
            @Override
            public void onSuccess() {
                call.resolve();
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    @PluginMethod
    public void getPointBalance(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().getPointBalance(call.getString("kind"), new LrSdkCallback() {
            @Override
            public void onSuccess(String s) {
                call.resolve(new JSObject().put(value, s));
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    @PluginMethod
    public void getTransactionHistory(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().getTransactionHistory(getLRTransactionType(call.getString("transactionType")), getDateFromString(call.getLong("startDate")), getDateFromString(call.getLong("endDate")), new LrSdkCallback() {
            @Override
            public void onSuccess(String s) {
                call.resolve(new JSObject().put(value, s));
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    @PluginMethod
    public void getRedemptionOptions(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().getRedemptionOptions(new LrSdkCallback() {
            @Override
            public void onSuccess(String s) {
                call.resolve(new JSObject().put(value, s));
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    @PluginMethod
    public void getBanners(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().getBanners(call.getString("moduleName"), new LrSdkCallback() {
            @Override
            public void onSuccess(String s) {
                call.resolve(new JSObject().put(value, s));
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    @PluginMethod
    public void getGiftVouchers(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().getGiftVouchers(new LrSdkCallback() {
            @Override
            public void onSuccess(String s) {
                call.resolve(new JSObject().put(value, s));
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    @PluginMethod
    public void getOfferCategories(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().getOfferCategories(new LrSdkCallback() {
            @Override
            public void onSuccess(String s) {
                call.resolve(new JSObject().put(value, s));
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    @PluginMethod
    public void getOffers(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().getOffers(getLROfferType(call.getString("type")), new LrSdkCallback() {
            @Override
            public void onSuccess(String s) {
                call.resolve(new JSObject().put(value, s));
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    @PluginMethod
    public void getBillPayCategories(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().getBillPayCategories(new LrSdkCallback() {
            @Override
            public void onSuccess(String s) {
                call.resolve(new JSObject().put(value, s));
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    @PluginMethod
    public void searchProducts(PluginCall call) {
        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().searchProducts(getLRSearchType(call.getString("type")), call.getString("keyword"), new LrSdkCallback() {
            @Override
            public void onSuccess(String s) {
                call.resolve(new JSObject().put(value, s));
            }

            @Override
            public void onError(Exception e) {
                call.reject(e.getMessage(), e);
            }
        }));
    }

    private LRSearchType getLRSearchType(String type) {
        if (TextUtils.isEmpty(type)) return null;
        else if (type.equalsIgnoreCase("giftVoucherSearch")) return LRSearchType.giftVoucherSearch;
        else if (type.equalsIgnoreCase("offerSearch")) return LRSearchType.offerSearch;
        return null;
    }
    private LRTransactionType getLRTransactionType(String type) {
        if (TextUtils.isEmpty(type)) return null;
        else if (type.equalsIgnoreCase("cr")) return LRTransactionType.credit;
        else if (type.equalsIgnoreCase("dr")) return LRTransactionType.debit;
        return null;
    }

    private LROfferType getLROfferType(String type) {
        if (TextUtils.isEmpty(type)) return null;
        else if (type.equalsIgnoreCase("recommended")) return LROfferType.recommended;
        else if (type.equalsIgnoreCase("buy")) return LROfferType.buy;
        else if (type.equalsIgnoreCase("avail")) return LROfferType.avail;
        else if (type.equalsIgnoreCase("exclusiveDeals")) return LROfferType.exclusiveDeals;
        else if (type.equalsIgnoreCase("exclusiveOffers")) return LROfferType.exclusiveOffers;
        else if (type.equalsIgnoreCase("affilate")) return LROfferType.affilate;
        else if (type.equalsIgnoreCase("personalizedAge")) return LROfferType.personalizedAge;
        else if (type.equalsIgnoreCase("personalizedGender")) return LROfferType.personalizedGender;
        else if (type.equalsIgnoreCase("personalizedSearch")) return LROfferType.personalizedSearch;
        else if (type.equalsIgnoreCase("personalizedTopCategory")) return LROfferType.personalizedTopCategory;
        else if (type.equalsIgnoreCase("collected")) return LROfferType.collected;
        else if (type.equalsIgnoreCase("locationBased")) return LROfferType.locationBased;
        return null;
    }

    private LRRedirectionType getLRRedirectionType(String type) {
        if (TextUtils.isEmpty(type)) return null;
        else if (type.equalsIgnoreCase("redemptionOption")) return LRRedirectionType.redemptionOption;
        else if (type.equalsIgnoreCase("merchandiseProduct")) return LRRedirectionType.merchandiseProduct;
        else if (type.equalsIgnoreCase("merchandiseCategory")) return LRRedirectionType.merchandiseCategory;
        else if (type.equalsIgnoreCase("merchandiseRecommended")) return LRRedirectionType.merchandiseRecommended;
        else if (type.equalsIgnoreCase("merchandiseFeatured")) return LRRedirectionType.merchandiseFeatured;
        else if (type.equalsIgnoreCase("merchandiseSearch")) return LRRedirectionType.merchandiseSearch;
        else if (type.equalsIgnoreCase("giftCategoryItem")) return LRRedirectionType.giftCategoryItem;
        else if (type.equalsIgnoreCase("giftVouchers")) return LRRedirectionType.giftVouchers;
        else if (type.equalsIgnoreCase("giftVoucherItem")) return LRRedirectionType.giftVoucherItem;
        else if (type.equalsIgnoreCase("giftSearch")) return LRRedirectionType.giftSearch;
        else if (type.equalsIgnoreCase("offerItem")) return LRRedirectionType.offerItem;
        else if (type.equalsIgnoreCase("offerRecommended")) return LRRedirectionType.offerRecommended;
        else if (type.equalsIgnoreCase("offerBuy")) return LRRedirectionType.offerBuy;
        else if (type.equalsIgnoreCase("offerAvail")) return LRRedirectionType.offerAvail;
        else if (type.equalsIgnoreCase("offerExclusiveDeals")) return LRRedirectionType.offerExclusiveDeals;
        else if (type.equalsIgnoreCase("offerAffilate")) return LRRedirectionType.offerAffilate;
        else if (type.equalsIgnoreCase("offerPersonalizedAge")) return LRRedirectionType.offerPersonalizedAge;
        else if (type.equalsIgnoreCase("offerPersonalizedGender")) return LRRedirectionType.offerPersonalizedGender;
        else if (type.equalsIgnoreCase("offerPersonalizedSearch")) return LRRedirectionType.offerPersonalizedSearch;
        else if (type.equalsIgnoreCase("offerPersonalizedTopCategory")) return LRRedirectionType.offerPersonalizedTopCategory;
        else if (type.equalsIgnoreCase("offerCategories")) return LRRedirectionType.offerCategories;
        else if (type.equalsIgnoreCase("offerSearch")) return LRRedirectionType.offerSearch;
        else if (type.equalsIgnoreCase("billPayCategory")) return LRRedirectionType.billPayCategory;
        else if (type.equalsIgnoreCase("banner")) return LRRedirectionType.banner;
        else if (type.equalsIgnoreCase("offerCategoryItem")) return LRRedirectionType.offerCategoryItem;
        else if (type.equalsIgnoreCase("offerExclusive")) return LRRedirectionType.offerExclusive;
        else if (type.equalsIgnoreCase("allRedemptionOption")) return LRRedirectionType.allRedemptionOption;
        else if (type.equalsIgnoreCase("collectedOffers")) return LRRedirectionType.collectedOffers;
        else if (type.equalsIgnoreCase("locationBased")) return LRRedirectionType.locationBased;
        else if (type.equalsIgnoreCase("rewardGoals")) return LRRedirectionType.rewardGoals;
        else if (type.equalsIgnoreCase("notification")) return LRRedirectionType.notification;
        else if (type.equalsIgnoreCase("allMerchandiseCategory")) return LRRedirectionType.allMerchandiseCategory;
        return null;
    }

    private Date getDateFromString(Long epochTime) {
        return new Date(epochTime);
    }

    @Override
    public void sdkLoggedOut() {
        notifyListeners("sdkLogoutEvent", new JSObject());
    }

    @Override
    public void sdkSessionNotifier(boolean b) {
        JSObject ret = new JSObject();
        ret.put("isSessionValid", b);
        notifyListeners("sdkSessionNotifierEvent", ret);
    }
}
