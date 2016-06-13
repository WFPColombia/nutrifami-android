package org.pma.nutrifami.model.unit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Peter Juras on 01.06.16.
 */

public abstract class Unit {
    @SerializedName("type")
    private final UnitType mUnitType;
    @SerializedName("question")
    private final String mQuestion;
    @SerializedName("answers")
    private final String[] mAnswers;
    @SerializedName("correctAnswer")
    private final int mCorrectAnswer;
    @SerializedName("answer-explanation")
    private final String mAnswerExplanation;
    private final String mVoiceUri;

    Unit(UnitType unitType, String question, String[] answers, int correctAnswer, String answerExplanation, String voiceUri) {
        this.mUnitType = unitType;
        this.mQuestion = question;
        this.mAnswers = answers;
        this.mCorrectAnswer = correctAnswer;
        this.mAnswerExplanation = answerExplanation;
        this.mVoiceUri = voiceUri;
    }

    public UnitType getGameType() {
        return this.mUnitType;
    }

    public String getQuestion() {
        return this.mQuestion;
    }

    public String[] getAnswers() {
        return this.mAnswers;
    }

    public int getCorrectAnswer() {
        return this.mCorrectAnswer;
    }

    public String getAnswerExplanation() {
        return this.mAnswerExplanation;
    }

    public String getVoiceUri() {
        return this.mVoiceUri;
    }
}
