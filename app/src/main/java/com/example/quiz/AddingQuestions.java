package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddingQuestions extends AppCompatActivity {
    Button optionConfirm;
    String correctOption;
    String option;
    List<Question> questions;
    int quizScore;
    RadioButton option_A;
    RadioButton option_B;
    RadioButton option_C;
    RadioButton option_D;
    RadioGroup radioGroup;
    TextView questionLabels;

    //Questions can only be added here...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        optionConfirm = findViewById(R.id.confirm);
        questionLabels = findViewById(R.id.lblPergunta);
        option_A = findViewById(R.id.optionA);
        option_B = findViewById(R.id.optionB);
        option_C = findViewById(R.id.optionC);
        option_D = findViewById(R.id.optionD);
        quizScore = 0;
        radioGroup = findViewById(R.id.radioGroup);

        questions = new ArrayList<Question>(){
            {
                add(new Question("Who invented Java Programming?", "B", "Guido van Rossum", "James Gosling","Dennis Ritchie", "Bjarne Stroustrup"));
                add(new Question("Which component is used to compile, debug and execute the java programs?", "C", "Polymorphism", "Inheritance","Compilation", "Encapsulation"));
                add(new Question("Which of the following is not an OOPS concept in Java?", "C", "JRE", "JIT","JDK", "JVM"));
                add(new Question("Which of these cannot be used for a variable name in Java?", "C", "identifier & keyword", "identifier","keyword", "none of the mentioned"));
                add(new Question("What is the extension of java code files?", "D", ".js", ".txt",".class", ".java"));
                add(new Question("Which environment variable is used to set the java path?", "D", "MAVEN_Path", "JavaPATH","JAVA", "JAVA_HOME"));
                add(new Question("What is Truncation in Java?", "D", "Floating-point value assigned to a Floating type", "Floating-point value assigned to an integer type","Integer value assigned to floating type", "Integer value assigned to floating type"));
                add(new Question("Which of these are selection statements in Java?", "D", "break", "continue","for()", "if()"));
                add(new Question("Which of the following is a superclass of every class in Java?", "C", "ArrayList", "Abstract class","Object class", "String"));
                add(new Question("Which of the below is not a Java Profiler?", "C", "JProfiler", "Eclipse Profiler","JVM", "JConsole"));
            }
        };

        getQuestion();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        getQuestion();
    }

    public void getCorrectOption(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.optionA:
                option ="A";
                break;

            case R.id.optionB:
                option ="B";
                break;

            case R.id.optionC:
                option ="C";
                break;

            case R.id.optionD:
                option ="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(checkQuestion(option));

    }

    private Intent checkQuestion(String Answer){
        Intent screen;
        if(Answer.equals(correctOption)) {
            this.quizScore += 1;
            screen = new Intent(this, RightAnswerBuzz.class);

        }else {
            screen = new Intent(this, WrongAnswerBuzz.class);
        }

        return screen;
    }

    private void getQuestion(){
        if(questions.size() > 0) {
            Question q = questions.remove(0);
            questionLabels.setText(q.getNewQuestion());
            List<String> answers = q.getOptions();

            option_A.setText(answers.get(0));
            option_B.setText(answers.get(1));
            option_C.setText(answers.get(2));
            option_D.setText(answers.get(3));

            correctOption = q.getCorrectOption();
        } else {
            Intent intent = new Intent(this, Result.class);
            intent.putExtra("score", quizScore);
            startActivity(intent);
            finish();
        }
    }
}
