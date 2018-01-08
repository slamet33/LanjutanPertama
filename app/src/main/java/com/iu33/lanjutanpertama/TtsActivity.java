package com.iu33.lanjutanpertama;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iu33.lanjutanpertama.helper.MyFunction;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TtsActivity extends MyFunction implements TextToSpeech.OnInitListener {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.btnSpeech)
    Button btnSpeech;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        ButterKnife.bind(this);
        tts = new TextToSpeech(c,this);
        }

    @OnClick(R.id.btnSpeech)
    public void onViewClicked() {
        String text = editText.getText().toString();
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    public void onInit(int i) {
        if (i==TextToSpeech.SUCCESS){
            Locale indo = new Locale("en_US", "ENG");
            int hasil =tts.setLanguage(Locale.ENGLISH);
            if (hasil == TextToSpeech.LANG_MISSING_DATA||hasil==TextToSpeech.LANG_NOT_SUPPORTED){
                myToast("Bahasa tidak mendung");
            } else {
                btnSpeech.setEnabled(true);
            }
        }else{
            myToast("TTS tidak Support");
        }
    }
}
