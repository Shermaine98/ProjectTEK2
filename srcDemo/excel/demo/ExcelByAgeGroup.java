/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel.demo;

import checker.demo.ByAgeGroupSexChecker;
import model.demo.ByAgeGroupSex;
import model.temp.demo.ByAgeGroupTemp;
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
 *
 * @author shermainesy
 */
public class ExcelByAgeGroup {

    //STORE FOR PRINTING
    private ArrayList<ByAgeGroupSex> arrayNoError;
    private ArrayList<ByAgeGroupTemp> arrayError;

    //JAVA
    ByAgeGroupSexChecker byAgeGroupSexChecker;

    //STORE TEMP WITHOUT CHECKING
    ArrayList<ByAgeGroupTemp> ArrErrorByAgeGroupSex;

    final private StringBuilder out = new StringBuilder(65536);
    final private SimpleDateFormat sdf;
    final private HSSFWorkbook book;
    final private HSSFPalette palette;
    final private FormulaEvaluator evaluator;
    private int colIndex;
    private int rowIndex, mergeStart, mergeEnd;
    // Row -> Column -> Pictures
    private Map<Integer, Map<Short, List<HSSFPictureData>>> pix = new HashMap<>();
    private String firstCell = "Location";
    private Boolean isSecond = false;
    private boolean isMerged = false;
    private HSSFSheet sheet;
    String errors;
    boolean checkGrandTotal;

    /**
     * Generates HTML from the InputStream of an Excel file. Generates sheet
     * name in HTML h1 element.
     *
     * @param in InputStream of the Excel file.
     * @throws IOException When POI cannot read from the input stream.
     */
    public ExcelByAgeGroup(final InputStream in, int sheetNumber) throws IOException {
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
        setArrayError(new ByAgeGroupSexChecker(ArrErrorByAgeGroupSex).getArrayError());
        setArrayNoError(new ByAgeGroupSexChecker(ArrErrorByAgeGroupSex).getArrayNoError());
        if (arrayError.isEmpty()) {
            checkGrandTotal = (new ByAgeGroupSexChecker().checkGrandTotal(ArrErrorByAgeGroupSex));
        }
    }

    public ExcelByAgeGroup(final HSSFWorkbook book, int sheetNumber) throws IOException {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.book = book;
        palette = book.getCustomPalette();
        evaluator = book.getCreationHelper().createFormulaEvaluator();
        sheet = book.getSheetAt(sheetNumber);
        table(sheet);
        setArrayError(new ByAgeGroupSexChecker(ArrErrorByAgeGroupSex).getArrayError());
        setArrayNoError(new ByAgeGroupSexChecker(ArrErrorByAgeGroupSex).getArrayNoError());
        if (arrayError.isEmpty()) {
            checkGrandTotal = (new ByAgeGroupSexChecker().checkGrandTotal(ArrErrorByAgeGroupSex));
        }
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

        ArrErrorByAgeGroupSex = new ArrayList<>();
        for (rowIndex = 6; rowIndex < sheet.getLastRowNum(); ++rowIndex) {
            HSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                tr(row, ArrErrorByAgeGroupSex);
            }
        }
    }

    /**
     * (Each Excel sheet row becomes an HTML table row) Generates an HTML table
     * row which has the same height as the Excel row.
     *
     * @param row The Excel row.
     */
    private void tr(final HSSFRow row, ArrayList<ByAgeGroupTemp> ArrErrorByAgeGroupSex) {
        if (row == null) {
            return;
        }
        //checks if null and if it contains the location
        if (row.getCell(0) != null && row.getCell(0).getCellType() == CELL_TYPE_STRING) {
            if (row.getCell(0).getStringCellValue().contains("Age Group")
                    || row.getCell(0).getStringCellValue().contains("Barangay")
                    || row.getCell(0).getStringCellValue().contains("CALOOCAN CITY")) {
                if (row.getCell(0).getStringCellValue().contains("Age Group")) {
                    rowIndex++;
                    return;
                } else if (row.getCell(0).getStringCellValue().contains("CALOOCAN CITY")) {
                    firstCell = "Caloocan City";
                    return;
                } else if (row.getCell(0).getStringCellValue().contains("Barangay")) {
                    firstCell = row.getCell(0).getStringCellValue();
                    return;
                }
            }
        }

        //WHAT IS THIS FOR
        if (row.getCell(0) == null || row.getCell(0).getCellType() == CELL_TYPE_BLANK) {
            if (row.getCell(1) == null || row.getCell(1).getCellType() == CELL_TYPE_BLANK) {
                if (row.getCell(2) == null || row.getCell(2).getCellType() == CELL_TYPE_BLANK) {
                    if (row.getCell(3) == null || row.getCell(3).getCellType() == CELL_TYPE_BLANK) {
                        return;
                    }
                }
            }
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

        ByAgeGroupTemp byAgeGroupError = new ByAgeGroupTemp();
        for (colIndex = 0; colIndex < 4; ++colIndex) {
            td(row.getCell(colIndex), byAgeGroupError);
        }
        ArrErrorByAgeGroupSex.add(byAgeGroupError);
        //getHTML();
    }

    /**
     * (Each Excel sheet cell becomes an HTML table cell) Generates an HTML
     * table cell which has the same font styles, alignments, colours and
     * borders as the Excel cell.
     *
     * @param cell The Excel cell.
     */
    private void td(final HSSFCell cell, ByAgeGroupTemp byAgeGroupError) {

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

        if (colIndex == 0) {
            byAgeGroupError.setBarangay(firstCell);
        }

        //Check for incorrect/incomplete data.
        switch (colIndex) {
            case 0: //AGE GROUP
                byAgeGroupError.setAgeGroup(GetFormat(cell));
                break;
            case 1: //BOTH SEXES
                byAgeGroupError.setBothSex(GetFormat(cell));
                break;
            case 2://MALE
                byAgeGroupError.setMaleCount(GetFormat(cell));
                break;
            case 3://FEMALE
                byAgeGroupError.setFemaleCount(GetFormat(cell));
                break;
        }
    }

    public String GetFormat(final HSSFCell cell) {
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
    public ArrayList<ByAgeGroupSex> getArrayNoError() {
        return arrayNoError;
    }

    /**
     * @param arrayNoError the arrayNoError to set
     */
    public void setArrayNoError(ArrayList<ByAgeGroupSex> arrayNoError) {
        this.arrayNoError = arrayNoError;
    }

    /**
     * @return the arrayError
     */
    public ArrayList<ByAgeGroupTemp> getArrayError() {
        return arrayError;
    }

    /**
     * @param arrayError the arrayError to set
     */
    public void setArrayError(ArrayList<ByAgeGroupTemp> arrayError) {
        this.arrayError = arrayError;
    }

    public boolean isCheckGrandTotal() {
        return checkGrandTotal;
    }

    public void setCheckGrandTotal(boolean checkGrandTotal) {
        this.checkGrandTotal = checkGrandTotal;
    }

}
