package com.iu33.lanjutanpertama;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.iu33.lanjutanpertama.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WifiActivity extends MyFunction {
    @BindView(R.id.wifi)
    Switch wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);
        wifi.setChecked(status());
        //wifi memiliki setonclick sendiri
        wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                wifichangestatus(b);
            }
        });
    }

    private void wifichangestatus(boolean b) {
        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE)
                    != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CHANGE_WIFI_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_WIFI_STATE,
                                Manifest.permission.CHANGE_WIFI_STATE},
                        110);


            }
            return;
        } else if (b == true && !manager.isWifiEnabled()) {
            myToast("Wifi aktif");
            manager.setWifiEnabled(true);
        } else if (b == false && manager.isWifiEnabled()) {
            myToast("Wifi Tidak Aktif");
            manager.setWifiEnabled(false);

        }
    }

    private boolean status() {
        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        return manager.isWifiEnabled();
    }
}
