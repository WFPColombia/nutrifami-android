package org.pma.nutrifami.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.pma.nutrifami.model.unit.IntroductionUnit;
import org.pma.nutrifami.model.unit.SwipeUnit;
import org.pma.nutrifami.model.unit.TextQuizUnit;
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
        UnitType unitType = UnitType.valueOf(json.getAsJsonObject().get("type").getAsString());
        switch (unitType) {
            case Introduction:
                return new Gson().fromJson(json, IntroductionUnit.class);
            case Swipe:
                return new Gson().fromJson(json, SwipeUnit.class);
            case TextQuiz:
                return new Gson().fromJson(json, TextQuizUnit.class);
            default:
                return null;
        }
    }
}
