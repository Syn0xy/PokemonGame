package model.util;

import java.io.StringWriter;

import view.scene.LoadingScene;

public class BufferedWriter extends StringWriter {

    private static final String NEW_LINE = "\n";

    private LoadingScene observer;

    public BufferedWriter(LoadingScene observer){
        this.observer = observer;
    }

    private void update(String str){
        observer.update(str);
    }
    
    public void println(String str){
        String s = str + NEW_LINE;
        super.append(s);
        update(s);
    }

    public void print(String str){
        super.append(str);
        update(str);
    }
}
