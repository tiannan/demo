// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics;

import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.meituan.metrics.config.MetricsConfig;
import com.meituan.metrics.util.AppUtils;
import com.meituan.metrics.util.NetUtils;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import org.json.JSONObject;

public class Environment
{
    public static interface ExtraGetter
    {

        public abstract Object onGetExtra();
    }


    public Environment(Context context, MetricsConfig metricsconfig)
    {
        Object aobj[] = new Object[2];
        aobj[0] = context;
        aobj[1] = metricsconfig;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "839d557219d765f9cb137db95d52a5b1", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "839d557219d765f9cb137db95d52a5b1");
            return;
        } else
        {
            contextWeakRef = new WeakReference(context);
            config = metricsconfig;
            os = "Android";
            osVersion = android.os.Build.VERSION.RELEASE;
            sdkVersion = "0.7.14";
            deviceProvider = Build.MANUFACTURER;
            deviceType = Build.MODEL;
            return;
        }
    }

    private String getDeviceIdInner()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "76980f54765c0261786d4baebe03b2fd", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "76980f54765c0261786d4baebe03b2fd");
        if(contextWeakRef == null) goto _L2; else goto _L1
_L1:
        Object obj1 = (Context)contextWeakRef.get();
        if(obj1 != null) goto _L3; else goto _L2
_L3:
        int i;
        int j;
        int k;
        Object obj;
        Object obj2;
        String s;
        try
        {
            obj = (TelephonyManager)((Context) (obj1)).getSystemService("phone");
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return "DeviceId0";
        }
        if(obj == null) goto _L5; else goto _L4
_L4:
        obj = ((TelephonyManager) (obj)).getDeviceId();
_L8:
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            return ((String) (obj)).trim();
        obj2 = new StringBuilder("35");
        ((StringBuilder) (obj2)).append(Build.BOARD.length() % 10);
        ((StringBuilder) (obj2)).append(Build.BRAND.length() % 10);
        ((StringBuilder) (obj2)).append(Build.CPU_ABI.length() % 10);
        ((StringBuilder) (obj2)).append(Build.DEVICE.length() % 10);
        ((StringBuilder) (obj2)).append(Build.DISPLAY.length() % 10);
        ((StringBuilder) (obj2)).append(Build.HOST.length() % 10);
        ((StringBuilder) (obj2)).append(Build.ID.length() % 10);
        ((StringBuilder) (obj2)).append(Build.MANUFACTURER.length() % 10);
        ((StringBuilder) (obj2)).append(Build.MODEL.length() % 10);
        ((StringBuilder) (obj2)).append(Build.PRODUCT.length() % 10);
        ((StringBuilder) (obj2)).append(Build.TAGS.length() % 10);
        ((StringBuilder) (obj2)).append(Build.TYPE.length() % 10);
        ((StringBuilder) (obj2)).append(Build.USER.length() % 10);
        obj2 = ((StringBuilder) (obj2)).toString();
        s = android.provider.Settings.Secure.getString(((Context) (obj1)).getContentResolver(), "android_id");
        obj1 = (WifiManager)((Context) (obj1)).getApplicationContext().getSystemService("wifi");
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_609;
        try
        {
            obj1 = ((WifiManager) (obj1)).getConnectionInfo().getMacAddress();
            break MISSING_BLOCK_LABEL_379;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1) { }
        ((Throwable) (obj1)).printStackTrace();
        break MISSING_BLOCK_LABEL_609;
_L9:
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(((String) (obj)));
        stringbuilder.append(((String) (obj2)));
        stringbuilder.append(s);
        stringbuilder.append(((String) (obj1)));
        stringbuilder.append(null);
        obj1 = stringbuilder.toString();
        try
        {
            obj = MessageDigest.getInstance("MD5");
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            obj = null;
        }
        ((MessageDigest) (obj)).update(((String) (obj1)).getBytes(), 0, ((String) (obj1)).length());
        obj = ((MessageDigest) (obj)).digest();
        obj1 = new StringBuilder();
        j = obj.length;
        i = 0;
_L7:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        k = obj[i] & 0xff;
        if(k > 15)
            break MISSING_BLOCK_LABEL_513;
        ((StringBuilder) (obj1)).append("0");
        ((StringBuilder) (obj1)).append(Integer.toHexString(k));
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        obj1 = new StringBuilder(((StringBuilder) (obj1)).toString().toUpperCase());
        obj = obj1;
        if(((StringBuilder) (obj1)).length() > 15)
            obj = new StringBuilder(((StringBuilder) (obj1)).substring(0, 15));
        return ((StringBuilder) (obj)).toString().trim();
_L2:
        return "DeviceId0";
