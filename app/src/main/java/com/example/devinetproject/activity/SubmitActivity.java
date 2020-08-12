package com.example.devinetproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.devinetproject.R;

import java.util.Objects;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class SubmitActivity extends AppCompatActivity {

    //Définition des codes REQUEST afin de déclencher les bons morceaux de codes en fonction du
    // requestCode
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int RESULT_LOAD_IMAGE = 2;
    public static final int RESULT_ALLOW_PERMISSION = 3;
    private Intent dataIntent;
    private ImageView imageSubmit = findViewById(R.id.iv_photo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
    }

    public void onClickSubmitNewWord(View view) {
    }

    public void onClickLastWord(View view) {
    }

    /**
     * Méthode permettant de prendre une photo avec l'appareil photo du téléphone et de l'afficher
     * dans l'application
     * @param view Vue liée à l'activité
     */
    public void onClickTakePicture(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * Méthode permettant de charger une photo existante (depuis la gallery du téléphone) et de
     * l'afficher dans l'application
     * @param view Vue liée à l'activité
     */
    public void onCLickLoadPicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Code activé après le onCLickLoadPicture
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            imageSubmit.setImageBitmap(imageBitmap);
        }

        //Code activé après le onClickTakePicture
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            dataIntent = data;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    RESULT_ALLOW_PERMISSION
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RESULT_ALLOW_PERMISSION && grantResults[0] == PERMISSION_GRANTED) {
            Uri selectedImage = dataIntent.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            assert selectedImage != null;
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Bitmap imageLoad = BitmapFactory.decodeFile(picturePath);
            imageSubmit.setImageBitmap(imageLoad);
            cursor.close();
        }
    }
}
