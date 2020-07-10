package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.instagramclone.databinding.ActivityFeedBinding;
import com.example.instagramclone.databinding.ActivityPostDetailsBinding;
import com.example.instagramclone.models.Post;
import com.parse.ParseException;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class PostDetailsActivity extends AppCompatActivity {

    public static final String TAG = "PostDetailsActivity";

    private ActivityPostDetailsBinding binding;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPostDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        ParseFile postImage = post.getImage();
        if (postImage != null) {
            Glide.with(this).load(postImage.getUrl()).into(binding.ivDetailsPost);
            Log.i(TAG, postImage.getUrl());

        }
        try {
            binding.tvDetailsUsername.setText(post.getUser().fetchIfNeeded().getString("username"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        binding.tvDetailsCaption.setText(post.getDescription());
        if (post.getTimeStamp() != null) {
            Log.i(TAG, post.getTimeStamp().toString());
        }
       binding.tvDetailsTimestamp.setText(post.getTimeStamp());
    }
}