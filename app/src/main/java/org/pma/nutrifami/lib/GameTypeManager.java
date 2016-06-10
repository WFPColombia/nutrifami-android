package org.pma.nutrifami.lib;

import android.content.Context;

import org.pma.nutrifami.R;
import org.pma.nutrifami.model.UnitType;

/**
 * Created by Peter Juras on 10.06.16.
 */

public class GameTypeManager {
    private static GameTypeManager mInstance;
    public static GameTypeManager getInstance() {
        if (mInstance == null) {
            mInstance = new GameTypeManager();
        }
        return mInstance;
    }

    public String getGameDescription(Context context, String gameType) {
        UnitType type = UnitType.valueOf(gameType);
        switch (type) {
            case Pairs:
                return context.getString(R.string.game_pairs_description);
            case Swipe:
                return context.getString(R.string.game_swipe_description);
            case TextQuiz:
                return context.getString(R.string.game_quiz_description);
            case PictureQuiz:
                return context.getString(R.string.game_picture_quiz_description);
            default:
                return null;
        }
    }

    public String getGameTitle(Context context, String gameType) {
        UnitType type = UnitType.valueOf(gameType);
        switch (type) {
            case Pairs:
                return context.getString(R.string.game_pairs_title);
            case Swipe:
                return context.getString(R.string.game_swipe_title);
            case TextQuiz:
                return context.getString(R.string.game_quiz_title);
            case PictureQuiz:
                return context.getString(R.string.game_picture_quiz_title);
            default:
                return null;
        }
    }
}
