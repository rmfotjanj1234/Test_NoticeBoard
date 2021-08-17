package com.example.test_noticeboard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test_noticeboard.R;
import com.example.test_noticeboard.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MemberInitActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    EditText nicknameET;
    Button registBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberinit);

        nicknameET = findViewById(R.id.nicknameET);
        registBtn = findViewById(R.id.registBtn);

        registBtn.setOnClickListener(onClickListener);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.registBtn:
                    String nickname = nicknameET.getText().toString();

                    if(nickname.length() > 0){
                        User user = new User(nickname);

                        db.collection("users").document(mAuth.getUid()).set(user);
                        myStartActivity(MainActivity.class);
                    }
                    else{
                        showToast(MemberInitActivity.this, "닉네임을 입력하세요.");
                    }
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
