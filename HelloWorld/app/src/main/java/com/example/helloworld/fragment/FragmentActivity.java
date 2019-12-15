package com.example.helloworld.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_left;
    private Button btn_setMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        btn_left = findViewById(R.id.btn_left);
        btn_left.setOnClickListener(this);

        btn_setMsg = findViewById(R.id.btn_setMsg);
        btn_setMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.right_fragment);
                ((IFragment)fragment).setMsg("new message");

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left:
                replaceFragment(new AnotherRightFragment());
                break;

            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.right_layout, fragment);
        //将fragment加入返回栈
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
