package io.github.loatchi.tiktok.test;

import io.github.loatchi.tiktok.InvalidSessionIDException;
import io.github.loatchi.tiktok.TiktokTTS;
import io.github.loatchi.tiktok.TiktokTTSException;
import io.github.loatchi.tiktok.Voice;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args)
            throws
            URISyntaxException,
            IOException,
            InterruptedException,
            InvalidSessionIDException,
            TiktokTTSException {

        String firstPrinciple = """
                FIRST PRINCIPLE. If a SMALL change is made that affects a long-term historical
                trend, then the effect of that change will almost always be transitory — the
                trend will soon revert to its original state. (Example: A reform
                movement designed to clean up political corruption in a society
                rarely has more than a short-term effect; sooner or later the reformers
                relax and corruption creeps back in. The level of political corruption
                in a given society tends to remain constant, or to change only slowly
                with the evolution of the society. Normally, a political cleanup will
                be permanent only if accompanied by widespread social changes;
                a SMALL change in the society won’t be enough.) If a small change
                in a long-term historical trend appears to be permanent, it is only
                because the change acts in the direction in which the trend is already
                moving, so that the trend is not altered by only pushed a step ahead.
                (Theodore Kaczynski, Industrial Society and its future)
                """.replaceAll("\n", " ");

        String petitPrince = """
                Quand on veut faire de l’esprit, il arrive que l’on mente un
                peu. Je n’ai pas été très honnête en vous parlant des allumeurs
                de réverbères. Je risque de donner une fausse idée de notre planète
                à ceux qui ne la connaissent pas. Les hommes occupent
                très peu de place sur la terre. Si les deux milliards d’habitants
                qui peuplent la terre se tenaient debout et un peu serrés, comme
                pour un meeting, ils logeraient aisément sur une place publique
                de vingt milles de long sur vingt milles de large. On pourrait
                entasser l’humanité sur le moindre petit îlot du Pacifique.
                (Antoine de Saint-Exupéry, Le Petit Prince)
                """.replaceAll("\n", " ");

        //I will gladly accept new tests in others languages to share its behavior.

        File testDir = new File("./test_tts");
        boolean fileCreated = true;
        if(!(testDir.isDirectory() && testDir.exists())){
            fileCreated = testDir.mkdir();
        }

        if(!fileCreated){
            throw new RuntimeException("Cannot create test dir.");
        }

        TiktokTTS tts = new TiktokTTS(args[0], null, null, null);

        tts.setOutput(Paths.get(testDir.getAbsolutePath(), "first_principle_english_uk_male_1.mp3").toFile());
        tts.setSpeech(firstPrinciple);
        tts.setVoice(Voice.ENGLISH_UK_MALE_1);
        tts.createAudioFile();
        tts.setVoice(Voice.ENGLISH_AU_FEMALE);
        tts.setOutput(Paths.get(testDir.getAbsolutePath(), "first_principle_english_au_female.mp3").toFile());
        tts.createAudioFile();

        tts.setOutput(Paths.get(testDir.getAbsolutePath(), "petit_prince_french_male_1.mp3").toFile());
        tts.setSpeech(petitPrince);
        tts.setVoice(Voice.FRENCH_MALE_1);
        tts.createAudioFile();
    }
}
