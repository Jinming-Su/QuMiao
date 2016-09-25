package com.example.sujinming.qumiao;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.example.sujinming.qumiao.myDefine.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private ViewPager viewPager;
    private List<View> views = new ArrayList<View>();
    private Button guide_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        LayoutInflater li = getLayoutInflater();
        views.add(li.inflate(R.layout.view_page_1, null, false));
        views.add(li.inflate(R.layout.view_page_2, null, false));
        views.add(li.inflate(R.layout.view_page_3, null, false));
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                if(position == views.size() - 1) {
                    views.get(position).findViewById(R.id.guide_btn).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(GuideActivity.this, MainActivity.class));
                        }
                    });
                }
                container.addView(views.get(position));
                return views.get(position);
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
        });
    }
}
