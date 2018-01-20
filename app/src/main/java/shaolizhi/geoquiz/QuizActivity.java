package shaolizhi.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button previousButton;
    private TextView questionTextView;

    //数据库
    private Question[] questionBox = new Question[]{
            new Question(R.string.question_0, true),
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true),
            new Question(R.string.question_4, false),
    };

    private int currentIndex = 0;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle(R.string.app_name);

        initView();
        initListener();

        getCurrentQuestion();
    }

    private void getCurrentQuestion() {
        int question = questionBox[currentIndex].getQuestionTextResId();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean answer) {
        boolean trueAnswer = questionBox[currentIndex].isAnswerTrue();
        int toastTextResId;

        if (answer == trueAnswer) {
            toastTextResId = R.string.correct_toast;
        } else {
            toastTextResId = R.string.incorrect_toast;
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
    }

    private void initView() {
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionTextView = findViewById(R.id.question_text_view);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);
    }
}
