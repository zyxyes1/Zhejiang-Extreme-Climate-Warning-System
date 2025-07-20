package com.example.zhejiangheat15places;

import android.os.Build;
import android.text.Html;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MeasuresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measures);

        double min = getIntent().getDoubleExtra("min_temp", 0);
        double max = getIntent().getDoubleExtra("max_temp", 0);

        TextView tvMeasures = findViewById(R.id.tv_measures);
        StringBuilder measures = new StringBuilder();

        if (min < 12) {
            measures.append(parseHtml(R.string.low_temp_measures));
        }
        if (max >= 33) {
            if (measures.length() > 0) {
                measures.append(parseHtml("<br><br>"));
            }
            measures.append(parseHtml(R.string.high_temp_measures));
        }
        if (measures.length() == 0) {
            tvMeasures.setText(R.string.no_extreme_measures);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvMeasures.setText(Html.fromHtml(measures.toString(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                tvMeasures.setText(Html.fromHtml(measures.toString()));
            }
        }

        // 返回按钮
        Button btnReturn = findViewById(R.id.btn_return);
        btnReturn.setOnClickListener(v -> finish());
    }

    private String parseHtml(String html) {
        return html;
    }

    private String parseHtml(int resId) {
        return getString(resId);
    }
}



