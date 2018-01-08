package com.iu33.lanjutanpertama.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.iu33.lanjutanpertama.R;

/**
 * Created by hp on 12/4/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    MediaPlayer mediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm berbunyi",Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(context, R.raw.alarm);
        mediaPlayer.start();
    }
}
