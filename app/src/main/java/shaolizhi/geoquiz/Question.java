package shaolizhi.geoquiz;

/**
 * MVC设计模式：
 * Question实际上是一个JavaBean类，或者叫POJO类，但是在《Android权威指南中》，提出的MVC设计模式里，把Question看作Model，也就是M。
 *
 * M: JavaBean（POJO）
 * V: layout(xml)
 * C: Activity
 */
public class Question {
    private int questionTextResId;

    private boolean isAnswerTrue;

    public Question(int questionTextResId, boolean isAnswerTrue) {
        this.questionTextResId = questionTextResId;
        this.isAnswerTrue = isAnswerTrue;
    }

    public int getQuestionTextResId() {
        return questionTextResId;
    }

    public void setQuestionTextResId(int questionTextResId) {
        this.questionTextResId = questionTextResId;
    }

    public boolean isAnswerTrue() {
        return isAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        isAnswerTrue = answerTrue;
    }
}
