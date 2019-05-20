package com.cyberspace.payment.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class Builder {

    private static Database database;
    private static final Object LOCK = new Object();

    public synchronized static Database getDatabase(Context context){
        if(database == null) {
            synchronized (LOCK) {
                if (database == null) {
                    database = Room.databaseBuilder(context,
                            Database.class, "live_database_master_v1").build();
                }
            }
        }
        return database;
    }
}
