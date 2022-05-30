package com.cire.formula1.packets.models.data;

public class Buttons {

    private long buttonStatus;

    /**
     * @return Bit flags specifying which buttons are being pressed
     */
    public long getButtonStatus() {
        return buttonStatus;
    }

    public void setButtonStatus(long buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    @Override
    public String toString() {
        return "Buttons[buttonStatus=" + this.buttonStatus +
                "]";
    }
}
