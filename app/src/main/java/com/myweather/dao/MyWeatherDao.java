package com.myweather.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myweather.db.MyWeatherOpenHelper;
import com.myweather.model.City;
import com.myweather.model.County;
import com.myweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 * 数据库操作类MyWeatherDao
 *
 * 存储省份数据、读取所有省份数据
 * 存储城市数据、读取某省内所有城市数据
 * 存储县数据、读取某市内所有县的数据
 */

public class MyWeatherDao {

    /*
    定义数据库名、数据库版本
     */
    public static final String DB_NAME="my_weather";
    public static final int DB_VERSION=1;
    private final MyWeatherOpenHelper myWeatherOpenHelper;
    private final SQLiteDatabase db;
    private static MyWeatherDao myWeatherDao;

    /*
    构造方法私有化
     */
    private MyWeatherDao(Context context) {
        myWeatherOpenHelper = new MyWeatherOpenHelper(context, DB_NAME, null, DB_VERSION);
        db = myWeatherOpenHelper.getWritableDatabase();
    }

    /*
    获取MyWeatherDao的实例
     */
    public synchronized static MyWeatherDao getInstance(Context context){
        if(myWeatherDao==null) {
            myWeatherDao = new MyWeatherDao(context);
        }
        return myWeatherDao;
    }


    /*
    将Province实例存储到数据库
     */
    public void saveProvince(Province province){
        if(province!=null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }

    /*
    从数据库读取全国的所有省份信息
     */
    public List<Province> loadProvinces(){
        ArrayList<Province> list = new ArrayList<>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if(cursor.moveToNext()){
            do{
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }


     /*
    将City实例存储到数据库
     */
    public void saveCities(City city){
        if(city!=null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            db.insert("City",null,values);
        }
    }

     /*
    从数据库读取某省份下所有的城市信息
     */
    public List<City> loadCities(int provinceId){
        ArrayList<City> list = new ArrayList<>();
        Cursor cursor = db.query("City", null, "province_id=?", new String[]{String.valueOf(provinceId)}, null, null, null);
        if(cursor.moveToNext()){
            do{
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("cityName")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("cityCode")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }


     /*
    将County实例存储到数据库
     */
    public void saveCounty(County county){
        if(county!=null){
            ContentValues values = new ContentValues();
            values.put("County",county.getCountyName());
            values.put("County",county.getCountyCode());
            db.insert("County",null,values);
        }
    }

     /*
    从数据库读取某城市下所有的县信息
     */
    public List<County> loadCounties(int cityId){
        ArrayList<County> list = new ArrayList<>();
        Cursor cursor = db.query("County", null, "city_id=?", new String[]{String.valueOf(cityId)}, null, null, null);
        if(cursor.moveToNext()){
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("countyName")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("countyCode")));
                county.setCityId(cityId);
                list.add(county);
            }while (cursor.moveToNext());
        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }
}
