package com.example.shop;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.checkerframework.checker.nullness.qual.NonNull;

@Database(entities = {Ingatlan.class}, version = 1,exportSchema = false)
public abstract class  IngatlanRoomDatabase extends RoomDatabase {

    public abstract IngatlanDAO ingatlanDAO();

    private static IngatlanRoomDatabase Instance;

    public static IngatlanRoomDatabase getInstance(Context context){

        if (Instance == null){
            synchronized (IngatlanRoomDatabase.class){
                if (Instance == null){
                    Instance = Room.databaseBuilder(context.getApplicationContext(),
                            IngatlanRoomDatabase.class, "ingatlan_database").fallbackToDestructiveMigration()
                            .addCallback(populationCallback).build();
                }
            }
        }

        return Instance;
     }

     private static RoomDatabase.Callback populationCallback = new RoomDatabase.Callback() {
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            new InitDb(Instance).execute();
        }
     };

    private static class InitDb extends AsyncTask<Void, Void, Void>{
         private IngatlanDAO dao;
        //String[] datas = {"asd", "sad","asd"};
        Ingatlan ing = new Ingatlan("Mecsek","23","32",0);
        InitDb(IngatlanRoomDatabase db){
            this.dao = db.ingatlanDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.dao.deleteAll();


                this.dao.insert(ing);

                return null;

        }
    }
}
