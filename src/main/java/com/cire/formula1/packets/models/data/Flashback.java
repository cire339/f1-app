package com.cire.formula1.packets.models.data;

public class Flashback {

    private long flashbackFrameIdentifier;
    private float flashbackSessionTime;

    /**
     * @return Frame identifier flashed back to
     */
    public long getFlashbackFrameIdentifier() {
        return flashbackFrameIdentifier;
    }

    public void setFlashbackFrameIdentifier(long flashbackFrameIdentifier) {
        this.flashbackFrameIdentifier = flashbackFrameIdentifier;
    }

    /**
     * @return Session time flashed back to
     */
    public float getFlashbackSessionTime() {
        return flashbackSessionTime;
    }

    public void setFlashbackSessionTime(float flashbackSessionTime) {
        this.flashbackSessionTime = flashbackSessionTime;
    }

    @Override
    public String toString() {
        return "Flashback[flashbackFrameIdentifier=" + this.flashbackFrameIdentifier +
                ",flashbackSessionTime=" + this.flashbackSessionTime +
                "]";
    }

}
