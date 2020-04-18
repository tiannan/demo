package com.meituan.metrics.config;

import java.util.HashMap;

/* compiled from: ProGuard */
public class MetricsLocalSwitchConfigManager {
    private static volatile MetricsLocalSwitchConfigManager instance;
    private boolean localSwCommonVal;
    private HashMap<String, MetricsLocalSwitchConfig> localSwConfigMap;

    public MetricsLocalSwitchConfigManager() {

        this.localSwConfigMap = new HashMap();
        this.localSwCommonVal = true;
    }

    public static MetricsLocalSwitchConfigManager getInstance() {

        if (instance == null) {
            synchronized (MetricsLocalSwitchConfigManager.class) {
                if (instance == null) {
                    instance = new MetricsLocalSwitchConfigManager();
                }
            }
        }
        return instance;
    }

    public void setLocalSwitch(boolean z) {
        this.localSwCommonVal = z;
    }

    public boolean getCommonLocalSw() {
        return this.localSwCommonVal;
    }

    public void updateConfig(MetricsLocalSwitchConfig metricsLocalSwitchConfig) {

            this.localSwConfigMap.put(metricsLocalSwitchConfig.getActivitySw(), metricsLocalSwitchConfig);

    }

    public void removeConfig(MetricsLocalSwitchConfig metricsLocalSwitchConfig) {

            this.localSwConfigMap.remove(metricsLocalSwitchConfig);

    }

    public void removeConfig(String str) {

            this.localSwConfigMap.remove(str);
        }


    public MetricsLocalSwitchConfig getLocalSwitchConfig(String str) {

        if (this.localSwConfigMap.containsKey(str)) {
            return (MetricsLocalSwitchConfig) this.localSwConfigMap.get(str);
        }
        return new MetricsLocalSwitchConfig(str, this.localSwCommonVal);
    }

    public boolean getFPSSw(String str) {

        MetricsLocalSwitchConfig metricsLocalSwitchConfig = (MetricsLocalSwitchConfig) this.localSwConfigMap.get(str);
        if (metricsLocalSwitchConfig != null) {
            return metricsLocalSwitchConfig.getFPSSw();
        }
        return this.localSwCommonVal;
    }

    public boolean getCPUSw(String str) {

        MetricsLocalSwitchConfig metricsLocalSwitchConfig = (MetricsLocalSwitchConfig) this.localSwConfigMap.get(str);
        if (metricsLocalSwitchConfig != null) {
            return metricsLocalSwitchConfig.getCPUSw();
        }
        return this.localSwCommonVal;
    }

    public boolean getMemSw(String str) {

        MetricsLocalSwitchConfig metricsLocalSwitchConfig = (MetricsLocalSwitchConfig) this.localSwConfigMap.get(str);
        if (metricsLocalSwitchConfig != null) {
            return metricsLocalSwitchConfig.getMemSw();
        }
        return this.localSwCommonVal;
    }

    public boolean getLagSw(String str) {

        MetricsLocalSwitchConfig metricsLocalSwitchConfig = (MetricsLocalSwitchConfig) this.localSwConfigMap.get(str);
        if (metricsLocalSwitchConfig != null) {
            return metricsLocalSwitchConfig.getLagSw();
        }
        return this.localSwCommonVal;
    }

    public boolean getSampleSw(String str) {

        MetricsLocalSwitchConfig metricsLocalSwitchConfig = (MetricsLocalSwitchConfig) this.localSwConfigMap.get(str);
        if (metricsLocalSwitchConfig == null) {
            return this.localSwCommonVal;
        }
        return metricsLocalSwitchConfig.getMemSw() & (metricsLocalSwitchConfig.getFPSSw() & metricsLocalSwitchConfig.getCPUSw());
    }
}