package org.pma.nutrifami.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.pma.nutrifami.model.unit.IntroductionUnit;
import org.pma.nutrifami.model.unit.SwipeUnit;
import org.pma.nutrifami.model.unit.Unit;
import org.pma.nutrifami.model.unit.UnitType;

import java.lang.reflect.Type;

/**
 * Created by juras on 13-Jun-16.
 */

public class GsonUnitDeserializer implements JsonDeserializer<Unit> {

    public static Gson createGson() {
        return new GsonBuilder()
            .registerTypeAdapter(Unit.class, new GsonUnitDeserializer())
            .create();
    }

    @Override
    public Unit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) json;

        UnitType unitType = UnitType.valueOf(jsonObject.get("type").getAsString());
        switch (unitType) {
            case Introduction:
                final String title = jsonObject.get("title").getAsString();
                final String description = jsonObject.get("description").getAsString();
                return new IntroductionUnit(title, description);
            case Swipe:
                final String question = jsonObject.get("question").getAsString();
                final JsonArray answersArray = jsonObject.get("answers").getAsJsonArray();
                final String[] answers = new String[answersArray.size()];
                for (int i = 0; i < answers.length; i++) {
                    answers[i] = answersArray.get(i).getAsString();
                }
                final int correctAnswer = jsonObject.get("correctAnswer").getAsInt();
                final String answerExplanation = jsonObject.get("answer-explanation").getAsString();
                return new SwipeUnit(question, answers, correctAnswer, answerExplanation, null);
        }
        return null;
    }
}
