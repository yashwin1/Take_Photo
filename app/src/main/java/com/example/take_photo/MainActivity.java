package com.example.take_photo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Binder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;     //Way to identify intent
    ImageView yashwinImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button yashwinButton = (Button) findViewById(R.id.yashwinButton);
        ImageView yashwinImageView = (ImageView) findViewById(R.id.yashwinImageView);

//      Disable the button if the user has no camera
        if(!hasCamera())
            yashwinButton.setEnabled(false);

    }


//  Check if the user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

//  Launching the camera
    public void launchCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//      Take a picture and pass results along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

//  If you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
//          Get the photo
            Bundle extras = data.getExtras();
            Bitmap  photo = (Bitmap) extras.get("data");
            yashwinImageView.setImageBitmap(photo);
        }
    }
}
