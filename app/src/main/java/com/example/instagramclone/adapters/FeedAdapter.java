package com.example.instagramclone.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagramclone.FeedActivity;
import com.example.instagramclone.PostDetailsActivity;
import com.example.instagramclone.R;
import com.example.instagramclone.databinding.ActivityMainBinding;
import com.example.instagramclone.databinding.ItemPostBinding;
import com.example.instagramclone.models.Post;
import com.parse.ParseException;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.io.File;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>{

    public static final String TAG = "FeedAdapter";

    Context context;
    List<Post> posts;

    public FeedAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View postView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        try {
            holder.clearImage();
            holder.bind(post);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemPostBinding binding = ItemPostBinding.bind(itemView);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

        }

        public void bind(Post post) throws ParseException {
            ParseFile postImage = post.getImage();
            if (postImage != null) {
                Glide.with(context).load(postImage.getUrl()).into(binding.ivPost);
                // Glide.with(context).load("https://sams-parstagram.herokuapp.com/parse/files/sams-parstagram/d0a971de02e5851ab0d39de895e18248_photo.jpg").into(binding.ivPost);
                Log.i(TAG, postImage.getUrl());

            }
            binding.tvUsername.setText(post.getUser().fetchIfNeeded().getString("username"));
            binding.tvCaption.setText(post.getDescription());
        }

        public void clearImage() {
            Glide.with(context).clear(binding.ivPost);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){
                Post post = posts.get(position);
                Intent intent = new Intent(context, PostDetailsActivity.class);
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                context.startActivity(intent);
            }

        }

        public void clear() {
            posts.clear();
            notifyDataSetChanged();
        }

        // Add a list of items -- change to type used
        public void addAll(List<Post> list) {
            posts.addAll(list);
            notifyDataSetChanged();
        }

    }


}
