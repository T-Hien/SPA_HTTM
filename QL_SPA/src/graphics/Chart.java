package graphics;

import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;

import entity.Statistics;

public class Chart 
{
	private JFreeChart chart;
	private String title;
	private Color color;
	
	public Chart() {}

	public Chart(String title, Color color)
	{
		this.title = title.trim();
		this.color = color;
	}

	public void createPieChart(String[] info, int[] value, int size)
	{
		DefaultPieDataset pieChart = new DefaultPieDataset();
		PiePlot piePlot;
		for(int i=0; i<size; i++)
		{
			pieChart.setValue(info[i], value[i]);
		}
		this.chart = ChartFactory.createPieChart(this.title, pieChart, true, true, true);
		this.chart.getPlot().setBackgroundPaint(this.color);
		piePlot = (PiePlot) this.chart.getPlot();
		piePlot.setLabelGenerator( new StandardPieSectionLabelGenerator(" {1} "));
		piePlot.setOutlineVisible(false);
	}
	
	public void createBarChart(ArrayList<Statistics> lst, String xLabel, String yLabel)
	{
		DefaultCategoryDataset barChart = new DefaultCategoryDataset();
		int count = 0;
		for(Statistics s : lst)
		{
			 barChart.setValue(s.getIndividualIncome(), "staffs", s.getStaffName().trim());
			 if(s.getIndividualIncome() == 0) count++;
		}
		if(lst.size() == count) barChart.clear();
		this.chart = ChartFactory.createBarChart(this.title, xLabel, yLabel, barChart, PlotOrientation.HORIZONTAL,false, true, false);
		this.chart.getPlot().setBackgroundPaint(this.color);
	}
	
	public void createAreaChart(String[] xData, String[] yData, double[][] data, String xLabel, String yLabel)
	{
		CategoryPlot plot;
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(xData, yData, data);
		this.chart = ChartFactory.createAreaChart(this.title, xLabel, yLabel, dataset, PlotOrientation.VERTICAL, true, true, false);
		this.chart.getPlot().setBackgroundPaint(this.color);
		plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.5f);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.getDomainAxis().setLowerMargin(0.0);
        plot.getDomainAxis().setUpperMargin(0.0);
        ((NumberAxis) plot.getRangeAxis()).setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	}
	
	public JFreeChart getChart() 
	{
		return this.chart;
	}
	
	public void setChart(JFreeChart chart) 
	{
		this.chart = chart;
	}
	
	public String getTitle() 
	{
		return this.title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public Color getColor() 
	{
		return this.color;
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}
}
