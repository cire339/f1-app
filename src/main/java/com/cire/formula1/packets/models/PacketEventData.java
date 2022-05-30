package com.cire.formula1.packets.models;

import com.cire.formula1.packets.models.data.*;
import com.cire.formula1.packets.models.constants.EventCode;

/**
 * Event Packet
 * 
 * This packet gives details of events that happen during the course of a session.
 * Frequency: When the event occurs
 */
public class PacketEventData extends Packet {
    
    private EventCode eventCode;
    private EventDataDetails eventDataDetails = new EventDataDetails();

    /**
     * @return Event code
     */
    public EventCode getEventCode() {
        return eventCode;
    }

    public void setEventCode(EventCode eventCode) {
        this.eventCode = eventCode;
    }

    /**
     * @return Event details 
     */
    public EventDataDetails getEventDataDetails() {
        return eventDataDetails;
    }

    public void setEventDataDetails(EventDataDetails eventDataDetails) {
        this.eventDataDetails = eventDataDetails;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Event[");
        sb.append(super.toString());
        sb.append(",eventStringCode=" +  this.eventCode);
        sb.append(",eventDataDetails=" + this.eventDataDetails);
        sb.append("]");
        return sb.toString();
    }
}