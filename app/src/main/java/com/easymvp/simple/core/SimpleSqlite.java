package com.easymvp.simple.core;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.easymvp.simple.pojo.Tab;

import cc.easyandroid.easysqlite.EasySqliteHelper;
import cc.easyandroid.easysqlite.core.TableManager;


/**
 * Created by cgpllx on 2016/6/13.
 */
public class SimpleSqlite extends EasySqliteHelper {
    static final int VERSION = 24;//第一个版本要是0
    static final String DBNAME = "EasyEndroidDB";

    public SimpleSqlite(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db,TableManager tableManager) {
        System.out.println("easyandroid SimpleSqlite onCreate=" + "SimpleSqlite onCreate");
        try {
            tableManager.createTable("a1", Tab.class);
            tableManager.createTable("a2", Tab.class);
            tableManager.createTable("a3", Tab.class);
            tableManager.createTable("a4", Tab.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion,TableManager tableManager) {
        try {
            tableManager.dropTable(db, "a1", Tab.class);
            tableManager.dropTable(db, "a2", Tab.class);
            tableManager.dropTable(db, "a3", Tab.class);
            tableManager.dropTable(db, "a4", Tab.class);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
