package com.example.logicalgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.example.logicalgame.util.Progress;

/**
 * Created by Greg on 2/23/15.
 */
public class ScreenSettings extends Activity
{
    public static final String SETTINGS_PREF       = "settings";
    public static final String SETTINGS_SOUND_PREF = "settings_sound";
    public static final String SETTINGS_MUSIC_PREF = "settings_music";

    private Switch mToggleSound;
    private Switch mToggleMusic;
    private Button mBackButton;
    private Button mShareButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_settings);

        this.mToggleSound = (Switch)findViewById(R.id.switch_sound_settings);
        this.mToggleMusic = (Switch)findViewById(R.id.switch_music_settings);
        this.mBackButton  = (Button)findViewById(R.id.btn_settings_back);
        this.mShareButton = (Button)findViewById(R.id.btn_share_settings);

        this.setSwitchDefaultValues();

        if (this.mToggleMusic != null)
        {
            this.mToggleMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    handleSwitchPresses(false, isChecked);
                }
            });
        }

        if (this.mToggleSound != null)
        {
            this.mToggleSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    handleSwitchPresses(true, isChecked);
                }
            });
        }

        if (this.mBackButton != null)
        {
            this.mBackButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    onBackButtonPressed();
                }
            });
        }

        if (this.mShareButton != null)
        {
            this.mShareButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    onShareButtonPressed();
                }
            });
        }

        Progress.getInstance().loadProgress(this);
        Progress.getInstance().setProgress(Progress.ESaveType.progress_score, 1000);
        Progress.getInstance().saveProgress(this);
    }

    public void setSwitchDefaultValues()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SETTINGS_PREF, Context.MODE_PRIVATE);
        boolean _music = sharedPreferences.getBoolean(SETTINGS_MUSIC_PREF, true);
        boolean _sound = sharedPreferences.getBoolean(SETTINGS_SOUND_PREF, true);

        if (this.mToggleSound != null)
        {
            this.mToggleSound.setChecked(_sound);
        }
        if (this.mToggleMusic != null)
        {
            this.mToggleMusic.setChecked(_music);
        }
    }

    public void handleSwitchPresses(boolean sound, boolean checked)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SETTINGS_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        if (sharedPreferences.contains(sound ? SETTINGS_SOUND_PREF : SETTINGS_MUSIC_PREF))
        {
            sharedPreferencesEditor.remove(sound ? SETTINGS_SOUND_PREF : SETTINGS_MUSIC_PREF);
        }
        sharedPreferencesEditor.putBoolean(sound ? SETTINGS_SOUND_PREF : SETTINGS_MUSIC_PREF, checked);
        sharedPreferencesEditor.commit();
    }

    public void onBackButtonPressed()
    {
        this.finish();
        onBackPressed();
    }

    public void onShareButtonPressed()
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.share_body));
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_popup_title)));
    }
}