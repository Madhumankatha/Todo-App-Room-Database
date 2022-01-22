package com.madhumankatha.todoapp;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class DBClient {

    private Context mCtx;
    private static DBClient mInstance;

    private AppDatabase appDatabase;

    public DBClient(Context mCtx) {
        this.mCtx = mCtx;

        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class,"TodoApp")
                .allowMainThreadQueries()
                .build();
    }

    public static synchronized DBClient getInstance(Context context) {
        if (mInstance == null){
            mInstance = new DBClient(context);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
