package com.easymvp.simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.easymvp.simple.core.DoubleDefault0Adapter;
import com.easymvp.simple.core.IntegerDefault0Adapter;
import com.easymvp.simple.core.LongDefault0Adapter;
import com.easymvp.simple.core.SimpleSqlite;
import com.easymvp.simple.pojo.Tab;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cc.easyandroid.easysqlite.abs.DataAccesObject;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    ListView listview;
    private String[] str_name = new String[]{"得到String 结果", "Gson1得到java对象", "Gson2得到java对象", "下载"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str_name));
        listview.setOnItemClickListener(this);
        SimpleSqlite simpleSqlite = new SimpleSqlite(this.getApplicationContext());
        DataAccesObject<Tab> dataAccesObject = simpleSqlite.getDao("a1");
        try {
            dataAccesObject.insert(new Tab());
            Tab tab1 = dataAccesObject.findById("d");
            Tab tab2 = dataAccesObject.findById("f");
            System.out.println("easyandroid tab1=" + tab1.getXxx());
            System.out.println("easyandroid tab2=" + tab2);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        simpleSqlite.


    }

    public Gson buildGson() {
        Gson gson = null;
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                    .registerTypeAdapter(long.class, new LongDefault0Adapter())
                    .create();
        }
        return gson;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startStringActivity();
                break;
            case 1:
                startGsonActivity();
                break;
            case 2:
                startGson2Activity();
                break;
            case 3:
                downLoadActivity();

                break;
        }
    }

    private void downLoadActivity() {
//        startActivity(new Intent(this, DownLoadActivity.class));
    }

    private void startGsonActivity() {
//        startActivity(new Intent(this, GsonActivity.class));
    }

    private void startGson2Activity() {
        startActivity(new Intent(this, Gson2Activity.class));
    }

    private void startStringActivity() {
        startActivity(new Intent(this, StringActivity.class));
    }
}
