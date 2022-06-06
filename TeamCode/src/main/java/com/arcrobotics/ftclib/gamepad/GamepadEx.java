package com.arcrobotics.ftclib.gamepad;

import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadKeys.Button;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;

public class GamepadEx {

    public Gamepad gamepad;

    private HashMap<Button, ButtonReader> buttonReaders;
    private HashMap<Button, GamepadButton> gamepadButtons;

    private final Button[] buttons = {
            Button.Y, Button.X, Button.A, Button.B, Button.LEFT_BUMPER, Button.RIGHT_BUMPER, Button.BACK,
            Button.START, Button.DPAD_UP, Button.DPAD_DOWN, Button.DPAD_LEFT, Button.DPAD_RIGHT,
            Button.LEFT_STICK_BUTTON, Button.RIGHT_STICK_BUTTON
    };

    public GamepadEx(Gamepad gamepad) {
        this.gamepad = gamepad;
        buttonReaders = new HashMap<>();
        gamepadButtons = new HashMap<>();
        for (Button button : buttons) {
            buttonReaders.put(button, new ButtonReader(this, button));
            gamepadButtons.put(button, new GamepadButton(this, button));
        }
    }

    public boolean getButton(Button button) {
        boolean buttonValue = false;
        switch (button) {
            case A:
                buttonValue = gamepad.a;
                break;
            case B:
                buttonValue = gamepad.b;
                break;
            case X:
                buttonValue = gamepad.x;
                break;
            case Y:
                buttonValue = gamepad.y;
                break;
            case LEFT_BUMPER:
                buttonValue = gamepad.left_bumper;
                break;
            case RIGHT_BUMPER:
                buttonValue = gamepad.right_bumper;
                break;
            case DPAD_UP:
                buttonValue = gamepad.dpad_up;
                break;
            case DPAD_DOWN:
                buttonValue = gamepad.dpad_down;
                break;
            case DPAD_LEFT:
                buttonValue = gamepad.dpad_left;
                break;
            case DPAD_RIGHT:
                buttonValue = gamepad.dpad_right;
                break;
            case BACK:
                buttonValue = gamepad.back;
                break;
            case START:
                buttonValue = gamepad.start;
                break;
            case LEFT_STICK_BUTTON:
                buttonValue = gamepad.left_stick_button;
                break;
            case RIGHT_STICK_BUTTON:
                buttonValue = gamepad.right_stick_button;
                break;
            default:
                buttonValue = false;
                break;
        }
        return buttonValue;
    }

    public double getTrigger(GamepadKeys.Trigger trigger) {
        double triggerValue = 0;
        switch (trigger) {
            case LEFT_TRIGGER:
                triggerValue = gamepad.left_trigger;
                break;
            case RIGHT_TRIGGER:
                triggerValue = gamepad.right_trigger;
                break;
            default:
                break;
        }
        return triggerValue;
    }

    public double getLeftY() {
        return -gamepad.left_stick_y;
    }

    public double getRightY() {
        return gamepad.right_stick_y;
    }

    public double getLeftX() {
        return gamepad.left_stick_x;
    }

    public double getRightX() {
        return gamepad.right_stick_x;
    }

    public boolean wasJustPressed(Button button) {
        return buttonReaders.get(button).wasJustPressed();
    }

    public boolean wasJustReleased(Button button) {
        return buttonReaders.get(button).wasJustReleased();
    }

    public void readButtons() {
        for (Button button : buttons) {
            buttonReaders.get(button).readValue();
        }
    }

    public boolean isDown(Button button) {
        return buttonReaders.get(button).isDown();
    }

    public boolean stateJustChanged(Button button) {
        return buttonReaders.get(button).stateJustChanged();
    }

    public GamepadButton getGamepadButton(Button button) {
        return gamepadButtons.get(button);
    }

}