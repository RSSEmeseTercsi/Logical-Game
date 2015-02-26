package com.redsteedstudios.logicalgame;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.redsteedstudios.logicalgame.Utils.RoundImage;
import com.redsteedstudios.logicalgame.util.FileUtils;
import com.redsteedstudios.logicalgame.util.Progress;

import java.io.InputStream;


public class StartActivity extends Activity implements View.OnClickListener,  GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener  {


    private GoogleApiClient mGoogleApiClient;
    private static int RC_SIGN_IN = 9001;

    private boolean mSignInClicked = false;
    private boolean mIntentInProgress;
    private ConnectionResult mConnectionResult;
    private ImageView imgProfilePic;
    private TextView playerName;

    private static final int PROFILE_PIC_SIZE = 400;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_start);

        Button settings = (Button) findViewById(R.id.settings_button);
        Button continueButton = (Button) findViewById(R.id.continue_btn);
        Button startButton = (Button) findViewById(R.id.new_game_btn);
        imgProfilePic = (ImageView) findViewById(R.id.player_image);
        playerName = (TextView) findViewById(R.id.player_name);
        settings.setOnClickListener(this);
        continueButton.setOnClickListener(this);
        startButton.setOnClickListener(this);

        LinearLayout player_info_container = (LinearLayout) findViewById(R.id.player_info_container);

        player_info_container.setOnClickListener(this);

        // Create the Google Api Client with access to the Play Game services
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
        
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.settings_button:
               startActivity(new Intent(this,ScreenSettings.class));
                break;
            case R.id.continue_btn:
                Intent intent = new Intent(this,ScreenGame.class);
                intent.putExtra("selected_level", Progress.getInstance().getProgress(Progress.ESaveType.progress_last_level_played));
                startActivity(intent);
                break;
            case R.id.new_game_btn:
                startActivity(new Intent(this,LevelChooserActivity.class));
                break;
            case R.id.player_info_container:


                mSignInClicked=true;

                mGoogleApiClient.connect();


                break;
            default:
                break;
        }
    }


    @Override
    public void onConnected(Bundle bundle) {


        // Get user's information
        getProfileInformation();

    }

    @Override
    public void onConnectionSuspended(int i) {
        // Attempt to reconnect
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!connectionResult.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this,
                    0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = connectionResult;

            if (mSignInClicked) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }
    }
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    private void getProfileInformation() {
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);
                String personName = currentPerson.getDisplayName();
                String personPhotoUrl = currentPerson.getImage().getUrl();

                playerName.setText(personName);




                personPhotoUrl = personPhotoUrl.substring(0,
                        personPhotoUrl.length() - 2)
                        + PROFILE_PIC_SIZE;

                new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);

            } else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadProfileImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageDrawable(new RoundImage(result));
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }
}
