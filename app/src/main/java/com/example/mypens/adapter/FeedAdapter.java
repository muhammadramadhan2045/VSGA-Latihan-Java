package com.example.mypens.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypens.databinding.EntryItemBinding;
import com.example.mypens.model.Feed;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private final List<Feed> feedList;
    public FeedAdapter(List<Feed> feedList) {
        this.feedList = feedList;
    }
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public FeedAdapter.FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        EntryItemBinding binding = EntryItemBinding.inflate(layoutInflater, parent, false);
        return new FeedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.FeedViewHolder holder, int position) {
        holder.bind(feedList.get(position));
        holder.itemView.setOnClickListener(v -> {
            onItemClickCallback.onItemClicked(feedList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;

        public FeedViewHolder(EntryItemBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            subtitle = binding.subtitle;
        }

        public void bind(Feed feed) {
            title.setText(feed.getTitle());
            subtitle.setText(feed.getSubtitle());

        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Feed feed);
    }
}
