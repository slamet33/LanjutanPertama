package com.iu33.lanjutanpertama;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;

import com.iu33.lanjutanpertama.helper.MyFunction;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends MyFunction {

    @BindView(R.id.btnvideo)
    Button btnvideo;
    Uri lokasifile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

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
    }

    @OnClick(R.id.btnvideo)
    public void onViewClicked() {
        String folderKamera = "videoku"; //photoku = nama folder
        File file = new File(Environment.getExternalStorageDirectory(), folderKamera);
        if (!file.exists()) {
            file.mkdir();
        }
        File file1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderKamera + "/VID" + currentDate() + ".MP4");
        //akses kamera
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        lokasifile = Uri.fromFile(file1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, lokasifile);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1) {
            if (resultCode == RESULT_OK) {
                myToast("Berhasil menyimpan video \n  + lokasi file" + lokasifile.toString());
            } else if (requestCode== RESULT_CANCELED) {
                myToast("cancel");
            } else {
                myToast("Gagal mengambil video");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
