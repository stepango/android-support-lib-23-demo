package com.elegion.recyclerviewdemo;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;

import java.util.List;

/**
 * Created by stepangoncarov on 03/07/14.
 */
public class DbUtils {

    public static <T extends Model> void bulkSave(List<T> list){
        ActiveAndroid.beginTransaction();
        try {
            for (T t : list) {
                t.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }

}
