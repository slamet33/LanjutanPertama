package com.iu33.lanjutanpertama.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.iu33.lanjutanpertama.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFunction extends AppCompatActivity {
    public Context c;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        c = MyFunction.this;
    }

    public void myToast(String isiPesan) {
        Toast.makeText(c, isiPesan, Toast.LENGTH_SHORT).show();
    }

    public void myIntent(Class kelasTujuan) {
        startActivity(new Intent(c, kelasTujuan));
    }

    public String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void myAnimation(EditText editText){
        animation = AnimationUtils.loadAnimation(c, R.anim.animasigetar);
        editText.setAnimation(animation);
    }
}
