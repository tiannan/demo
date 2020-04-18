// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic.apache;

import com.meituan.metrics.traffic.*;
import com.meituan.metrics.traffic.reflection.ReflectWrapper;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.io.*;
import java.net.URI;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.protocol.HttpContext;

public class ApacheNetworkInterceptor
    implements ReflectWrapper, HttpRequestInterceptor, HttpResponseInterceptor
{
    static class TrackedHttpRequestEntity extends HttpEntityWrapper
    {

        public void writeTo(OutputStream outputstream)
            throws IOException
        {
            Object aobj[] = new Object[1];
            aobj[0] = outputstream;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "7ce6f551f7d5901788c1690e31107bf8", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "7ce6f551f7d5901788c1690e31107bf8");
                return;
            } else
            {
                super.writeTo(tracker.trackRequestBody(outputstream));
                return;
            }
        }

        public static ChangeQuickRedirect changeQuickRedirect;
        private final HttpConnectionTracker tracker;

        public TrackedHttpRequestEntity(HttpEntity httpentity, HttpConnectionTracker httpconnectiontracker)
        {
            super(httpentity);
            Object aobj[] = new Object[2];
            aobj[0] = httpentity;
            aobj[1] = httpconnectiontracker;
            httpentity = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, httpentity, false, "7cca8d4815a9a6f389034cecf1aeecb7", 0x6000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, httpentity, false, "7cca8d4815a9a6f389034cecf1aeecb7");
                return;
            } else
            {
                tracker = httpconnectiontracker;
                return;
            }
        }
    }

    public class TrackedHttpResponseEntity extends HttpEntityWrapper
    {

        public InputStream getContent()
            throws IOException
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c61cb7c4114fb2391451f9960f58bafa", 0x4000000000000000L))
                return (InputStream)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c61cb7c4114fb2391451f9960f58bafa");
            if(content == null)
                content = tracker.trackResponseBody(super.getContent());
            return content;
        }

        public static ChangeQuickRedirect changeQuickRedirect;
        private InputStream content;
        public final ApacheNetworkInterceptor this$0;
        private final HttpConnectionTracker tracker;

        public TrackedHttpResponseEntity(HttpEntity httpentity, HttpConnectionTracker httpconnectiontracker)
        {
            this$0 = ApacheNetworkInterceptor.this;
            super(httpentity);
            Object aobj[] = new Object[3];
            aobj[0] = ApacheNetworkInterceptor.this;
            aobj[1] = httpentity;
            aobj[2] = httpconnectiontracker;
            apachenetworkinterceptor = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, ApacheNetworkInterceptor.this, false, "39569b1f24acc8f69de634ac7487343c", 0x6000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, ApacheNetworkInterceptor.this, false, "39569b1f24acc8f69de634ac7487343c");
                return;
            } else
            {
                tracker = httpconnectiontracker;
                return;
            }
        }
    }


    public ApacheNetworkInterceptor()
    {
    }

    private Map toMultimap(Header aheader[])
    {
        Object aobj[] = new Object[1];
        int i = 0;
        aobj[0] = aheader;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "0eeb5159966b401ae2c73f6b2316acf7", 0x4000000000000000L))
            return (Map)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "0eeb5159966b401ae2c73f6b2316acf7");
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        for(int j = aheader.length; i < j; i++)
        {
            Header header = aheader[i];
            Object obj = (List)linkedhashmap.get(header.getName());
            if(obj == null)
            {
                obj = new ArrayList();
                ((List) (obj)).add(header.getValue());
                linkedhashmap.put(header.getName(), obj);
            } else
            {
                ((List) (obj)).add(header.getValue());
            }
        }

        java.util.Map.Entry entry;
        for(aheader = linkedhashmap.entrySet().iterator(); aheader.hasNext(); entry.setValue(Collections.unmodifiableList((List)entry.getValue())))
            entry = (java.util.Map.Entry)aheader.next();

        return linkedhashmap;
    }

    public void onWrapper(Object obj)
    {
        Object aobj[] = new Object[1];
        aobj[0] = obj;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "8a716534a84af572fa16020a5a6f5813", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "8a716534a84af572fa16020a5a6f5813");
            return;
        }
        if(obj instanceof DefaultHttpClient)
        {
            obj = (DefaultHttpClient)obj;
            ((DefaultHttpClient) (obj)).addRequestInterceptor(this);
            ((DefaultHttpClient) (obj)).addResponseInterceptor(this);
        }
    }

    public void process(HttpRequest httprequest, HttpContext httpcontext)
        throws HttpException, IOException
    {
        Object aobj[] = new Object[2];
        aobj[0] = httprequest;
        aobj[1] = httpcontext;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9ed705dad4d91805c039d3bf3091ac9b", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9ed705dad4d91805c039d3bf3091ac9b");
            return;
        }
        if(httprequest instanceof HttpUriRequest)
        {
            Object obj1 = (HttpUriRequest)httprequest;
            Object obj = obj1;
            if(obj1 instanceof RequestWrapper)
            {
                RequestWrapper requestwrapper = (RequestWrapper)obj1;
                obj = obj1;
                if(requestwrapper.getOriginal() instanceof HttpRequestBase)
                    obj = (HttpRequestBase)requestwrapper.getOriginal();
            }
            obj1 = HttpTracker.trackConnection(((HttpUriRequest) (obj)).getURI().toASCIIString(), MetricsTrafficManager.getInstance());
            ((HttpConnectionTracker) (obj1)).trackRequest(((HttpUriRequest) (obj)).getMethod(), toMultimap(((HttpUriRequest) (obj)).getAllHeaders()));
            if(httprequest instanceof HttpEntityEnclosingRequest)
            {
                httprequest = (HttpEntityEnclosingRequest)httprequest;
                if(httprequest.getEntity() != null)
                    httprequest.setEntity(new TrackedHttpRequestEntity(httprequest.getEntity(), ((HttpConnectionTracker) (obj1))));
            }
            httpcontext.setAttribute("x-metrics-context-attr-tracker", obj1);
            return;
        } else
        {
            return;
        }
    }

    public void process(HttpResponse httpresponse, HttpContext httpcontext)
    {
        Object aobj[] = new Object[2];
        aobj[0] = httpresponse;
        aobj[1] = httpcontext;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4b45c7fc0d7efc7de09207387ef2ab21", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4b45c7fc0d7efc7de09207387ef2ab21");
            return;
        }
        httpcontext = ((HttpContext) (httpcontext.getAttribute("x-metrics-context-attr-tracker")));
        if(httpcontext instanceof HttpConnectionTracker)
        {
            httpcontext = (HttpConnectionTracker)httpcontext;
            httpcontext.trackResponse(httpresponse.getStatusLine().getStatusCode(), httpresponse.getStatusLine().toString(), toMultimap(httpresponse.getAllHeaders()));
            if(httpresponse.getEntity() != null)
            {
                httpresponse.setEntity(new TrackedHttpResponseEntity(httpresponse.getEntity(), httpcontext));
                return;
            }
            httpcontext.reportResponseBody(0L);
        }
    }

    private static final String METRICS_CONTEXT_ATTRIBUTE_KEY = "x-metrics-context-attr-tracker";
    public static ChangeQuickRedirect changeQuickRedirect;
}
