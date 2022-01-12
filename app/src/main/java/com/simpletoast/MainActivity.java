package com.simpletoast;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.toastlib.SimpleToast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleToast.success(getApplicationContext(), "This is an Info Message");
    }
}