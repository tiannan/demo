// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic.hurl;

import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.io.*;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Map;
import javax.net.ssl.*;

// Referenced classes of package com.meituan.metrics.traffic.hurl:
//            TrackedHttpURLConnection

public final class HttpsURLConnection$ extends HttpsURLConnection
{

    public HttpsURLConnection$(HttpsURLConnection httpsurlconnection)
    {
        super(httpsurlconnection.getURL());
        Object aobj[] = new Object[1];
        aobj[0] = httpsurlconnection;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b261b4fdba65a34d56805e071c39491e", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b261b4fdba65a34d56805e071c39491e");
            return;
        } else
        {
            myTrackedConnection = new TrackedHttpURLConnection(httpsurlconnection);
            myWrappedHttps = httpsurlconnection;
            return;
        }
    }

    public final void addRequestProperty(String s, String s1)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = s1;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "33dee6c71774c71181168990b489442e", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "33dee6c71774c71181168990b489442e");
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a327bacb9a75082c5800be34e3dad123", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a327bacb9a75082c5800be34e3dad123");
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a806deb1ad8482bb56b0af3acd007863", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a806deb1ad8482bb56b0af3acd007863");
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "32d0c2b7301ea8954338c5d38d763733", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "32d0c2b7301ea8954338c5d38d763733")).booleanValue();
        else
            return myTrackedConnection.getAllowUserInteraction();
    }

    public final String getCipherSuite()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c102e274a214654e570a3e94378d4306", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c102e274a214654e570a3e94378d4306");
        else
            return myWrappedHttps.getCipherSuite();
    }

    public final int getConnectTimeout()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a4bc3a5edf7539d6136a5e24d8596540", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a4bc3a5edf7539d6136a5e24d8596540")).intValue();
        else
            return myTrackedConnection.getConnectTimeout();
    }

    public final Object getContent()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "33d33a878600715b01754a7d85be616f", 0x4000000000000000L))
            return (Object)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "33d33a878600715b01754a7d85be616f");
        else
            return myTrackedConnection.getContent();
    }

    public final Object getContent(Class aclass[])
        throws IOException
    {
        Object aobj[] = new Object[1];
        aobj[0] = aclass;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "2d7c993c19725241429eb4540fd17b44", 0x4000000000000000L))
            return (Object)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "2d7c993c19725241429eb4540fd17b44");
        else
            return myTrackedConnection.getContent(aclass);
    }

    public final String getContentEncoding()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "8d6c14980a3d537ff1ade42c5e9de170", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "8d6c14980a3d537ff1ade42c5e9de170");
        else
            return myTrackedConnection.getContentEncoding();
    }

    public final int getContentLength()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "be0ed2c6be3993c7e2b5a913aa6a6cda", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "be0ed2c6be3993c7e2b5a913aa6a6cda")).intValue();
        else
            return myTrackedConnection.getContentLength();
    }

    public final long getContentLengthLong()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "df82f4536f53dcfd6c3616ca1a4b480f", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "df82f4536f53dcfd6c3616ca1a4b480f")).longValue();
        else
            return myTrackedConnection.getContentLengthLong();
    }

    public final String getContentType()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e25a42e4f3d8b8ac0154785ffab21e01", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e25a42e4f3d8b8ac0154785ffab21e01");
        else
            return myTrackedConnection.getContentType();
    }

    public final long getDate()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "1feeae6327495d9d5e4f3b62087d45b4", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "1feeae6327495d9d5e4f3b62087d45b4")).longValue();
        else
            return myTrackedConnection.getDate();
    }

    public final boolean getDefaultUseCaches()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4de4c415be8a46598277be7478865f52", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4de4c415be8a46598277be7478865f52")).booleanValue();
        else
            return myTrackedConnection.getDefaultUseCaches();
    }

    public final boolean getDoInput()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c5e9d23549292bbf9b38ffd3eb811492", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c5e9d23549292bbf9b38ffd3eb811492")).booleanValue();
        else
            return myTrackedConnection.getDoInput();
    }

    public final boolean getDoOutput()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c6459ef8460a80bd0978822499e0b668", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c6459ef8460a80bd0978822499e0b668")).booleanValue();
        else
            return myTrackedConnection.getDoOutput();
    }

    public final InputStream getErrorStream()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "293319ea37168a8f1eb81038dc94af4d", 0x4000000000000000L))
            return (InputStream)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "293319ea37168a8f1eb81038dc94af4d");
        else
            return myTrackedConnection.getErrorStream();
    }

    public final long getExpiration()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b3feca9bb713836b49ef230a6a06f8e5", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b3feca9bb713836b49ef230a6a06f8e5")).longValue();
        else
            return myTrackedConnection.getExpiration();
    }

    public final String getHeaderField(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "98f637218374f956cf1af7612b7512fd", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "98f637218374f956cf1af7612b7512fd");
        else
            return myTrackedConnection.getHeaderField(i);
    }

    public final String getHeaderField(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e9f14bd958cc1afd7453ce8bee076444", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e9f14bd958cc1afd7453ce8bee076444");
        else
            return myTrackedConnection.getHeaderField(s);
    }

    public final long getHeaderFieldDate(String s, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b68ee5a9a92dc4374f5191f284df8d13", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b68ee5a9a92dc4374f5191f284df8d13")).longValue();
        else
            return myTrackedConnection.getHeaderFieldDate(s, l);
    }

    public final int getHeaderFieldInt(String s, int i)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c372c1d8069cb8863ed227d1a4c3d064", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c372c1d8069cb8863ed227d1a4c3d064")).intValue();
        else
            return myTrackedConnection.getHeaderFieldInt(s, i);
    }

    public final String getHeaderFieldKey(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "93a6603c9f90e75be9875c85b15665fd", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "93a6603c9f90e75be9875c85b15665fd");
        else
            return myTrackedConnection.getHeaderFieldKey(i);
    }

    public final long getHeaderFieldLong(String s, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "5285e0f8fcd4d8179ebcfb0285acd690", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "5285e0f8fcd4d8179ebcfb0285acd690")).longValue();
        else
            return myTrackedConnection.getHeaderFieldLong(s, l);
    }

    public final Map getHeaderFields()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "2646b6f9d7c7f6b3080a0ebd40d0066c", 0x4000000000000000L))
            return (Map)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "2646b6f9d7c7f6b3080a0ebd40d0066c");
        else
            return myTrackedConnection.getHeaderFields();
    }

    public final HostnameVerifier getHostnameVerifier()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a94cd77a8007fe0a5ca960bae362c342", 0x4000000000000000L))
            return (HostnameVerifier)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a94cd77a8007fe0a5ca960bae362c342");
        else
            return myWrappedHttps.getHostnameVerifier();
    }

    public final long getIfModifiedSince()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "fb426f052a6da7954a9c688f19b170af", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "fb426f052a6da7954a9c688f19b170af")).longValue();
        else
            return myTrackedConnection.getIfModifiedSince();
    }

    public final InputStream getInputStream()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "0d6fcaeab71c5481356f63700ff5d7e3", 0x4000000000000000L))
            return (InputStream)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "0d6fcaeab71c5481356f63700ff5d7e3");
        else
            return myTrackedConnection.getInputStream();
    }

    public final boolean getInstanceFollowRedirects()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a92282f9bb5a64dad9048c28ddd8634f", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a92282f9bb5a64dad9048c28ddd8634f")).booleanValue();
        else
            return myTrackedConnection.getInstanceFollowRedirects();
    }

    public final long getLastModified()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9df273ad22a203f19b2cba7c540b9995", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9df273ad22a203f19b2cba7c540b9995")).longValue();
        else
            return myTrackedConnection.getLastModified();
    }

    public final Certificate[] getLocalCertificates()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "aaad2ffd88cc623e4a4c35d7c14065c2", 0x4000000000000000L))
            return (Certificate[])PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "aaad2ffd88cc623e4a4c35d7c14065c2");
        else
            return myWrappedHttps.getLocalCertificates();
    }

    public final Principal getLocalPrincipal()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3b2ae41fa8902b0713874bd510d69a48", 0x4000000000000000L))
            return (Principal)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3b2ae41fa8902b0713874bd510d69a48");
        else
            return myWrappedHttps.getLocalPrincipal();
    }

    public final OutputStream getOutputStream()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "dbcb42dad5a6fe69192a4beee291b39d", 0x4000000000000000L))
            return (OutputStream)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "dbcb42dad5a6fe69192a4beee291b39d");
        else
            return myTrackedConnection.getOutputStream();
    }

    public final Principal getPeerPrincipal()
        throws SSLPeerUnverifiedException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3438e1b59fdd1a58f659baa326236a88", 0x4000000000000000L))
            return (Principal)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3438e1b59fdd1a58f659baa326236a88");
        else
            return myWrappedHttps.getPeerPrincipal();
    }

    public final Permission getPermission()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "20e12e7a60e6a1bd6f500fa52e79269e", 0x4000000000000000L))
            return (Permission)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "20e12e7a60e6a1bd6f500fa52e79269e");
        else
            return myTrackedConnection.getPermission();
    }

    public final int getReadTimeout()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "69a12d22410f1879c2df502776a1b741", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "69a12d22410f1879c2df502776a1b741")).intValue();
        else
            return myTrackedConnection.getReadTimeout();
    }

    public final String getRequestMethod()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "29132bb1eca7de3fcd996d73a65a136f", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "29132bb1eca7de3fcd996d73a65a136f");
        else
            return myTrackedConnection.getRequestMethod();
    }

    public final Map getRequestProperties()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "083f54d30e8c62fd5e8d160547963e5c", 0x4000000000000000L))
            return (Map)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "083f54d30e8c62fd5e8d160547963e5c");
        else
            return myTrackedConnection.getRequestProperties();
    }

    public final String getRequestProperty(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "2851618241f440c7bdd185677b6ff87b", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "2851618241f440c7bdd185677b6ff87b");
        else
            return myTrackedConnection.getRequestProperty(s);
    }

    public final int getResponseCode()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e76f7f1744ada0b70c25fa022e3ee208", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e76f7f1744ada0b70c25fa022e3ee208")).intValue();
        else
            return myTrackedConnection.getResponseCode();
    }

    public final String getResponseMessage()
        throws IOException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "7f2b97fe116f57873fa7ee248e90f913", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "7f2b97fe116f57873fa7ee248e90f913");
        else
            return myTrackedConnection.getResponseMessage();
    }

    public final SSLSocketFactory getSSLSocketFactory()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "23d223dc34347d439831957966b36061", 0x4000000000000000L))
            return (SSLSocketFactory)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "23d223dc34347d439831957966b36061");
        else
            return myWrappedHttps.getSSLSocketFactory();
    }

    public final Certificate[] getServerCertificates()
        throws SSLPeerUnverifiedException
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "cb4d1f5c8e398b4c99bf401001285fc8", 0x4000000000000000L))
            return (Certificate[])PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "cb4d1f5c8e398b4c99bf401001285fc8");
        else
            return myWrappedHttps.getServerCertificates();
    }

    public final URL getURL()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "5499faa8e7abe42798ddaba09332e7af", 0x4000000000000000L))
            return (URL)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "5499faa8e7abe42798ddaba09332e7af");
        else
            return myTrackedConnection.getURL();
    }

    public final boolean getUseCaches()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f1a1f3fac7a376a7f4b6cb4926db2baf", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f1a1f3fac7a376a7f4b6cb4926db2baf")).booleanValue();
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "0d58c7845c28c1a6f542dd550aad9778", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "0d58c7845c28c1a6f542dd550aad9778");
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "88b67e15daa541cafb1ccff92035735c", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "88b67e15daa541cafb1ccff92035735c");
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "06c8b568c88378203b437d45e041989d", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "06c8b568c88378203b437d45e041989d");
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b64b2f57f59718f8f807b2632d1ff07c", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b64b2f57f59718f8f807b2632d1ff07c");
            return;
        } else
        {
            myTrackedConnection.setFixedLengthStreamingMode(l);
            return;
        }
    }

    public final void setHostnameVerifier(HostnameVerifier hostnameverifier)
    {
        Object aobj[] = new Object[1];
        aobj[0] = hostnameverifier;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "d98f01b053e4633e4493927ed18c2786", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "d98f01b053e4633e4493927ed18c2786");
            return;
        } else
        {
            myWrappedHttps.setHostnameVerifier(hostnameverifier);
            return;
        }
    }

    public final void setIfModifiedSince(long l)
    {
        Object aobj[] = new Object[1];
        aobj[0] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "fc794c27a24ce0682db8b0c248dee08e", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "fc794c27a24ce0682db8b0c248dee08e");
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "59c263f142d8b356201a43ccc3419607", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "59c263f142d8b356201a43ccc3419607");
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b721b9eee1324c62c223a520e7f9f74c", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b721b9eee1324c62c223a520e7f9f74c");
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3c44ec80fa5d879d91df285ade6f9bb3", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3c44ec80fa5d879d91df285ade6f9bb3");
            return;
        } else
        {
            myTrackedConnection.setRequestProperty(s, s1);
            return;
        }
    }

    public final void setSSLSocketFactory(SSLSocketFactory sslsocketfactory)
    {
        Object aobj[] = new Object[1];
        aobj[0] = sslsocketfactory;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "46facc5d7f80ea5c8192c02dfce8b01b", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "46facc5d7f80ea5c8192c02dfce8b01b");
            return;
        } else
        {
            myWrappedHttps.setSSLSocketFactory(sslsocketfactory);
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
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "60f25be06647aca30202af2f2a370709", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "60f25be06647aca30202af2f2a370709");
        else
            return myTrackedConnection.toString();
    }

    public final boolean usingProxy()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "47db0ebe24171240aaf8c776afd2c5d2", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "47db0ebe24171240aaf8c776afd2c5d2")).booleanValue();
        else
            return myTrackedConnection.usingProxy();
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private final TrackedHttpURLConnection myTrackedConnection;
    private final HttpsURLConnection myWrappedHttps;
}
