package com.cire.formula1.service;

import com.cire.formula1.packet.model.data.LapData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.UnknownKeyException;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class GraphServiceImpl implements GraphService{

    JFreeChart graph;
    XYSeriesCollection dataSet = new XYSeriesCollection();

    @Override
    public synchronized JFreeChart createGraph() {
        if(graph == null){
            graph = ChartFactory.createXYLineChart(
                    "Player Positions",
                    "Race Session", "Player position", dataSet);

            String fontName = "Palatino";
            graph.getTitle().setFont(new Font(fontName, Font.BOLD, 18));
            graph.addSubtitle(new TextTitle(
                    "This is a test.",
                    new Font(fontName, Font.PLAIN, 14)));

            graph.getLegend().setItemFont(new Font(fontName, Font.PLAIN, 14));
            graph.getLegend().setFrame(BlockBorder.NONE);
            graph.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);

            graph.getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            //graph.getXYPlot().getDomainAxis().setVisible(false);

            final NumberAxis xAxis = (NumberAxis) graph.getXYPlot().getDomainAxis();
            xAxis.setTickUnit(new NumberTickUnit(1000));

            graph.getXYPlot().setRenderer(new XYSplineRenderer());

        }
        return graph;
    }

    @Override
    public JFreeChart getGraph() {
        if(this.graph == null){
            return createGraph();
        }else{
            return graph;
        }
    }

    @Override
    public void updatePlayerPositionDataSet(List<LapData> lapDataList) {
        for(int i=0; i<lapDataList.size();i++){
            LapData lapData = lapDataList.get(i);
            XYSeries series;
            try {
                series = dataSet.getSeries("Player " + i);
                series.add( System.currentTimeMillis()/1000, lapData.getCarPosition());
            }catch(UnknownKeyException ex){
                //Key not found. Create new one.
                series = new XYSeries("Player " + i);
                series.add( System.currentTimeMillis()/1000, lapData.getCarPosition());
                dataSet.addSeries(series);
            }
        }

    }

}
