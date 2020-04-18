// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sampler;

import android.os.*;
import com.meituan.metrics.util.LogUtil;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.lang.ref.WeakReference;

public class SamplingThread extends HandlerThread
{
    public static interface SamplingCallback
    {

        public transient abstract void onSamplingEvent(int i, Object aobj[]);
    }


    public SamplingThread(SamplingCallback samplingcallback, long l)
    {
        super("MetricsSamplingThread", 10);
        Object aobj[] = new Object[2];
        aobj[0] = samplingcallback;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "7016c38414c9fb3fe078d407b7bbbe07", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "7016c38414c9fb3fe078d407b7bbbe07");
            return;
        } else
        {
            start();
            handler = new Handler(l) {

                public void handleMessage(Message message)
                {
                    Object aobj1[] = new Object[1];
                    aobj1[0] = message;
                    ChangeQuickRedirect changequickredirect1 = changeQuickRedirect;
                    if(PatchProxy.isSupport(aobj1, this, changequickredirect1, false, "243f0df032a79b683a51e37424e66515", 0x4000000000000000L))
                    {
                        PatchProxy.accessDispatch(aobj1, this, changequickredirect1, false, "243f0df032a79b683a51e37424e66515");
                        return;
                    }
                    message.what;
                    JVM INSTR tableswitch 1 8: default 232
                //                               1 159
                //                               2 92
                //                               3 92
                //                               4 92
                //                               5 92
                //                               6 92
                //                               7 92
                //                               8 92;
                       goto _L1 _L2 _L3 _L3 _L3 _L3 _L3 _L3 _L3
_L3:
                    if(samplingCallback == null || !(message.obj instanceof WeakReference)) goto _L5; else goto _L4
_L4:
                    int i;
                    Object obj;
                    Object obj1;
                    obj = ((WeakReference)message.obj).get();
                    obj1 = samplingCallback;
                    i = message.what;
                    if(!(obj instanceof Object[])) goto _L7; else goto _L6
_L6:
                    obj = ((Object) ((Object[])(Object[])obj));
                      goto _L8
_L2:
                    if(samplingCallback != null)
                        samplingCallback.onSamplingEvent(1, new Object[0]);
                    removeMessages(1);
                    sendEmptyMessageDelayed(1, samplingTimeInterval * 1000L);
                    return;
_L5:
                    return;
_L8:
                    try
                    {
                        ((SamplingCallback) (obj1)).onSamplingEvent(i, ((Object []) (obj)));
                        return;
                    }
                    // Misplaced declaration of an exception variable
                    catch(Object obj)
                    {
                        obj1 = new StringBuilder("exception when handling message ");
                    }
                    ((StringBuilder) (obj1)).append(message);
                    LogUtil.e("SamplingThread", ((StringBuilder) (obj1)).toString(), ((Throwable) (obj)));
                    return;
_L1:
                    return;
_L7:
                    obj = null;
                    if(true) goto _L8; else goto _L9
_L9:
                }

                public static ChangeQuickRedirect changeQuickRedirect;
                public final SamplingThread this$0;
                public final SamplingCallback val$samplingCallback;
                public final long val$samplingTimeInterval;

            public 
            {
                this$0 = SamplingThread.this;
                samplingCallback = samplingcallback;
                samplingTimeInterval = l;
                super(final_looper);
            }
            }
;
            return;
        }
    }

    public Handler getHandler()
    {
        return handler;
    }

    public transient void sendMessage(int i, Object aobj[])
    {
        Object aobj1[] = new Object[2];
        aobj1[0] = Integer.valueOf(i);
        aobj1[1] = ((Object) (aobj));
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj1, this, changequickredirect, false, "00f17781971561938ebc0cceba54e817", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj1, this, changequickredirect, false, "00f17781971561938ebc0cceba54e817");
            return;
        } else
        {
            handler.obtainMessage(i, new WeakReference(((Object) (aobj)))).sendToTarget();
            return;
        }
    }

    public void startSampleOnce()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "15b6cf638a12be7077d51658b8323c51", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "15b6cf638a12be7077d51658b8323c51");
            return;
        } else
        {
            handler.sendEmptyMessage(1);
            return;
        }
    }

    public void startSampleTimer()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c5a7bab5cf0f75363e0014473d8a7f33", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c5a7bab5cf0f75363e0014473d8a7f33");
            return;
        } else
        {
            handler.removeMessages(1);
            handler.sendEmptyMessage(1);
            return;
        }
    }

    public void stopSampleTimer()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "1a93f877b4f48ce5ef9ee16ef313671e", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "1a93f877b4f48ce5ef9ee16ef313671e");
            return;
        } else
        {
            handler.removeMessages(1);
            return;
        }
    }

    public static final int SAMPLING_CUSTOM_SCROLL_START = 5;
    public static final int SAMPLING_CUSTOM_SCROLL_STOP = 6;
    public static final int SAMPLING_FPS = 2;
    public static final int SAMPLING_PAGE_ENTER = 3;
    public static final int SAMPLING_PAGE_EXIT = 4;
    public static final int SAMPLING_PERIODIC = 1;
    public static final int SAMPLING_SET_CUSTOM_SCROLL = 7;
    public static final int SAMPLING_SET_FRAGMENT = 8;
    public static ChangeQuickRedirect changeQuickRedirect;
    private Handler handler;
}
