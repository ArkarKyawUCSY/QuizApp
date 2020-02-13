package com.arkar.quizapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String SCORE_KEY = "SCORE";
    private final String INDEX_KEY = "INDEX";
    private TextView mQuestion;
    private TextView mAnswer;
    private ProgressBar progressBar;
    private int mQuestionIndex = 0;
    private int mUserScore;

    private QuizModel[] questionCollection = new QuizModel[] {

        new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4, false),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, false),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, false),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, false),
    };

    final int USER_PROGRESS = (int) Math.ceil(100 / questionCollection.length);
    private String TAG = "MainPage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQuestion = findViewById(R.id.txtQuestion);
        mAnswer = findViewById(R.id.txtAnswer);
        if (savedInstanceState != null) {
            Log.i(TAG, "Data Exists!!");
            mUserScore = savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex = savedInstanceState.getInt(INDEX_KEY);
            mAnswer.setText(mUserScore);
            QuizModel q1 = questionCollection[mQuestionIndex];
            mQuestion.setText(q1.getmQuestion());
            Log.i(TAG, "Data Exists!! mUserScore " + mUserScore);
            Log.i(TAG, "Data Exists!! mQuestionIndex " + mQuestionIndex);
        } else {
            Log.i(TAG, "Data NOt Exists!!");
            mUserScore = 0;
            mQuestionIndex = 0;
        }

        Button btnTrue = findViewById(R.id.btnTrue);
        Button btnFalse = findViewById(R.id.btnFalse);
        progressBar = findViewById(R.id.quizPB);
        QuizModel q1 = questionCollection[mQuestionIndex];
        mQuestion.setText(q1.getmQuestion());
        mAnswer.setText(mUserScore + "");

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnTrue:
                        evaluateUserAnswer(true);
                        changeQuestion();

                        break;
                    case R.id.btnFalse:
                        evaluateUserAnswer(false);
                        changeQuestion();
                        break;
                    default:
                        break;
                }
            }
        };
        btnTrue.setOnClickListener(listener);
        btnFalse.setOnClickListener(listener);
    }
    private void changeQuestion() {
        mQuestionIndex = (mQuestionIndex + 1) % 10;
        Log.i("MainActivity","AAA "+ mQuestionIndex);

        if (mQuestionIndex == 0) {
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setTitle("The quiz is  Complete!!!");
            quizAlert.setCancelable(false);
            quizAlert.setMessage("Your Score is " + mUserScore);
            quizAlert.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quizAlert.show();
        }
        int mQuizQuestion = questionCollection[mQuestionIndex].getmQuestion();
        mQuestion.setText(mQuizQuestion);
        progressBar.incrementProgressBy(USER_PROGRESS);
        mAnswer.setText(mUserScore + "");

    }
    private void evaluateUserAnswer(boolean userGuess) {
        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].getmAnswer();
        if(currentQuestionAnswer == userGuess) {
            Toast.makeText(this, R.string.correct_text, Toast.LENGTH_SHORT).show();
            mUserScore = mUserScore + 1;
        } else {
            Toast.makeText(this, R.string.incorrect_text, Toast.LENGTH_SHORT).show();
        }

        Log.i(TAG, "EvaluateUserAnswer mUserScore " + mUserScore);
        Log.i(TAG, "EvaluateUserAnswer mQuestion" + mQuestionIndex);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionIndex);
    }
}
