package com.loylty.plugins.elitesdk;

import android.text.TextUtils;
import android.util.Log;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.rewardz.redemptionkit.LRRedemptionKit;
import com.rewardz.redemptionkit.interfaces.LRRedemptionKitHandler;

@CapacitorPlugin(name = "RedemptionKitIonicHelper")
public class RedemptionKitIonicHelperPlugin extends Plugin implements LRRedemptionKitHandler {
    @PluginMethod
    public void initializeAndLaunch(PluginCall call) {
        String apiKey = call.getString("value");
        Log.i("kk", "apiKey............: "+apiKey);
        String customerId = call.getString("name");
        Log.i("kk", "customerId............: "+customerId);

        if (TextUtils.isEmpty(apiKey)) {
            call.reject("Missing apiKey");
            return;
        } else if (TextUtils.isEmpty(customerId)) {
            call.reject("Missing customerId");
            return;
        }

        getActivity().runOnUiThread(() -> LRRedemptionKit.getInstance().initializeAndLaunch(getActivity(),
                apiKey,
                RedemptionKitIonicHelperPlugin.this,
                customerId,
                0,
                e -> Log.d("LRRedemptionKit", "LRRedemptionKit Exception.............: " + e.getMessage())));

        call.resolve();
    }

    @Override
    public void sdkLoggedOut() {

    }

    @Override
    public void sdkSessionNotifier(boolean b) {

    }
}
