package com.cire.formula1.packets.models;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Base class for all packets
 */
public abstract class Packet {

    protected PacketHeader header = new PacketHeader();

    public PacketHeader getHeader() {
        return header;
    }

    public void setHeader(PacketHeader header) {
        this.header = header;
    }
    
    @Override
    public String toString() {
        return this.header.toString();
    }

    public String toJSON() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(this);
        }catch(Exception e) {
            //TODO: Handle this exception
        }
        return json.replace("\\u0000", "");
    }
}