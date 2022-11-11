package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.models.Others.SliderImage;

import java.util.ArrayList;

public class SlideOnBoardAdapter extends PagerAdapter {
    Context context;
    ArrayList<SliderImage> ListSlider_Image;

    public SlideOnBoardAdapter(Context context, ArrayList<SliderImage> listSlider_Image) {
        this.context = context;
        ListSlider_Image = listSlider_Image;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_slider_image, container, false);
        ImageView ivSlider = view.findViewById(R.id.ivSlider);

        SliderImage slider = ListSlider_Image.get(position);
        if (slider != null) {
            Glide.with(context).load(slider.getResoure_image()).into(ivSlider);
        }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (ListSlider_Image != null) {
            return ListSlider_Image.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
