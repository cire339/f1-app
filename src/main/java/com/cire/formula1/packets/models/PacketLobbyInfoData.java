package com.cire.formula1.packets.models;

import com.cire.formula1.packets.models.data.LobbyInfoData;
import com.cire.formula1.packets.utils.PacketConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Lobby Info Packet
 * 
 * This packet details the players currently in a multiplayer lobby. It details
 * each player’s selected car, any AI involved in the game and also the ready
 * status of each of the participants.
 * Frequency: Two every second when in the lobby
 */
public class PacketLobbyInfoData extends Packet {

    // 1169
    public static final int SIZE = PacketHeader.SIZE +
                                    1 +
                                    LobbyInfoData.SIZE * PacketConstants.LOBBY_PLAYERS;
    
    private short numPlayers;
    private List<LobbyInfoData> lobbyInfoData = new ArrayList<>(PacketConstants.LOBBY_PLAYERS);

    /**
     * @return Number of players in the lobby data
     */
    public short getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(short numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * @return Lobby info data for all cars
     */
    public List<LobbyInfoData> getLobbyInfoData() {
        return lobbyInfoData;
    }

    public void setLobbyInfoData(List<LobbyInfoData> lobbyInfoData) {
        this.lobbyInfoData = lobbyInfoData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LobbyInfo[");
        sb.append(super.toString());
        sb.append(",numPlayers=" + this.numPlayers);
        sb.append(",lobbyInfoData=");
        for (LobbyInfoData l : lobbyInfoData) {
            sb.append(l.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

}