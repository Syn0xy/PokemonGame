package model.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class Input implements KeyListener{

    private static Map<KeyCode, InputKeyCode> INPUTS_KEY_CODE = InputKeyCode.getInputsKeyCode();

    private static Input singleton;

    public static Input getInstance(){
        if(singleton == null) singleton = new Input();
        return singleton;
    }
    
    public static void update(){
        for(InputKeyCode input : INPUTS_KEY_CODE.values()){
            input.update();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        INPUTS_KEY_CODE.get(KeyCode.key(keyEvent)).enter();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        INPUTS_KEY_CODE.get(KeyCode.key(keyEvent)).exit();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}
    
    public static boolean getKey(KeyCode keyCode){
        return INPUTS_KEY_CODE.get(keyCode).isStay();
    }

    public static boolean getKeyDown(KeyCode keyCode){
        return INPUTS_KEY_CODE.get(keyCode).isEnter();
    }

    public static boolean getKeyUp(KeyCode keyCode){
        return INPUTS_KEY_CODE.get(keyCode).isExit();
    }

}
