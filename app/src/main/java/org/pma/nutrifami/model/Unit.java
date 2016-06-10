package org.pma.nutrifami.model;

/**
 * Created by Peter Juras on 01.06.16.
 */

public abstract class Unit {
    protected UnitType mUnitType;
    protected String mQuestion;
    protected String[] mAnswers;
    protected int mCorrectAnswer;
    protected String mAnswerExplanation;
    protected String mVoiceUri;

    public UnitType getGameType() {
        return mUnitType;
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
