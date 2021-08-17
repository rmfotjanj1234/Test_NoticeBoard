package com.example.test_noticeboard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_noticeboard.Post;
import com.example.test_noticeboard.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    ArrayList<Post> list;
    private OnItemClickListener mListener = null;


    public PostAdapter(ArrayList<Post> list){
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        if(mListener != null){
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }

        void onBind(Post post) {
            titleTV.setText(post.getTitle());
            writerTV.setText(post.getWriter());
            SimpleDateFormat sdfCurrent = new SimpleDateFormat ("yyyy-mm-dd hh:mm:ss");
            String time = sdfCurrent.format(post.getTimestamp());
            timeTV.setText(time);
            clickTV.setText(post.getClcik());
            likeTV.setText(post.getLike());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    public void setOnItemListener(OnItemClickListener listener){
        this.mListener = listener;
    }
}
