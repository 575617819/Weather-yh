package com.myweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/11/25.
 */

public class JsonUtils {


    /*
            解析服务器返回的json数据，并将解析出来的数据存储到本地
             */
    public static void handleWeatherResponse(Context context,String response){

          /*
            测试log
             */
        Log.i("JsonUtils","进入handleWeatherResponse方法");


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject weatherInfo = jsonObject.getJSONObject("weatherinfo");
            String cityName = weatherInfo.getString("city");
            String weatherCode = weatherInfo.getString("cityid");
            String temp1 = weatherInfo.getString("temp1");
            String temp2 = weatherInfo.getString("temp2");
            String weatherDesp = weatherInfo.getString("weather");
            String publishTime = weatherInfo.getString("ptime");

            /*
            测试log
             */
            Log.i("JsonUtils",cityName+weatherCode+temp1+temp2+weatherDesp+publishTime);

            saveWeatherInfo(context,cityName,weatherCode,temp1,temp2,weatherDesp,publishTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /*
    将服务器返回的所有天气信息存储到SharedPreferences文件中
     */
    private static void saveWeatherInfo(Context context, String cityName, String weatherCode,
                                        String temp1, String temp2, String weatherDesp, String publishTime) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            editor.putBoolean("city_selected",true);
            editor.putString("city_name",cityName);
            editor.putString("weather_code",weatherCode);
            editor.putString("temp1",temp1);
            editor.putString("temp2",temp2);
            editor.putString("weather_desp",weatherDesp);
            editor.putString("publish_time",publishTime);
            editor.putString("current_date",sdf.format(new Date()));
            editor.commit();
        }
    }
}
