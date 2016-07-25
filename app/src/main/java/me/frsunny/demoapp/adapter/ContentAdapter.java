package me.frsunny.demoapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import me.frsunny.demoapp.DetailActivity;
import me.frsunny.demoapp.R;
import me.frsunny.demoapp.databinding.ListItemCardImageViewBinding;
import me.frsunny.demoapp.mock.MockData;

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;

    public ContentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemCardImageViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_card_image_view, parent, false);

        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final CardViewHolder cardViewHolder = (CardViewHolder) holder;
        Glide.with(mContext).load(MockData.IMAGE_URL_LIST.get(position))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(cardViewHolder.binding.cardImageView);

//        Glide.with(mContext).load(MockData.IMAGE_URL_LIST.get(position))
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .preload();
        /**
         * skipMemoryCache, DiskCacheStrategy.SOURCE, dontTransform 해줘야 함. (그래야 화면 전환시에 버벅거리지 않음)
         */

        cardViewHolder.binding.cardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, cardViewHolder.binding.cardImageView, "mainImageTransition");
                    mContext.startActivity(DetailActivity.newIntent(mContext, MockData.IMAGE_URL_LIST.get(position)), options.toBundle());
                } else {
                    mContext.startActivity(DetailActivity.newIntent(mContext, MockData.IMAGE_URL_LIST.get(position)));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return MockData.IMAGE_URL_LIST.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public ListItemCardImageViewBinding binding;

        public CardViewHolder(ListItemCardImageViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
