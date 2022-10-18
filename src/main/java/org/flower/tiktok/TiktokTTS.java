package org.flower.tiktok;

import com.google.gson.Gson;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Map;

public class TiktokTTS {

    private String sessionId;
    private Voice voice;
    private String speech;
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

    public String getSpeech() {
        return speech;
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
        this.speech = speech
                .replaceAll("\\+", "plus")
                .replaceAll(" ", "+")
                .replaceAll("&", "and");

        if(this.speech.length() > 300){
            throw new IllegalArgumentException("Speech=\"" + this.speech
                    + "\" is too long: " + this.speech.length() + " length for max: 300");
        }
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public void createAudioFile()
            throws InvalidSessionIDException, URISyntaxException, IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        String url  = "https://api22-normal-c-useast1a.tiktokv.com/media/api/text/speech/invoke" +
                "/?text_speaker=" + voice.tiktokId + "&req_text=" + speech +"&speaker_map_type=0&aid=1233";
        System.out.println(url);
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

        if(map.get("message").equals("Couldn't load speech. Try again.")){
            throw new InvalidSessionIDException();
        }

        System.out.println(response.body());

        @SuppressWarnings("unchecked")
        byte[] b64 = Base64.getDecoder().decode(((Map<String, Object>) map.get("data")).get("v_str").toString());

        try(FileOutputStream outputStream = new FileOutputStream(output)){
            outputStream.write(b64);
        }

    }
}
