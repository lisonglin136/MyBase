package com.lsl.mybase.model.base;

import android.os.Build;

import com.lsl.mybase.utils.DeviceHelper;
import com.lsl.mybase.utils.PhoneUtil;
import com.lsl.mybase.utils.SignatureUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * author:Created by lsl on 2017/5/2 14:36.
 * description:请求头封装
 */

public class RequestHead {

    //// TODO: 2017/5/2 随机字符生成方法   token   channelId  暂时写死

    /**
     * 请求头获取
     * @param bean 参与签名的body中的参数bean
     * @return  HeadBean
     */
    public  static <T>HeadBean getHeadBean(T bean) {
        HeadBean headBean = new HeadBean();
        headBean.setDeviceNo(DeviceHelper.getDeviceBindUniqueId());//设备号
        headBean.setDeviceType("1");//设备类型
        headBean.setOs(Build.VERSION.SDK_INT + "");//设备操作系统版本
        headBean.setAppVersion(PhoneUtil.getVersionName());//APP版本
        headBean.setInstallTime(DeviceHelper.getAppInstallTime());//安装时间戳
        headBean.setEncoding("utf-8");//字符集编码
        headBean.setFormat("json");//参数格式
        headBean.setTime(System.currentTimeMillis() + "");//请求时间戳
        headBean.setDeviceToken("asdfsdafdsafdsa");//设备有效token
        headBean.setInstallChannelId("1001");
        headBean.setRequestSesstionId("");
        headBean.setNonceStr("abcde");
        headBean.setClientIp(PhoneUtil.getLocalIpAddress());//客户端ip
        Map<String, Object> map = beanToMap(bean);
        map.put("nonceStr", "abcde");
        String sign = SignatureUtils.sign(map, "MD5", "3A079234067D4597AB9A431CB2D0E1F2", true);
        headBean.setSign(sign);
        return headBean;
    }


    /**
     * 将简单（只包含存在基本类型及其封装类和string）bean类型转换为map，此方法只做第一层的处理，即：如果param中有符合类型的对象，只取出对象
     * @param bean
     * @return
     */
    public static <T> Map<String,Object> beanToMap(T bean){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //判断是否为空bean，如果是直接返回
        if(bean==null){
            return resultMap;
        }
        //获取类的class
        Class cls = bean.getClass();

        Field[] fields = cls.getDeclaredFields();//获取所有字段
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();//获取所有字段名称
            Object filedValue=null;
            try {
                int typeInt= fields[i].getModifiers();//获取字段的类型
                //获取字段的类型申明表，8静态，2私有，16final  =26，类型26为静态常量，不做处理如最终serialVersionUID
                if(typeInt!=26){
                    fields[i].setAccessible(true);//设置访问权限
                    filedValue = fields[i].get(bean);//获取所有字段的值
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (filedValue != null && !"".equals(filedValue))
            resultMap.put(fieldName, filedValue);
        }

        return resultMap;

    }


}
