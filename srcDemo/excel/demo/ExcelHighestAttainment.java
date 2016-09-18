package excel.demo;

import checker.demo.HighestCompletedChecker;
import model.demo.HighestCompleted;
import model.temp.demo.HighestCompletedAgeGroupTemp;
import model.temp.demo.HighestCompletedTemp;
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
 * @author gian, shermaine, geraldine
 */
public class ExcelHighestAttainment {

    //STORE FOR PRINTING
    private ArrayList<HighestCompleted> arrayNoError;
    private ArrayList<HighestCompletedTemp> arrayError;

    //JAVA
    HighestCompletedChecker byAgeGroupSexChecker;

    //STORE TEMP WITHOUT CHECKING
    ArrayList<HighestCompletedTemp> ArrErrorByAgeGroupSex;

    HighestCompletedTemp HighestCompletedTemp = new HighestCompletedTemp();
    ArrayList<HighestCompletedAgeGroupTemp> arrHighestCompletedAgeGroupTemp;

    final private StringBuilder out = new StringBuilder(65536);
    final private SimpleDateFormat sdf;
    final private HSSFWorkbook book;
    final private HSSFPalette palette;
    final private FormulaEvaluator evaluator;
    private int colIndex;
    private int rowIndex, mergeStart, mergeEnd;
    private Map<Integer, Map<Short, List<HSSFPictureData>>> pix = new HashMap<Integer, Map<Short, List<HSSFPictureData>>>();
    private String firstCell = "Caloocan City";
    private String secondCell = "Both Sexes";
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
    public ExcelHighestAttainment(final InputStream in, int sheetNumber) throws IOException {
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
        ArrErrorByAgeGroupSex = new ArrayList<HighestCompletedTemp>();
        table(sheet);
        System.out.println("ARRAY SIZE 1: "+ArrErrorByAgeGroupSex.size());
        setArrayError(new HighestCompletedChecker(ArrErrorByAgeGroupSex).getArrayError());
        setArrayNoError(new HighestCompletedChecker(ArrErrorByAgeGroupSex).getArrayNoError());
    }

    public ExcelHighestAttainment(final HSSFWorkbook book, int sheetNumber) throws IOException {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.book = book;
        palette = book.getCustomPalette();
        evaluator = book.getCreationHelper().createFormulaEvaluator();
        sheet = book.getSheetAt(sheetNumber);

        ArrErrorByAgeGroupSex = new ArrayList<HighestCompletedTemp>();
        table(sheet);
        System.out.println("ARRAY SIZE 1: "+ArrErrorByAgeGroupSex.size());
        setArrayError(new HighestCompletedChecker(ArrErrorByAgeGroupSex).getArrayError());
        setArrayNoError(new HighestCompletedChecker(ArrErrorByAgeGroupSex).getArrayNoError());
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

        for (rowIndex = 6; rowIndex < sheet.getLastRowNum(); ++rowIndex) {
            HSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                tr(row);
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
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase("Female")) {
                    secondCell = "Female";
                    return;
                } else if (row.getCell(0).getStringCellValue().equalsIgnoreCase("Male")) {
                    secondCell = "Male";
                    return;
                } else if (row.getCell(0).getStringCellValue().replaceAll(" ", "").equalsIgnoreCase("BothSexes")) {
                    secondCell = "Both Sexes";
                    return;
                }            
        }
        
