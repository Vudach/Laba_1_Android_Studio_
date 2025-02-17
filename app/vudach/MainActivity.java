package com.example.vudach;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textCounter;
    private Button buttonClick;
    private int count = 0; // Количество кликов
    private static final String PREFS_NAME = "ClickerPrefs"; // Имя файла настроек
    private static final String KEY_COUNT = "click_count"; // Ключ для сохранения

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCounter = findViewById(R.id.textCounter);
        buttonClick = findViewById(R.id.buttonClick);

        // Загружаем сохраненное значение
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        count = prefs.getInt(KEY_COUNT, 0);
        updateCounter();

        // Обработчик кликов
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                updateCounter();
                saveCount();
            }
        });
    }

    // Метод для обновления текста на экране
    private void updateCounter() {
        textCounter.setText("Клики: " + count);
    }

    // Метод для сохранения счетчика
    private void saveCount() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_COUNT, count);
        editor.apply();
    }
}
