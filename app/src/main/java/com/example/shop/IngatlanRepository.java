package com.example.shop;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class IngatlanRepository {
    private IngatlanDAO dao;
    private LiveData<List<Ingatlan>> ingatlan;

    IngatlanRepository(Application application){
        IngatlanRoomDatabase db = IngatlanRoomDatabase.getInstance(application);
        dao = db.ingatlanDAO();
        ingatlan = dao.getIngatlanList();
    }
    public LiveData<List<Ingatlan>> getAllIngatlan(){
        return ingatlan;
    }

    public void insert(Ingatlan ingatlan){
        new Insert(this.dao).execute(ingatlan);
    }

    private static class Insert extends AsyncTask<Ingatlan, Void,Void>{

        private IngatlanDAO mDAO;

        Insert(IngatlanDAO dao){
            this.mDAO = dao;
        }

        @Override
        protected Void doInBackground(Ingatlan... ingatlans) {
            mDAO.insert(ingatlans[0]);
            return null;
        }
    }
}
