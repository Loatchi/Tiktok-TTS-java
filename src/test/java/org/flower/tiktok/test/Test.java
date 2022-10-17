package org.flower.tiktok.test;

import org.flower.tiktok.InvalidSessionIDException;
import org.flower.tiktok.TiktokTTS;
import org.flower.tiktok.Voice;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Test {
    public static void main(String[] args)
            throws URISyntaxException, IOException, InterruptedException, InvalidSessionIDException {
        TiktokTTS tts = new TiktokTTS(args[0], Voice.ENGLISH_US_MALE_1,
                "Testing random things", new File("test.mp3"));
        tts.createAudioFile();

        tts.setVoice(Voice.ENGLISH_CHEWBACCA);
        tts.setOutput(new File("test_chewbacca.mp3"));
        tts.createAudioFile();
    }
}
