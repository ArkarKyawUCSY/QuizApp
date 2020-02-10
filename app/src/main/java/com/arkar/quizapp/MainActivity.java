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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String SCORE_KEY = "SCORE";
    private final String INDEX_KEY = "INDEX";
    private TextView mQuestion;
    private TextView mAnswer;
    private ProgressBar progressBar;
    private int mQuestionIndex = 0;
    private int mQuizQuestion;
    private int mUserScore;

    private QuizModel[] quizModelAry = new QuizModel[] {

        new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,true),
            new QuizModel(R.string.q3,false),
            new QuizModel(R.string.q4, true),
            new QuizModel(R.string.q5, false),
            new QuizModel(R.string.q6, true),
            new QuizModel(R.string.q7, false),
            new QuizModel(R.string.q8, true),
            new QuizModel(R.string.q9, false),
            new QuizModel(R.string.q10, true),
    };

    final int USER_PROGRESS = (int) Math.ceil(100 / quizModelAry.length);
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
            mAnswer.setText(mUserScore + "");
            QuizModel q1 = quizModelAry[mQuestionIndex];
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
        QuizModel q1 = quizModelAry[mQuestionIndex];
        mQuestion.setText(q1.getmQuestion());


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnTrue:
                        changeQuestion();
                        evaluateUserAnswer(true);
                        break;
                    case R.id.btnFalse:
                        changeQuestion();
                        evaluateUserAnswer(false);
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
            AlertDialog.Builder alertBuild = new AlertDialog.Builder(this);
            alertBuild.setTitle("The quiz is  Complete!!!");
            alertBuild.setCancelable(false);
            alertBuild.setMessage("Your Score is " + mUserScore);
            alertBuild.setPositiveButton("Finish the Quizz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertBuild.show();
        }
        mQuizQuestion = quizModelAry[mQuestionIndex].getmQuestion();
        mQuestion.setText(mQuizQuestion);
        progressBar.incrementProgressBy(USER_PROGRESS);
        mAnswer.setText(mUserScore + "");
    }
    private void evaluateUserAnswer(boolean userGuess) {
        boolean currentQuestionAnswer = quizModelAry[mQuestionIndex].getmAnswer();
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionIndex);
    }
}
