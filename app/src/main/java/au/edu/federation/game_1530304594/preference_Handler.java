package au.edu.federation.game_1530304594;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by bipin on 5/13/2018.
 */

public class preference_Handler {

    public static final String SHARED_PREFERENCE = "topScore";
    public static final String PLAYER = "player";
    public static final String SCORE = "score";

    // get the highest saved score
    public static Integer _getHighScore(Context applicationContext) {
        SharedPreferences pref = applicationContext.getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Integer highScore =pref.getInt(SCORE, 0);
        editor.apply();
        return highScore;
    }

    // get the highest scoring players name
    public static String _getHighScorer(Context applicationContext) {
        SharedPreferences pref = applicationContext.getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String hScorer =pref.getString(PLAYER, null);
        editor.apply();
        return hScorer;
    }

    // reset the saved high scorer and high score
    public static void _clearResult(Context applicationContest) {
        SharedPreferences pref = applicationContest.getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(SCORE, 0);
        editor.putString(PLAYER, null);
        editor.apply();
    }

    // save the new highest scorer and high score
    public static void _setNewScore(Context applicationContext, String name, int moves) {
        SharedPreferences pref = applicationContext.getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PLAYER, name);
        editor.putInt(SCORE, moves);
        editor.apply();
    }

}
