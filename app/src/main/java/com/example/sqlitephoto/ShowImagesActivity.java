package com.example.sqlitephoto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ShowImagesActivity extends AppCompatActivity {



  private   DatabaseHandler objectDatabaseHandler;
         private    RecyclerView objectRV;
    private  RVAdapter objectRVAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);

        try {


            objectRV  = findViewById(R.id.imageRV);
            objectDatabaseHandler = new DatabaseHandler(this);

        }catch (Exception e ){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(View view){

        try {


            objectRVAdapter = new RVAdapter((objectDatabaseHandler.getAllImageData()));
            objectRV.setHasFixedSize(true);
            objectRV.setLayoutManager((new LinearLayoutManager(this)));
            objectRV.setAdapter(objectRVAdapter);

        }catch (Exception e ){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }





    }



}
