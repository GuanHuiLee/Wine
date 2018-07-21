package com.lgh.wine.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * Created by niujingtong on 2018/7/21.
 * 模块：
 */
public class MyGlideEngine implements ImageEngine {

    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder)
                .override(resize, resize)
                .centerCrop();


        Glide.with(context)
                .asBitmap()
                .apply(options)
                .load(uri)
                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder)
                .override(resize, resize)
                .centerCrop();


        Glide.with(context)
                .asBitmap()
                .apply(options)
                .load(uri)
                .into(imageView);
    }


    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH)
                .fitCenter();


        Glide.with(context)
                .asBitmap()
                .apply(options)
                .load(uri)
                .into(imageView);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH);


        Glide.with(context)
                .asGif()
                .apply(options)
                .load(uri)
                .into(imageView);
    }


    @Override
    public boolean supportAnimatedGif() {
        return true;
    }

}
