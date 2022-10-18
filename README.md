# Tiktok-TTS-java
A program to transform a text to a vocal message using Tiktok voice template.</br>

It is a rewrite of [Oscie57's project](https://github.com/oscie57/tiktok-voice) in Java. It aims at providing a Java alternative to its program
for everyone to use in its own project.

## How to use it ?

You simply use a `TiktokTTS` instance and follow its format.</br>

```java
String sessionID;

TiktokTTS tts = new TiktokTTS(sessionID, Voice.ENGLISH_US_MALE_1, "Testing random things", new File("test.mp3"));

tts.createAudioFile();
tts.setVoice(Voice.ENGLISH_CHEWBACCA);
tts.setOutput(new File("test_chewbacca.mp3"));
tts.createAudioFile();

```

They're is no limit in speech size.


## Getting SessionID

While connected to the Tiktok web app you go to your Cookie menu and retrieve the value of `sessionid`.

#### Chromium-Based Browsers

1. Go to [Tiktok](https://www.tiktok.com/) and login yourself.
2. Press `Ctrl+Shift+i`.
3. Go to `Application/Cookies`.
4. Search `sessionid` and copy its value.

## Goals

- [X] Have no limit on speech size
- [ ] Properly concatenate each audio together (A concatenation should take place at an end of a sentence "," or ".")
