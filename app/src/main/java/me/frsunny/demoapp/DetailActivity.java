package me.frsunny.demoapp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import me.frsunny.demoapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    public static final String ARG_IMAGE_URL = "imageUrl";

    private String mImageUrl;
    private ActivityDetailBinding mBinding;

    public static Intent newIntent(Context context, String imageUrl) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(ARG_IMAGE_URL, imageUrl);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        if (getIntent() != null) {
            mImageUrl = getIntent().getStringExtra(ARG_IMAGE_URL);
        }

        Glide.with(this).load(mImageUrl)
                .skipMemoryCache(true)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mBinding.imageView);
    }
}
