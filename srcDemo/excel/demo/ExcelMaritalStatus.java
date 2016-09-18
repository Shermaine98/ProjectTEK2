package excel.demo;

import checker.demo.MaritalStatusChecker;
import model.demo.MaritalStatus;
import model.temp.demo.MaritalStatusTemp;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Math.abs;
import static java.lang.String.valueOf;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Use Apache POI to read an Excel (.xls) file and output an HTML table per
 * sheet.
 *
 * @author gian, shermaine and geraldine
 */
public class ExcelMaritalStatus {
//STORE FOR PRINTING

    private ArrayList<MaritalStatus> arrayNoError;
    private ArrayList<MaritalStatusTemp> arrayError;

    //JAVA
    MaritalStatusChecker checker;

    //STORE TEMP WITHOUT CHECKING
    ArrayList<MaritalStatusTemp> temp;

    final private StringBuilder out = new StringBuilder(65536);
    final private SimpleDateFormat sdf;
    final private HSSFWorkbook book;
    final private HSSFPalette palette;
    final private FormulaEvaluator evaluator;
    private int colIndex;
    private int rowIndex, mergeStart, mergeEnd;
    // Row -> Column -> Pictures
    private Map<Integer, Map<Short, List<HSSFPictureData>>> pix = new HashMap<>();
    private String firstCell = "Caloocan City";
    private String secondCell = "Both Sexes";
    private boolean isMerged = false;
    private HSSFSheet sheet;
    String errors;
    MaritalStatusTemp MaritalStatusTemp = new MaritalStatusTemp();

    /**
     * Generates HTML from the InputStream of an Excel file. Generates sheet
     * name in HTML h1 element.
     *
     * @param in InputStream of the Excel file.
     * @throws IOException When POI cannot read from the input stream.
     */
    public ExcelMaritalStatus(final InputStream in, int sheetNumber) throws IOException {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (in == null) {
            book = null;
            palette = null;
            evaluator = null;
            return;
        }
        book = new HSSFWorkbook(in);
        palette = book.getCustomPalette();
        evaluator = book.getCreationHelper().createFormulaEvaluator();
        sheet = book.getSheetAt(sheetNumber);
        table(sheet);
        setArrayError(new MaritalStatusChecker(temp).getArrayError());
        setArrayNoError(new MaritalStatusChecker(temp).getArrayNoError());

    }

    public ExcelMaritalStatus(final HSSFWorkbook book, int sheetNumber) throws IOException {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.book = book;
        palette = book.getCustomPalette();
        evaluator = book.getCreationHelper().createFormulaEvaluator();
        temp = new ArrayList<>();
        sheet = book.getSheetAt(sheetNumber);
        table(sheet);
        setArrayError(new MaritalStatusChecker(temp).getArrayError());
        setArrayNoError(new MaritalStatusChecker(temp).getArrayNoError());

    }

