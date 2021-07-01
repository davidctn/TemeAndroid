package com.example.secondhomework.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondhomework.R;
import com.example.secondhomework.interfaces.OnUserItemClick;
import com.example.secondhomework.models.Post;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private ArrayList<Post> postList;
    private OnUserItemClick onUserItemClick;

    public PostAdapter(ArrayList<Post> postList){
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_post,parent,false);
        PostViewHolder postViewHolder = new PostViewHolder(view);
        return postViewHolder;
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.bind(post);
        //prepare loading next fragment
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        private TextView titleText;
        private TextView bodyText;

        public PostViewHolder(View view){
            super(view);
            titleText = view.findViewById(R.id.title_id);
            bodyText = view.findViewById(R.id.body_id);
        }

        public void bind(Post post){
            titleText.setText(post.getTitle());
            bodyText.setText(post.getBody());
        }
    }
}
