package com.meituan.metrics.laggy;

import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Printer;
import com.meituan.metrics.config.MetricsLocalSwitchConfigManager;
import com.meituan.metrics.laggy.anr.AnrCallback;
import com.meituan.metrics.laggy.anr.MetricsAnrManager;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager;
import com.meituan.metrics.looper_logging.LooperLoggingManager;
import com.meituan.metrics.util.LogUtil;
import com.meituan.metrics.util.TimeUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ProGuard */
public class LaggyLooperPrinter implements Printer {
    private static final char LOOP_START_TAG = '>';
    public static final int MIN_INTERVAL = 1000;
    private static final int SAMPLE_COUNT = 2;
    private AnrCallback anrCallback;
    private boolean isAnrEnable;
    private boolean isLagReported;
    private volatile boolean isLooperBusy;
    private boolean isMainThread;
    protected long lagThreshold;
    protected LaggyCallback laggyCallback;
    private final List<ThreadStackEntity> laggyCheckedSTs;
    protected final Looper looper;
    protected final Handler sampleHandler;
    protected long sampleInterval;
    private volatile int sampleTimes;
    private final Runnable stacktraceSampleTask;
    private volatile long startTimeMillis;
    public String targetThreadName;
    protected final Thread thread;

    /* compiled from: ProGuard */
    final class AnrTask implements Runnable {
        public final long startTime;

        public AnrTask(long j) {

                this.startTime = j;

        }

        public final void run() {
          if (this.startTime != LaggyLooperPrinter.this.startTimeMillis) {
                LogUtil.d(MetricsAnrManager.TAG, "anrTask startTime != startTimeMillis");
            } else {
                if (LaggyLooperPrinter.this.isLooperBusy && LaggyLooperPrinter.this.isMainThread && LaggyLooperPrinter.this.isAnrEnable && LaggyLooperPrinter.this.anrCallback != null) {
                    LaggyLooperPrinter.this.getStack();
                    System.out.println("LaggyLooperPrinter onAnrEvent");
                    LaggyLooperPrinter.this.anrCallback.onAnrEvent(TimeUtil.currentTimeMillis(), null, new ArrayList(LaggyLooperPrinter.this.laggyCheckedSTs));
                    LaggyLooperPrinter.this.sampleHandler.postDelayed(this, MetricsAnrManager.ANR_THRESHOLD);
                }
            }
        }
    }

    public LaggyLooperPrinter(long j, Looper looper, String str) {

        this.isLooperBusy = false;
        this.startTimeMillis = 0;
        this.laggyCheckedSTs = Collections.synchronizedList(new ArrayList());
        this.stacktraceSampleTask = new Runnable() {

            public void run() {

                if (LaggyLooperPrinter.this.isLooperBusy) {
                    LaggyLooperPrinter.this.getStack();
                    LaggyLooperPrinter.this.sampleTimes = LaggyLooperPrinter.this.sampleTimes + 1;
                    long elapsedRealtime = SystemClock.elapsedRealtime() - LaggyLooperPrinter.this.startTimeMillis;
                    PrintStream printStream = System.out;
                    StringBuilder stringBuilder = new StringBuilder("LaggyLooperPrinter stacktraceSampleTask costs:");
                    stringBuilder.append(elapsedRealtime);
                    printStream.println(stringBuilder.toString());
                    if (LaggyLooperPrinter.this.sampleTimes == 1) {
                        LaggyLooperPrinter.this.isLagReported = false;
                        if (LaggyLooperPrinter.this.isMainThread && LaggyLooperPrinter.this.isAnrEnable) {
                            LaggyLooperPrinter.this.sampleHandler.postDelayed(new AnrTask(LaggyLooperPrinter.this.startTimeMillis), MetricsAnrManager.ANR_THRESHOLD - elapsedRealtime);
                        }
                    }
                    if (!(LaggyLooperPrinter.this.laggyCallback == null || LaggyLooperPrinter.this.isLagReported || elapsedRealtime < LaggyLooperPrinter.this.lagThreshold || LaggyLooperPrinter.this.laggyCheckedSTs.isEmpty())) {
                        LaggyLooperPrinter.this.laggyCallback.onLaggyEvent(elapsedRealtime, LaggyLooperPrinter.this.targetThreadName, new ArrayList(LaggyLooperPrinter.this.laggyCheckedSTs));
                        LaggyLooperPrinter.this.sampleHandler.removeCallbacks(this);
                        LaggyLooperPrinter.this.isLagReported = true;
                    }
                    if (LaggyLooperPrinter.this.isLooperBusy && !LaggyLooperPrinter.this.isLagReported) {
                        LaggyLooperPrinter.this.sampleHandler.postDelayed(this, LaggyLooperPrinter.this.sampleInterval);
                    }
                }
            }
        };
        this.laggyCallback = MetricsLaggyManager.getInstance();
        this.lagThreshold = j;
        this.sampleInterval = Math.max(j / 2, 1000);
        this.sampleHandler = MetricsLaggyManager.getInstance().createLaggyDetectHandler();
        this.thread = looper.getThread();
        this.looper = looper;
        this.targetThreadName = str;
    }

