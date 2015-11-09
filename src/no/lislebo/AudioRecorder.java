package no.lislebo;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import java.io.File;
import java.io.IOException;

/**
 * Created by evenlis on 09.11.15.
 */
public class AudioRecorder extends Thread {
    private TargetDataLine _line;
    private AudioFileFormat.Type _targetType;
    private AudioInputStream _audioInStream;
    private File _outFile;

    public AudioRecorder(TargetDataLine line, AudioFileFormat.Type targetType, File file) {
        this._line = line;
        this._targetType = targetType;
        this._outFile = file;

        this._audioInStream = new AudioInputStream(line);
    }

    public void start() {
        _line.start();
        super.start();
    }

    public void stopRecording() {
        _line.stop();
        _line.close();
    }

    public void run() {
        try {
            AudioSystem.write(_audioInStream, _targetType, _outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
