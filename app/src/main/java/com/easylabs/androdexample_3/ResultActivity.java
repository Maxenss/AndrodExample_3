package com.easylabs.androdexample_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    TextView tvWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvWinner = (TextView) findViewById(R.id.tvWinner);

        // Получаем intent, создавший Activity
        Intent intent = getIntent();

        String winner = "";
        try {
            // Пытаемся получить строку, с именем победителя
            winner = intent.getStringExtra("winner");
        } catch (Exception e) {
            Toast.makeText(this, "Возникла критическая ошиибка", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // Изменяем текст в текстовом поле tvWinner
        tvWinner.setText(winner);
    }
}
