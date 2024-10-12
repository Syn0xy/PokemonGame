package utils;

import java.io.StringWriter;

import view.scene.LoadingScene;

public class BufferedWriter extends StringWriter {

    private static final String NEW_LINE = "\n";

    private final LoadingScene observer;

    public BufferedWriter(final LoadingScene observer) {
        this.observer = observer;
    }

    private void update(final String str) {
        observer.update(str);
    }

    public void print(final String str) {
        super.append(str);
        this.update(str);
    }
    
    public void println(final String str) {
        this.print(str + BufferedWriter.NEW_LINE);
    }
}
