package com.example.zhejiangheat15places;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private double minTemp;
    private double maxTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 接收数据
        Bundle extras = getIntent().getExtras();
        String city = extras.getString("city");
        String region = extras.getString("region");
        int year = extras.getInt("year");
        int month = extras.getInt("month");
        int day = extras.getInt("day");
        minTemp = extras.getDouble("min_temp");
        maxTemp = extras.getDouble("max_temp");
        double avgTemp = extras.getDouble("avg_temp");

        // 显示基础信息
        TextView tvResult = findViewById(R.id.tv_result);
        String resultText = String.format("城市：%s\n区县：%s\n预测日期：%d年%d月%d日\n\n当日预测气温：\n最低气温：%.1f℃\n最高气温：%.1f℃\n平均气温：%.1f℃",
                city, region, year, month, day, minTemp, maxTemp, avgTemp);
        tvResult.setText(resultText);

        // 初始化图表
        LineChart chart = findViewById(R.id.temperatureChart);
        setupChart(chart, year, minTemp, maxTemp, avgTemp);

        // 按钮点击事件
        findViewById(R.id.btn_measures).setOnClickListener(v -> showWeatherMeasures());
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }

    private void showWeatherMeasures() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("极端天气防范指南");

        // 创建可滚动的TextView
        TextView textView = new TextView(this);
        textView.setText(getFormattedMeasures());
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setTextSize(16);
        textView.setPadding(50, 30, 50, 30);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(textView);

        builder.setView(scrollView)
                .setPositiveButton("确定", null)
                .show();
    }

    private Spanned getFormattedMeasures() {
        StringBuilder content = new StringBuilder();

        if (minTemp < 12) {
            content.append(getString(R.string.low_temp_measures));
        }
        if (maxTemp > 34) {
            if (content.length() > 0) content.append("<br><br>");
            content.append(getString(R.string.high_temp_measures));
        }
        if (content.length() == 0) {
            return Html.fromHtml("<p>当前气温条件无需特殊防范措施</p>");
        }

        // 兼容不同Android版本的HTML解析
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(content.toString(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(content.toString());
        }
    }

    private void setupChart(LineChart chart, int baseYear, double min, double max, double avg) {
        List<Entry> minEntries = new ArrayList<>();
        List<Entry> maxEntries = new ArrayList<>();
        List<Entry> avgEntries = new ArrayList<>();

        // 生成30年数据（前15年+后15年）
        for (int offset = -10; offset <= 10; offset++) {
            int currentYear = baseYear + offset;
            float xValue = offset + 10;

            minEntries.add(new Entry(xValue, (float) (min + offset * 0.1)));
            maxEntries.add(new Entry(xValue, (float) (max + offset * 0.15)));
            avgEntries.add(new Entry(xValue, (float) (avg + offset * 0.12)));
        }

        // 创建数据集
        LineDataSet minDataSet = createDataSet(minEntries, "最低气温", Color.BLUE);
        LineDataSet maxDataSet = createDataSet(maxEntries, "最高气温", Color.RED);
        LineDataSet avgDataSet = createDataSet(avgEntries, "平均气温", Color.GREEN);

        // 组合数据
        LineData lineData = new LineData(minDataSet, maxDataSet, avgDataSet);
        chart.setData(lineData);

        // 配置图表样式
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setValueFormatter(new YearFormatter(baseYear));
        chart.getAxisRight().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.animateX(1000);
        chart.invalidate();
    }

    private LineDataSet createDataSet(List<Entry> entries, String label, int color) {
        LineDataSet dataSet = new LineDataSet(entries, label);
        dataSet.setColor(color);
        dataSet.setCircleColor(color);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(3f);
        dataSet.setValueTextSize(10f);
        dataSet.setDrawValues(false);
        return dataSet;
    }

    private static class YearFormatter extends ValueFormatter {
        private final int baseYear;

        YearFormatter(int baseYear) {
            this.baseYear = baseYear;
        }

        @Override
        public String getFormattedValue(float value) {
            int offset = (int) value - 10;
            return String.valueOf(baseYear + offset);
        }
    }
}