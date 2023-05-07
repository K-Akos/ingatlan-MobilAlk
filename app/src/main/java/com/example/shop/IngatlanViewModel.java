package com.example.shop;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class IngatlanViewModel extends AndroidViewModel {
    private IngatlanRepository repository;
    private LiveData<List<Ingatlan>> ingatlan;

    public IngatlanViewModel(Application application){
        super(application);

        this.repository = new IngatlanRepository(application);
        this.ingatlan = repository.getAllIngatlan();

    }
    public LiveData<List<Ingatlan>> getAllIngatlan(){
        return this.ingatlan;
    }
    public void insert(Ingatlan ingatlan){
        this.repository.insert(ingatlan);
    }

}
