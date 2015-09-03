package com.chen.science;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chen.science.util.HanZiToPinYin;

import java.io.InputStream;
import java.util.List;

/**
 * Created by chenchi_94 on 2015/9/3.
 */
public class AnimalList extends TitleActivity implements View.OnClickListener{

    private String country;
    private List<String> animals;
    private ListView lv;

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
        setContentView(R.layout.animal_list);
        showBackwardView(R.string.button_backward, true);
        animals = getIntent().getExtras().getStringArrayList("list");
        country = getIntent().getExtras().getString("country");
        setTitle(country+""+getString(R.string.animal));
        lv = (ListView) findViewById(R.id.listView);

        lv.setAdapter(new ArrayAdapter<String>(this,
                R.layout.list_item, animals));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                TextView tv = (TextView) arg1.findViewById(R.id.text_item);
                Bundle mBundle = new Bundle();
                mBundle.putString("animal", tv.getText().toString());
                Intent intent = new Intent();
                intent.setClass(AnimalList.this, AnimalDetail.class);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
        initBackground();
    }

    private void initBackground(){
        try {
            country = HanZiToPinYin.toPinYin(country);
            String path = "pic/" + country + ".jpg";
            InputStream is = getResources().getAssets().open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            BitmapDrawable ob = new BitmapDrawable(getResources(), bitmap);
            lv.setBackground(ob);
        }catch (Exception e){
            Log.d("info", e.toString());
        }
    }
}