        //checks if null and if it contains the location
        if (row.getCell(0) != null && row.getCell(0).getCellType() == CELL_TYPE_STRING) {
            if (row.getCell(0).getStringCellValue().contains("Highest Grade/Year Completed and Sex")
                    || row.getCell(0).getStringCellValue().contains("Barangay")
                    || row.getCell(0).getStringCellValue().contains("CALOOCAN CITY")) {
                if (row.getCell(0).getStringCellValue().contains("Highest Grade/Year Completed and Sex")) {
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

        if (row.getCell(0) == null || row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
            if (row.getCell(1) == null || row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if (row.getCell(2) == null || row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    if (row.getCell(3) == null || row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        if (row.getCell(4) == null || row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                            if (row.getCell(5) == null || row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                if (row.getCell(6) == null || row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                    if (row.getCell(7) == null || row.getCell(7).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                        if (row.getCell(8) == null || row.getCell(8).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

//        out.append("<tr ");
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
        for (colIndex = 0; colIndex < 9; ++colIndex) {
            td(row.getCell(colIndex), row);
        }

    }

    /**
     * (Each Excel sheet cell becomes an HTML table cell) Generates an HTML
     * table cell which has the same font styles, alignments, colours and
     * borders as the Excel cell.
     *
     * @param cell The Excel cell.
     */
    private void td(final HSSFCell cell, final HSSFRow row) {

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

        HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp;
        HighestCompletedTemp = new HighestCompletedTemp();
        String position = null;
        switch (colIndex) {
            case 0:return;
            case 1://total
                position = GetFormat(row.getCell(0));
                if (position.equalsIgnoreCase("Total")) {
                    HighestCompletedTemp.setLocation(firstCell);
                    HighestCompletedTemp.setSex(secondCell);
                    HighestCompletedTemp.setAgeGroup("5 and above");
                    HighestCompletedTemp.setTotal(GetFormat(cell));
                    
                    arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                    
                    //No Grade Completed
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+1).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+1).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Pre school
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+2).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+2).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Elementary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+3).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+3).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //1st - 4th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+4).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+4).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //5th to 6th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+5).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+5).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //Elem Graduate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Elementary "+GetFormat(sheet.getRow(rowIndex+6).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+6).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+7).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+7).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School "+GetFormat(sheet.getRow(rowIndex+8).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+8).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School " +GetFormat(sheet.getRow(rowIndex+9).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+9).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+10).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+10).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+11).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+11).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+12).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+12).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //College Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+13).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+13).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Academic Degree Holder
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+14).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+14).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Baccalaureate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+15).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+15).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Not Stated
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+16).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+16).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    ArrErrorByAgeGroupSex.add(HighestCompletedTemp);
                } else {
                    return;
                }
                break;
            case 2://5 - 9
                position = GetFormat(row.getCell(0));
                if (position.equalsIgnoreCase("Total")) {
                    HighestCompletedTemp.setLocation(firstCell);
                    HighestCompletedTemp.setSex(secondCell);
                    HighestCompletedTemp.setAgeGroup("5-9");
                    HighestCompletedTemp.setTotal(GetFormat(cell));
                    
                    arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                    
                    //No Grade Completed
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+1).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+1).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Pre school
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+2).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+2).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Elementary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+3).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+3).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //1st - 4th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+4).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+4).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //5th to 6th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+5).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+5).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //Elem Graduate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Elementary "+GetFormat(sheet.getRow(rowIndex+6).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+6).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+7).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+7).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School "+GetFormat(sheet.getRow(rowIndex+8).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+8).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School " +GetFormat(sheet.getRow(rowIndex+9).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+9).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+10).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+10).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+11).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+11).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+12).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+12).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //College Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+13).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+13).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Academic Degree Holder
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+14).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+14).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Baccalaureate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+15).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+15).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Not Stated
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+16).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+16).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    ArrErrorByAgeGroupSex.add(HighestCompletedTemp);
                } else {
                    return;
                }
                break;
            case 3://10 - 14
                position = GetFormat(row.getCell(0));
                if (position.equalsIgnoreCase("Total")) {
                    HighestCompletedTemp.setLocation(firstCell);
                    HighestCompletedTemp.setSex(secondCell);
                    HighestCompletedTemp.setAgeGroup("10-14");
                    HighestCompletedTemp.setTotal(GetFormat(cell));
                    
                    arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                    
                    //No Grade Completed
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+1).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+1).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Pre school
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+2).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+2).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Elementary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+3).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+3).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //1st - 4th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+4).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+4).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //5th to 6th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+5).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+5).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //Elem Graduate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Elementary "+GetFormat(sheet.getRow(rowIndex+6).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+6).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+7).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+7).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School "+GetFormat(sheet.getRow(rowIndex+8).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+8).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School " +GetFormat(sheet.getRow(rowIndex+9).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+9).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+10).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+10).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+11).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+11).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+12).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+12).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //College Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+13).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+13).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Academic Degree Holder
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+14).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+14).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Baccalaureate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+15).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+15).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Not Stated
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+16).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+16).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    ArrErrorByAgeGroupSex.add(HighestCompletedTemp);
                } else {
                    return;
                }

                break;
            case 4://15 - 19
                position = GetFormat(row.getCell(0));
                if (position.equalsIgnoreCase("Total")) {
                    HighestCompletedTemp.setLocation(firstCell);
                    HighestCompletedTemp.setSex(secondCell);
                    HighestCompletedTemp.setAgeGroup("15-19");
                    HighestCompletedTemp.setTotal(GetFormat(cell));
                    
                    arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                    
                    //No Grade Completed
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+1).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+1).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Pre school
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+2).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+2).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Elementary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+3).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+3).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //1st - 4th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+4).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+4).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //5th to 6th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+5).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+5).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //Elem Graduate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Elementary "+GetFormat(sheet.getRow(rowIndex+6).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+6).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+7).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+7).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School "+GetFormat(sheet.getRow(rowIndex+8).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+8).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School " +GetFormat(sheet.getRow(rowIndex+9).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+9).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+10).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+10).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+11).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+11).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+12).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+12).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //College Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+13).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+13).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Academic Degree Holder
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+14).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+14).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Baccalaureate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+15).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+15).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Not Stated
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+16).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+16).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    ArrErrorByAgeGroupSex.add(HighestCompletedTemp);
                } else {
                    return;
                }
                break;
            case 5://20 - 24
                position = GetFormat(row.getCell(0));
                if (position.equalsIgnoreCase("Total")) {
                    HighestCompletedTemp.setLocation(firstCell);
                    HighestCompletedTemp.setSex(secondCell);
                    HighestCompletedTemp.setAgeGroup("20-24");
                    HighestCompletedTemp.setTotal(GetFormat(cell));
                    
                    arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                    
                    //No Grade Completed
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+1).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+1).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Pre school
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+2).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+2).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Elementary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+3).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+3).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //1st - 4th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+4).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+4).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //5th to 6th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+5).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+5).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //Elem Graduate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Elementary "+GetFormat(sheet.getRow(rowIndex+6).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+6).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+7).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+7).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School "+GetFormat(sheet.getRow(rowIndex+8).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+8).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School " +GetFormat(sheet.getRow(rowIndex+9).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+9).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+10).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+10).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+11).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+11).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+12).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+12).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //College Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+13).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+13).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Academic Degree Holder
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+14).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+14).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Baccalaureate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+15).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+15).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Not Stated
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+16).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+16).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    ArrErrorByAgeGroupSex.add(HighestCompletedTemp);
                } else {
                    return;
                }
                break;

            case 6://25 - 29

                position = GetFormat(row.getCell(0));
                if (position.equalsIgnoreCase("Total")) {
                    HighestCompletedTemp.setLocation(firstCell);
                    HighestCompletedTemp.setSex(secondCell);
                    HighestCompletedTemp.setAgeGroup("25-29");
                    HighestCompletedTemp.setTotal(GetFormat(cell));
                    
                    arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                    
                    //No Grade Completed
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+1).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+1).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Pre school
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+2).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+2).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Elementary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+3).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+3).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //1st - 4th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+4).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+4).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //5th to 6th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+5).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+5).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //Elem Graduate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Elementary "+GetFormat(sheet.getRow(rowIndex+6).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+6).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+7).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+7).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School "+GetFormat(sheet.getRow(rowIndex+8).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+8).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School " +GetFormat(sheet.getRow(rowIndex+9).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+9).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+10).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+10).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+11).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+11).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+12).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+12).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //College Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+13).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+13).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Academic Degree Holder
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+14).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+14).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Baccalaureate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+15).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+15).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Not Stated
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+16).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+16).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    ArrErrorByAgeGroupSex.add(HighestCompletedTemp);
                } else {
                    return;
                }
                break;
            case 7://30 - 34

                position = GetFormat(row.getCell(0));
                if (position.equalsIgnoreCase("Total")) {
                    HighestCompletedTemp.setLocation(firstCell);
                    HighestCompletedTemp.setSex(secondCell);
                    HighestCompletedTemp.setAgeGroup("30-34");
                    HighestCompletedTemp.setTotal(GetFormat(cell));
                    
                    arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                    
                    //No Grade Completed
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+1).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+1).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Pre school
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+2).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+2).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Elementary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+3).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+3).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //1st - 4th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+4).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+4).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //5th to 6th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+5).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+5).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //Elem Graduate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Elementary "+GetFormat(sheet.getRow(rowIndex+6).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+6).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+7).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+7).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School "+GetFormat(sheet.getRow(rowIndex+8).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+8).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School " +GetFormat(sheet.getRow(rowIndex+9).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+9).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+10).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+10).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+11).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+11).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+12).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+12).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //College Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+13).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+13).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Academic Degree Holder
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+14).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+14).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Baccalaureate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+15).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+15).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Not Stated
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+16).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+16).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    ArrErrorByAgeGroupSex.add(HighestCompletedTemp);
                } else {
                    return;
                }
                break;
            case 8://35 and over
                position = GetFormat(row.getCell(0));
                if (position.equalsIgnoreCase("Total")) {
                    HighestCompletedTemp.setLocation(firstCell);
                    HighestCompletedTemp.setSex(secondCell);
                    HighestCompletedTemp.setAgeGroup("35 and above");
                    HighestCompletedTemp.setTotal(GetFormat(cell));
                    
                    arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                    
                    //No Grade Completed
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+1).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+1).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Pre school
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+2).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+2).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Elementary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+3).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+3).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //1st - 4th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+4).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+4).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //5th to 6th
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+5).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+5).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    
                    //Elem Graduate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Elementary "+GetFormat(sheet.getRow(rowIndex+6).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+6).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+7).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+7).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School "+GetFormat(sheet.getRow(rowIndex+8).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+8).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //High School Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("High School " +GetFormat(sheet.getRow(rowIndex+9).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+9).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+10).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+10).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+11).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+11).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Secondary Grad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment("Post Secondary "+GetFormat(sheet.getRow(rowIndex+12).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+12).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //College Undergrad
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+13).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+13).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Academic Degree Holder
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+14).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+14).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Post Baccalaureate
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+15).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+15).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    //Not Stated
                    HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroupTemp.sethighestAttaintment(GetFormat(sheet.getRow(rowIndex+16).getCell(0)));
                    HighestCompletedAgeGroupTemp.setCount(GetFormat(sheet.getRow(rowIndex+16).getCell(colIndex)));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    ArrErrorByAgeGroupSex.add(HighestCompletedTemp);
                } else {
                    return;
                }
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
    public ArrayList<HighestCompleted> getArrayNoError() {
        return arrayNoError;
    }

    /**
     * @param arrayNoError the arrayNoError to set
     */
    public void setArrayNoError(ArrayList<HighestCompleted> arrayNoError) {
        this.arrayNoError = arrayNoError;
    }

    /**
     * @return the arrayError
     */
    public ArrayList<HighestCompletedTemp> getArrayError() {
        return arrayError;
    }

    /**
     * @param arrayError the arrayError to set
     */
    public void setArrayError(ArrayList<HighestCompletedTemp> arrayError) {
        this.arrayError = arrayError;
    }

    public boolean isCheckGrandTotal() {
        return checkGrandTotal;
    }

    public void setCheckGrandTotal(boolean checkGrandTotal) {
        this.checkGrandTotal = checkGrandTotal;
    }
}
