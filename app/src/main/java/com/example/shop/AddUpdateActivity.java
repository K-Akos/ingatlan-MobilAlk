package com.example.shop;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AddUpdateActivity extends AppCompatActivity {
    private Context mContext;
    private int lastPosition = -1;
    private static final int SECRET_KEY = 99;
    int tag =1;
    EditText telepulesName;

    EditText ingatlanTerulet;
    EditText ingatlanErtek;
    private NotificationHelper mNotificationHelper;
    private static final String LOG_TAG = AddUpdateActivity.class.getName();
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    ImageView imageView;
    private IngatlanDAO dao;
    Ingatlan ing = new Ingatlan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update);
        mNotificationHelper = new NotificationHelper(this);
        //int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        //userNameEditText = findViewById(R.id.userNameEditText);
        telepulesName = findViewById(R.id.telepulesName);
        ingatlanTerulet = findViewById(R.id.ingatlanTerulet);
        ingatlanErtek = findViewById(R.id.ingatlanErtek);
        imageView = (ImageView) findViewById(R.id.imageView);



    }
    public void add(View view){
        Intent intent = new Intent(this, ListActivity.class);
        mNotificationHelper.send("Sikeres hozzáadás");
        String telepules = telepulesName.getText().toString();
        String terulet = ingatlanTerulet.getText().toString();
        String ertek = ingatlanErtek.getText().toString();


        //this.ing.setLocation(telepulesName.getText().toString());
        //this.ing.setTerulet(ingatlanTerulet.getText().toString());
        //this.ing.setPrice(ingatlanErtek.getText().toString());
        //Log.i(LOG_TAG, this.ing.toString());
        //dao.insert(this.ing);
        //Log.i(LOG_TAG, dao.getIngatlanList().toString());
        startActivity(intent);
    }
    public void back(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    public void openCamera(View view) {
        checkUserPermission();
    }
    public void takePicture(){
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, tag);
    }
    void checkUserPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }

        takePicture();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == tag && resultCode == RESULT_OK) {
            Bundle b = data.getExtras();
            Bitmap img = (Bitmap) b.get("data");
            imageView.setImageBitmap(img);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart");
    }


}

