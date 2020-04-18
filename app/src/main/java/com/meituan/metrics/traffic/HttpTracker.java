// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic;

import com.meituan.metrics.util.ByteBatcher;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.io.*;
import java.util.*;

// Referenced classes of package com.meituan.metrics.traffic:
//            HttpConnectionTracker, MetricsTrafficManager

public final class HttpTracker
{
    static final class Connection
        implements HttpConnectionTracker
    {

        private int nextId()
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "06abdfe089371a89f61fd93494081e4a", 0x4000000000000000L))
                return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "06abdfe089371a89f61fd93494081e4a")).intValue();
            else
                return trafficManager.getNextRequestId();
        }

        private void onDisconnect(int i)
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "43261f33991ed051abdb9b1975dcf3d4", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "43261f33991ed051abdb9b1975dcf3d4");
                return;
            }
            if(outputTracker != null && outputTracker.needReport())
                outputTracker.reportBatcher();
        }

        private void onError(int i, String s)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = s;
            s = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, s, false, "d27d6b93f843e1e04daa029e8401ef06", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, s, false, "d27d6b93f843e1e04daa029e8401ef06");
                return;
            }
            if(outputTracker != null && outputTracker.needReport())
                outputTracker.reportBatcher();
            trafficManager.onRequestFailed(i);
        }

        private void onPreConnect(int i, String s)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = s;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c8ca2ae549b3460225051e600972a42d", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c8ca2ae549b3460225051e600972a42d");
                return;
            } else
            {
                trafficManager.onPreRequest(i, s);
                return;
            }
        }

        private void onRequest(int i, String s, Map map)
        {
            Object aobj[] = new Object[3];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = s;
            aobj[2] = map;
            Object obj1 = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, ((ChangeQuickRedirect) (obj1)), false, "c49e09e35af378c399baa08a9dff0f64", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, ((ChangeQuickRedirect) (obj1)), false, "c49e09e35af378c399baa08a9dff0f64");
                return;
            }
            Object obj = System.getProperty("http.keepAlive", "");
            obj1 = new HashMap();
            if(((String) (obj)).length() > 0)
                ((Map) (obj1)).put("Connection", Collections.singletonList(obj));
            obj = System.getProperty("http.agent", "");
            if(((String) (obj)).length() > 0)
                ((Map) (obj1)).put("User-Agent", Collections.singletonList(obj));
            obj = map;
            if(((Map) (obj1)).size() > 0)
            {
                ((Map) (obj1)).putAll(map);
                obj = obj1;
            }
            trafficManager.onRequest(i, s, ((Map) (obj)));
        }

        private void onRequestBody(int i)
        {
        }

        private void onResponse(int i, int j, String s, Map map)
        {
            Object aobj[] = new Object[4];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = Integer.valueOf(j);
            aobj[2] = s;
            aobj[3] = map;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3dfc0ca1ce4aa1ae889b6cdf26d3400d", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3dfc0ca1ce4aa1ae889b6cdf26d3400d");
                return;
            } else
            {
                trafficManager.onResponse(i, j, s, map);
                return;
            }
        }

        private void onResponseBody(int i)
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e10154b029f47fa5e9b4931b957117cf", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e10154b029f47fa5e9b4931b957117cf");
                return;
            }
            if(outputTracker != null && outputTracker.needReport())
                outputTracker.reportBatcher();
        }

        public final void disconnect()
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e5d9cbafe28e9f435aee3d1a930fd264", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e5d9cbafe28e9f435aee3d1a930fd264");
                return;
            } else
            {
                onDisconnect(myId);
                return;
            }
        }

        public final void error(String s)
        {
            Object aobj[] = new Object[1];
            aobj[0] = s;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a02044e14e3f14096bd0e326732531ef", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a02044e14e3f14096bd0e326732531ef");
                return;
            } else
            {
                onError(myId, s);
                return;
            }
        }

        public final void reportRequestBody(long l)
        {
            Object aobj[] = new Object[1];
            aobj[0] = new Long(l);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9117a8c739654b8410e1d6cb0e7b391f", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9117a8c739654b8410e1d6cb0e7b391f");
                return;
            } else
            {
                trafficManager.onPostRequest(myId, l);
                return;
            }
        }

        public final void reportResponseBody(long l)
        {
            Object aobj[] = new Object[1];
            aobj[0] = new Long(l);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4c0a0bfdea7674d7fb321c8b22d35bbe", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4c0a0bfdea7674d7fb321c8b22d35bbe");
                return;
            } else
            {
                trafficManager.onResponseBody(myId, l);
                trafficManager.onRequestFinished(myId);
                return;
            }
        }

        public final void trackRequest(String s, Map map)
        {
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = map;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "0a71940c2882eabe83e3039cd571294e", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "0a71940c2882eabe83e3039cd571294e");
                return;
            } else
            {
                onRequest(myId, s, map);
                return;
            }
        }

        public final OutputStream trackRequestBody(OutputStream outputstream)
        {
            Object aobj[] = new Object[1];
            aobj[0] = outputstream;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a731436b1eb7152b90ac557dcea9aec6", 0x4000000000000000L))
            {
                return (OutputStream)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a731436b1eb7152b90ac557dcea9aec6");
            } else
            {
                onRequestBody(myId);
                outputTracker = new OutputStreamTracker(outputstream, this, trafficManager);
                return outputTracker;
            }
        }

        public final void trackResponse(int i, String s, Map map)
        {
            Object aobj[] = new Object[3];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = s;
            aobj[2] = map;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "18715710b6f3c53be1824959849a56ca", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "18715710b6f3c53be1824959849a56ca");
                return;
            }
            if(outputTracker != null && outputTracker.needReport())
                outputTracker.reportBatcher();
            onResponse(myId, i, s, map);
            if(inputTracker != null && inputTracker.needReport())
                inputTracker.reportBatcher();
        }

        public final InputStream trackResponseBody(InputStream inputstream)
        {
            Object aobj[] = new Object[1];
            aobj[0] = inputstream;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ca23f56738b4ffa37b0e9665efe96a05", 0x4000000000000000L))
            {
                return (InputStream)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ca23f56738b4ffa37b0e9665efe96a05");
            } else
            {
                onResponseBody(myId);
                inputTracker = new InputStreamTracker(inputstream, this, trafficManager);
                return inputTracker;
            }
        }

        private static final String TAG = "TrackerConnection";
        public static ChangeQuickRedirect changeQuickRedirect;
        private InputStreamTracker inputTracker;
        private final int myId;
        private OutputStreamTracker outputTracker;
        private final MetricsTrafficManager trafficManager;


        public Connection(String s, MetricsTrafficManager metricstrafficmanager)
        {
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = metricstrafficmanager;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3a601a4f9bcda44e768c5caaada713e7", 0x6000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3a601a4f9bcda44e768c5caaada713e7");
                return;
            } else
            {
                trafficManager = metricstrafficmanager;
                myId = nextId();
                onPreConnect(myId, s);
                return;
            }
        }

    }

    static final class InputStreamTracker extends InputStream
    {

        private void onClose(int i)
        {
        }

        private void onReadBegin(int i)
        {
        }

        private void reportBytes(int i, int j)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = Integer.valueOf(j);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "58d142c3b00d63d3f7d3dafb37b7e81c", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "58d142c3b00d63d3f7d3dafb37b7e81c");
                return;
            } else
            {
                reported = true;
                trafficManager.onResponseBody(i, j);
                trafficManager.onRequestFinished(i);
                return;
            }
        }

        public final int available()
            throws IOException
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a087c3fc1bc1532735385934963b2304", 0x4000000000000000L))
                return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a087c3fc1bc1532735385934963b2304")).intValue();
            else
                return myWrapped.available();
        }

        public final void close()
            throws IOException
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "fa428c6be42069537803e5239f1658df", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "fa428c6be42069537803e5239f1658df");
                return;
            } else
            {
                myWrapped.close();
                mByteBatcher.finish();
                onClose(myConnectionTracker.myId);
                return;
            }
        }

        public final void mark(int i)
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9a26f80923291c00ec5f3c81465ca742", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9a26f80923291c00ec5f3c81465ca742");
                return;
            } else
            {
                myWrapped.mark(i);
                return;
            }
        }

        public final boolean markSupported()
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "acf82cb3ea2962a7d90c954e4fa38c78", 0x4000000000000000L))
                return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "acf82cb3ea2962a7d90c954e4fa38c78")).booleanValue();
            else
                return myWrapped.markSupported();
        }

        public final boolean needReport()
        {
            boolean flag1 = false;
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3b6a924c544d065796df01cddd08418a", 0x4000000000000000L))
                return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3b6a924c544d065796df01cddd08418a")).booleanValue();
            boolean flag = flag1;
            if(!reported)
            {
                flag = flag1;
                if(mByteBatcher.getLength() > 0)
                    flag = true;
            }
            return flag;
        }

        public final int read()
            throws IOException
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f4ee1ee1b70a0a21f8fb0bbe990f815b", 0x4000000000000000L))
                return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f4ee1ee1b70a0a21f8fb0bbe990f815b")).intValue();
            if(myFirstRead)
            {
                onReadBegin(myConnectionTracker.myId);
                myFirstRead = false;
            }
            int i = myWrapped.read();
            mByteBatcher.addBytes(i);
            return i;
        }

        public final int read(byte abyte0[])
            throws IOException
        {
            Object aobj[] = new Object[1];
            aobj[0] = abyte0;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "d874240983f897e9841f0bc4c2522a2f", 0x4000000000000000L))
                return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "d874240983f897e9841f0bc4c2522a2f")).intValue();
            else
                return read(abyte0, 0, abyte0.length);
        }

        public final int read(byte abyte0[], int i, int j)
            throws IOException
        {
            Object aobj[] = new Object[3];
            aobj[0] = abyte0;
            aobj[1] = Integer.valueOf(i);
            aobj[2] = Integer.valueOf(j);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3077511060596c45fc128a530e2c422f", 0x4000000000000000L))
                return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3077511060596c45fc128a530e2c422f")).intValue();
            if(myFirstRead)
            {
                onReadBegin(myConnectionTracker.myId);
                myFirstRead = false;
            }
            i = myWrapped.read(abyte0, i, j);
            mByteBatcher.addBytes(i);
            return i;
        }

        public final void reportBatcher()
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ccc2537128976837dc8f34cd34b1b00b", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ccc2537128976837dc8f34cd34b1b00b");
                return;
            } else
            {
                mByteBatcher.finish();
                return;
            }
        }

        public final void reset()
            throws IOException
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "973d1fea38533de40f395f4689582840", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "973d1fea38533de40f395f4689582840");
                return;
            } else
            {
                myWrapped.reset();
                return;
            }
        }

        public final long skip(long l)
            throws IOException
        {
            Object aobj[] = new Object[1];
            aobj[0] = new Long(l);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "d70574336c27efbb3d476b5bef6c7035", 0x4000000000000000L))
                return ((Long)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "d70574336c27efbb3d476b5bef6c7035")).longValue();
            if(myFirstRead)
            {
                onReadBegin(myConnectionTracker.myId);
                myFirstRead = false;
            }
            return myWrapped.skip(l);
        }

        private static final String TAG = "InputStreamTracker";
        public static ChangeQuickRedirect changeQuickRedirect;
        private final ByteBatcher mByteBatcher;
        private final Connection myConnectionTracker;
        private boolean myFirstRead;
        private final InputStream myWrapped;
        private boolean reported;
        private final MetricsTrafficManager trafficManager;



        public InputStreamTracker(InputStream inputstream, Connection connection, MetricsTrafficManager metricstrafficmanager)
        {
            Object aobj[] = new Object[3];
            aobj[0] = inputstream;
            aobj[1] = connection;
            aobj[2] = metricstrafficmanager;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "93ba48593f3f2a47106ac8e85405a3c6", 0x6000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "93ba48593f3f2a47106ac8e85405a3c6");
                return;
            } else
            {
                reported = false;
                myFirstRead = true;
                mByteBatcher = new ByteBatcher(new _cls1());
                myWrapped = inputstream;
                myConnectionTracker = connection;
                trafficManager = metricstrafficmanager;
                return;
            }
        }
    }

    static final class OutputStreamTracker extends OutputStream
    {

        private void onClose(int i)
        {
        }

        private void onWriteBegin(int i)
        {
        }

        private void reportBytes(int i, int j)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = Integer.valueOf(j);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c367019f89ac6a03ef91d2875f517db5", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c367019f89ac6a03ef91d2875f517db5");
                return;
            } else
            {
                reported = true;
                trafficManager.onPostRequest(i, j);
                return;
            }
        }

        public final void close()
            throws IOException
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c26ab1df27727901fe949d3691a95960", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c26ab1df27727901fe949d3691a95960");
                return;
            } else
            {
                myWrapped.close();
                mByteBatcher.finish();
                onClose(myConnectionTracker.myId);
                return;
            }
        }

        public final void flush()
            throws IOException
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9d7fb565c2201e415e7bfeccf59cf0e7", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9d7fb565c2201e415e7bfeccf59cf0e7");
                return;
            } else
            {
                myWrapped.flush();
                return;
            }
        }

        public final boolean needReport()
        {
            boolean flag1 = false;
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "83ccb98911bff18cef16a71e99f9d610", 0x4000000000000000L))
                return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "83ccb98911bff18cef16a71e99f9d610")).booleanValue();
            boolean flag = flag1;
            if(!reported)
            {
                flag = flag1;
                if(mByteBatcher.getLength() > 0)
                    flag = true;
            }
            return flag;
        }

        public final void reportBatcher()
        {
            Object aobj[] = new Object[0];
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "367f0534ece066473cb7d0d836201a62", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "367f0534ece066473cb7d0d836201a62");
                return;
            } else
            {
                mByteBatcher.finish();
                return;
            }
        }

        public final void write(int i)
            throws IOException
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4da0ec322825a2825a1cecea376f14c2", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4da0ec322825a2825a1cecea376f14c2");
                return;
            }
            if(myFirstWrite)
            {
                onWriteBegin(myConnectionTracker.myId);
                myFirstWrite = false;
            }
            mByteBatcher.addBytes(1);
            myWrapped.write(i);
        }

        public final void write(byte abyte0[])
            throws IOException
        {
            Object aobj[] = new Object[1];
            aobj[0] = abyte0;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b9febe40a969c395aa154f7325c9b22b", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b9febe40a969c395aa154f7325c9b22b");
                return;
            } else
            {
                write(abyte0, 0, abyte0.length);
                return;
            }
        }

        public final void write(byte abyte0[], int i, int j)
            throws IOException
        {
            Object aobj[] = new Object[3];
            aobj[0] = abyte0;
            aobj[1] = Integer.valueOf(i);
            aobj[2] = Integer.valueOf(j);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "31fe4dd9e1b10508a7b6ead2686e000d", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "31fe4dd9e1b10508a7b6ead2686e000d");
                return;
            }
            if(myFirstWrite)
            {
                onWriteBegin(myConnectionTracker.myId);
                myFirstWrite = false;
            }
            mByteBatcher.addBytes(j);
            myWrapped.write(abyte0, i, j);
        }

        private static final String TAG = "OutputStreamTracker";
        public static ChangeQuickRedirect changeQuickRedirect;
        private final ByteBatcher mByteBatcher;
        private final Connection myConnectionTracker;
        private boolean myFirstWrite;
        private final OutputStream myWrapped;
        private boolean reported;
        private final MetricsTrafficManager trafficManager;



        public OutputStreamTracker(OutputStream outputstream, Connection connection, MetricsTrafficManager metricstrafficmanager)
        {
            Object aobj[] = new Object[3];
            aobj[0] = outputstream;
            aobj[1] = connection;
            aobj[2] = metricstrafficmanager;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "cdfb8c4923fdb3488fc62a8f85aaa7a2", 0x6000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "cdfb8c4923fdb3488fc62a8f85aaa7a2");
                return;
            } else
            {
                myFirstWrite = true;
                reported = false;
                mByteBatcher = new ByteBatcher(new _cls1());
                myWrapped = outputstream;
                myConnectionTracker = connection;
                trafficManager = metricstrafficmanager;
                return;
            }
        }
    }


    public HttpTracker()
    {
    }

    public static HttpConnectionTracker trackConnection(String s, MetricsTrafficManager metricstrafficmanager)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = metricstrafficmanager;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "ba18dfe2995adb3812b07cb63edb4fc9", 0x4000000000000000L))
            return (HttpConnectionTracker)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "ba18dfe2995adb3812b07cb63edb4fc9");
        else
            return new Connection(s, metricstrafficmanager);
    }

    public static ChangeQuickRedirect changeQuickRedirect;

    // Unreferenced inner class com/meituan/metrics/traffic/HttpTracker$InputStreamTracker$1

