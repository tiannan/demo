// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.meituan.metrics.Metrics;
import com.meituan.snare.a;
import com.meituan.snare.h;
import com.sankuai.common.utils.ProcessUtils;

public abstract class MetricsConfig
{

    public MetricsConfig()
    {
    }

    public String getAnrOption()
    {
        return "";
    }

    public String getApkHash()
    {
        return "";
    }

    public String getAppName()
    {
        Throwable throwable;

        Object obj = Metrics.getInstance().getContext();
        if(obj == null)
            return "";
        try
        {
            obj = ((Context) (obj)).getPackageManager().getApplicationInfo(((Context) (obj)).getPackageName(), 128).metaData.getString("APP_NAME");
        }
        // Misplaced declaration of an exception variable
        catch(Throwable throwable)
        {
            return "";
        }
        return ((String) (obj));
    }

    public String getChannel()
    {
        return "";
    }

    public long getCityId()
    {
        return -1L;
    }

    public int getDebugMaxLaggyReportTimes()
    {
        return 0x7fffffff;
    }

    public h getReportStrategy()
    {

            return new a();
    }

    public abstract String getToken();

    public String getTrafficApiUrlPattern()
    {
        return "^http(s)?://(.*\\.)?(meituan\\.com|maoyan\\.com|dianping\\.com|kuxun\\.cn).*";
    }

    public String getTrafficResUrlPattern()
    {
        return "^http(s)?://(.*\\.)?(meituan\\.net|dpfile\\.com).*";
    }

    public String getTrafficWebUrlPattern()
    {
        return "^http(s)?://(.*\\.)?(meituan\\.com|maoyan\\.com|dianping\\.com|kuxun\\.cn|meituan\\.net|dpfile\\.com).*";
    }

    public String getUserId()
    {
        return "";
    }

    public abstract String getUuid();

    public boolean isAnrEnable()
    {
        return true;
    }

    public boolean isBigImageEnable()
    {
        return true;
    }

    public boolean isLagEnable()
    {
        return true;
    }

    public boolean isNativeFPSSampleEnable()
    {
        return true;
    }

    public boolean isSampleEnable()
    {
        return true;
    }

    public boolean isStartupTimerEnable()
    {
        return true;
    }

    public boolean isTrafficStatDisabled()
    {
            Context context = Metrics.getInstance().getContext();
        return context == null || !ProcessUtils.isMainProcess(context);
    }

    private static final String TRAFFIC_DEFAULT_REG_API = "^http(s)?://(.*\\.)?(meituan\\.com|maoyan\\.com|dianping\\.com|kuxun\\.cn).*";
    private static final String TRAFFIC_DEFAULT_REG_RES = "^http(s)?://(.*\\.)?(meituan\\.net|dpfile\\.com).*";
    private static final String TRAFFIC_DEFAULT_REG_WEB = "^http(s)?://(.*\\.)?(meituan\\.com|maoyan\\.com|dianping\\.com|kuxun\\.cn|meituan\\.net|dpfile\\.com).*";
}
