package com.redsteedstudios.logicalgame.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Greg on 2/24/15.
 */
public class Progress
{
    public static final String PROGRESS_PREF = "progress_pref";
    public enum ESaveType
    {
        progress_score,
        progress_level,
        progress_last_level_played,
        progress_best_time,
        progress_none
    }

    private static Progress _progress;
    private HashMap<ESaveType, String> _progressMap;

    public static Progress getInstance()
    {
        if (_progress == null)
        {
            _progress = new Progress();
        }

        return _progress;
    }

    public Progress()
    {
        _progressMap = new HashMap<ESaveType, String>();
    }

    //todo: remove this crap
    private String enumToString(ESaveType type)
    {
        String retVal = "";
        switch (type)
        {
            case progress_score:
            {
                retVal = "score";
            } break;
            case progress_level:
            {
                retVal = "level";
            } break;
            case progress_best_time:
            {
                retVal = "best_time";
            } break;
            case progress_last_level_played:
            {
                retVal = "last_level_played";
            } break;
            case progress_none: break;
            default: break;
        }

        return retVal;
    }

    private ESaveType stringToEnum(String str)
    {
        String finalString = "progress_" + str;

        return ESaveType.valueOf(finalString);
    }

    public void setProgress(ESaveType type, String value)
    {
        _progressMap.put(type, value);
    }

    public String getProgress(ESaveType type)
    {
        if (!_progressMap.containsKey(type)){
            return null;
        }
        return String.valueOf(_progressMap.get(type));
    }

    //no rounding should really happen, because we save it as a rounded number already.
    public int getProgressInt(ESaveType type){ return Integer.valueOf(_progressMap.get(type)); }

    public void removeProgress(ESaveType type)
    {
        _progressMap.remove(type);
    }

    public void loadProgress(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PROGRESS_PREF, Context.MODE_PRIVATE);
        Map<String, ?> sharedPreferencesMap = sharedPreferences.getAll();

        _progressMap.clear();
        for (Map.Entry<String,?> entry : sharedPreferencesMap.entrySet())
        {
            _progressMap.put(stringToEnum(entry.getKey()), String.valueOf(entry.getValue()));
        }
    }

    public void saveProgress(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PROGRESS_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        sharedPreferencesEditor.clear();
        for(HashMap.Entry<ESaveType, String> entry : _progressMap.entrySet()) {
            if (entry != null)
            {
                sharedPreferencesEditor.putString(enumToString(entry.getKey()), String.valueOf(entry.getValue()));
            }
        }

        sharedPreferencesEditor.apply();
    }

}
