package com.example.test_noticeboard.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test_noticeboard.Post;
import com.example.test_noticeboard.R;
import com.example.test_noticeboard.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewPostActivity extends AppCompatActivity {
    FirebaseFirestore db;
    Post post;
    TextView titleTV, contentsTV, writer_nicnameTV, likeTV, clickTV, timeTV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpost);

        titleTV = findViewById(R.id.titleTV);
        contentsTV = findViewById(R.id.contentsTV);
        writer_nicnameTV = findViewById(R.id.writer_nicnameTV);
        likeTV = findViewById(R.id.likeTV);
        clickTV = findViewById(R.id.clickTV);
        timeTV = findViewById(R.id.timeTV);

        post = (Post) getIntent().getSerializableExtra("post");

        Log.e("###", post.getPostID());

        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("posts").document(post.getPostID());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                post = documentSnapshot.toObject(Post.class);
                post.setClcik(post.getClcik() + 1);
                db.collection("posts").document(post.getPostID()).set(post);

                titleTV.setText("제목 : " + post.getTitle());
                contentsTV.setText("내용 : " + post.getContents());
                writer_nicnameTV.setText("닉네임 : " + post.getWriter_nickname());
                likeTV.setText("좋아요 : " + Integer.toString(post.getLike()));
                clickTV.setText("조회수 : " + Integer.toString(post.getClcik()));
                long timestamp = post.getTimestamp();
                Date date = new Date(timestamp * 1000);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                timeTV.setText("시각 : " + simpleDateFormat.format(date));
            }
        });
    }
}
