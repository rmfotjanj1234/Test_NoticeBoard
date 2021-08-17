package com.example.test_noticeboard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test_noticeboard.Post;
import com.example.test_noticeboard.R;
import com.example.test_noticeboard.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class WritePostActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    Button registBtn;
    EditText titleET, contentsET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writepost);

        registBtn = findViewById(R.id.registBtn);
        titleET = findViewById(R.id.titleET);
        contentsET = findViewById(R.id.contentsET);

        registBtn.setOnClickListener(onClickListener);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.registBtn:
                    String title = titleET.getText().toString();
                    String contents = contentsET.getText().toString();

                    DocumentReference docRef = db.collection("users").document(mAuth.getUid());
                    docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            User user = documentSnapshot.toObject(User.class);
                            Date date = new Date(System.currentTimeMillis());
                            Timestamp timestamp = new Timestamp(date);

                            Log.e("###", "WritePost nickname : " + user.getNickname());

                            Post post = new Post(mAuth.getUid(), title, contents, user.getNickname(), 0, 0, timestamp, null);

                            db.collection("posts").document(db.collection("Post").document().getId()).set(post);

                            showToast(WritePostActivity.this, "게시글 작성 완료");
                            myStartActivity(MainActivity.class);
                        }
                    });
                    break;
            }
        }
    };

    public static void showToast(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
