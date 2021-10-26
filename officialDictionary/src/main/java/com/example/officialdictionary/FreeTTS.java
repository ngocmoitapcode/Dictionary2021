package com.example.officialdictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTS {
    public static void Speak(String target) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice;//Creating object of Voice class
        voice = VoiceManager.getInstance().getVoice("kevin");//Getting voice
        if (voice != null) {
            voice.allocate();//Allocating Voice
        }
        try {
            voice.setRate(150);//Setting the rate of the voice
            voice.setPitch(100);//Setting the Pitch of the voice
            voice.setVolume(3);//Setting the volume of the voice
            voice.speak(target);//Calling speak() method

        }
        catch(Exception e)
        {
            System.out.println("FreeTTS failed!");
            e.printStackTrace();
        }
    }
}
