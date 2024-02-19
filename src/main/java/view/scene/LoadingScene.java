package view.scene;

import javax.swing.JTextArea;

import view.BufferedWriter;
import view.ImageManager;

public class LoadingScene extends Scene {

    private BufferedWriter bufferedWriter;

    private JTextArea textArea;
    
    @Override
    public void start(){
        this.bufferedWriter = new BufferedWriter();
        bufferedWriter.attach(this);
        textArea = new JTextArea("bla ?");
        add(textArea);
        ImageManager.init(bufferedWriter);
    }
    
    public void update(String data) {
        textArea.append(data);
    }

}
