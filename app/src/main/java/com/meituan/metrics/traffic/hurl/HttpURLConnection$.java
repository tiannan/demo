// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic.hurl;

import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.io.*;
import java.net.*;
import java.security.Permission;
import java.util.Map;

// Referenced classes of package com.meituan.metrics.traffic.hurl:
//            TrackedHttpURLConnection

public final class HttpURLConnection$ extends HttpURLConnection
{

    public HttpURLConnection$(HttpURLConnection httpurlconnection)
    {
        super(httpurlconnection.getURL());
        Object aobj[] = new Object[1];
        aobj[0] = httpurlconnection;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c25d843bdbf75c9a8add2d73d4763d73", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c25d843bdbf75c9a8add2d73d4763d73");
            return;
        } else
        {
            myTrackedConnection = new TrackedHttpURLConnection(httpurlconnection);
            return;
        }
    }

    public final void addRequestProperty(String s, String s1)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = s1;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b78255c3ab9d999ddc4a88710ff6a038", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b78255c3ab9d999ddc4a88710ff6a038");
            return;
        } else
        {
            myTrackedConnection.addRequestProperty(s, s1);
            return;
        }
    }

    public final void connect()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "bc5e674399382138634f6154f9679a19", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "bc5e674399382138634f6154f9679a19");
            return;
        } else
        {
            myTrackedConnection.connect();
            return;
        }
    }

    public final void disconnect()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e17df8b2866162d0ffcec26604575db7", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e17df8b2866162d0ffcec26604575db7");
            return;
        } else
        {
            myTrackedConnection.disconnect();
            return;
        }
    }

    public final boolean getAllowUserInteraction()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "63db2a450b177f623d8bcae45453ca11", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "63db2a450b177f623d8bcae45453ca11")).booleanValue();
        else
            return myTrackedConnection.getAllowUserInteraction();
    }

    public final int getConnectTimeout()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "73bda7611460c5975a191688e0363ef0", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "73bda7611460c5975a191688e0363ef0")).intValue();
        else
            return myTrackedConnection.getConnectTimeout();
    }

    public final Object getContent()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "0fa4cb90bc714ff3bbcf7acebdfc0f59", 0x4000000000000000L))
            return (Object)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "0fa4cb90bc714ff3bbcf7acebdfc0f59");
        else
            return myTrackedConnection.getContent();
    }

    public final Object getContent(Class aclass[])
        throws IOException
    {
        Object aobj[] = new Object[1];
        aobj[0] = aclass;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "034e65d98a096fac01c5341fc94d446b", 0x4000000000000000L))
            return (Object)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "034e65d98a096fac01c5341fc94d446b");
        else
            return myTrackedConnection.getContent(aclass);
    }

    public final String getContentEncoding()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e510266c3dae6c332e30687e98f9b15d", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e510266c3dae6c332e30687e98f9b15d");
        else
            return myTrackedConnection.getContentEncoding();
    }

    public final int getContentLength()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "77f17f3c1bedc98fd877809befd3432f", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "77f17f3c1bedc98fd877809befd3432f")).intValue();
        else
            return myTrackedConnection.getContentLength();
    }

    public final long getContentLengthLong()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e8c5d4e4c84e3ba63f3d0b83946844f2", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e8c5d4e4c84e3ba63f3d0b83946844f2")).longValue();
        else
            return myTrackedConnection.getContentLengthLong();
    }

    public final String getContentType()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "48dd070a768abe6decbf190b4b13917a", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "48dd070a768abe6decbf190b4b13917a");
        else
            return myTrackedConnection.getContentType();
    }

    public final long getDate()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e20cbb54aa691af274f4067e7eae204f", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e20cbb54aa691af274f4067e7eae204f")).longValue();
        else
            return myTrackedConnection.getDate();
    }

    public final boolean getDefaultUseCaches()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9c87595bc08ad01a1e85ce4d76bce7c3", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9c87595bc08ad01a1e85ce4d76bce7c3")).booleanValue();
        else
            return myTrackedConnection.getDefaultUseCaches();
    }

    public final boolean getDoInput()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "d3edc1c1c76398cfd98d7ea699d6243d", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "d3edc1c1c76398cfd98d7ea699d6243d")).booleanValue();
        else
            return myTrackedConnection.getDoInput();
    }

    public final boolean getDoOutput()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "cfdf87bf45034ca596ce79aa94d7f41a", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "cfdf87bf45034ca596ce79aa94d7f41a")).booleanValue();
        else
            return myTrackedConnection.getDoOutput();
    }

    public final InputStream getErrorStream()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4eeacf4bc6df4e83d44b5a6d547f628b", 0x4000000000000000L))
            return (InputStream)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4eeacf4bc6df4e83d44b5a6d547f628b");
        else
            return myTrackedConnection.getErrorStream();
    }

    public final long getExpiration()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "8b5497d1c8970d325fbb3954c462c3d9", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "8b5497d1c8970d325fbb3954c462c3d9")).longValue();
        else
            return myTrackedConnection.getExpiration();
    }

    public final String getHeaderField(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "25ecc188e868228b9c6ef4023a25518c", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "25ecc188e868228b9c6ef4023a25518c");
        else
            return myTrackedConnection.getHeaderField(i);
    }

    public final String getHeaderField(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e0ace021ba3e792851f339f249b7e698", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e0ace021ba3e792851f339f249b7e698");
        else
            return myTrackedConnection.getHeaderField(s);
    }

    public final long getHeaderFieldDate(String s, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "0946ef67b865b8e53491089a1b9d9d96", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "0946ef67b865b8e53491089a1b9d9d96")).longValue();
        else
            return myTrackedConnection.getHeaderFieldDate(s, l);
    }

    public final int getHeaderFieldInt(String s, int i)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "56a4848dd21c35078b33fcd6908ceda1", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "56a4848dd21c35078b33fcd6908ceda1")).intValue();
        else
            return myTrackedConnection.getHeaderFieldInt(s, i);
    }

    public final String getHeaderFieldKey(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "589f2b4cfc465deaf837845cce5bf49e", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "589f2b4cfc465deaf837845cce5bf49e");
        else
            return myTrackedConnection.getHeaderFieldKey(i);
    }

    public final long getHeaderFieldLong(String s, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "aed68108fb502e4f8dcbf79de9a2b23a", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "aed68108fb502e4f8dcbf79de9a2b23a")).longValue();
        else
            return myTrackedConnection.getHeaderFieldLong(s, l);
    }

    public final Map getHeaderFields()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "7a5305a660b8271ce29aecb047c07a8b", 0x4000000000000000L))
            return (Map)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "7a5305a660b8271ce29aecb047c07a8b");
        else
            return myTrackedConnection.getHeaderFields();
    }

    public final long getIfModifiedSince()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "6059a74cd5d5221da76ad851ca591556", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "6059a74cd5d5221da76ad851ca591556")).longValue();
        else
            return myTrackedConnection.getIfModifiedSince();
    }

    public final InputStream getInputStream()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "447159a6b8befe9b42d3dd8cfb712a8a", 0x4000000000000000L))
            return (InputStream)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "447159a6b8befe9b42d3dd8cfb712a8a");
        else
            return myTrackedConnection.getInputStream();
    }

    public final boolean getInstanceFollowRedirects()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b9d83c60816f2f411e629db22866763c", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b9d83c60816f2f411e629db22866763c")).booleanValue();
        else
            return myTrackedConnection.getInstanceFollowRedirects();
    }

    public final long getLastModified()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "609674ad8ce9a43162d202f91baec6a5", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "609674ad8ce9a43162d202f91baec6a5")).longValue();
        else
            return myTrackedConnection.getLastModified();
    }

    public final OutputStream getOutputStream()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "8e74533f8fdaee2957eced95e66f5733", 0x4000000000000000L))
            return (OutputStream)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "8e74533f8fdaee2957eced95e66f5733");
        else
            return myTrackedConnection.getOutputStream();
    }

    public final Permission getPermission()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "bb931bcbf33d82e9a2527d474fda7c8d", 0x4000000000000000L))
            return (Permission)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "bb931bcbf33d82e9a2527d474fda7c8d");
        else
            return myTrackedConnection.getPermission();
    }

    public final int getReadTimeout()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "1a8d4058ad4eebf1c129d823f01f094c", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "1a8d4058ad4eebf1c129d823f01f094c")).intValue();
        else
            return myTrackedConnection.getReadTimeout();
    }

    public final String getRequestMethod()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b3d426c6ae5c7e4a904f77be3805aeec", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b3d426c6ae5c7e4a904f77be3805aeec");
        else
            return myTrackedConnection.getRequestMethod();
    }

    public final Map getRequestProperties()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "834a1b95ba0b9bf19c71fd557771150f", 0x4000000000000000L))
            return (Map)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "834a1b95ba0b9bf19c71fd557771150f");
        else
            return myTrackedConnection.getRequestProperties();
    }

    public final String getRequestProperty(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "2ca268bd7775f1e23c7c362687bdc807", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "2ca268bd7775f1e23c7c362687bdc807");
        else
            return myTrackedConnection.getRequestProperty(s);
    }

    public final int getResponseCode()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "5065455f78a99a3ac830d455a70f5abd", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "5065455f78a99a3ac830d455a70f5abd")).intValue();
        else
            return myTrackedConnection.getResponseCode();
    }

    public final String getResponseMessage()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9d97545b58530b4e3cc094442e42e7d1", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9d97545b58530b4e3cc094442e42e7d1");
        else
            return myTrackedConnection.getResponseMessage();
    }

    public final URL getURL()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e21505fe57ab02a58babca0e71ab51ae", 0x4000000000000000L))
            return (URL)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e21505fe57ab02a58babca0e71ab51ae");
        else
            return myTrackedConnection.getURL();
    }

    public final boolean getUseCaches()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "16d7e1b63f818c34ce49b6c9a8dc2899", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "16d7e1b63f818c34ce49b6c9a8dc2899")).booleanValue();
        else
            return myTrackedConnection.getUseCaches();
    }

    public final void setAllowUserInteraction(boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public final void setChunkedStreamingMode(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "6fd35f634200a8b09eaac6877de7d90f", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "6fd35f634200a8b09eaac6877de7d90f");
            return;
        } else
        {
            myTrackedConnection.setChunkedStreamingMode(i);
            return;
        }
    }

    public final void setConnectTimeout(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "188350ea623050f4f79b14c1a317b210", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "188350ea623050f4f79b14c1a317b210");
            return;
        } else
        {
            myTrackedConnection.setConnectTimeout(i);
            return;
        }
    }

    public final void setDefaultUseCaches(boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public final void setDoInput(boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public final void setDoOutput(boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public final void setFixedLengthStreamingMode(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "cc17ec1d341a75621dd95563f0b116b7", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "cc17ec1d341a75621dd95563f0b116b7");
            return;
        } else
        {
            myTrackedConnection.setFixedLengthStreamingMode(i);
            return;
        }
    }

    public final void setFixedLengthStreamingMode(long l)
    {
        Object aobj[] = new Object[1];
        aobj[0] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9ddb3509e19c2b06dba02c22a42696de", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9ddb3509e19c2b06dba02c22a42696de");
            return;
        } else
        {
            myTrackedConnection.setFixedLengthStreamingMode(l);
            return;
        }
    }

    public final void setIfModifiedSince(long l)
    {
        Object aobj[] = new Object[1];
        aobj[0] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c9e380184d4614a9fbadfaf6b054ce51", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c9e380184d4614a9fbadfaf6b054ce51");
            return;
        } else
        {
            myTrackedConnection.setIfModifiedSince(l);
            return;
        }
    }

    public final void setInstanceFollowRedirects(boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public final void setReadTimeout(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "289474f3193d293399efac873b15a5d0", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "289474f3193d293399efac873b15a5d0");
            return;
        } else
        {
            myTrackedConnection.setReadTimeout(i);
            return;
        }
    }

    public final void setRequestMethod(String s)
        throws ProtocolException
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a851fd3348cda2fce912b3f470b00a96", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a851fd3348cda2fce912b3f470b00a96");
            return;
        } else
        {
            myTrackedConnection.setRequestMethod(s);
            return;
        }
    }

    public final void setRequestProperty(String s, String s1)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = s1;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "00c6bb2011034a8abdb15934529ead0b", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "00c6bb2011034a8abdb15934529ead0b");
            return;
        } else
        {
            myTrackedConnection.setRequestProperty(s, s1);
            return;
        }
    }

    public final void setUseCaches(boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public final String toString()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "1d6381870eb36517f1d8b2b194642eec", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "1d6381870eb36517f1d8b2b194642eec");
        else
            return myTrackedConnection.toString();
    }

    public final boolean usingProxy()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "eb366e9aa7fe53ba6cb8ef0bf201e867", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "eb366e9aa7fe53ba6cb8ef0bf201e867")).booleanValue();
        else
            return myTrackedConnection.usingProxy();
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private final TrackedHttpURLConnection myTrackedConnection;
}
