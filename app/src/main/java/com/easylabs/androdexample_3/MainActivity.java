package com.easylabs.androdexample_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener{
    // Что ещё сделать :
    // Меню
    // ВОзможность игры с компьютером
    // Учет результатов
    // Возможность ввода имён игроков
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    Button bt5;
    Button bt6;
    Button bt7;
    Button bt8;
    Button bt9;

    Button[][] buttonsArray;

    final String X_CHAR = "X";
    final String O_CHAR = "O";
    final String SPACE_CHAR = " ";
    // Символ X
    final int PLAYER1 = 1;
    // Символ O
    final int PLAYER2 = 2;
    // Указывает на то, какой игрок ходи в данный момент
    int currentPlayer = PLAYER1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonsArray = new Button[3][3];

        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
        bt6 = (Button) findViewById(R.id.bt6);
        bt7 = (Button) findViewById(R.id.bt7);
        bt8 = (Button) findViewById(R.id.bt8);
        bt9 = (Button) findViewById(R.id.bt9);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);

        buttonsArray[0][0] = bt1;
        buttonsArray[0][1] = bt2;
        buttonsArray[0][2] = bt3;
        buttonsArray[1][0] = bt4;
        buttonsArray[1][1] = bt5;
        buttonsArray[1][2] = bt6;
        buttonsArray[2][0] = bt7;
        buttonsArray[2][1] = bt8;
        buttonsArray[2][2] = bt9;
    }



    @Override
    public void onClick(View view) {
        // Преобразовываем view элемент к типу Button
        Button bt = (Button) view;

        // Получаем текст из кнопки
        String buttonText = bt.getText().toString();

        // Проверяем, пустая ли кнопка
        if (buttonText.equals(SPACE_CHAR)) {
            Toast.makeText(this, "Данная ячейка занята", Toast.LENGTH_SHORT).show();
            // Прекращаем выполнение обработчика на нажатие
            return;
        }

        if (currentPlayer == PLAYER1) {
            bt.setText(X_CHAR);
            currentPlayer = PLAYER2;
        } else {
            bt.setText(O_CHAR);
            currentPlayer = PLAYER1;
        }

        String ch00 = buttonsArray[0][0].getText().toString();
        String ch01 = buttonsArray[0][1].getText().toString();
        String ch02 = buttonsArray[0][2].getText().toString();
        String ch10 = buttonsArray[1][0].getText().toString();
        String ch11 = buttonsArray[1][1].getText().toString();
        String ch12 = buttonsArray[1][2].getText().toString();
        String ch20 = buttonsArray[2][0].getText().toString();
        String ch21 = buttonsArray[2][1].getText().toString();
        String ch22 = buttonsArray[2][2].getText().toString();

        if (fieldIsFull()) {
            Toast.makeText(this, "Поле заполнено", Toast.LENGTH_SHORT).show();
            // Реализовать возможность запуска новой игры
            return;
        }

        testField(ch00, ch01, ch02);
        testField(ch10, ch11, ch12);
        testField(ch20, ch21, ch22);

        testField(ch00, ch10, ch20);
        testField(ch01, ch11, ch21);
        testField(ch02, ch12, ch22);

        testField(ch00, ch11, ch22);
        testField(ch02, ch11, ch20);
    }

    private int testField(String ch1, String ch2, String ch3) {
        if (ch1.equals(ch2) && ch2.equals(ch3) && !(ch1.equals(SPACE_CHAR))) {
            if (ch1.equals(X_CHAR)) {
                Toast.makeText(this, "Победил первый игрок", Toast.LENGTH_SHORT).show();
                startResultActivity(PLAYER1);
                return PLAYER1;
            } else {
                Toast.makeText(this, "Победил второй игрок", Toast.LENGTH_SHORT).show();
                startResultActivity(PLAYER2);
                return PLAYER2;
            }
        } else {
            // Никто не победил
            return 0;
        }
    }

    private boolean fieldIsFull() {
        boolean isFull = true;

        for (int i = 0; i < buttonsArray.length; i++) {
            for (int j = 0; j < buttonsArray[i].length; j++) {
                if (buttonsArray[i][j].getText().toString().equals(SPACE_CHAR)) {
                    isFull = false;
                    break;
                }
            }
        }

        return isFull;
    }

    private void startResultActivity(int winner) {
        Intent intent = new Intent(this, ResultActivity.class);

        if (winner == PLAYER1) {
            intent.putExtra("winner", "Первый игрок");
        } else {
            intent.putExtra("winner", "Второй игрок");
        }

        startActivity(intent);
    }
}

