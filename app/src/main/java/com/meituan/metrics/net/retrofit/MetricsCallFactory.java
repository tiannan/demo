package com.meituan.metrics.net.retrofit;


import com.sankuai.meituan.retrofit2.callfactory.urlconnection.UrlConnectionCallFactory;
import com.sankuai.meituan.retrofit2.raw.RawCall.Factory;

/* compiled from: ProGuard */
public class MetricsCallFactory {
    private static Factory externalFactory;
    private static volatile Factory sCallFactory;

    public static Factory getCallFactory() {

        if (externalFactory != null) {
            return externalFactory;
        }
        if (sCallFactory == null) {
            synchronized (MetricsCallFactory.class) {
                if (sCallFactory == null) {
                    sCallFactory = UrlConnectionCallFactory.create(30000, 30000);
                }
            }
        }
        return sCallFactory;
    }

    public static void setExternalFactory(Factory factory) {
        externalFactory = factory;
    }
}