    /**
     * (Each Excel sheet produces an HTML table) Generates an HTML table with no
     * cell, border spacing or padding.
     *
     * @param sheet The Excel sheet.
     */
    private void table(final HSSFSheet sheet) {
        if (sheet == null) {
            return;
        }
        
        for (rowIndex = 5; rowIndex <= sheet.getLastRowNum(); ++rowIndex) {
            HSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
//                tr(row, temp);
                tr(row);
                
                //validation.checker;
            }
        }
    }

    /**
     * (Each Excel sheet row becomes an HTML table row) Generates an HTML table
     * row which has the same height as the Excel row.
     *
     * @param row The Excel row.
     */
    private void tr(final HSSFRow row) {
        if (row == null) {
            return;
        }
        //checks if null and if it contains the location
        if(row.getCell(0)!= null &&row.getCell(0).getCellType() == CELL_TYPE_STRING) {
                if (row.getCell(0).getStringCellValue().contains("CALOOCAN CITY")) {
                    firstCell = "Caloocan City";
                    return;
                } else if (row.getCell(0).getStringCellValue().startsWith("Barangay")) {
                    firstCell = row.getCell(0).getStringCellValue();
                    return;
                } else if (row.getCell(0).getStringCellValue().equalsIgnoreCase("Female")) {
                    secondCell = "Female";
                } else if (row.getCell(0).getStringCellValue().equalsIgnoreCase("Male")) {
                    secondCell = "Male";
                } else if (row.getCell(0).getStringCellValue().replaceAll(" ", "").equalsIgnoreCase("BothSexes")) {
                    secondCell = "Both Sexes";
                }            
        }
        
        if(row.getCell(0) != null && row.getCell(0).getCellType() == CELL_TYPE_STRING){            
            if (row.getCell(0).getStringCellValue().contains("Female")) {
                secondCell = "Female";
            } else if (row.getCell(0).getStringCellValue().contains("Male")) {
                secondCell = "Male";
            }
        }
        
         if(row.getCell(0) == null || row.getCell(0).getCellType() == CELL_TYPE_BLANK)
                if(row.getCell(1) == null || row.getCell(1).getCellType() == CELL_TYPE_BLANK)
                if(row.getCell(2) == null || row.getCell(2).getCellType() == CELL_TYPE_BLANK)
                if(row.getCell(3) == null || row.getCell(3).getCellType() == CELL_TYPE_BLANK)
                if(row.getCell(4) == null || row.getCell(4).getCellType() == CELL_TYPE_BLANK)
                if(row.getCell(5) == null || row.getCell(5).getCellType() == CELL_TYPE_BLANK)
                if(row.getCell(6) == null || row.getCell(6).getCellType() == CELL_TYPE_BLANK)
                if(row.getCell(7) == null || row.getCell(7).getCellType() == CELL_TYPE_BLANK){
                return;
        }

        // Find merged cells in current row.
        for (int i = 0; i < row.getSheet().getNumMergedRegions(); ++i) {
            final CellRangeAddress merge = row.getSheet().getMergedRegion(i);
            if (rowIndex >= merge.getFirstRow()
                    && rowIndex <= merge.getLastRow()) {
                mergeStart = merge.getFirstColumn();
                mergeEnd = merge.getLastColumn();
                isMerged = true;
                break;
            } else {
                isMerged = false;
            }
        }

        MaritalStatusTemp = new MaritalStatusTemp();
        for (colIndex = 0; colIndex < 8; ++colIndex) {
            td(row.getCell(colIndex));
        }


        temp.add(MaritalStatusTemp);
    }

    /**
     * (Each Excel sheet cell becomes an HTML table cell) Generates an HTML
     * table cell which has the same font styles, alignments, colours and
     * borders as the Excel cell.
     *
     * @param cell The Excel cell.
     */
    private void td(final HSSFCell cell) {
        if(cell == null){
            return;
        }
        int colspan = 1;

        if (colIndex == mergeStart) {
            // First cell in the merging region - set colspan.
            colspan = mergeEnd - mergeStart + 1;
        } else if (colIndex == mergeEnd) {
            // Last cell in the merging region - no more skipped cells.
            mergeStart = -1;
            mergeEnd = -1;
            return;
        } else if (mergeStart != -1 && mergeEnd != -1 && colIndex > mergeStart
                && colIndex < mergeEnd) {
            // Within the merging region - skip the cell.
            return;
        }

        if (colIndex == 0) {;
            MaritalStatusTemp.setLocation(firstCell);
            MaritalStatusTemp.setSex(secondCell);
        }

        switch (colIndex) {
            case 0:
                if(getFormat(cell).replaceAll(" ", "").equalsIgnoreCase("BothSexes")
                        || getFormat(cell).equalsIgnoreCase("Female")
                        || getFormat(cell).equalsIgnoreCase("Male")){
                    MaritalStatusTemp.setAgeGroup("Total");
                }
                else
                MaritalStatusTemp.setAgeGroup(getFormat(cell));
                break;
            case 1: //BOTH TOTAL
                MaritalStatusTemp.setTotal(getFormat(cell));
                break;
            case 2://SINGLE
                MaritalStatusTemp.setSingle(getFormat(cell));
                break;
            case 3://MARRIED
                MaritalStatusTemp.setMarried(getFormat(cell));
                break;
            case 4://WIDOWED
                MaritalStatusTemp.setWidowed(getFormat(cell));
                break;
            case 5://MARRIED
                MaritalStatusTemp.setDivorcedSeparated(getFormat(cell));
                break;
            case 6://MARRIED
                MaritalStatusTemp.setCommonLawLiveIn(getFormat(cell));
                break;
            case 7://MARRIED
                MaritalStatusTemp.setUnknown(getFormat(cell));
                break;
        }

    }

    public String getFormat(final HSSFCell cell) {
        String val = "";
        try {
            switch (cell.getCellType()) {
                case CELL_TYPE_STRING:
                    val = cell.getStringCellValue();
                    break;
                case CELL_TYPE_NUMERIC:
                    // POI does not distinguish between integer and double, thus:
 final double original = cell.getNumericCellValue(),
                     rounded = Math.round(original);
                    if (abs(rounded - original) < 0.00000000000000001) {
                        val = valueOf((int) rounded);
                    } else {
                        val = valueOf(original);
                    }
                    break;
                case CELL_TYPE_FORMULA:
                    final CellValue cv = evaluator.evaluate(cell);
                    switch (cv.getCellType()) {
                        case CELL_TYPE_BOOLEAN:
                            val = valueOf(cv.getBooleanValue());
                            break;
                        case CELL_TYPE_NUMERIC:
                            val = valueOf(cv.getNumberValue());
                            break;
                        case CELL_TYPE_STRING:
                            val = valueOf(cv.getStringValue());
                            break;
                        case CELL_TYPE_BLANK:
                            val = "";
                            break;
                        case CELL_TYPE_ERROR:
                            val = "";
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    // Neither string or number? Could be a date.
                    try {
                        val = sdf.format(cell.getDateCellValue());
                    } catch (final Exception e1) {
                    }
            }
        } catch (final Exception e) {
            val = e.getMessage();
        }
        return val;
    }

    /**
     * @return the arrayNoError
     */
    public ArrayList<MaritalStatus> getArrayNoError() {
        return arrayNoError;
    }

    /**
     * @param arrayNoError the arrayNoError to set
     */
    public void setArrayNoError(ArrayList<MaritalStatus> arrayNoError) {
        this.arrayNoError = arrayNoError;
    }

    /**
     * @return the arrayError
     */
    public ArrayList<MaritalStatusTemp> getArrayError() {
        return arrayError;
    }

    /**
     * @param arrayError the arrayError to set
     */
    public void setArrayError(ArrayList<MaritalStatusTemp> arrayError) {
        this.arrayError = arrayError;
    }
}
