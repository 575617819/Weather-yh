package com.fragment.sms.fragment;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fragment.sms.R;

public class MainFragment2 extends Fragment {

    String[] data = {"圣诞之夜想起你，发个短信问候你，想不起我没关系，只要你快乐又顺心，就是我由衷之惬意。",
            "献上我无限的祝福之意，祝福您不论何时何地，不论在何处，我都愿意让您知道，我深深地为您祝福，圣诞快乐!",
            "我向你保证，我对你的情感纯洁如圣诞夜之雪--如果今年圣诞不下雪，此保证有效期延长至明年的12月25日!!",
            "今天要吃蛋炒饭，但只能吃饭，不能吃蛋。如果你能达到蛋饭分离的境界，必能剩蛋快乐!如果你能把炒饭中的蛋再变回完整的蛋，圆蛋也快乐喔!",
            "炉火渐渐地温暖了房间,也温暖了我摇曳的思念;饰品装点着橱窗，也装点着我美丽的梦;贺卡缤纷了这个季节，也缤纷了我浓浓的祝福。闭上双眼许下个愿望，这个圣诞夜只想陪在你的身边。",
            "朋友，如果平安夜当晚有个红衣胖老头儿从窗户爬进你屋子把你装进口袋。。。请不要怕!因为我对圣诞老人说：“想要一个你这样的朋友”!",
            "圣诞的夜，在祥和中徜徉;圣诞的心，在思念中跳动;圣诞的爱，在烟花中绽放;圣诞的情，在空气中燃烧;圣诞的我，在夜色中想你：亲爱的，圣诞快乐!",
            "即使寒冬，倍感温暖。烛光摇曳，华灯璀灿。炉火熊熊，张灯结彩。圣诞树下，礼物如山。钟声敲响，吉祥浪漫。祝福声声，幸福永远!",
            "听说你最近乐蒙了，刚到路口就遇到绿灯了，刚买的股票就开始飙升了，刚就的业又开始荣升了，总之想干啥啥都能成功了，来祝你圣诞快乐了!也该回个短信请我吃撑了。",
            "问世间，何以为贵?山以青为贵，水以秀为贵，物以稀为贵，月以明为贵，人以正为贵，友以挚为贵，情以真为贵，我以你为贵!祝你圣诞快乐!元旦快乐!"};


    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment2, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_list_item_1, data);
        ListView lv_fragment2_Christmas = (ListView) view.findViewById(R.id.lv_fragment2_Christmas);
        lv_fragment2_Christmas.setAdapter(adapter);
        lv_fragment2_Christmas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //跳转到系统发送短信页面
                sendSms(data[i]);

            }
        });
    }

    private void sendSms(String value) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("smsto:"));
        sendIntent.putExtra("sms_body", value);
        startActivity(sendIntent);
    }


}