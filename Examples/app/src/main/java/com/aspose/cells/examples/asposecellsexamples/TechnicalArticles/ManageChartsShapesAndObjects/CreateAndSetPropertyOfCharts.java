package com.aspose.cells.examples.asposecellsexamples.TechnicalArticles.ManageChartsShapesAndObjects;

import android.os.Environment;
import android.util.Log;

import com.aspose.cells.Axis;
import com.aspose.cells.Cells;
import com.aspose.cells.Chart;
import com.aspose.cells.ChartCollection;
import com.aspose.cells.ChartType;
import com.aspose.cells.Color;
import com.aspose.cells.Font;
import com.aspose.cells.LegendPositionType;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.SeriesCollection;
import com.aspose.cells.Title;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;

import java.io.File;

public class CreateAndSetPropertyOfCharts {

    private static final String TAG = CreateAndSetPropertyOfCharts.class.getName();

    public void createAndSetPropertyOfCharts() {
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + File.separator + "Aspose");
            String filePath = myDir.getCanonicalPath() + File.separator;

            Workbook workbook = new Workbook(filePath + "source.xlsx");

            WorksheetCollection worksheets = workbook.getWorksheets();
            Worksheet worksheet = worksheets.get(0);
            // Set the name of worksheet
            worksheet.setName("Area");

            // Create chart
            ChartCollection charts = worksheet.getCharts();
            Chart chart = charts.get(charts.add(ChartType.AREA, 5, 1, 29, 10));

            // Set properties of nseries
            SeriesCollection nSeries = chart.getNSeries();
            nSeries.add("B4:F4", false);
            nSeries.add("B3:F3", false);
            nSeries.add("B2:F2", false);
            nSeries.setCategoryData("B1:F1");

            Cells cells = worksheet.getCells();
            String temp = "";
            for (int i = 0; i < chart.getNSeries().getCount(); i++) {
                temp = cells.get(i + 1, 0).getStringValue();
                nSeries.get(i).setName(temp);
                nSeries.get(i).setColorVaried(true);
            }

            // Set properties of Legend
            chart.getLegend().setPosition(LegendPositionType.TOP);

            // Set properties of chart title
            Title title = chart.getTitle();
            title.setText("Sales By Region");
            Font font1 = title.getFont();
            font1.setColor(Color.getBlack());
            font1.setBold(true);
            font1.setSize(12);

            // Set properties of categoryaxis title
            Axis categoryAxis = chart.getCategoryAxis();
            title = categoryAxis.getTitle();
            title.setText("Year(2002-2006)");
            Font font2 = title.getFont();
            font2.setColor(Color.getBlack());
            font2.setBold(true);
            font2.setSize(10);
            categoryAxis.setAxisBetweenCategories(false);
            workbook.save(filePath + "SetPropertyOfCharts_Out.xlsx", SaveFormat.XLSX);
        } catch (Exception e) {
            Log.e(TAG, "Create and Set Property of Charts", e);
        }
    }
}
