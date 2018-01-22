package shaolizhi.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    //常量
    private static final String EXTRA_ANSWER_IS_TRUE = "shaolizhi.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "shaolizhi.geoquiz.answer_shown";

    private boolean isAnswerTrue;

    private TextView answerTextView;
    private Button showAnswerButton;

    //showAnswerButton click event
    private void clickShowAnswerButton() {
        if (isAnswerTrue) {
            answerTextView.setText(R.string.true_button);
        } else {
            answerTextView.setText(R.string.false_button);
        }
        setAnswerShownResult();
    }

    private void setAnswerShownResult() {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, true);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        isAnswerTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        initView();
        initListener();
    }

    private void initListener() {
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickShowAnswerButton();
            }
        });
    }

    private void initView() {
        answerTextView = findViewById(R.id.answer_text_view);
        showAnswerButton = findViewById(R.id.show_answer_button);
    }

    //----------------------------------------static method---------------------------------------//
    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent intent) {
        return intent.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
}
