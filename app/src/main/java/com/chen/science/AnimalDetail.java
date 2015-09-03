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
public class AnimalDetail extends TitleActivity implements View.OnClickListener {

    private String animal;
    private String animalPy;
    private ImageView imageView;
    private TextView textView;

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
        animal = getIntent().getExtras().getString("animal");
        setTitle(animal);
        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView);
        animalPy = HanZiToPinYin.toPinYin(animal);
        loadData();
    }

    private void loadData(){
        try {
            String path = "pic/"+animalPy+".jpg";
            InputStream is = getResources().getAssets().open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(is);

            imageView.setImageBitmap(bitmap);

            path = "doc/" + animalPy + ".doc";
            is = getResources().getAssets().open(path);
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
