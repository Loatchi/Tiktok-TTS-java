package org.flower.tiktok;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TTS {

    private final Voice voice;
    private final String speech;
    private final File output;

    private final String sessionId;

    public TTS(String sessionId, Voice voice, String speech, File output){
        this.voice = voice;
        this.speech = speech;
        this.output = output;
        this.sessionId = sessionId;
    }

    public void createAudioFile() throws URISyntaxException, IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        String url  = "https://api22-normal-c-useast1a.tiktokv.com/media/api/text/speech/invoke/" +
                "?text_speaker=" + voice.tiktokId +  "&req_text=" + speech + "&speaker_map_type=0&aid=1233";

        HttpRequest request = HttpRequest.newBuilder()
                .header("User-Agent", "com.zhiliaoapp.musically/2022600030 (Linux; U;" +
                        " Android 7.1.2; es_ES; SM-G988N; Build/NRD90M;tt-ok/3.12.13.1)")
                .header("Cookie", "sessionid=" + sessionId)
                .uri(new URI(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
