// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic;

import com.meituan.metrics.util.*;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.util.Map;

public class TrafficRecord extends BasicTrafficUnit
{

    public TrafficRecord(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "fd02156388fa6df8c67c90add234b281", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "fd02156388fa6df8c67c90add234b281");
            return;
        } else
        {
            startTime = TimeUtil.currentTimeMillis();
            type = -1;
            url = s;
            date = TimeUtil.currentDate();
            txBytes = txBytes + (long)NetUtils.getStringByteSize(s);
            return;
        }
    }

    public long getDuration()
    {
        return duration;
    }

    public long getRequestBodySize()
    {
        return requestBodySize;
    }

    public long getRequestHeaderSize()
    {
        return requestHeaderSize;
    }

    public Map getRequestHeaders()
    {
        return requestHeaders;
    }

    public long getResponseBodySize()
    {
        return responseBodySize;
    }

    public int getResponseCode()
    {
        return responseCode;
    }

    public long getResponseHeaderSize()
    {
        return responseHeaderSize;
    }

    public Map getResponseHeaders()
    {
        return responseHeaders;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public int getType()
    {
        return type;
    }

    public String getUrl()
    {
        return url;
    }

    public void setRequestBodySize(long l)
    {
        Object aobj[] = new Object[1];
        aobj[0] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9e65b9292eb9807e93387c9839f03387", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9e65b9292eb9807e93387c9839f03387");
            return;
        } else
        {
            requestBodySize = l;
            txBytes = txBytes + l;
            return;
        }
    }

    public void setRequestHeaders(String s, Map map)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = map;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e2b93b3db4b56ecc9a5837ede4042112", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e2b93b3db4b56ecc9a5837ede4042112");
            return;
        } else
        {
            requestHeaders = map;
            requestHeaderSize = NetUtils.getStringByteSize(s) + NetUtils.getHeaderByteSize(map);
            txBytes = txBytes + requestHeaderSize;
            return;
        }
    }

    public void setResponseBodySize(long l)
    {
        Object aobj[] = new Object[1];
        aobj[0] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f28631587d6beb1b7e49f4bd2f5d27dc", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f28631587d6beb1b7e49f4bd2f5d27dc");
            return;
        } else
        {
            responseBodySize = l;
            rxBytes = rxBytes + l;
            return;
        }
    }

    public void setResponseCode(int i)
    {
        responseCode = i;
    }

    public void setResponseHeaders(String s, Map map)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = map;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "8b2a5788ab3ab40300a6a00fa4bf65fb", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "8b2a5788ab3ab40300a6a00fa4bf65fb");
            return;
        } else
        {
            responseHeaders = map;
            responseHeaderSize = NetUtils.getStringByteSize(s) + NetUtils.getHeaderByteSize(map) + 2;
            rxBytes = rxBytes + responseHeaderSize;
            return;
        }
    }

    public String toString()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9799b67acada94771220c7f5043869f1", 0x4000000000000000L))
        {
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9799b67acada94771220c7f5043869f1");
        } else
        {
            StringBuilder stringbuilder = new StringBuilder("TrafficRecord{type:");
            stringbuilder.append(type);
            stringbuilder.append(", tx:");
            stringbuilder.append(txBytes);
            stringbuilder.append(" bytes, rx:");
            stringbuilder.append(rxBytes);
            stringbuilder.append("}");
            return stringbuilder.toString();
        }
    }

    private static final int RESPONSE_STATUS_STOCK_SIZE = 2;
    public static ChangeQuickRedirect changeQuickRedirect;
    public final String date;
    public long duration;
    public long requestBodySize;
    public long requestHeaderSize;
    public Map requestHeaders;
    public long responseBodySize;
    private int responseCode;
    public long responseHeaderSize;
    public Map responseHeaders;
    public final long startTime;
    public int type;
    public final String url;
}
