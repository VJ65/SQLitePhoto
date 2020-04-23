package com.example.sqlitephoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText imageDetailEt;
   private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private ImageView objectImageView;
    private Bitmap imageToStore;
    DatabaseHandler objectDatabaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            objectDatabaseHandler = new DatabaseHandler(this);



            imageDetailEt = findViewById(R.id.imageNameEt);
            objectImageView = findViewById(R.id.image);


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public void chooseImage(View view) {

        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");


            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent, PICK_IMAGE_REQUEST);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        try {

    if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null)
    {
     imageFilePath=data.getData();
        imageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
        objectImageView.setImageBitmap(imageToStore);

         Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show();

    }
}catch (Exception e) {
    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
}

    }
    public  void StoreImage(View view ){
        try {
            if(!imageDetailEt.getText().toString().isEmpty() && objectImageView.getDrawable()!=null && imageToStore!=null)
            {
                objectDatabaseHandler.storeImage(new ModelClass(imageDetailEt.getText().toString(),imageToStore));

            }else{
                Toast.makeText(this, "Please Select name & image", Toast.LENGTH_SHORT).show();
            }




        }catch (Exception e ){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
public  void mvShowActivity(View view){


        startActivity(new Intent(this, ShowImagesActivity.class));
}



}