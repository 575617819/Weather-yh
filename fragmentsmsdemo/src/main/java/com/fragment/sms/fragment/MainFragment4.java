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

public class MainFragment4 extends Fragment {

    String[] data = {"寿星佬，我祝你所有的希望都能如愿，所有的梦想都能实现，所有的等候都能出现，所有的付出都能兑现。",
            "今天是你的生日，为了表示祝贺，所有女厕和女浴室均免费向您开放，欢迎光临！",
            "祝你：福如东海老王八，寿比南山大石头。",
            "兽星：猪你生日非常狠地快乐，祝贺你又长了一年的皱纹了啦……离满脸褶子又近了一步啦……",
            "在你生日来临之即，祝你百事可乐，万事芬达，天天哇哈哈，月月乐百事，年年高乐高，心情似雪碧，永远都醒目",
            "祝你吃饭大鱼大肉；唱歌美女伴奏；日进斗金不够；敢与乌龟比寿。生日快乐！",
            "虽然不能与你共度良宵，不能为你点燃蜡烛，不能看你许下心愿，但老公有千言万语从内心生起，默默地把祝福点燃！老婆生日快乐，希望您多多保重身体快乐地度过这一天！",
            "大海啊全他妈是水，蜘蛛啊全他妈是腿，辣椒啊真他妈辣嘴，认识你啊真他妈不后悔。祝生日快乐，天天开怀合不拢嘴 悠悠的云里有淡淡的诗，淡淡的诗里有绵绵的喜悦，绵绵的喜悦里有我轻轻的祝福，生日快乐！",
            "我送你这条温暖如春的短信，让你的生活春意盎然，爱情春暖花开，事业春风得意，好运长春不老，天天满面春风！",
            "人生在世，没有那么简单，就可以找到聊得来的伴；生活没那么容易，多少苦闷在心间。还好友情在这世间陪伴，祝生日快乐，幸福绵绵！",
            "愿你拥有与你相同星座的——物理学家玛丽居里般的睿智，画家毕加索才气，总统戴高乐魄力，影星阿兰德龙的人气，祝你生日快乐，天天开心！",
            "领导您好，今儿是你的生日，我特意送上我的祝福，愿他如一碗甜甜的长寿面，有嚼劲的面条是我长长的祝愿，可口的汤，是我对您快乐、幸福和甜蜜的祝福。",
            "朋友生日时我结识了你，和你太投契了，之后深深地爱上了你。",
            "今天是你的生日，为了表示祝贺，所有女厕和女浴室均免费向您开放，欢迎光临！ 猪，你快快长！猪，你早日出栏！猪，你生日快乐！",
            "只有懂得糊口的人,才能领略鲜花的娇艳.只有懂得爱的人,才能领略到心中芳香。祝您有一个特别的生日！",
            "有句话一直没敢对你说，可是你生日的时候再不说就没机会了：你真地好讨厌……讨人喜欢，百看不厌。"};

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment4, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, data);
        ListView lv_fragment4_birthday = (ListView) view.findViewById(R.id.lv_fragment4_birthday);
        lv_fragment4_birthday.setAdapter(adapter);
        lv_fragment4_birthday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //挑战到发送短信界面
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