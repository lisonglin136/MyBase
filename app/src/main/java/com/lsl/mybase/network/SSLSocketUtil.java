package com.lsl.mybase.network;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;


public class SSLSocketUtil {

    private Context context;
    private String keyStoreName; //证书名
    private String keyPwd; //证书密码
    private String keyType; //证书类型

    public SSLSocketUtil(Builder builder){
        this.context = builder.context;
        this.keyStoreName = builder.keyStoreName;
        this.keyPwd = builder.keyPwd;
        this.keyType = builder.keyType;
    }

    public Context getContext(){
        return context;
    }

    public String getKeyStoreName(){
        return keyStoreName;
    }

    public String getKeyPwd(){
        return keyPwd;
    }

    public String getKeyType(){
        return keyType;
    }

    public SSLSocketFactory getSocketFactory(){
        try{
            AssetManager am = context.getAssets();
            InputStream is = am.open("fm.p12");
            String password = "zhuihu";
            //CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            //Certificate certificate = certificateFactory.generateCertificate(is);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(is, password.toCharArray());
            //keyStore.setCertificateEntry("trust", certificateFactory.generateCertificate(is));

            TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        }catch (Exception ex){
            Log.e("SSL", ex.getMessage());
            return null;
        }
    }

    public static class Builder{

        private Context context;
        private String keyStoreName; //证书名
        private String keyPwd; //证书密码
        private String keyType; //证书类型

        public Builder(){
            this.context = null;
            this.keyStoreName = "";
            this.keyPwd = "";
            this.keyType = "PKCS12";
        }

        public Builder context(Context context){
            this.context = context;
            return this;
        }

        public Builder keyStoreName(String keyStoreName){
            this.keyStoreName = keyStoreName;
            return this;
        }

        public Builder keyPwd(String keyPwd){
            this.keyPwd = keyPwd;
            return this;
        }

        public Builder keyType(String keyType){
            this.keyType = keyType;
            return this;
        }

        public SSLSocketUtil build(){
            return new SSLSocketUtil(this);
        }
    }
}
