package com.chen.science;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends TitleActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView(){
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_title);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        super.onClick(v);
        Intent intent;
        switch (v.getId()) {
            case R.id.button:
                intent = new Intent(MainActivity.this, AreaList.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(MainActivity.this, Above.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
