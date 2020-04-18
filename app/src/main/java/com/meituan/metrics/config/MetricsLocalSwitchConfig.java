package com.meituan.metrics.config;

public class MetricsLocalSwitchConfig
{

    public MetricsLocalSwitchConfig(String s, boolean flag)
    {
    }

    public void commit()
    {
        MetricsLocalSwitchConfigManager.getInstance().updateConfig(this);
    }

    public String getActivitySw()
    {
        return mActivityStr;
    }

    public boolean getCPUSw()
    {
        return mCpuSw;
    }

    public boolean getFPSSw()
    {
        return mFpsSw;
    }

    public boolean getLagSw()
    {
        return mLagSw;
    }

    public boolean getMemSw()
    {
        return mMemSw;
    }

    public boolean getSampleSw()
    {
        return mFpsSw && mCpuSw && mMemSw;
    }

    public MetricsLocalSwitchConfig setAllSw(boolean flag)
    {
        return null;
    }

    public MetricsLocalSwitchConfig setCpuSw(boolean flag)
    {
        mCpuSw = flag;
        return this;
    }

    public MetricsLocalSwitchConfig setFPSSw(boolean flag)
    {
        mFpsSw = flag;
        return this;
    }

    public MetricsLocalSwitchConfig setLagSw(boolean flag)
    {
        mLagSw = flag;
        return this;
    }

    public MetricsLocalSwitchConfig setMemSw(boolean flag)
    {
        mMemSw = flag;
        return this;
    }

    private String mActivityStr;
    private boolean mCpuSw;
    private boolean mFpsSw;
    private boolean mLagSw;
    private boolean mMemSw;
}
