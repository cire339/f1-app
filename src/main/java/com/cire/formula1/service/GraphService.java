package com.cire.formula1.service;

import com.cire.formula1.packet.model.data.LapData;
import org.jfree.chart.JFreeChart;

import java.util.List;

public interface GraphService {
    JFreeChart createGraph();

    JFreeChart getGraph();

    void updatePlayerPositionDataSet(List<LapData> lapDataList);
}
