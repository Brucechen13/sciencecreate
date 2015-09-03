package com.chen.science;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.science.util.HanZiToPinYin;

import org.textmining.text.extraction.WordExtractor;

import java.io.InputStream;

/**
 * Created by chenchi_94 on 2015/9/3.
 */
public class TextInfoView extends TitleActivity implements View.OnClickListener{

    private TextView textView;
    private String pyValue;
    private String type;

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
        setContentView(R.layout.animal_view);
        showBackwardView(R.string.button_backward, true);
        String value = getIntent().getExtras().getString("value");
        type = getIntent().getExtras().getString("type");
        setTitle(value);
        textView = (TextView)findViewById(R.id.textView);
        pyValue = HanZiToPinYin.toPinYin(value);
        loadData();
    }

    private void loadData(){
        try {
            String path = type + "/" + pyValue + ".doc";
            InputStream is = getResources().getAssets().open(path);
            WordExtractor extractor = null;
            String text = null;
            extractor = new WordExtractor();
            text = extractor.extractText(is);
            textView.setText(text);
        }catch (Exception e){
            Log.d("info", e.toString());
        }
    }
}
