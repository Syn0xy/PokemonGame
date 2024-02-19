package model.input;

import java.util.HashMap;
import java.util.Map;

public class InputKeyCode {
    
    private boolean enter;
    
    private boolean stay;
    
    private boolean exit;
    
    private boolean nothing;
    
    public InputKeyCode() {
        this.enter = false;
        this.stay = false;
        this.exit = false;
        this.nothing = true;
    }

    public boolean isStay() {
        return stay;
    }

    public boolean isEnter() {
        return enter;
    }

    public boolean isExit() {
        return exit;
    }

    protected void enter(){
        if(nothing) enter = true;
        stay = true;
        exit = false;
        nothing = false;
    }

    protected void exit(){
        this.exit = true;
    }

    protected void update(){
        if(nothing) return;
        if(enter) enter = false;
        if(exit) reset();
    }

    private void reset(){
        this.enter = false;
        this.stay = false;
        this.exit = false;
        this.nothing = true;
    }

    public static Map<KeyCode, InputKeyCode> getInputsKeyCode(){
        Map<KeyCode, InputKeyCode> map = new HashMap<>();
        for(KeyCode keyCode : KeyCode.values()){
            map.put(keyCode, new InputKeyCode());
        }
        return map;
    }

}
