package shaolizhi.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //控件
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button previousButton;
    private TextView questionTextView;
    private Button cheatButton;

    //常量
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEATER = 1;

    //数据库
    private Question[] questionBox = new Question[]{
            new Question(R.string.question_0, true),
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true),
            new Question(R.string.question_4, false),
    };

    private int currentIndex = 0;
    private boolean isCheater;

    //falseButton click event
    private void clickFalseButton() {
        checkAnswer(false);
    }

    //trueButton click event
    private void clickTrueButton() {
        checkAnswer(true);
    }

    //nextButton click event
    private void clickNextButton() {
        currentIndex = (currentIndex + 1) % questionBox.length;
        isCheater = false;
        getCurrentQuestion();
    }

    //previousButton click event
    private void clickPreviousButton() {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = currentIndex + questionBox.length;
        }
        getCurrentQuestion();
    }

    //cheatButton click event
    private void clickCheatButton() {
        boolean answerIsTrue = questionBox[currentIndex].isAnswerTrue();
        Intent intent = CheatActivity.newIntent(this, answerIsTrue);
        startActivityForResult(intent, REQUEST_CODE_CHEATER);
    }

    //---------------------------------------override method--------------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle(R.string.app_name);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        initView();
        initListener();

        getCurrentQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEATER) {
            if (data == null) {
                return;
            }
            Log.e("Result", String.valueOf(isCheater));
            isCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    //----------------------------------------private method--------------------------------------//
    private void getCurrentQuestion() {
        int question = questionBox[currentIndex].getQuestionTextResId();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean answer) {
        boolean trueAnswer = questionBox[currentIndex].isAnswerTrue();
        int toastTextResId;

        if (isCheater) {
            toastTextResId = R.string.judgment_toast;
        } else {
            if (answer == trueAnswer) {
                toastTextResId = R.string.correct_toast;
            } else {
                toastTextResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, toastTextResId, Toast.LENGTH_SHORT).show();
    }

    private void initListener() {
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTrueButton();
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickFalseButton();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNextButton();
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPreviousButton();
            }
        });
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCheatButton();
            }
        });
    }

    private void initView() {
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionTextView = findViewById(R.id.question_text_view);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);
        cheatButton = findViewById(R.id.cheat_button);
    }
}
