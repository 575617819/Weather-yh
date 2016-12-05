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

public class MainFragment3 extends Fragment {

    String[] data = {"祝愿您在新的一年里，所有的希望都能如愿，所有的梦想都能实现，所有的期待都能出现，所有的付出都能兑现!",
            "一家和和睦睦，一年开开心心，一生快快乐乐，一世平平安安，天天精神百倍，月月喜气扬扬，年年财源广进。元旦快乐!",
            "清晨曙光初现，幸福在你身边;中午艳阳高照，微笑在你心间;傍晚日落西山，欢乐随你365天。元旦快乐!新年吉祥!好运齐来!",
            "第一缕阳光是我对你的深深祝福，夕阳收起的最后一抹嫣红是我对你衷心的问候，在元旦来临之际，送上真挚的祝福：新年快乐!",
            "元旦快乐!祝你在新的一年里：事业正当午，身体壮如虎，金钱不胜数，干活不辛苦，悠闲像猴子，浪漫似乐谱，快乐非你莫属!",
            "元旦快乐!祝你财源滚滚，发得像肥猪;身体棒棒，壮得像狗熊;爱情甜甜，美得像蜜蜂;好运连连，多得像牛毛!",
            "元旦佳节到，向你问个好;身体倍健康，心情特别好;好运天天交，口味顿顿妙;家里出黄金，墙上长钞票。",
            "一天我擦亮阿拉丁的神灯，灯神说：我会满足你一个愿望。我说：请祝福正在看短消息的人新年快乐!",
            "元旦到了，我托空气为邮差，把热腾腾的问候装订成包裹，印上真心为邮戳，37度恒温快递，收件人是你，真心祝你：新年好!",
            "请用一秒钟忘掉烦恼，用一分钟想想新年;用一小时与爱人度过，用一年来体会生活!在新旧交替之际，请用一个微笑来接收我传递给你的祝福!元旦快乐"};

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment3, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_list_item_1, data);
        ListView lv_fragment3_yuandan = (ListView) view.findViewById(R.id.lv_fragment3_yuandan);
        lv_fragment3_yuandan.setAdapter(adapter);
        lv_fragment3_yuandan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //跳转到发送短信页面
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