package com.example.aaron.fortuneteller;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * Created by aaron on 11/11/15.
 */

public class FortuneTellerApp implements TextToSpeech.OnInitListener{

    private TextToSpeech textToSpeech;
    private MainActivity mainActivity;
    private Context context;
    public String voiceResponse;
    Random random = new Random();

    String [] howResponses = {"Use me(computer) to research", "You determine the how", "Trust your instincts", "Be creative, figure it out", "I honestly do not know"};
    String [] whatResponses = {"I do not know, you tell me", "Whatever your heart desire", "Let me get back to you on that", "Why you asking me?", "Nothing"};
    String [] whenResponses = {"2-5 years from now", "Whenever you make it happen", "Hmmmm let me think about it....Tomorrow", "In 3-5 months", "Right Now", "Time will tell"};
    String [] whereResponses = {"On the side of the road", "Cancun", "Wherever you want it", "Your dream location", "At a Church"};
    String [] amAndWillResponses = {"Yes", "No", "I do not know", "Maybe", "You are the smart person, I am just a computer"};
    String [] whoResponses = {"You best friend", "The person you are with", "How do I know, I am not living your life...", "Next door neighbor", "Someone you would never guess"};

    public FortuneTellerApp(Context context){
        this.context = context;
    }

    public FortuneTellerApp(){

    }

    public String getFortune(String userFortune) {
        Log.d("flow", "string being passed is: " + userFortune);

            if (userFortune.toLowerCase().contains("how ") || userFortune.toLowerCase().contains(" how ")
                    || userFortune.toLowerCase().equals("how")) {
                return howResponses[random.nextInt((howResponses.length))];
            }
            else if (userFortune.toLowerCase().contains("who ") || userFortune.toLowerCase().contains(" who ")
                    || userFortune.toLowerCase().equals("who")) {
                return whoResponses[random.nextInt((whoResponses.length))];
            }
            else if (userFortune.toLowerCase().contains("when ") || userFortune.toLowerCase().contains(" when ") ||
                    userFortune.toLowerCase().equals("when")) {
                return whenResponses[random.nextInt((whenResponses.length))];
            }
            else if (userFortune.toLowerCase().contains("what ") || userFortune.toLowerCase().contains(" what ")
                    || userFortune.toLowerCase().equals("what")) {
                return whatResponses[random.nextInt((whatResponses.length))];
            }
            else if (userFortune.toLowerCase().contains("where ") || userFortune.toLowerCase().contains(" where ")
                    || userFortune.toLowerCase().equals("where")) {
                return whereResponses[random.nextInt((whereResponses.length))];
            }
            else if (userFortune.toLowerCase().equals("am") || userFortune.toLowerCase().equals("will")
                    || userFortune.toLowerCase().contains("am ") || userFortune.toLowerCase().contains("will ")
                    || userFortune.toLowerCase().contains(" am ") || userFortune.toLowerCase().contains(" will ")) {
                return amAndWillResponses[random.nextInt((amAndWillResponses.length))];
            }
            else
                return "Ask me a real question";
    }
    public void textToSpeechTeller(String spokenText){
        textToSpeech = new TextToSpeech(context, this);
        textToSpeech.setLanguage(Locale.US);
        textToSpeech.setPitch((float) 2);

        if (spokenText.toLowerCase().contains("how ") || spokenText.toLowerCase().contains(" how ")
                || spokenText.toLowerCase().equals("how")) {
            voiceResponse = howResponses[random.nextInt((howResponses.length))];
        }
        else if (spokenText.toLowerCase().contains("who ") || spokenText.toLowerCase().contains(" who ")
                || spokenText.toLowerCase().equals("who")) {
            voiceResponse = whoResponses[random.nextInt((whoResponses.length))];
        }
        else if (spokenText.toLowerCase().contains("when ") || spokenText.toLowerCase().contains(" when ") ||
                spokenText.toLowerCase().equals("when")) {
            voiceResponse = whenResponses[random.nextInt((whenResponses.length))];
        }
        else if (spokenText.toLowerCase().contains("what ") || spokenText.toLowerCase().contains(" what ")
                || spokenText.toLowerCase().equals("what")) {
            voiceResponse = whatResponses[random.nextInt((whatResponses.length))];
        }
        else if (spokenText.toLowerCase().contains("where ") || spokenText.toLowerCase().contains(" where ")
                || spokenText.toLowerCase().equals("where")) {
            voiceResponse = whereResponses[random.nextInt((whereResponses.length))];
        }
        else if (spokenText.toLowerCase().equals("am") || spokenText.toLowerCase().equals("will")
                || spokenText.toLowerCase().contains("am ") || spokenText.toLowerCase().contains("will ")
                || spokenText.toLowerCase().contains(" am ") || spokenText.toLowerCase().contains(" will ")) {
            voiceResponse = amAndWillResponses[random.nextInt((amAndWillResponses.length))];
        }
        else
            voiceResponse = "Ask me a real question";
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            Log.d("flow", "success");

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                textToSpeech.speak(voiceResponse, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                textToSpeech.speak(voiceResponse, TextToSpeech.QUEUE_ADD, null);
            }
        }
    }
}
