package com.aspose.cells.examples.asposecellsexamples.TechnicalArticles.ManageRowsColumnsCells;

import android.os.Environment;
import android.util.Log;

import com.aspose.cells.PasteOptions;
import com.aspose.cells.PasteType;
import com.aspose.cells.Range;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import java.io.File;

public class CopyRowHeightsOfSourceRangeToDestinationRange {

    private static final String TAG = CopyRowHeightsOfSourceRangeToDestinationRange.class.getName();

    public void copyRowHeightsOfSourceRangeToDestinationRange() {
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + File.separator + "Aspose");
            String filePath = myDir.getCanonicalPath() + File.separator;

            //Create workbook object
            Workbook workbook = new Workbook();

            //Source worksheet
            Worksheet srcSheet = workbook.getWorksheets().get(0);

            //Add destination worksheet
            Worksheet dstSheet = workbook.getWorksheets().add("Destination Sheet");

            //Set the row height of the 4th row
            //This row height will be copied to destination range
            srcSheet.getCells().setRowHeight(3, 50);

            //Create source range to be copied
            Range srcRange = srcSheet.getCells().createRange("A1:D10");

            //Create destination range in destination worksheet
            Range dstRange = dstSheet.getCells().createRange("A1:D10");

            //PasteOptions, we want to copy row heights of source range to
            //destination range
            PasteOptions opts = new PasteOptions();
            opts.setPasteType(PasteType.ROW_HEIGHTS);

            //Copy source range to destination range with paste options
            dstRange.copy(srcRange, opts);

            //Write informative message in cell D4 of destination worksheet
            dstSheet.getCells().get("D4").putValue("Row heights of source range copied to destination range");

            //Save the workbook in xlsx format
            workbook.save(filePath + "CopyRowHeights_Out.xlsx", SaveFormat.XLSX);
        } catch (Exception e) {
            Log.e(TAG, "Copy Row Heights of Source Range to Destination Range", e);
        }
    }

}
