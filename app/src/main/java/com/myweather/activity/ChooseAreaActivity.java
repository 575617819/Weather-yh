package com.myweather.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.myweather.R;
import com.myweather.dao.MyWeatherDao;
import com.myweather.model.City;
import com.myweather.model.County;
import com.myweather.model.Province;
import com.myweather.util.HttpCallbackListener;
import com.myweather.util.HttpUtils;
import com.myweather.util.SplitUtils;

import java.util.ArrayList;
import java.util.List;

public class ChooseAreaActivity extends Activity {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    private ListView lv_choose_area_place;
    private TextView tv_choose_area_titile;

    private ArrayList<String> dataList = new ArrayList<String>();
    private MyWeatherDao myWeatherDao;
    private ArrayAdapter<String> adapter;

    /*
    当前选中的级别
     */
    private int currentLevel;

    /*
    省、市、县列表
     */
    private List<Province> provincelist;
    private List<City> cityList;
    private List<County> countyList;

    /*
    选中的省、市
     */
    private Province selectedProvince;
    private City selectedCity;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("city_selected", false)) {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        lv_choose_area_place = (ListView) findViewById(R.id.lv_choose_area_place);
        tv_choose_area_titile = (TextView) findViewById(R.id.tv_choose_area_title);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        lv_choose_area_place.setAdapter(adapter);
        myWeatherDao = MyWeatherDao.getInstance(this);
        lv_choose_area_place.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provincelist.get(i);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(i);
                    queryCounties();
                } else if (currentLevel == LEVEL_COUNTY) {
                    String countyCode = countyList.get(i).getCountyCode();

                    /*
                    测试log  测试通过
                     */
                    Log.i("ChooseAreaActivity",countyCode);

                    Intent intent = new Intent(ChooseAreaActivity.this, WeatherActivity.class);
                    intent.putExtra("county_code", countyCode);
                    startActivity(intent);
                    finish();
                }
            }
        });
        queryProvinces();
    }


    /*
    查询全国所有省份，优先从数据库获取，如果数据库中没有再去服务器查询
     */
    private void queryProvinces() {
        provincelist = myWeatherDao.loadProvinces();
        if (provincelist.size() > 0) {
            dataList.clear();
            for (Province province : provincelist) {
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            lv_choose_area_place.setSelection(0);
            tv_choose_area_titile.setText("中国");
            currentLevel = LEVEL_PROVINCE;
        } else {
            queryFromServer(null, "province");
        }
    }


    /*
    查询全国所有市，优先从数据库获取，如果数据库中没有再去服务器查询
     */
    private void queryCities() {
        cityList = myWeatherDao.loadCities(selectedProvince.getId());
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            lv_choose_area_place.setSelection(0);
            tv_choose_area_titile.setText(selectedProvince.getProvinceName());
            currentLevel = LEVEL_CITY;
        } else {
            queryFromServer(selectedProvince.getProvinceCode(), "city");
        }
    }


    /*
    查询全国所有县，优先从数据库获取，如果数据库中没有再去服务器查询
     */
    private void queryCounties() {
        countyList = myWeatherDao.loadCounties(selectedCity.getId());
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            lv_choose_area_place.setSelection(0);
            tv_choose_area_titile.setText(selectedCity.getCityName());
            currentLevel = LEVEL_COUNTY;
        } else {
            queryFromServer(selectedCity.getCityCode(), "county");
        }
    }


    /*
    根据传入的代号getCityCode和类型从服务器上查询省市县数据
     */
    private void queryFromServer(final String code, final String type) {
        String address;
        if (!TextUtils.isEmpty(code)) {
            address = "http://www.weather.com.cn/data/list3/city" + code + ".xml";
        } else {
            address = "http://www.weather.com.cn/data/list3/city.xml";
        }
        showProgressDialog();
        HttpUtils.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = false;
                if ("province".equals(type)) {
                    result = SplitUtils.handleProvincesResponse(myWeatherDao, response);
                } else if ("city".equals(type)) {
                    result = SplitUtils.handleCitiesResponse(myWeatherDao, response, selectedProvince.getId());
                } else if ("county".equals(type)) {
                    result = SplitUtils.handleCountiesResponse(myWeatherDao, response, selectedCity.getId());
                }
                if (result) {
                    //通过runOnUiThread()方法回到主线程处理逻辑
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            if ("province".equals(type)) {
                                queryProvinces();
                            } else if ("city".equals(type)) {
                                queryCities();
                            } else if ("county".equals(type)) {
                                queryCounties();
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                //通过runOnUiThread()方法回到主线程处理逻辑
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(ChooseAreaActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }


    /*
    显示进度条对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
    }


    /*
    关闭进度条对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    /*
    捕获back键，根据当前的级别来判断，判断是返回市列表、省列表，还是直接退出
     */
    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/
        if (currentLevel == LEVEL_COUNTY) {
            queryProvinces();
        } else if (currentLevel == LEVEL_CITY) {
            queryProvinces();
        } else {
            finish();
        }
    }
}
