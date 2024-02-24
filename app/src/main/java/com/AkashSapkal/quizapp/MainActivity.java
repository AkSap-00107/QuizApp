package com.AkashSapkal.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text1;
    TextView text2;
    Button setA,setB,setC,setD;
    Button next_question,quit;

    int score=0;
    int total_questions=QuestionAns.questions.length;
    int CurrentQuesIndex= 0;
    String selected_ans="";

    public MainActivity(int contentLayoutId) {
        super(contentLayoutId);
    }

    public MainActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1=(TextView)findViewById(R.id.textView);
        text2=(TextView)findViewById(R.id.text2);

        setA=(Button)findViewById(R.id.button1);
        setB=(Button)findViewById(R.id.button2);
        setC=(Button)findViewById(R.id.button3);
        setD=(Button)findViewById(R.id.button4);

        next_question=(Button)findViewById(R.id.button5);
        quit=(Button)findViewById(R.id.button6);

        setA.setOnClickListener(this);
        setB.setOnClickListener(this);
        setC.setOnClickListener(this);
        setD.setOnClickListener(this);
        next_question.setOnClickListener(this);
        quit.setOnClickListener(this);

        text1.setText("Toatl Questions :"+total_questions);

        LoadNewQuestion();
    }

    @Override
    public void onClick(View view) {

        //setA.setBackgroundColor(Color.MAGENTA);
        setA.setBackgroundColor(Color.MAGENTA);
        setB.setBackgroundColor(Color.MAGENTA);
        setC.setBackgroundColor(Color.MAGENTA);
        setD.setBackgroundColor(Color.MAGENTA);

        Button ClickedButton =(Button) view;
        if (ClickedButton.getId() == R.id.button5 )
        {

            if (selected_ans.equals(QuestionAns.answers[CurrentQuesIndex]))
            {
                score++;
            }
            CurrentQuesIndex++;
            LoadNewQuestion();
        }
        else
        {
            selected_ans = ClickedButton.getText().toString();
            ClickedButton.setBackgroundColor(Color.GREEN);

        }

    }

    void LoadNewQuestion(){

        if (CurrentQuesIndex == total_questions)
        {
            FinishQuiz();
            return;
        }
        text2.setText(QuestionAns.questions[CurrentQuesIndex]);
        setA.setText(QuestionAns.options[CurrentQuesIndex][0]);
        setB.setText(QuestionAns.options[CurrentQuesIndex][1]);
        setC.setText(QuestionAns.options[CurrentQuesIndex][2]);
        setD.setText(QuestionAns.options[CurrentQuesIndex][3]);
    }
    void FinishQuiz(){
        String Passed_status ="";
        if(score>total_questions*0.5)
        {
            Passed_status ="PASS";
        }
        else
            Passed_status="FAILED";
        new AlertDialog.Builder(this)
                .setTitle(Passed_status)
                .setMessage("Score is "+score+" out of "+total_questions)
                .setPositiveButton("RESTART",(dialogInterface, i) -> Restart_quiz())
                .setCancelable(false)
                .show();
    }

    void Restart_quiz(){
        score=0;
        CurrentQuesIndex =0;
        LoadNewQuestion();
    }
}