package com.example.test_noticeboard.adapters;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_noticeboard.Post;
import com.example.test_noticeboard.R;
import com.example.test_noticeboard.activity.ViewPostActivity;
import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    Context mContext;
    ArrayList<Post> list;


    public PostAdapter(Context mContext, ArrayList<Post> list){
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        PostViewHolder postViewHolder = new PostViewHolder(view);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.onBind(list.get(position));

        final int posi=holder.getAdapterPosition();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posi!= RecyclerView.NO_POSITION){
                    Intent intent=new Intent(v.getContext(), ViewPostActivity.class);
                    intent.putExtra("post", list.get(posi));
                    mContext.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTV, writerTV, timeTV, clickTV, likeTV;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            writerTV = itemView.findViewById(R.id.writerTV);
            timeTV = itemView.findViewById(R.id.timeTV);
            clickTV = itemView.findViewById(R.id.clickTV);
            likeTV = itemView.findViewById(R.id.likeTV);
        }

        void onBind(Post post) {
            titleTV.setText(post.getTitle());
            writerTV.setText(post.getWriter_nickname());
            long timestamp = post.getTimestamp();
            Date date = new Date(timestamp * 1000);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            timeTV.setText(simpleDateFormat.format(date));
            clickTV.setText("조회수 " + Integer.toString(post.getClcik()));
            likeTV.setText("좋아요 " + Integer.toString(post.getLike()));
        }
    }
}
