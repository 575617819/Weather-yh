package com.myweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.myweather.dao.MyWeatherDao;
import com.myweather.model.City;
import com.myweather.model.County;
import com.myweather.model.Province;

/**
 * Created by Administrator on 2016/11/23.
 */

public class SplitUtils {

    /*
    解析和处理服务器返回的省级数据,将数据保存到本地数据库
     */
    public synchronized static boolean handleProvincesResponse(MyWeatherDao myWeatherDao,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if(allProvinces!=null&&allProvinces.length>0){
                for(String p:allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    myWeatherDao.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /*
    解析和处理服务器返回的市级数据，将数据保存到本地数据库
     */
    public static boolean handleCitiesResponse(MyWeatherDao myWeatherDao,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities!=null&&allCities.length>0){
                for(String c:allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    myWeatherDao.saveCities(city);
                }
                return true;
            }
        }
        return false;
    }


    /*
    解析和处理服务器返回的县级数据，将数据保存到本地数据库
     */
    public static boolean handleCountiesResponse(MyWeatherDao myWeatherDao,String response,int cityId){

        /*
        测试log
         */
        Log.i("handleCountiesResponse","1");

        if(!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if(allCounties!=null&&allCounties.length>0){
                for(String c:allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);

                    /*
                    打印log测试
                     */
                    Log.i("handleCountiesResponse",array[0]);
                    Log.i("handleCountiesResponse",array[1]);

                    myWeatherDao.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
