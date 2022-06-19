package com.cire.formula1.service;

import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;

public interface GraphService {
    JFreeChart createPlayerPositionGraph(XYSeriesCollection playerPositionDataSet);
    JFreeChart createMotionGraph(XYSeriesCollection motionDataSet);
}
