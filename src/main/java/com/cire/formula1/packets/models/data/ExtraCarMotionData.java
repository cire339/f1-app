package com.cire.formula1.packets.models.data;

import com.cire.formula1.packets.utils.PacketConstants;

import java.util.Arrays;

public class ExtraCarMotionData {

    // Wheels in order: RL, RR, FL, FR.
    private float[] suspensionPosition = new float[PacketConstants.WHEELS];
    private float[] suspensionVelocity = new float[PacketConstants.WHEELS];
    private float[] suspensionAcceleration = new float[PacketConstants.WHEELS];
    private float[] wheelSpeed = new float[PacketConstants.WHEELS];
    private float[] wheelSlip = new float[PacketConstants.WHEELS];
    private float localVelocityX;
    private float localVelocityY;
    private float localVelocityZ;
    private float angularVelocityX;
    private float angularVelocityY;
    private float angularVelocityZ;
    private float angularAccelerationX;
    private float angularAccelerationY;
    private float angularAccelerationZ;
    private float frontWheelsAngle;

    /**
     * @return Suspension position of each wheel
     */
    public float[] getSuspensionPosition() {
        return suspensionPosition;
    }

    public void setSuspensionPosition(float[] suspensionPosition) {
        this.suspensionPosition = suspensionPosition;
    }

    /**
     * @return Suspension velocity of each wheel
     */
    public float[] getSuspensionVelocity() {
        return suspensionVelocity;
    }

    public void setSuspensionVelocity(float[] suspensionVelocity) {
        this.suspensionVelocity = suspensionVelocity;
    }

    /**
     * @return Suspension acceleration of each wheel
     */
    public float[] getSuspensionAcceleration() {
        return suspensionAcceleration;
    }

    public void setSuspensionAcceleration(float[] suspensionAcceleration) {
        this.suspensionAcceleration = suspensionAcceleration;
    }

    /**
     * @return Speed of each wheel
     */
    public float[] getWheelSpeed() {
        return wheelSpeed;
    }

    public void setWheelSpeed(float[] wheelSpeed) {
        this.wheelSpeed = wheelSpeed;
    }

    /**
     * @return Slip ratio for each wheel
     */
    public float[] getWheelSlip() {
        return wheelSlip;
    }

    public void setWheelSlip(float[] wheelSlip) {
        this.wheelSlip = wheelSlip;
    }

    /**
     * @return Velocity in local space
     */
    public float getLocalVelocityX() {
        return localVelocityX;
    }

    public void setLocalVelocityX(float localVelocityX) {
        this.localVelocityX = localVelocityX;
    }

    /**
     * @return Velocity in local space
     */
    public float getLocalVelocityY() {
        return localVelocityY;
    }

    public void setLocalVelocityY(float localVelocityY) {
        this.localVelocityY = localVelocityY;
    }

    /**
     * @return Velocity in local space
     */
    public float getLocalVelocityZ() {
        return localVelocityZ;
    }

    public void setLocalVelocityZ(float localVelocityZ) {
        this.localVelocityZ = localVelocityZ;
    }

    /**
     * @return Angular velocity x-component
     */
    public float getAngularVelocityX() {
        return angularVelocityX;
    }

    public void setAngularVelocityX(float angularVelocityX) {
        this.angularVelocityX = angularVelocityX;
    }

    /**
     * @return Angular velocity y-component
     */
    public float getAngularVelocityY() {
        return angularVelocityY;
    }

    public void setAngularVelocityY(float angularVelocityY) {
        this.angularVelocityY = angularVelocityY;
    }

    /**
     * @return Angular velocity z-component
     */
    public float getAngularVelocityZ() {
        return angularVelocityZ;
    }

    public void setAngularVelocityZ(float angularVelocityZ) {
        this.angularVelocityZ = angularVelocityZ;
    }

    /**
     * @return Angular acceleration x-component
     */
    public float getAngularAccelerationX() {
        return angularAccelerationX;
    }

    public void setAngularAccelerationX(float angularAccelerationX) {
        this.angularAccelerationX = angularAccelerationX;
    }

    /**
     * @return Angular acceleration y-component
     */
    public float getAngularAccelerationY() {
        return angularAccelerationY;
    }

    public void setAngularAccelerationY(float angularAccelerationY) {
        this.angularAccelerationY = angularAccelerationY;
    }

    /**
     * @return Angular acceleration z-component
     */
    public float getAngularAccelerationZ() {
        return angularAccelerationZ;
    }

    public void setAngularAccelerationZ(float angularAccelerationZ) {
        this.angularAccelerationZ = angularAccelerationZ;
    }

    /**
     * @return Current front wheels angle in radians
     */
    public float getFrontWheelsAngle() {
        return frontWheelsAngle;
    }

    public void setFrontWheelsAngle(float frontWheelsAngle) {
        this.frontWheelsAngle = frontWheelsAngle;
    }

    @Override
    public String toString() {
        return "ExtraCarMotionData[suspensionPosition=" + Arrays.toString(this.suspensionPosition) +
                ",suspensionVelocity=" + Arrays.toString(this.suspensionVelocity) +
                ",suspensionAcceleration= " + Arrays.toString(this.suspensionAcceleration) +
                ",wheelSpeed=" + Arrays.toString(this.wheelSpeed) +
                ",wheelSlip=" + Arrays.toString(this.wheelSlip) +
                ",localVelocityX=" + this.localVelocityX +
                ",localVelocityY=" + this.localVelocityY +
                ",localVelocityZ=" + this.localVelocityZ +
                ",angularVelocityX=" + this.angularVelocityX +
                ",angularVelocityY=" + this.angularVelocityY +
                ",angularVelocityZ=" + this.angularVelocityZ +
                ",angularAccelerationX=" + this.angularAccelerationX +
                ",angularAccelerationY=" + this.angularAccelerationY +
                ",angularAccelerationZ=" + this.angularAccelerationZ +
                ",frontWheelsAngle=" + this.frontWheelsAngle +
                "]";
    }
}
