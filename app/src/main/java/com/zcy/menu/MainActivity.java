package com.zcy.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zcy.circlemenu.BaseView;
import com.zcy.circlemenu.CircleMenuView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CircleMenuView circleMenuView, circleMenuView1, circleMenuView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleMenuView = (CircleMenuView) findViewById(R.id.circleMenuView);
        circleMenuView1 = (CircleMenuView) findViewById(R.id.circleMenuView1);
        circleMenuView2 = (CircleMenuView) findViewById(R.id.circleMenuView2);
        circleMenuView.setOnMenuClickListener(new BaseView.OnMenuClickListener() {
            @Override
            public void onLeftMenuClick() {
                Toast.makeText(MainActivity.this, "点击了左菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTopMenuClick() {
                Toast.makeText(MainActivity.this, "点击了上菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightMenuClick() {
                Toast.makeText(MainActivity.this, "点击了右菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBottomMenuClick() {
                Toast.makeText(MainActivity.this, "点击了下菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCenterMenuClick() {
                Toast.makeText(MainActivity.this, "点击了中间菜单,文字大小颜色改变", Toast.LENGTH_SHORT).show();
                Random random = new Random();
                int textColor = 0xff000000 | random.nextInt(0x00ffffff);
                circleMenuView.setTextColor(textColor);
                circleMenuView.setText("变化");
                int textSize = (int) (10 * Math.random() + 30);
                circleMenuView.setTextSize(textSize);
            }
        });

        circleMenuView1.setOnMenuClickListener(new BaseView.OnMenuClickListener() {
            @Override
            public void onLeftMenuClick() {
                Toast.makeText(MainActivity.this, "点击了左菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTopMenuClick() {
                Toast.makeText(MainActivity.this, "点击了上菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightMenuClick() {
                Toast.makeText(MainActivity.this, "点击了右菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBottomMenuClick() {
                Toast.makeText(MainActivity.this, "点击了下菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCenterMenuClick() {
                Toast.makeText(MainActivity.this, "点击了中间菜单,文字大小颜色改变", Toast.LENGTH_SHORT).show();
                Random random = new Random();
                int textColor = 0xff000000 | random.nextInt(0x00ffffff);
                circleMenuView1.setTextColor(textColor);
                circleMenuView1.setText("变化");
                int textSize = (int) (20 * Math.random() + 40);
                circleMenuView1.setTextSize(textSize);
            }
        });
        circleMenuView2.setOnMenuClickListener(new BaseView.OnMenuClickListener() {
            @Override
            public void onLeftMenuClick() {
                Toast.makeText(MainActivity.this, "点击了左菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTopMenuClick() {
                Toast.makeText(MainActivity.this, "点击了上菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightMenuClick() {
                Toast.makeText(MainActivity.this, "点击了右菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBottomMenuClick() {
                Toast.makeText(MainActivity.this, "点击了下菜单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCenterMenuClick() {
                Toast.makeText(MainActivity.this, "点击了中间菜单,文字大小颜色改变", Toast.LENGTH_SHORT).show();
                Random random = new Random();
                int textColor = 0xff000000 | random.nextInt(0x00ffffff);
                circleMenuView2.setTextColor(textColor);
                circleMenuView2.setText("变化");
                int textSize = (int) (30 * Math.random() + 50);
                circleMenuView2.setTextSize(textSize);
            }
        });
    }
}
