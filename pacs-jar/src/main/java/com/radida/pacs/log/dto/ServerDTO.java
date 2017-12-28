package com.radida.pacs.log.dto;

import java.util.HashMap;
import java.util.Set;

public class ServerDTO extends HashMap<String, Integer> {

    public ServerDTO() {
        this.put("wmi_maxprocess", 10);
        this.put("wmi_minprocess", 1);
        this.put("wmi_thread", 10);
        this.put("syslog_thread", 10);
        this.put("summary_thread", 10);
        this.put("summary_maxprocess", 10);
        this.put("summary_minprocess", 10);
        this.put("server_port", 5501);
        this.put("cache_port", 5500);
        this.put("udp_port", 515);
    }

    @Override
    public Integer get(Object key) {
        if (super.get(key) == null) {
            return 2;
        }
        return super.get(key);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        Set<String> keys = keySet();
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            sb.append("\r\n").append(key.replaceAll("_", ".")).append("=")
                    .append(this.get(key));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ServerDTO());
    }

    public Integer getWmi_maxprocess() {
        return this.get("wmi_maxprocess");
    }

    public void setWmi_maxprocess(Integer wmi_maxprocess) {
        this.put("wmi_maxprocess", wmi_maxprocess);
    }

    public Integer getWmi_minprocess() {
        return this.get("wmi_minprocess");
    }

    public void setWmi_minprocess(Integer wmi_minprocess) {
        this.put("wmi_minprocess", wmi_minprocess);
    }

    public Integer getWmi_thread() {
        return this.get("wmi_thread");
    }

    public void setWmi_thread(Integer wmi_thread) {
        this.put("wmi_thread", wmi_thread);
    }

    public Integer getSyslog_thread() {
        return this.get("syslog_thread");
    }

    public void setSyslog_thread(Integer syslog_thread) {
        this.put("syslog_thread", syslog_thread);
    }

    public Integer getSummary_thread() {
        return this.get("summary_thread");
    }

    public void setSummary_thread(Integer summary_thread) {
        this.put("summary_thread", summary_thread);
    }

    public Integer getSummary_maxprocess() {
        return this.get("summary_maxprocess");
    }

    public void setSummary_maxprocess(Integer summary_maxprocess) {
        this.put("summary_maxprocess", summary_maxprocess);
    }

    public Integer getSummary_minprocess() {
        return this.get("summary_minprocess");
    }

    public void setSummary_minprocess(Integer summary_minprocess) {
        this.put("summary_minprocess", summary_minprocess);
    }

    public Integer getServer_port() {
        return this.get("server_port");
    }

    public void setServer_port(Integer server_port) {
        this.put("server_port", server_port);
    }

    public Integer getCache_port() {
        return this.get("cache_port");
    }

    public void setCache_port(Integer cache_port) {
        this.put("cache_port", cache_port);
    }
}
