package com.lsl.mybase.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YH
 * 封装的是使用Gson解析json的方法
 */
public class GsonUtil {

    public GsonUtil() {

    }

    //把一个map变成json字符串
    public static String parseMapToJson(Map<?, ?> map) {
        try {
            Gson gson = new Gson();
            return gson.toJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //把json字符串变成 bean 对象
    public static <T> T parseJsonToBean(String json, Class<T> cls) {
        Gson gson = new Gson();
        T t = null;
        try {
            t = gson.fromJson(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }

    //把json字符串变成 map 集合
    public static <T> HashMap<String, T> parseJsonToMap(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, T>>() {
        }.getType();
        HashMap<String, T> map = null;
        try {
            map = gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

/*	public static <T> List<T> changeGsonToList(String gsonString, T cls) {
        Gson gson = new Gson();
		List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
		}.getType());
		return list;
	}*/


    /**
     * 把json字符串变成 List 集合
     * params: new TypeToken<List<yourbean>>(){}.getType(),
     *
     * @param json
     * @param type new TypeToken<List<yourbean>>(){}.getType()
     * @return
     */
    //1、已经有type的类型
/*    public static <T> List<T> parseJsonToList(String json, Type type) {
        Gson gson = new Gson();
        List<T> list = gson.fromJson(json, type);
        return list;
    }*/

    //2、传入clss的类型
	public static <T> List<T> parseJsonToList(String json, Class<T> clss) {
        Gson gson = new Gson();
		List<T> list = gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
		return list;
	}

    //3、直接传入json，其他不传
/*	public static <T> List<T> parseJsonToList2(String json) {
        Gson gson = new Gson();
		List<T> list = gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
		return list;
	}*/


    //解析json数组
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        List<T> lst = null;
        try {
            lst = new ArrayList<T>();

            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (final JsonElement elem : array) {
                lst.add(new Gson().fromJson(elem, clazz));
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }

        return lst;
    }

    //获取json串中某个字段的值，注意，只能获取同一层级的value
    public static String getFieldValue(String json, String key) {
        if (TextUtils.isEmpty(json))
            return null;
        if (!json.contains(key))
            return "";
        JSONObject jsonObject = null;
        String value = null;
        try {
            jsonObject = new JSONObject(json);
            value = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

        return value;
    }

}
