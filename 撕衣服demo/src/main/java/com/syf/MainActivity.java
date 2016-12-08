package com.syf;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_main_before;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        1.显示的图片
         */
        iv_main_before = (ImageView) findViewById(R.id.iv_main_before);

        /*
        2.把要操作的图片转化成bitmap
         */
        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.before);

        /*
        3.创建原图的副本
         */

        //创建模板
        final Bitmap templateBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        //把模板当画布
        Canvas canvas = new Canvas(templateBitmap);
        //创建一个画笔
        Paint paint = new Paint();
        //开始作画
        canvas.drawBitmap(srcBitmap, new Matrix(), paint);

        /*
        4.把templateBitmap显示在iv上
         */
        iv_main_before.setImageBitmap(templateBitmap);

        /*
        5.给iv_main_before设置一个触摸事件
         */
        iv_main_before.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        break;

                    case MotionEvent.ACTION_MOVE:
                        for (int i = -7; i < 7; i++) {
                            for (int j = -7; j < 7; j++) {
                                if (Math.sqrt(i * i + j * j) < 7) {
                                    try {
                                        templateBitmap.setPixel((int) motionEvent.getX() + i, (int) motionEvent.getY() + j, Color.TRANSPARENT);
                                    } catch (Exception e) {
                                    }
                                }


                            }

                        }


                        //一定要记得更新iv
                        iv_main_before.setImageBitmap(templateBitmap);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;

                }

                return true;
            }
        });


    }
}
