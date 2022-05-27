package org.firstinspires.ftc.teamcode;

public class Button {

    private boolean lastState;
    private boolean currState;

    public Button(boolean value) {
        currState = value;
        lastState = currState;
    }
    public void update(boolean value) {
        lastState = currState;
        currState = value;
    }
    public boolean down() {
        return currState;
    }
    public boolean pressed() {
        return (!lastState && currState);
    }
    public boolean released() {
        return (lastState && !currState);
    }
    public boolean changed() {
        return (lastState != currState);
    }
}