package com.zhoubigbo.dbflowone;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "DBFlow_DB";

    public static final int VERSION = 1;

}
