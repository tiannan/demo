// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sampler.cpu;

import com.meituan.metrics.config.MetricsRemoteConfig;


public class CpuUsageProviderFactory
{

    public CpuUsageProviderFactory()
    {
    }

    public static ICpuUsageProvider create(MetricsRemoteConfig metricsremoteconfig)
    {

        if(android.os.Build.VERSION.SDK_INT < 26)
            return new CpuUsageProviderV25();
        if(metricsremoteconfig != null && metricsremoteconfig.isCpuInnovationEnable())
            return new CpuUsageProviderV26Plus();
        else
            return null;
    }

}
