package org.pma.nutrifami.model;

/**
 * Created by Peter Juras on 01.06.16.
 */

public abstract class Unit {
    protected GameType mGameType;
    protected String mQuestion;
    protected String[] mAnswers;
    protected int mCorrectAnswer;
    protected String mAnswerExplanation;
    protected String mVoiceUri;

    public GameType getGameType() {
        return mGameType;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String[] getAnswers() {
        return mAnswers;
    }

    public int getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public String getAnswerExplanation() {
        return mAnswerExplanation;
    }

    public String getVoiceUri() {
        return mVoiceUri;
    }
}
