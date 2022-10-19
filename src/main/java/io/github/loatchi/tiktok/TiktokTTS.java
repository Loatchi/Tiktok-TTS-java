package io.github.loatchi.tiktok;

import com.google.gson.Gson;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class TiktokTTS {

    private String sessionId;
    private Voice voice;
    private final List<String> speeches = new ArrayList<>();
    private File output;
    public TiktokTTS(String sessionId, Voice voice, String speech, File output){
        this.voice = voice;
        setSpeech(speech);
        this.output = output;
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Voice getVoice() {
        return voice;
    }

    public List<String> getSpeeches() {
        return speeches;
    }

    public File getOutput() {
        return output;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setVoice(Voice voice){
        this.voice = voice;
    }

    public void setSpeech(String speech) {
        speeches.clear();

        if(speech == null)
            return;

        StringBuilder builder = new StringBuilder();
        int nbOfWords = 0;
        for(String word: speech
                .replaceAll("\\+", "plus")
                .replaceAll(" ", "+")
                .replaceAll("&", "and")
                .split("\\+")){
            nbOfWords++;
            // 294 and 55 are based on a phenomenological study with the Test Class
            // I don't really know exactly if those data are exact. But for now it worked so...
            if(builder.length() + word.length() >= 294 || nbOfWords >= 55) {
                speeches.add(builder.toString());
                builder.setLength(0);
                nbOfWords = 0;
            }
            builder.append(word).append("+");
        }

        speeches.add(builder.toString().stripTrailing());

    }

    public void setOutput(File output) {
        this.output = output;
    }

    private byte[] getAudioAsBytes(String speech) throws URISyntaxException, IOException,
            InterruptedException, InvalidSessionIDException, TiktokTTSException {

        HttpClient client = HttpClient.newHttpClient();

        String url  = "https://api22-normal-c-useast1a.tiktokv.com/media/api/text/speech/invoke" +
                "/?text_speaker=" + voice.tiktokId + "&req_text=" + speech +"&speaker_map_type=0&aid=1233";

        // based on oscie57's project
        HttpRequest request = HttpRequest.newBuilder()
                .header("User-Agent", "com.zhiliaoapp.musically/2022600030 (Linux; U;" +
                        " Android 7.1.2; es_ES; SM-G988N; Build/NRD90M;tt-ok/3.12.13.1)")
                .header("Cookie", "sessionid=" + sessionId)
                .header("Content-Type", "application/json; charset=utf8")
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(new URI(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();

        @SuppressWarnings("unchecked")
        Map<String, Object> map = gson.fromJson(response.body(), Map.class);

        String message = map.get("message").toString();

        if(message.equals("Couldn't load speech. Try again.")){
            throw new InvalidSessionIDException();
        }

        if(message.equals("Text-to-speech isn't supported for this language")){
            throw new TiktokTTSException("Seems like a typo.");
        }

        if(message.equals("Text too long to create speech audio")){
            throw new TiktokTTSException("This is unexpected: create an issue on Github with the input data.");
        }

        @SuppressWarnings("unchecked")
        byte[] b64 = Base64.getDecoder().decode(((Map<String, Object>) map.get("data")).get("v_str").toString());

        return b64;
    }

    public void createAudioFile()
            throws
            InvalidSessionIDException,
            URISyntaxException,
            IOException,
            InterruptedException,
            TiktokTTSException {

        try(FileOutputStream stream = new FileOutputStream(output)){
            for (String speech : speeches) {
                byte[] b64 = getAudioAsBytes(speech);
                stream.write(b64);
            }
        }
    }
}