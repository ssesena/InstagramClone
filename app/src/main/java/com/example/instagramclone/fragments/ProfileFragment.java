package com.example.instagramclone.fragments;

import android.util.Log;

import com.example.instagramclone.adapters.FeedAdapter;
import com.example.instagramclone.databinding.FragmentPostsBinding;
import com.example.instagramclone.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    @Override
    protected void queryPosts(final FeedAdapter feedAdapter, final Boolean refreshFeed) {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // include data referred by user key
        query.include(Post.KEY_USER);
        // limit query to latest 20 items
        query.setLimit(20);
        // order posts by creation date (newest first)
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
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
            }
        });
    }
}
