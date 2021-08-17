package com.example.test_noticeboard.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.test_noticeboard.Post;
import com.example.test_noticeboard.R;
import com.example.test_noticeboard.adapters.PostAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    Button logoutBtn, writeBtn;
    Spinner sort_spinner;
    PostAdapter postAdapter;
    ArrayList<Post> mDatas;
    RecyclerView postRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutBtn = findViewById(R.id.logoutBtn);
        writeBtn = findViewById(R.id.writeBtn);
        sort_spinner = findViewById(R.id.sort_spinner);
        postRV = findViewById(R.id.postRV);

        logoutBtn.setOnClickListener(onClickListener);
        writeBtn.setOnClickListener(onClickListener);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        init();

        String[] items = getResources().getStringArray(R.array.sort_spinner_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sort_spinner.setAdapter(adapter);
        sort_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (items[position].equals("최신순")) {
                    Log.e("###","최신순");
                    timeSort();
                }
                if(items[position].equals("좋아요순")) {
                    Log.e("###","좋아요순");
                    likeSort();
                }
                if(items[position].equals("조회수순")){
                    Log.e("###","조회수순");
                    clickSort();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("###","최신순");
                timeSort();
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.logoutBtn:
                    mAuth.signOut();
                    myStartActivity(SignUpActivity.class);
                    break;
                case R.id.writeBtn:
                    myStartActivity(WritePostActivity.class);
                    break;
            }
        }
    };

    /* 최신순 정렬 */
    Comparator<Post> timeComparator = new Comparator<Post>() {
        @Override
        public int compare(Post a, Post b) {
            return (int) (b.getTimestamp() - a.getTimestamp());
        }
    };
    public void timeSort() {
        mDatas = new ArrayList<>();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            mDatas.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Post post = document.toObject(Post.class);
                                mDatas.add(post);
                            }
                        } else {
                        }
                        Collections.sort(mDatas, timeComparator);
                        postAdapter = new PostAdapter(getApplicationContext(), mDatas);
                        postRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        postRV.setAdapter(postAdapter);
                    }
                });
    }

    /* 좋아요순 정렬 */
    Comparator<Post> likeComparator = new Comparator<Post>() {
        @Override
        public int compare(Post a, Post b) {
            return b.getLike() - a.getLike();
        }
    };
    public void likeSort() {
        mDatas = new ArrayList<>();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            mDatas.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Post post = document.toObject(Post.class);
                                mDatas.add(post);
                            }
                        } else {
                        }
                        Collections.sort(mDatas, likeComparator);
                        postAdapter = new PostAdapter(getApplicationContext(), mDatas);
                        postRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        postRV.setAdapter(postAdapter);
                    }
                });
    }

    /* 조회수순 정렬 */
    Comparator<Post> clickComparator = new Comparator<Post>() {
        @Override
        public int compare(Post a, Post b) {
            return b.getClcik() - a.getClcik();
        }
    };
    public void clickSort() {
        mDatas = new ArrayList<>();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            mDatas.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Post post = document.toObject(Post.class);
                                mDatas.add(post);
                            }
                        } else {
                        }
                        Collections.sort(mDatas, clickComparator);
                        postAdapter = new PostAdapter(getApplicationContext(), mDatas);
                        postRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        postRV.setAdapter(postAdapter);
                    }
                });
    }

    /* 로그인 상태에 따라 회원가입, 로그인, 회원정보 등록 연결 */
    public void init(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            //현재 로그인되어있지 않음.
            myStartActivity(LoginActivity.class);
        }
        else{
            //로그인 됨
            DocumentReference docRef = db.collection("users").document(mAuth.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.e("###", "회원정보 있음 : " + document.getData());
                        } else {
                            Log.e("###", "회원정보 없음 : " + document.getData());
                            myStartActivity(MemberInitActivity.class);
                        }
                    } else {
                        Log.d("MainActivity", "get failed with ", task.getException());
                    }
                }
            });
        }
    }

    public static void showToast(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}