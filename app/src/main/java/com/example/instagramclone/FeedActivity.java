package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.instagramclone.adapters.FeedAdapter;
import com.example.instagramclone.databinding.ActivityFeedBinding;
import com.example.instagramclone.databinding.ActivityMainBinding;
import com.example.instagramclone.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private ActivityFeedBinding binding;
    public static final String TAG = "FeedActivity";

    Boolean refresh = false;

    List<Post> allPosts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMainActivity();
            }
        });

        final FeedAdapter feedAdapter = new FeedAdapter(this, allPosts);
        binding.rvPosts.setAdapter(feedAdapter);
        binding.rvPosts.setLayoutManager(new LinearLayoutManager(this));
        queryPosts(feedAdapter, refresh);

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                refresh = true;
                queryPosts(feedAdapter, refresh);

            }
        });
        // Configure the refreshing colors
        binding.swipeContainer.setColorSchemeResources(android.R.color.darker_gray);

    }


    private void queryPosts(final FeedAdapter feedAdapter, final Boolean refreshFeed){
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // include data referred by user key
        query.include(Post.KEY_USER);
        // limit query to latest 20 items
        query.setLimit(20);
        // order posts by creation date (newest first)
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e!=null){
                    Log.e(TAG, "Issue with getting posts");
                    return;
                }
                for (Post post:posts){
                    Log.i(TAG, "Post: " + post.toString());
                }
                if(refreshFeed){
                    allPosts.clear();

                }
                allPosts.addAll(posts);
                feedAdapter.notifyDataSetChanged();
                refresh = true;
                binding.swipeContainer.setRefreshing(false);

            }
        });
    }
    private void goMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}