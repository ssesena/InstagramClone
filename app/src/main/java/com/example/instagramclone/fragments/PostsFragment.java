package com.example.instagramclone.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagramclone.LoginActivity;
import com.example.instagramclone.R;
import com.example.instagramclone.adapters.FeedAdapter;
import com.example.instagramclone.databinding.FragmentComposeBinding;
import com.example.instagramclone.databinding.FragmentPostsBinding;
import com.example.instagramclone.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class PostsFragment extends Fragment {

    private FragmentPostsBinding binding;
    public static final String TAG = "PostsFragment";

    Boolean refresh = false;

    protected List<Post> allPosts = new ArrayList<>();

    protected FeedAdapter feedAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPostsBinding.inflate(getLayoutInflater());
        feedAdapter = new FeedAdapter(getContext(), allPosts);
        binding.rvPosts.setAdapter(feedAdapter);
        binding.rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts(feedAdapter, refresh);

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                goLoginActivity();
            }
        });

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
        return binding.getRoot();
    }

    protected void queryPosts(final FeedAdapter feedAdapter, final Boolean refreshFeed) {
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
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts");
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.toString());
                }
                if (refreshFeed) {
                    allPosts.clear();

                }
                allPosts.addAll(posts);
                feedAdapter.notifyDataSetChanged();
                refresh = true;
                binding.swipeContainer.setRefreshing(false);
            }
        });
    }
    private void goLoginActivity() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

}