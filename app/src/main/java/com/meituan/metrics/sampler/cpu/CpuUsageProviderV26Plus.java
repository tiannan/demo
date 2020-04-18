package com.meituan.metrics.sampler.cpu;

import android.os.Process;
import com.meituan.metrics.common.Constants;
import com.sankuai.common.utils.IOUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStreamReader;

/* compiled from: ProGuard */
public class CpuUsageProviderV26Plus implements ICpuUsageProvider {
    private int cpuCounts;

    public CpuUsageProviderV26Plus() {

        this.cpuCounts = 1;
        this.cpuCounts = getCpuCounts();
    }

    public double getCpuUsagePercent() throws Throwable {
        Throwable th;

        Closeable bufferedReader;
        try {
            String readLine;
            Runtime runtime = Runtime.getRuntime();
            StringBuilder stringBuilder = new StringBuilder("top -n 1 -p ");
            stringBuilder.append(Process.myPid());
            bufferedReader = new BufferedReader(new InputStreamReader(runtime.exec(stringBuilder.toString()).getInputStream()));
            StringBuilder stringBuilder2;
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(Process.myPid());
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.close(bufferedReader);
                    throw th;
                }
            } while (!readLine.contains(stringBuilder2.toString()));
            if (readLine == null) {
                IOUtils.close(bufferedReader);
                return -1.0d;
            }
            String[] split = readLine.trim().split("\\s+");
            if (split.length < 12) {
                IOUtils.close(bufferedReader);
                return -1.0d;
            }
            double parseDouble = Double.parseDouble(split[8]) / ((double) this.cpuCounts);
            IOUtils.close(bufferedReader);
            return parseDouble;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            IOUtils.close(bufferedReader);
            throw th;
        }
    }

    private int getCpuCounts() {

        int length = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() {
            public static ChangeQuickRedirect changeQuickRedirect;

            public boolean accept(File file) {
                Object[] objArr = new Object[]{file};
                ChangeQuickRedirect changeQuickRedirect = changeQuickRedirect;
                String str = "244f3cc256922305b8789b71fc6c7405";
                if (PatchProxy.isSupport(objArr, this, changeQuickRedirect, false, str, RobustBitConfig.DEFAULT_VALUE)) {
                    return ((Boolean) PatchProxy.accessDispatch(objArr, this, changeQuickRedirect, false, str)).booleanValue();
                }
                String name = file.getName();
                if (!name.startsWith(Constants.CPU)) {
                    return false;
                }
                int i = 3;
                while (i < name.length()) {
                    if (name.charAt(i) >= '0') {
                        if (name.charAt(i) <= '9') {
                            i++;
                        }
                    }
                    return false;
                }
                return true;
            }
        }).length;
        if (length == 0) {
            length = 1;
        }
        return length;
    }
}