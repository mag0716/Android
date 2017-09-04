package com.github.mag0716.textspacing;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.andrognito.kerningview.KerningTextView;

public class MainActivity extends AppCompatActivity {

    private TextView defaultText;
    private TextView letterSpacingText;
    private KerningTextView kerningTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 20以下でも letterSpacing を使いたい
        // https://stackoverflow.com/questions/1640659/how-to-adjust-text-kerning-in-android-textview/16429758#16429758
        defaultText = findViewById(R.id.default_text);
        letterSpacingText = findViewById(R.id.letter_spacing_text);
        kerningTextView = findViewById(R.id.kerning_text);

        final float textSize = defaultText.getTextSize();
        final float letterSpacing = 1.2f;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            letterSpacingText.setLetterSpacing(letterSpacing);
        }
        kerningTextView.setKerningFactor(textSize * letterSpacing);
    }
}
