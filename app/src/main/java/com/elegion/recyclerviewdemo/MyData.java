package com.elegion.recyclerviewdemo;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;

/**
 * Created by stepangoncarov on 03/07/14.
 */
@Table(name = "demo_data", id = BaseColumns._ID)
public class MyData extends Model {

    public static final String NAME = "name";
    @Column(name = NAME)
    String name;

    public MyData(String name) {
        this.name = name;
    }

    public static void deleteAll() {
        new Delete().from(MyData.class).execute();
    }

}