/* anonymous class */
    public class InputStreamTracker._cls1
        implements com.meituan.metrics.util.ByteBatcher.FlushReceiver
    {

        public void receive(int i)
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "54a2b009ddef64c598f33570e044230d", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "54a2b009ddef64c598f33570e044230d");
                return;
            } else
            {
                reportBytes(myConnectionTracker.myId, i);
                return;
            }
        }

        public static ChangeQuickRedirect changeQuickRedirect;
        public final InputStreamTracker this$0;

            public 
            {
                this$0 = InputStreamTracker.this;
                super();
            }
    }


    // Unreferenced inner class com/meituan/metrics/traffic/HttpTracker$OutputStreamTracker$1

/* anonymous class */
    public class OutputStreamTracker._cls1
        implements com.meituan.metrics.util.ByteBatcher.FlushReceiver
    {

        public void receive(int i)
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ba83c8463c1321517292e394bf5edd0f", 0x4000000000000000L))
            {
                PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ba83c8463c1321517292e394bf5edd0f");
                return;
            } else
            {
                reportBytes(myConnectionTracker.myId, i);
                return;
            }
        }

        public static ChangeQuickRedirect changeQuickRedirect;
        public final OutputStreamTracker this$0;

            public 
            {
                this$0 = OutputStreamTracker.this;
                super();
            }
    }

}
