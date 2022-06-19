package com.cire.formula1.service;

import com.cire.formula1.packet.model.data.CarMotionData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.Align;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.UnknownKeyException;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class GraphServiceImpl implements GraphService{

    private final static Logger LOGGER = LoggerFactory.getLogger(GraphServiceImpl.class);

    @Override
    public synchronized JFreeChart createPlayerPositionGraph(XYSeriesCollection playerPositionDataSet) {
        JFreeChart playerPositionGraph;
        playerPositionGraph = ChartFactory.createXYLineChart(
                "Player Positions",
                "Race Session", "Player position", playerPositionDataSet);

        String fontName = "Palatino";
        playerPositionGraph.getTitle().setFont(new Font(fontName, Font.BOLD, 18));
        playerPositionGraph.addSubtitle(new TextTitle(
                "This is a test.",
                new Font(fontName, Font.PLAIN, 14)));

        playerPositionGraph.getLegend().setItemFont(new Font(fontName, Font.PLAIN, 14));
        playerPositionGraph.getLegend().setFrame(BlockBorder.NONE);
        playerPositionGraph.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);

        playerPositionGraph.getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        //graph.getXYPlot().getDomainAxis().setVisible(false);

        final NumberAxis xAxis = (NumberAxis) playerPositionGraph.getXYPlot().getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(1000));

        playerPositionGraph.getXYPlot().setRenderer(new XYSplineRenderer());

        return playerPositionGraph;
    }

    @Override
    public JFreeChart createMotionGraph(XYSeriesCollection motionDataSet) {
        JFreeChart motionGraph;
        motionGraph = ChartFactory.createXYLineChart(
                "Motion Graph Test",
                "Motion Graph", null, motionDataSet);

        String fontName = "Palatino";
        motionGraph.getTitle().setFont(new Font(fontName, Font.BOLD, 18));
        motionGraph.addSubtitle(new TextTitle(
                "This is a test.",
                new Font(fontName, Font.PLAIN, 14)));

        motionGraph.getLegend().setItemFont(new Font(fontName, Font.PLAIN, 14));
        motionGraph.getLegend().setFrame(BlockBorder.NONE);
        motionGraph.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);

        Image image = null;
        try {
            File pathToFile = new File("BahrainMap_test.png");
            image = ImageIO.read(pathToFile);
        } catch (IOException ex) {
            LOGGER.info("Failed to load map.");
        }

        if(image != null) {
            motionGraph.getXYPlot().setDomainGridlinesVisible(false);
            motionGraph.getXYPlot().setRangeGridlinesVisible(false);
            Color trans = new Color(0xFF, 0xFF, 0xFF, 0);
            motionGraph.getPlot().setBackgroundPaint( trans );
            motionGraph.setBackgroundImage(image);
            motionGraph.setBackgroundImageAlignment(Align.FIT);
        }

        //motionGraph.getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        //graph.getXYPlot().getDomainAxis().setVisible(false);
        //final NumberAxis xAxis = (NumberAxis) motionGraph.getXYPlot().getDomainAxis();
        //xAxis.setTickUnit(new NumberTickUnit(1000));

        //motionGraph.getXYPlot().setRenderer(new XYSplineRenderer());

        return motionGraph;
    }

}
