package com.chen.science;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AreaList extends TitleActivity implements View.OnClickListener{

    private Map<String, List<String>> animalBuf = new HashMap<String, List<String>>();
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

    private void initView(){
        setContentView(R.layout.area_list);
        setTitle(R.string.area);
        showBackwardView(R.string.button_backward, true);
        initJson();
        lv = (ListView) findViewById(R.id.listView);//得到ListView对象的引用 /*为ListView设置Adapter来绑定数据*/

        Set<String> areaBuf = animalBuf.keySet();
        Log.d("info", "" + areaBuf.size());
        String[] areas = new String[areaBuf.size()];
        areaBuf.toArray(areas);
        Log.d("info", "" + areas.length);
        lv.setAdapter(new ArrayAdapter<String>(this,
                R.layout.list_item, areas));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                TextView tv = (TextView) arg1.findViewById(R.id.text_item);
                Bundle mBundle = new Bundle();
                mBundle.putString("country", tv.getText().toString());
                mBundle.putStringArrayList("list", (ArrayList<String>) animalBuf.get(tv.getText()));
                Intent intent = new Intent();
                intent.setClass(AreaList.this, AnimalList.class);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });

    }

    private void initJson() {
        try {
            //将json文件读取到buffer数组中
            Log.d("info","start");
            InputStream is = this.getResources().openRawResource(R.raw.animal);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);

            //将字节数组转换为以GB2312编码的字符串
            String json = new String(buffer, "utf-8");

            //将字符串json转换为json对象，以便于取出数据
            JSONObject jsonObject = new JSONObject(json);

            //解析info数组，解析中括号括起来的内容就表示一个数组，使用JSONArray对象解析
            JSONArray array = jsonObject.getJSONArray("countries");

            Log.d("info",""+array.length());
            //StringBuffer操作字符串的一个高效类，保存解析的结果，以便于在TextView中显示
            //StringBuffer strBuf = new StringBuffer();
            //遍历JSONArray数组
            for (int i = 0; i < array.length(); i++) {
                //取出数组的第一项,表示州
                JSONObject item = array.getJSONObject(i);
                String country = item.getString("country");
                Log.d("info", country);
                JSONArray animals = item.getJSONArray("animals");
                Log.d("info", ""+animals.length());
                List<String> anis = new ArrayList<String>();
                //获取州的所有动物
                for (int j = 0; j < animals.length(); j++) {
                    item = animals.getJSONObject(j);
                    String animal = item.getString("name");
                    anis.add(animal);
                    Log.d("info", animal);
                }
                animalBuf.put(country,anis);
            }
        } catch (Exception e) {
            Log.d("info", e.toString());
        }
    }
}
