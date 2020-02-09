package com.arkar.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mQuestion;
    private TextView mAnswer;
    private int mQuestionIndex = 0;
    private int mQuizQuestion;
    private QuizModel[] quizModelAry = new QuizModel[] {
        new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,true),
            new QuizModel(R.string.q3,false),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQuestion = findViewById(R.id.txtQuestion);
        Button btnTrue = findViewById(R.id.btnTrue);
        Button btnFalse = findViewById(R.id.btnFalse);

        QuizModel q1 = quizModelAry[mQuestionIndex];
        mQuestion.setText(q1.getmQuestion());


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnTrue:
                        changeQuestion();
                        Toast.makeText(MainActivity.this, "Click Button True", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btnFalse:
                        changeQuestion();
                        Toast.makeText(MainActivity.this, "Click Button False", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
        btnTrue.setOnClickListener(listener);
        btnFalse.setOnClickListener(listener);

        QuizModel quizModel = new QuizModel(R.string.q1,true);

    }
    private void changeQuestion() {
        mQuestionIndex = (mQuestionIndex + 1) % 10;
        mQuizQuestion = quizModelAry[mQuestionIndex].getmQuestion();
        mQuestion.setText(mQuizQuestion);
    }

}
