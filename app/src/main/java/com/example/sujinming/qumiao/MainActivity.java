package com.example.sujinming.qumiao;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ViewPager viewPager;
    private LinearLayout tab1;
    private LinearLayout tab2;
    private LinearLayout tab3;
    private LinearLayout tab4;
    private ImageButton tab1Img;
    private ImageButton tab2Img;
    private ImageButton tab3Img;
    private ImageButton tab4Img;
    private PagerAdapter pagerAdapter;
    private List<View> views = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();

        initEvents();
    }

    private void initEvents(){
        tab1.setOnClickListener(onClickListener);
        tab2.setOnClickListener(onClickListener);
        tab3.setOnClickListener(onClickListener);
        tab4.setOnClickListener(onClickListener);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetImg();
                int currentItem = viewPager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        tab1Img.setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        tab2Img.setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 2:
                        tab3Img.setImageResource(R.drawable.tab_address_pressed);
                        break;
                    case 3:
                        tab4Img.setImageResource(R.drawable.tab_settings_pressed);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetImg();
            switch (v.getId()) {
                case R.id.tab_1:
                    viewPager.setCurrentItem(0);
                    tab1Img.setImageResource(R.drawable.tab_weixin_pressed);
                    break;
                case R.id.tab_2:
                    viewPager.setCurrentItem(1);
                    tab2Img.setImageResource(R.drawable.tab_find_frd_pressed);
                    break;
                case R.id.tab_3:
                    viewPager.setCurrentItem(2);
                    tab3Img.setImageResource(R.drawable.tab_address_pressed);
                    break;
                case R.id.tab_4:
                    viewPager.setCurrentItem(3);
                    tab4Img.setImageResource(R.drawable.tab_settings_pressed);
                    break;
            }
        }
    };

    /**
     * 将所有图片切换为暗色
     */
    private void resetImg(){
        tab1Img.setImageResource(R.drawable.tab_weixin_normal);
        tab2Img.setImageResource(R.drawable.tab_find_frd_normal);
        tab3Img.setImageResource(R.drawable.tab_address_normal);
        tab4Img.setImageResource(R.drawable.tab_settings_normal);
    }

    private void initView() {
        viewPager = (ViewPager)findViewById(R.id.main_viewPager);
        tab1 = (LinearLayout)findViewById(R.id.tab_1);
        tab2 = (LinearLayout)findViewById(R.id.tab_2);
        tab3 = (LinearLayout)findViewById(R.id.tab_3);
        tab4 = (LinearLayout)findViewById(R.id.tab_4);
        tab1Img = (ImageButton)findViewById(R.id.tab_1_img);
        tab2Img = (ImageButton)findViewById(R.id.tab_2_img);
        tab3Img = (ImageButton)findViewById(R.id.tab_3_img);
        tab4Img = (ImageButton)findViewById(R.id.tab_4_img);

        LayoutInflater inflater = LayoutInflater.from(this);
        View tab01 = inflater.inflate(R.layout.tab1, null);
        View tab02 = inflater.inflate(R.layout.tab2, null);
        View tab03 = inflater.inflate(R.layout.tab3, null);
        View tab04 = inflater.inflate(R.layout.tab4, null);
        views.add(tab01);
        views.add(tab02);
        views.add(tab03);
        views.add(tab04);

        pagerAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = views.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }
}

