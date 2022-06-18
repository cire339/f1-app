package com.cire.formula1.service;

import com.cire.formula1.packet.model.data.CarMotionData;
import com.cire.formula1.packet.model.data.LapData;
import org.jfree.chart.JFreeChart;

import java.util.List;

public interface GraphService {

    JFreeChart createPlayerPositionGraph();
    JFreeChart getPlayerPositionGraph();
    void updatePlayerPositionDataSet(List<LapData> lapDataList);

    JFreeChart createMotionGraph();
    JFreeChart getMotionGraph();
    void updateMotionDataSet(List<CarMotionData> carMotionDataList, short carIndex, short lapNumber);
}
