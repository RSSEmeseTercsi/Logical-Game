package com.redsteedstudios.logicalgame.util;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 2/25/15.
 */
public class FileUtils
{
    private static FileUtils _fileUtils;

    public static FileUtils getInstance()
    {
        if (_fileUtils == null)
        {
            _fileUtils = new FileUtils();
        }

        return _fileUtils;
    }

    public ArrayList<String> getLevelJSONLocations(Context context)
    {
        ArrayList<String> locations = new ArrayList<String>();

        try {
            String[] list = context.getAssets().list("");
            for (String file : list)
            {
                if (file.contains(".json"))
                {
                    locations.add(file.replace(".json", ""));
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return locations;
    }
}
