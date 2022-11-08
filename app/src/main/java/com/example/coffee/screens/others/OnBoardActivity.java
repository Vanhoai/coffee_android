package com.example.coffee.screens.others;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.coffee.R;
import com.example.coffee.adapters.SlideOnBoardAdapter;
import com.example.coffee.models.Others.SliderImage;
import com.example.coffee.screens.auth.LoginActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class OnBoardActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private SlideOnBoardAdapter adapter;
    private ArrayList<SliderImage> listSL;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        AppCompatButton btnNext = findViewById(R.id.btnNext);
        viewPager = findViewById(R.id.viewpagerSlider);
        circleIndicator = findViewById(R.id.circleSlider);

        listSL = getList();
        adapter = new SlideOnBoardAdapter(OnBoardActivity.this,listSL);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlider();



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnBoardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private ArrayList<SliderImage> getList(){
        ArrayList<SliderImage> sliderImages = new ArrayList<>();
        sliderImages.add(new SliderImage(R.drawable.slider_img1));
        sliderImages.add(new SliderImage(R.drawable.slider_img2));
        sliderImages.add(new SliderImage(R.drawable.slider_img3));
        sliderImages.add(new SliderImage(R.drawable.slider_img4));
        return sliderImages;
    }
    private void autoSlider(){
        if (listSL == null || listSL.isEmpty() || viewPager == null){
            return;
        }
        if (timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int curentItem = viewPager.getCurrentItem();
                        int totalItem = listSL.size()-1;
                        if (curentItem < totalItem){
                            curentItem ++;
                            viewPager.setCurrentItem(curentItem);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 1000, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!= null){
            timer.cancel();
            timer=null;
        }
    }
}