_L5:
        obj = null;
          goto _L8
        obj1 = null;
          goto _L9
    }

    public String getApkHash()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "227171b93990eb10559112d69c108e19", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "227171b93990eb10559112d69c108e19");
        if(config != null)
            return config.getApkHash();
        else
            return "";
    }

    public String getAppVersion()
    {
label0:
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b0eeebf67b0087232b884df926b74562", 0x4000000000000000L))
                return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b0eeebf67b0087232b884df926b74562");
            if(appVersion != null)
                break label0;
            if(contextWeakRef != null)
            {
                Context context = (Context)contextWeakRef.get();
                if(context != null)
                {
                    appVersion = AppUtils.getVersionName(context);
                    break label0;
                }
            }
            return "";
        }
        return appVersion;
    }

    public String getAppnm()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4a8078815c8d45697ff6b6ed9bc0cbdd", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4a8078815c8d45697ff6b6ed9bc0cbdd");
        if(config != null)
            return config.getAppName();
        else
            return "";
    }

    public String getCh()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f95775db3a88588e497c5d868c9d6947", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f95775db3a88588e497c5d868c9d6947");
        if(config != null)
            return config.getChannel();
        else
            return "";
    }

    public long getCityId()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "1cb9184b3f5745c1e41909383eb104d1", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "1cb9184b3f5745c1e41909383eb104d1")).longValue();
        if(config != null)
            return config.getCityId();
        else
            return -1L;
    }

    public String getDeviceId()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "592befda96d4c7f2a5b3218aa19d948b", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "592befda96d4c7f2a5b3218aa19d948b");
        if(TextUtils.isEmpty(deviceId) || TextUtils.equals(deviceId, "DeviceId0"))
            deviceId = getDeviceIdInner();
        return deviceId;
    }

    public String getMccmnc()
    {
label0:
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "6a15da1fb2c330af090bf94cb8382309", 0x4000000000000000L))
                return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "6a15da1fb2c330af090bf94cb8382309");
            if(mccmnc != null)
                break label0;
            if(contextWeakRef != null)
            {
                Context context = (Context)contextWeakRef.get();
                if(context != null)
                {
                    mccmnc = AppUtils.getMccmnc(context);
                    break label0;
                }
            }
            return "";
        }
        return mccmnc;
    }

    public final String getNet()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9dabc31551651e60f71f2bf8afe0c0b6", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9dabc31551651e60f71f2bf8afe0c0b6");
        if(contextWeakRef != null)
        {
            Context context = (Context)contextWeakRef.get();
            if(context != null)
                return NetUtils.getCurrentClassNetworkType(context);
        }
        return "unknown";
    }

    public final String getSc()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "6d12498d4796516b67701dfacfc0250f", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "6d12498d4796516b67701dfacfc0250f");
        if(!TextUtils.isEmpty(sc))
            return sc;
        if(contextWeakRef != null)
        {
            Object obj = (Context)contextWeakRef.get();
            if(obj != null)
            {
                obj = ((Context) (obj)).getResources().getDisplayMetrics();
                int i = ((DisplayMetrics) (obj)).widthPixels;
                int j = ((DisplayMetrics) (obj)).heightPixels;
                obj = new StringBuilder();
                ((StringBuilder) (obj)).append(i);
                ((StringBuilder) (obj)).append("*");
                ((StringBuilder) (obj)).append(j);
                sc = ((StringBuilder) (obj)).toString();
                return sc;
            }
        }
        return "";
    }

    public String getToken()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9c55a538d3677e003d83a17eb501603e", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9c55a538d3677e003d83a17eb501603e");
        if(config != null)
            return config.getToken();
        else
            return "";
    }

    public String getUid()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "85bcc3de60d668eb30a0cc074d006531", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "85bcc3de60d668eb30a0cc074d006531");
        if(config != null)
            return config.getUserId();
        else
            return "";
    }

    public String getUuid()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "63209d33196a5e795d6464d48bb4c16d", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "63209d33196a5e795d6464d48bb4c16d");
        if(config != null)
            return config.getUuid();
        else
            return "";
    }

    public JSONObject toJson()
    {
        Object obj;
        Object aobj[] = new Object[0];
        obj = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, ((ChangeQuickRedirect) (obj)), false, "bb897a93bcc8c750301c4880f5fa915f", 0x4000000000000000L))
            return (JSONObject)PatchProxy.accessDispatch(aobj, this, ((ChangeQuickRedirect) (obj)), false, "bb897a93bcc8c750301c4880f5fa915f");
        String s;
        try
        {
            obj = new JSONObject();
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            return null;
        }
        ((JSONObject) (obj)).put("os", os);
        ((JSONObject) (obj)).put("token", getToken());
        ((JSONObject) (obj)).put("osVersion", osVersion);
        ((JSONObject) (obj)).put("sdkVersion", sdkVersion);
        ((JSONObject) (obj)).put("appVersion", getAppVersion());
        ((JSONObject) (obj)).put("deviceProvider", deviceProvider);
        if(getUuid() == null)
        {
            s = "";
            break MISSING_BLOCK_LABEL_138;
        }
        s = getUuid();
        ((JSONObject) (obj)).put("deviceId", s);
        ((JSONObject) (obj)).put("deviceType", deviceType);
        ((JSONObject) (obj)).put("mccmnc", getMccmnc());
        ((JSONObject) (obj)).put("hash", getApkHash());
        ((JSONObject) (obj)).put("ts", System.currentTimeMillis());
        Exception exception;
        return ((JSONObject) (obj));
        exception;
        return ((JSONObject) (obj));
    }

    private static final String DEFAULT_DEVICE_ID = "DeviceId0";
    public static ChangeQuickRedirect changeQuickRedirect;
    private String appVersion;
    private MetricsConfig config;
    private WeakReference contextWeakRef;
    private String deviceId;
    public String deviceProvider;
    public String deviceType;
    private String mccmnc;
    public String os;
    public String osVersion;
    private String sc;
    public String sdkVersion;
}
