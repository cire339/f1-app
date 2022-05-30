package com.cire.formula1.packets.models.data;

public class CarMotionData {

    private float worldPositionX;
    private float worldPositionY;
    private float worldPositionZ;
    private float worldVelocityX;
    private float worldVelocityY;
    private float worldVelocityZ;
    private float worldForwardDirX;
    private float worldForwardDirY;
    private float worldForwardDirZ;
    private float worldRightDirX;
    private float worldRightDirY;
    private float worldRightDirZ;
    private float gForceLateral;
    private float gForceLongitudinal;
    private float gForceVertical;
    private float yaw;
    private float pitch;
    private float roll;

    /**
     * @return World space X position
     */
    public float getWorldPositionX() {
        return worldPositionX;
    }

    public void setWorldPositionX(float worldPositionX) {
        this.worldPositionX = worldPositionX;
    }

    /**
     * @return World space Y position
     */
    public float getWorldPositionY() {
        return worldPositionY;
    }

    public void setWorldPositionY(float worldPositionY) {
        this.worldPositionY = worldPositionY;
    }

    /**
     * @return World space Z position
     */
    public float getWorldPositionZ() {
        return worldPositionZ;
    }

    public void setWorldPositionZ(float worldPositionZ) {
        this.worldPositionZ = worldPositionZ;
    }

    /**
     * @return Velocity in world space X
     */
    public float getWorldVelocityX() {
        return worldVelocityX;
    }

    public void setWorldVelocityX(float worldVelocityX) {
        this.worldVelocityX = worldVelocityX;
    }

    /**
     * @return Velocity in world space Y
     */
    public float getWorldVelocityY() {
        return worldVelocityY;
    }

    public void setWorldVelocityY(float worldVelocityY) {
        this.worldVelocityY = worldVelocityY;
    }

    /**
     * @return Velocity in world space Z
     */
    public float getWorldVelocityZ() {
        return worldVelocityZ;
    }

    public void setWorldVelocityZ(float worldVelocityZ) {
        this.worldVelocityZ = worldVelocityZ;
    }

    /**
     * @return World space forward X direction (normalised)
     */
    public float getWorldForwardDirX() {
        return worldForwardDirX;
    }

    public void setWorldForwardDirX(float worldForwardDirX) {
        this.worldForwardDirX = worldForwardDirX;
    }

    /**
     * @return World space forward Y direction (normalised)
     */
    public float getWorldForwardDirY() {
        return worldForwardDirY;
    }

    public void setWorldForwardDirY(float worldForwardDirY) {
        this.worldForwardDirY = worldForwardDirY;
    }

    /**
     * @return World space forward Z direction (normalised)
     */
    public float getWorldForwardDirZ() {
        return worldForwardDirZ;
    }

    public void setWorldForwardDirZ(float worldForwardDirZ) {
        this.worldForwardDirZ = worldForwardDirZ;
    }

    /**
     * @return World space right X direction (normalised)
     */
    public float getWorldRightDirX() {
        return worldRightDirX;
    }

    public void setWorldRightDirX(float worldRightDirX) {
        this.worldRightDirX = worldRightDirX;
    }

    /**
     * @return World space right Y direction (normalised)
     */
    public float getWorldRightDirY() {
        return worldRightDirY;
    }

    public void setWorldRightDirY(float worldRightDirY) {
        this.worldRightDirY = worldRightDirY;
    }

    /**
     * @return World space right Z direction (normalised)
     */
    public float getWorldRightDirZ() {
        return worldRightDirZ;
    }

    public void setWorldRightDirZ(float worldRightDirZ) {
        this.worldRightDirZ = worldRightDirZ;
    }

    /**
     * @return Lateral G-Force component
     */
    public float getgForceLateral() {
        return gForceLateral;
    }

    public void setgForceLateral(float gForceLateral) {
        this.gForceLateral = gForceLateral;
    }

    /**
     * @return Longitudinal G-Force component
     */
    public float getgForceLongitudinal() {
        return gForceLongitudinal;
    }

    public void setgForceLongitudinal(float gForceLongitudinal) {
        this.gForceLongitudinal = gForceLongitudinal;
    }

    /**
     * @return Vertical G-Force component
     */
    public float getgForceVertical() {
        return gForceVertical;
    }

    public void setgForceVertical(float gForceVertical) {
        this.gForceVertical = gForceVertical;
    }

    /**
     * @return Yaw angle in radians
     */
    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
     * @return Pitch angle in radians
     */
    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * @return Roll angle in radians
     */
    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    @Override
    public String toString() {
        return "CarMotionData[worldPositionX=" + this.worldPositionX +
                ",worldPositionY=" + this.worldPositionY +
                ",worldPositionZ=" + this.worldPositionZ +
                ",worldVelocityX=" + this.worldVelocityX +
                ",worldVelocityY=" + this.worldVelocityY +
                ",worldVelocityZ=" + this.worldVelocityZ +
                ",worldForwardDirX=" + this.worldForwardDirX +
                ",worldForwardDirY=" + this.worldForwardDirY +
                ",worldForwardDirZ=" + this.worldForwardDirZ +
                ",worldRightDirX=" + this.worldRightDirX +
                ",worldRightDirY=" + this.worldRightDirY +
                ",worldRightDirZ=" + this.worldRightDirZ +
                ",gForceLateral=" + this.gForceLateral +
                ",gForceLongitudinal=" + this.gForceLongitudinal +
                ",gForceVertical=" + this.gForceVertical +
                ",yaw=" + this.yaw +
                ",pitch=" + this.pitch +
                ",roll=" + this.roll +
                "]";
    }
}
