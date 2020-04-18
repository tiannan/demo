// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic.hurl;

import com.meituan.metrics.Metrics;
import com.meituan.metrics.config.MetricsConfig;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.sankuai.waimai.trafficlimiter.injection.c;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

// Referenced classes of package com.meituan.metrics.traffic.hurl:
//            HttpURLConnection$, HttpsURLConnection$

public final class HttpURLWrapper
{

    public HttpURLWrapper()
    {
    }

    public static Object wrapGetContent(URL url)
        throws IOException
    {
        Object aobj[] = new Object[1];
        aobj[0] = url;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "e7dd6d8a07ec4e2556d29e36f9d09370", 0x4000000000000000L))
            return (Object)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "e7dd6d8a07ec4e2556d29e36f9d09370");
        else
            return wrapURLConnectionHelper(c.a(url)).getContent();
    }

    public static Object wrapGetContent(URL url, Class aclass[])
        throws IOException
    {
        Object aobj[] = new Object[2];
        aobj[0] = url;
        aobj[1] = aclass;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "387da718725ce13ade7de3ae5789f6c1", 0x4000000000000000L))
            return (Object)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "387da718725ce13ade7de3ae5789f6c1");
        else
            return wrapURLConnectionHelper(c.a(url)).getContent(aclass);
    }

    public static InputStream wrapOpenStream(URL url)
        throws IOException
    {
        Object aobj[] = new Object[1];
        aobj[0] = url;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "95edf8c3442407e22b37c729be0dd721", 0x4000000000000000L))
            return (InputStream)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "95edf8c3442407e22b37c729be0dd721");
        else
            return wrapURLConnectionHelper(c.a(url)).getInputStream();
    }

    public static URLConnection wrapURLConnection(URLConnection urlconnection)
    {
        Object aobj[] = new Object[1];
        aobj[0] = urlconnection;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "e9a0540b02a8296cd97a8eff66443e59", 0x4000000000000000L))
            return (URLConnection)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "e9a0540b02a8296cd97a8eff66443e59");
        else
            return wrapURLConnectionHelper(urlconnection);
    }

    private static URLConnection wrapURLConnectionHelper(URLConnection urlconnection)
    {
        Object aobj[] = new Object[1];
        aobj[0] = urlconnection;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "ce808f43b576004cdd4904617f778ac0", 0x4000000000000000L))
            return (URLConnection)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "ce808f43b576004cdd4904617f778ac0");
        if(Metrics.getInstance().getAppConfig().isTrafficStatDisabled())
            return urlconnection;
        if(urlconnection instanceof HttpURLConnection$)
            return urlconnection;
        if(urlconnection instanceof HttpsURLConnection)
            return new HttpsURLConnection$((HttpsURLConnection)urlconnection);
        if(urlconnection instanceof HttpURLConnection)
            return new HttpURLConnection$((HttpURLConnection)urlconnection);
        else
            return urlconnection;
    }

    public static ChangeQuickRedirect changeQuickRedirect;
}
