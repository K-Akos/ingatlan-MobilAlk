package com.example.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements Filterable {
    // Member variables.
    private ArrayList<IngatlanDetails> mIngatlanDetailsData = new ArrayList<>();
    private ArrayList<IngatlanDetails> mIngatlanDetailsDataAll = new ArrayList<>();
    private static Context mContext;
    private int lastPosition = -1;

    private List<Ingatlan> mIngatlan;
    ItemAdapter(Context context, ArrayList<IngatlanDetails> itemsData) {
        this.mIngatlanDetailsData = itemsData;
        this.mIngatlanDetailsDataAll = itemsData;
        this.mContext = context;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false));


    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        // Get current sport.
        IngatlanDetails currentItem = mIngatlanDetailsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentItem);


        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mIngatlanDetailsData.size();
    }


    /**
     * RecycleView filter
     * **/
    @Override
    public Filter getFilter() {
        return shopingFilter;
    }

    private Filter shopingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<IngatlanDetails> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0) {
                results.count = mIngatlanDetailsDataAll.size();
                results.values = mIngatlanDetailsDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(IngatlanDetails item : mIngatlanDetailsDataAll) {
                    if(item.getLocation().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mIngatlanDetailsData = (ArrayList)filterResults.values;
            notifyDataSetChanged();
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mPriceText;
        private ImageView mItemImage;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.itemTitle);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mItemImage = itemView.findViewById(R.id.itemImage);
            mPriceText = itemView.findViewById(R.id.price);

            itemView.findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListActivity)mContext).updateAlertIcon();
                }
            });
        }

        void bindTo(IngatlanDetails currentItem){
            mTitleText.setText(currentItem.getLocation());
            mInfoText.setText(currentItem.getTerulet());
            mPriceText.setText(currentItem.getPrice());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(currentItem.getImageResource()).into(mItemImage);
        }
    }
    void setIngatlan(List<Ingatlan> ingatlan){
        mIngatlan = ingatlan;
        notifyDataSetChanged();
    }
}
