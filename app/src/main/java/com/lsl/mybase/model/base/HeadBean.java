package com.lsl.mybase.model.base;

/**
 * author:Created by lsl on 2017/4/28 18:05.
 * description:请求head模型
 */

public class HeadBean {

    private String deviceNo;//设备号
    private String deviceType;//设备类型 1=ios  /  2 = android / 3 = windows
    private String deviceToken;//设备有效token
    private String os; //设备操作系统版本
    private String appVersion; //APP版本
    private String installChannelId;//安装渠道ID，比如：百度应用，应用宝之类的应用市场,
    private long installTime;//安装时间戳
    private String encoding; //字符集编码
    private String format;//参数格式
    private String time;//请求时间戳
    private String clientIp;//客户端IP
    private String requestSesstionId;//请求sessionId
    private String nonceStr;//随机字符
    private String sign;//加密后密文值，加密规则参考报文签名


    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getInstallChannelId() {
        return installChannelId;
    }

    public void setInstallChannelId(String installChannelId) {
        this.installChannelId = installChannelId;
    }

    public long getInstallTime() {
        return installTime;
    }

    public void setInstallTime(long installTime) {
        this.installTime = installTime;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getRequestSesstionId() {
        return requestSesstionId;
    }

    public void setRequestSesstionId(String requestSesstionId) {
        this.requestSesstionId = requestSesstionId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
