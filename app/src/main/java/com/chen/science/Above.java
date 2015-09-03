package com.chen.science;

import android.os.Bundle;
import android.view.View;

/**
 * Created by chenchi_94 on 2015/9/3.
 */
public class Above extends TitleActivity implements View.OnClickListener{

    @Override
    protected void onBackward(View backwardView) {
        // TODO Auto-generated method stub
        super.onBackward(backwardView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_above);
        setTitle(R.string.above);
        showBackwardView(R.string.button_backward, true);
    }
}
