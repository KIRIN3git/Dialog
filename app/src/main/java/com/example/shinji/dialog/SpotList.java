package com.example.shinji.dialog;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * Created by shinji on 2016/06/29.
 */
public class SpotList {

    private Bitmap icon;
    private String ssid;
    private Integer priority;
    private Boolean enable;

    SpotList(Bitmap icon, String ssid, Integer priority, Boolean enable){
        this.icon = icon;
        this.ssid = ssid;
        this.priority = priority;
        this.enable = enable;
    }

    @NonNull
    public Bitmap getIcon() {
        return icon;
    }

    @NonNull
    public String getSsid() {
        return this.ssid;
    }

    @NonNull
    public Integer getPriority() {
        return this.priority;
    }

    @NonNull
    public String getPriorityText() {
        return this.priority.toString();
    }

    @NonNull
    public Boolean getEnable() {
        return this.enable;
    }
}