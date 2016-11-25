package com.myweather.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myweather.R;
import com.myweather.util.HttpCallbackListener;
import com.myweather.util.HttpUtils;
import com.myweather.util.JsonUtils;

public class WeatherActivity extends Activity {

    private LinearLayout weather_info_layout;
    private TextView city_name;
    private TextView publish_text;
    private TextView weather_desp;
    private TextView temp1;
    private TextView temp2;
    private TextView current_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weather_info_layout = (LinearLayout) findViewById(R.id.weather_info_layout);
        city_name = (TextView) findViewById(R.id.city_name);
        publish_text = (TextView) findViewById(R.id.publish_text);
        weather_desp = (TextView) findViewById(R.id.weather_desp);
        temp1 = (TextView) findViewById(R.id.temp1);
        temp2 = (TextView) findViewById(R.id.temp2);
        current_date = (TextView) findViewById(R.id.current_date);

        String countyCode = getIntent().getStringExtra("county_code");

        /*
        测试log 测试通过
         */
        Log.i("WeatherActivity",countyCode);

        if(!TextUtils.isEmpty(countyCode)){
            //有县级代号时就去查询天气
            publish_text.setText("同步中...");
            weather_info_layout.setVisibility(View.INVISIBLE);
            city_name.setVisibility(View.INVISIBLE);
            queryWeatherCode(countyCode);
        }else{
            //没有县级代号时就直接显示本地天气
            showWeather();
        }
    }

    /*
    查询县级代号所对应的天气代号
     */
    private void queryWeatherCode(String countyCode) {

        String address="http://www.weather.com.cn/data/list3/city"+countyCode+".xml";
        queryFromServer(address,"countyCode");

    }

    /*
    查询天气代号所对应的天气
    http://www.weather.com.cn/weather/101100701.shtml?from=cn
    http://www.weather.com.cn/weather/101080102.shtml?from=cn
    http://www.weather.com.cn/data/cityinfo/101080102.html
     */
    private void queryWeatherInfo(String weatherCode){

        /*
        测试log 测试成功
         */
        Log.i("WeatherActivity",weatherCode);

        String address="http://www.weather.com.cn/data/cityinfo/"+weatherCode+".html";
        queryFromServer(address,"weatherCode");

    }

    private void queryFromServer(final String address, final String type) {
        HttpUtils.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(final String response) {
                if("countyCode".equals(type)){
                    if(!TextUtils.isEmpty(response)){
                        String[] array = response.split("\\|");
                        if(array!=null&&array.length==2){
                            String weatherCode = array[1];
                            queryWeatherInfo(weatherCode);
                        }
                    }else if("weatherCode".equals(type)){

                        /*
                        测试log
                         */
                        Log.i("WeatherActivity","处理服务器返回的天气信息");

                        //处理服务器返回的天气信息
                        JsonUtils.handleWeatherResponse(WeatherActivity.this,response);

                        /*
                        测试log
                         */
                        Log.i("WeatherActivity","处理服务器返回的天气信息");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showWeather();
                            }
                        });
                    }
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        publish_text.setText("同步失败");
                    }
                });
            }
        });
    }

    /*
    从SharedPreferences文件中读取存储的天气信息，并显示到界面上。
     */
    private void showWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        city_name.setText(prefs.getString("city_name",""));
        temp1.setText(prefs.getString("temp1",""));
        temp2.setText(prefs.getString("temp2",""));
        weather_desp.setText(prefs.getString("weather_desp",""));
        publish_text.setText("今天"+prefs.getString("publish_text","")+"发布");
        current_date.setText(prefs.getString("current_date",""));
        weather_info_layout.setVisibility(View.VISIBLE);
        city_name.setVisibility(View.VISIBLE);
    }

}


