package model.input;

import java.awt.event.KeyEvent;

public enum KeyCode {
    A(KeyEvent.VK_A, "A"),
    B(KeyEvent.VK_B, "B"),
    C(KeyEvent.VK_C, "C"),
    D(KeyEvent.VK_D, "D"),
    E(KeyEvent.VK_E, "E"),
    F(KeyEvent.VK_F, "F"),
    G(KeyEvent.VK_G, "G"),
    H(KeyEvent.VK_H, "H"),
    I(KeyEvent.VK_I, "I"),
    J(KeyEvent.VK_J, "J"),
    K(KeyEvent.VK_K, "K"),
    L(KeyEvent.VK_L, "L"),
    M(KeyEvent.VK_M, "M"),
    N(KeyEvent.VK_N, "N"),
    O(KeyEvent.VK_O, "O"),
    P(KeyEvent.VK_P, "P"),
    Q(KeyEvent.VK_Q, "Q"),
    R(KeyEvent.VK_R, "R"),
    S(KeyEvent.VK_S, "S"),
    T(KeyEvent.VK_T, "T"),
    U(KeyEvent.VK_U, "U"),
    V(KeyEvent.VK_V, "V"),
    W(KeyEvent.VK_W, "W"),
    X(KeyEvent.VK_X, "X"),
    Y(KeyEvent.VK_Y, "Y"),
    Z(KeyEvent.VK_Z, "Z"),
    ALPHA_0(KeyEvent.VK_0, "alpha-0"),
    ALPHA_1(KeyEvent.VK_1, "alpha-1"),
    ALPHA_2(KeyEvent.VK_2, "alpha-2"),
    ALPHA_3(KeyEvent.VK_3, "alpha-3"),
    ALPHA_4(KeyEvent.VK_4, "alpha-4"),
    ALPHA_5(KeyEvent.VK_5, "alpha-5"),
    ALPHA_6(KeyEvent.VK_6, "alpha-6"),
    ALPHA_7(KeyEvent.VK_7, "alpha-7"),
    ALPHA_8(KeyEvent.VK_8, "alpha-8"),
    ALPHA_9(KeyEvent.VK_9, "alpha-9"),
    UP_ARROW(KeyEvent.VK_UP, "up"),
    DOWN_ARROW(KeyEvent.VK_DOWN, "down"),
    RIGHT_ARROW(KeyEvent.VK_RIGHT, "right"),
    LEFT_ARROW(KeyEvent.VK_LEFT, "left"),
    NUMPAD_0(KeyEvent.VK_0, "numpad-0"),
    NUMPAD_1(KeyEvent.VK_1, "numpad-1"),
    NUMPAD_2(KeyEvent.VK_2, "numpad-2"),
    NUMPAD_3(KeyEvent.VK_3, "numpad-3"),
    NUMPAD_4(KeyEvent.VK_4, "numpad-4"),
    NUMPAD_5(KeyEvent.VK_5, "numpad-5"),
    NUMPAD_6(KeyEvent.VK_6, "numpad-6"),
    NUMPAD_7(KeyEvent.VK_7, "numpad-7"),
    NUMPAD_8(KeyEvent.VK_8, "numpad-8"),
    NUMPAD_9(KeyEvent.VK_9, "numpad-9"),
    F1(KeyEvent.VK_F1, "f1"),
    F2(KeyEvent.VK_F2, "f2"),
    F3(KeyEvent.VK_F3, "f3"),
    F4(KeyEvent.VK_F4, "f4"),
    F5(KeyEvent.VK_F5, "f5"),
    F6(KeyEvent.VK_F6, "f6"),
    F7(KeyEvent.VK_F7, "f7"),
    F8(KeyEvent.VK_F8, "f8"),
    F9(KeyEvent.VK_F9, "f9"),
    F10(KeyEvent.VK_F10, "f10"),
    F11(KeyEvent.VK_F11, "f11"),
    F12(KeyEvent.VK_F12, "f12"),
    LESS(KeyEvent.VK_LESS, "less"),
    CAPS_LOCK(KeyEvent.VK_CAPS_LOCK, "caps-lock"),
    CTRL(KeyEvent.VK_CONTROL, "ctrl"),
    ALT(KeyEvent.VK_ALT, "alt"),
    SHIFT(KeyEvent.VK_SHIFT, "shift"),
    SPACE(KeyEvent.VK_SPACE, "space"),
    ENTER(KeyEvent.VK_ENTER, "enter"),
    ESCAPE(KeyEvent.VK_ESCAPE, "escape"),
    MOUSE_1(1, "mouse-1"),
    MOUSE_2(2, "mouse-2"),
    MOUSE_3(3, "mouse-3"),
    MOUSE_4(4, "mouse-4"),
    MOUSE_5(5, "mouse-5");

    private int code;

    private String key;
    
    private KeyCode(int code, String key){
        this.code = code;
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public String getKey(){
        return key;
    }

    public static KeyCode key(KeyEvent keyEvent){
        for(KeyCode keyCode : values()){
            if(keyEvent.getKeyCode() == keyCode.code){
                return keyCode;
            }
        }
        return null;
    }

}
