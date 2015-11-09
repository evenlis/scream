package no.lislebo;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Main {

    private static void recordAudio(String fileName) {
        File outputFile = new File(fileName);
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100.0F, 16, 2, 4, 44100.0F, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        TargetDataLine targetDataLine = null;
        try {
            targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(format);
        } catch(LineUnavailableException e) {
            System.err.println("Unable to get a recording line");
            e.printStackTrace();
            System.exit(1);
        }
        AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;
        AudioRecorder recorder = new AudioRecorder(targetDataLine, targetType, outputFile);
        System.out.println("Press RETURN to start recording");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
        System.out.println("Recording...");
        System.out.println("Press RETURN to stop recording");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.stopRecording();
        System.out.println("Finished recording");
    }

    public static void main(String[] args) {
        recordAudio("flesk.wav");
    }
}
