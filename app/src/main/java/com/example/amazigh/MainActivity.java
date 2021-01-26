package com.example.amazigh;

        import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button oefenen = (Button) findViewById(R.id.oefenen);
        Button quiz = (Button) findViewById(R.id.quiz);
        Button resultaten = (Button) findViewById(R.id.resultaten);
        Button over = (Button) findViewById(R.id.over);
        oefenen.setOnClickListener(this);
        quiz.setOnClickListener(this);
        resultaten.setOnClickListener(this);
        over.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.oefenen:
                openOefenen();
                break;
            case R.id.quiz:
                openQuiz();
                break;
            case R.id.resultaten:
                openResultaten();
                break;
            case R.id.over:
                openOver();
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
    }

    public void openOefenen() {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("type", "oefen");
        startActivity(intent);
    }

    public void openQuiz() {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("type", "quiz");
        startActivity(intent);
    }

    public void openResultaten() {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }

    public void openOver() {
        Intent intent = new Intent(this, OverActivity.class);
        startActivity(intent);
    }
}