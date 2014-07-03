package com.elegion.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        populateDb();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ListFragment())
                    .commit();
        }

    }

    private void populateDb() {
        MyData.deleteAll();
        List<MyData> list = new ArrayList<MyData>(100);
        for (int i = 0; i < 100; i++) {
            list.add(new MyData("Demo " + i * 1000 + i));
        }
        DbUtils.bulkSave(list);
    }

}
