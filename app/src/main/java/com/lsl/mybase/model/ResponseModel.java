package com.lsl.mybase.model;

/**
 * author:Created by lsl on 2017/5/17 15:37.
 * description:请求结果的封装类
 */

public class ResponseModel<T> {


    /**
     * responseHead : {"code":200,"message":"OK","encoding":"UTF-8","format":"json",
     * "time":1493101606,"nonceStr":"T0oB7z5OTceHSSw1","sign":"6ecf7803039310ffe486ee52a27885bd"}
     * responseBody : {"code":1000,"message":"OK","data":null}
     */

    private ResponseHeadBean responseHead;
    private ResponseBodyBean responseBody;

    public ResponseHeadBean getResponseHead() {
        return responseHead;
    }

    public void setResponseHead(ResponseHeadBean responseHead) {
        this.responseHead = responseHead;
    }

    public ResponseBodyBean getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBodyBean responseBody) {
        this.responseBody = responseBody;
    }

    public  class ResponseHeadBean {
        /**
         * code : 200
         * message : OK
         * encoding : UTF-8
         * format : json
         * time : 1493101606
         * nonceStr : T0oB7z5OTceHSSw1
         * sign : 6ecf7803039310ffe486ee52a27885bd
         */

        private int code;
        private String message;
        private String encoding;
        private String format;
        private int time;
        private String nonceStr;
        private String sign;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
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

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
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

        @Override
        public String toString() {
            return "ResponseHeadBean{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    ", encoding='" + encoding + '\'' +
                    ", format='" + format + '\'' +
                    ", time=" + time +
                    ", nonceStr='" + nonceStr + '\'' +
                    ", sign='" + sign + '\'' +
                    '}';
        }
    }

    public  class ResponseBodyBean {
        /**
         * code : 1000
         * message : OK
         * data : null
         */

        private int code;
        private String message;
        private T data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "ResponseBodyBean{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "responseHead=" + responseHead +
                ", responseBody=" + responseBody +
                '}';
    }
}
