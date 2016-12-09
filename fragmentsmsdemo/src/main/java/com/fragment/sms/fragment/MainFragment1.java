package com.fragment.sms.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.fragment.sms.R;
import com.fragment.sms.activity.MainActivity;

public class MainFragment1 extends Fragment {

    String[] data = {"大地回暖春节来，小桥流水百花开。大红灯笼挂起来，笑迎宾客喜迎财。过年红包快拿来，姑娘漂亮小伙帅。烦恼忧愁不再来，福星财神相对拜。新年快乐，恭喜发财！",
            "炮竹声声辞旧岁，欢度佳节福连天，宾朋满座笑开颜，举杯共饮幸福春，条条短信传祝福，新春快乐合家欢，吉庆有余过大年，祝鸡年春节愉快！",
            "鸡年快乐！祝事业像马克思主义一样不断发展，魅力似毛泽东思想一样光芒四射，生活如邓小平理论一样演绎春天的故事，财富同三个代表一样与时俱进！",
            "爆竹声声辞旧岁，带走昔日的烦恼；烟花簇簇耀夜空，照耀前程的辉煌；春联副副贴门墙，期盼来年更美好；佳肴美味端上桌，新年团聚乐淘淘。条条短信送祝愿，祝你新年快乐家团圆！",
            "昨天相识是缘份，今天相处是福份，明天重逢是情份，友谊的陈酿是年份，彼此关心是天分，新年祝福是气氛，朋友是生活的一部份。预祝春节快乐！",
            "一点喜，两点乐，三点美，四点欢，五点福，六点禄，七点吉，八点财，九点寿，十点富，十一点运，十二点子时钟声响，新年好运！鸡年万事顺意，平安幸福！",
            "辛辛苦苦又一年，忙忙碌碌为挣钱；你来我往联系少，转眼又到春节了，发条短信情真意切了：愿你薪水多赚，奖金满满；快乐天天，幸福年年！",
            "一丝真诚，胜过千两黄金；一丝温暖，能抵万里寒霜，一声问候，送来温馨甜蜜；一条短信，捎去我万般心意！愿中国人民新年万事如意！",
            "愿你新的一年里：事业正当午，身体壮如虎，金钱不胜数，干活不辛苦，悠闲像老鼠，浪漫似乐谱，快乐非你莫属！祝鸡年春节快乐！",
            "恰逢新年春节忙，送走猴年迎鸡年，吉祥话要趁早，祝福语提前念。祝愿你新年新气象，快乐依然，幸福绵绵，平安一生，健康到永远！",
            "春节马上到，电话短信挤爆。为了不凑热闹，提前向你问好。祝你顿顿吃得饱，夜夜睡得好，清晨起得早，工资长得高，新年财运到！",
            "春节送福：清晨早起精神抖，晚上早睡做美梦，饮食合理防节症，健康为主享万年，春节聚会酒肉多，祝福关心早送到，祝君节制体强健，来年还要为君送。祝：开心健康！快乐平安！如意吉祥！",
            "春节假期回放：时间是亲朋的，眼睛是熬红的，肠胃是酒杯的，大脑是牌局的，奖金是挥霍的，身心是疲惫的，心里是开心的，最终整个人是要交给工作的！",
            "鸡年到了，给你鸡情的祝福。愿你的生活鸡极向上，能把握每个发财的鸡会。把鸡肤保养得青春焕发。事业生鸡勃勃，要记得经常联系哦，不许总关鸡！",
            "春节啦！有伙人到处打听你，还说逮住你决不轻饶，他们一个叫财神，一个叫顺心，领头的叫幸福，你就别躲了认命吧！春节快乐！"};


    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment1, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, data);
        ListView lv_fragment1_NewYear = (ListView) view.findViewById(R.id.lv_fragment1_NewYear);
        lv_fragment1_NewYear.setAdapter(adapter);
        lv_fragment1_NewYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //跳转到发送短信页面,发送短信
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