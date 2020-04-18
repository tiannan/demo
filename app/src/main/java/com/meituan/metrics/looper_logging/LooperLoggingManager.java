package com.meituan.metrics.looper_logging;

import android.os.Build.VERSION;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.MessageQueue.IdleHandler;
import android.util.Printer;
import com.meituan.metrics.util.LogUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: ProGuard */
public class LooperLoggingManager implements IdleHandler {
    private static final String TAG = "LooperLoggingManager";
    private static volatile LooperLoggingManager loggingManager;
    private Map<Looper, InnerPrinter> looperLoggingMap;

    static class InnerPrinter implements Printer {
        private Set<Printer> realPrinters;

        public void addPrinter(Printer printer) {

                this.realPrinters.add(printer);

        }

        public void removePrinter(Printer printer) {

                this.realPrinters.remove(printer);

        }

        public InnerPrinter() {

                this.realPrinters = new CopyOnWriteArraySet();

        }

        public int getPrinterSize() {

            return this.realPrinters.size();
        }

        public void println(String str) {

            for (Printer printer : this.realPrinters) {
                if (printer != null) {
                    printer.println(str);
                }
            }
        }
    }

    public LooperLoggingManager() {

        this.looperLoggingMap = new HashMap();
        if (VERSION.SDK_INT >= 23) {
            Looper.getMainLooper().getQueue().addIdleHandler(this);
            return;
        }
        MessageQueue messageQueue = (MessageQueue) reflectObject(Looper.getMainLooper(), "mQueue");
        if (messageQueue != null) {
            messageQueue.addIdleHandler(this);
        }
    }

    public static LooperLoggingManager getInstance() {

        if (loggingManager == null) {
            synchronized (LooperLoggingManager.class) {
                if (loggingManager == null) {
                    loggingManager = new LooperLoggingManager();
                }
            }
        }
        return loggingManager;
    }

    public void registerMainLooperLogging(Printer printer) {

            registerLogging(Looper.getMainLooper(), printer);

    }

    public void unRegisterMainLooperLogging(Printer printer) {

            unRegisterLogging(Looper.getMainLooper(), printer);

    }

    public void registerLogging(Looper looper, Printer printer) {

        if (looper != null) {
            if (printer != null) {
                InnerPrinter innerPrinter = (InnerPrinter) this.looperLoggingMap.get(looper);
                if (innerPrinter == null) {
                    innerPrinter = new InnerPrinter();
                    looper.setMessageLogging(innerPrinter);
                    this.looperLoggingMap.put(looper, innerPrinter);
                }
                innerPrinter.addPrinter(printer);
            }
        }
    }

    public void forceMainLooperSetMessageLogging() {

            forceSetMessageLogging(Looper.getMainLooper());

    }

    public void forceSetMessageLogging(Looper looper) {
        if (looper != null) {
            InnerPrinter innerPrinter = (InnerPrinter) this.looperLoggingMap.get(looper);
            if (innerPrinter != null) {
                looper.setMessageLogging(innerPrinter);
            }
        }
    }

    public void unRegisterLogging(Looper looper, Printer printer) {

        if (looper != null) {
            if (printer != null) {
                InnerPrinter innerPrinter = (InnerPrinter) this.looperLoggingMap.get(looper);
                if (innerPrinter != null) {
                    innerPrinter.removePrinter(printer);
                    if (innerPrinter.getPrinterSize() <= 0) {
                        looper.setMessageLogging(null);
                        this.looperLoggingMap.remove(looper);
                    }
                }
            }
        }
    }

    public boolean queueIdle() {

        resetPrinter();
        return true;
    }

    private void resetPrinter() {

        for (Entry entry : this.looperLoggingMap.entrySet()) {
            Looper looper = (Looper) entry.getKey();
            Printer printer = (Printer) entry.getValue();
            if (!(looper == null || printer == null)) {
                Printer printer2 = (Printer) reflectObject(looper, "mLogging");
                if (printer2 != printer) {
                    LogUtil.d(TAG, "[resetPrinter] maybe looper printer was replace other!");
                    ((InnerPrinter) printer).addPrinter(printer2);
                    looper.setMessageLogging(printer);
                }
            }
        }
    }

    private static <T> T reflectObject(Object obj, String str) {

        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}