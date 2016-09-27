package com.example.sujinming.qumiao;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sujinming.qumiao.myDefine.MyConst;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ViewPager viewPager;
    private LinearLayout friend;
    private LinearLayout weiMiao;
    private LinearLayout profile;
    private ImageView friend_img;
    private ImageView weiMiao_img;
    private ImageView profile_img;
    private PagerAdapter pagerAdapter;
    private List<View> views = new ArrayList<View>();

    //auth
    private EditText username_et, password_et;
    private CheckBox saveUsername_cb;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Button login_btn;
    private Button cancel_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();

        initEvents();
    }

    private void initEvents(){
        friend.setOnClickListener(onClickListener);
        weiMiao.setOnClickListener(onClickListener);
        profile.setOnClickListener(onClickListener);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetImg();
                int currentItem = viewPager.getCurrentItem();
                switch (currentItem) {
                    case MyConst.ZERO:
                        friend_img.setImageResource(R.drawable.tab_message_pressed);
                        break;
                    case MyConst.ONE:
                        weiMiao_img.setImageResource(R.drawable.tab_miao_pressed);
                        break;
                    case MyConst.TWO:
                        profile_img.setImageResource(R.drawable.tab_profile_pressed);
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
                case R.id.friend:
                    viewPager.setCurrentItem(MyConst.ZERO);
                    friend_img.setImageResource(R.drawable.tab_message_pressed);
                    break;
                case R.id.weiMiao:
                    viewPager.setCurrentItem(MyConst.ONE);
                    weiMiao_img.setImageResource(R.drawable.tab_miao_pressed);
                    break;
                case R.id.profile:
                    viewPager.setCurrentItem(MyConst.TWO);
                    friend_img.setImageResource(R.drawable.tab_profile_pressed);
                    break;
            }
        }
    };

    /**
     * 将所有图片切换为暗色
     */
    private void resetImg(){
        friend_img.setImageResource(R.drawable.tab_message_normal);
        weiMiao_img.setImageResource(R.drawable.tab_miao_normal);
        profile_img.setImageResource(R.drawable.tab_profile_normal);
    }

    private void initView() {
        viewPager = (ViewPager)findViewById(R.id.main_viewPager);
        friend = (LinearLayout)findViewById(R.id.friend);
        weiMiao = (LinearLayout)findViewById(R.id.weiMiao);
        profile = (LinearLayout)findViewById(R.id.profile);
        friend_img = (ImageView)findViewById(R.id.friend_img);
        weiMiao_img = (ImageView)findViewById(R.id.weiMiao_img);
        profile_img = (ImageView)findViewById(R.id.profile_img);

        //添加layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View friend_lyt = inflater.inflate(R.layout.friend, null);
        View weiMiao_lyt = inflater.inflate(R.layout.wei_miao, null);
        View profile_lyt = inflater.inflate(R.layout.profile, null);
        views.add(friend_lyt);
        views.add(weiMiao_lyt);
        views.add(profile_lyt);

        pagerAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = views.get(position);
                container.addView(view);
                switch (position) {
                    case MyConst.TWO:
                        username_et = (EditText)findViewById(R.id.username_et);
                        password_et = (EditText)findViewById(R.id.password_et);
                        login_btn = (Button)findViewById(R.id.login_btn);
                        cancel_btn = (Button)findViewById(R.id.cancel_btn);
                        saveUsername_cb = (CheckBox)findViewById(R.id.saveUsername_cb);
                        sp = getSharedPreferences("auth", MODE_PRIVATE);
                        editor = sp.edit();

                        String username_tmp = sp.getString("username","");
                        if( username_tmp == null) {
                            saveUsername_cb.setChecked(false);
                        } else {
                            saveUsername_cb.setChecked(true);
                            username_et.setText(username_tmp);
                        }

                        //auth
                        login_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (v.getId()) {
                                    case R.id.login_btn:
                                        String username = username_et.getText().toString().trim();
                                        String password = password_et.getText().toString().trim();
                                        if("admin".equals(username) && "123456".equals(password)) {
                                            if(saveUsername_cb.isChecked()) {
                                                editor.putString("username", username);
                                                editor.commit();
                                                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                            } else {
                                                editor.remove("username");
                                                editor.commit();
                                            }
                                        } else {
                                            Toast.makeText(MainActivity.this, "禁止登陆", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                }
                            }
                        });
                        break;
                }
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