    public static LaggyLooperPrinter newMaiLooperPrinter(boolean z, long j, boolean z2) {
        return  new LaggyLooperPrinter(z, j, z2);
    }

    public LaggyLooperPrinter(boolean z, long j, boolean z2) {
        long j2 = j;
        boolean z3 = z2;

        this.isLooperBusy = false;
        this.startTimeMillis = 0;
        this.laggyCheckedSTs = Collections.synchronizedList(new ArrayList());
        this.stacktraceSampleTask = /* anonymous class already generated */;
        if (z && j2 > 0 && z3) {
            this.laggyCallback = MetricsLaggyManager.getInstance();
            this.lagThreshold = j2;
            this.sampleInterval = Math.max(Math.min(MetricsAnrManager.ANR_THRESHOLD, j2) / 2, 1000);
        } else if (z && j2 > 0) {
            this.laggyCallback = MetricsLaggyManager.getInstance();
            this.lagThreshold = j2;
            this.sampleInterval = Math.max(j2 / 2, 1000);
        } else if (z3) {
            this.sampleInterval = Math.max(2500, 1000);
        }
        this.isAnrEnable = z3;
        this.sampleHandler = MetricsLaggyManager.getInstance().createLaggyDetectHandler();
        this.looper = Looper.getMainLooper();
        this.thread = this.looper.getThread();
        this.isMainThread = true;
        this.targetThreadName = MetricsLaggyManager.MAIN;
    }

    public void setAnrCallback(AnrCallback anrCallback) {
        this.anrCallback = anrCallback;
    }

    public void println(String str) {
        boolean z = true;

        if (!(Debug.isDebuggerConnected() || str == null)) {
            if (str.length() > 0) {
                if (isPrinterEnable()) {
                    if (str.charAt(0) != LOOP_START_TAG) {
                        z = false;
                    }
                    this.isLooperBusy = z;
                    if (z) {
                        this.startTimeMillis = SystemClock.elapsedRealtime();
                        this.laggyCheckedSTs.clear();
                        this.sampleHandler.postDelayed(this.stacktraceSampleTask, this.sampleInterval);
                        return;
                    }
                    this.sampleTimes = 0;
                    this.sampleHandler.removeCallbacks(this.stacktraceSampleTask);
                }
            }
        }
    }

    private boolean isPrinterEnable() {

        String str2 = MetricsActivityLifecycleManager.currentActivity;
        if (this.lagThreshold > 0 && MetricsLocalSwitchConfigManager.getInstance().getLagSw(str2)) {
            return true;
        }
        if (this.isMainThread && this.isAnrEnable) {
            return true;
        }
        return false;
    }

    private void getStack() {

        try {
            if (this.laggyCheckedSTs.size() >= 5) {
                this.laggyCheckedSTs.remove(this.laggyCheckedSTs.size() - 1);
            }
            long currentTimeMillis = TimeUtil.currentTimeMillis();
            StackTraceElement[] stackTrace = this.thread.getStackTrace();
            if (stackTrace == null || stackTrace.length <= 0) {
                System.out.println("LaggyLooperPrinter getStack Error, stackTrace.length<=0");
            } else {
                this.laggyCheckedSTs.add(new ThreadStackEntity(currentTimeMillis, stackTrace));
            }
        } catch (Throwable th) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder("LaggyLooperPrinter getStack Error, clear stack, msg: ");
            stringBuilder.append(th.getMessage());
            printStream.println(stringBuilder.toString());
            this.laggyCheckedSTs.clear();
        }
    }

    public void register() {

            LooperLoggingManager.getInstance().registerLogging(this.looper, this);

    }

    public void unregister() {

            LooperLoggingManager.getInstance().unRegisterLogging(this.looper, this);

    }
}