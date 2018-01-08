package com.iu33.lanjutanpertama;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.iu33.lanjutanpertama.helper.MyFunction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends MyFunction {

    @BindView(R.id.btncamera)
    Button btncamera;
    @BindView(R.id.btnshow)
    Button btnshow;
    @BindView(R.id.imgshow)
    ImageView imgshow;
    Uri lokasifile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        //PERMISSION

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.CAMERA},
                        110);
            }
            return;
        }
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());
    }

    @OnClick({R.id.btncamera, R.id.btnshow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btncamera:
                //Buat Folder
                String folderKamera = "photoku"; //photoku = nama folder
                File file = new File(Environment.getExternalStorageDirectory(), folderKamera);
                if (!file.exists()) {
                    file.mkdir();
                }
                File file1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + folderKamera + "/PIC" + currentDate() + ".jpg");
                //akses kamera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                lokasifile = Uri.fromFile(file1);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, lokasifile);
                startActivityForResult(intent, 1);
                break;
            case R.id.btnshow:
                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, 2);
                break;
        }
    }

    //Method untuk informasi
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1) {
            if (resultCode == RESULT_OK) {
                myToast("Berhasil menyimpan gambar \n  + lokasi file" + lokasifile.toString());
            } else if (resultCode== RESULT_CANCELED) {
                myToast("cancel");
            } else {
                myToast("Gagal mengambil gambar");
            }
        } else if(requestCode==2){
            if(resultCode == RESULT_OK){
                Uri lokasigambar = data.getData();
                InputStream inputStream = null;
                try {
                    inputStream = getContentResolver().openInputStream(lokasigambar);
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgshow.setImageBitmap(bitmap);
            } else if (resultCode ==RESULT_CANCELED){
                myToast("cancel");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
