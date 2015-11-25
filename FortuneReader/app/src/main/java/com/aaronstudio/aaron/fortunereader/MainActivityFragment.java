package com.aaronstudio.aaron.fortunereader;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    FortuneTeller fortuneTellerReader;
    Button talkButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_main, container, false);
        talkButton = (Button) layout.findViewById(R.id.talk_button);
        talkButton.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.talk_button:
                Log.d("flow", "talk_button clicked");
                displaySpeechRecognizer();
                break;
        }
    }

    private static final int SPEECH_REQUEST_CODE = 0;

    //Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        Log.d("flow", "start intent");
        //Start the activity, ht intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String spokenText = results.get(0);
                fortuneTellerReader = new FortuneTeller(getActivity());
                fortuneTellerReader.textToSpeechTeller(spokenText);
            } else {
                Log.d("flow", "failure");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}