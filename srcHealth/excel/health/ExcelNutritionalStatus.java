/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package excel.health;

import checker.health.NutritionalStatusChecker;
import model.health.NutritionalStatus;
import model.temp.health.NutritionalStatusBMITemp;
import model.temp.health.NutritionalStatusTemp;
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

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class ExcelNutritionalStatus {

    private ArrayList<NutritionalStatus> arrayNoError;
    private ArrayList<NutritionalStatusTemp> arrayError;

    NutritionalStatusChecker NutritionalStatusChecker;

    ArrayList<NutritionalStatusTemp> arrNutritionalStatus;

    NutritionalStatusTemp NutritionalStatusTemp = new NutritionalStatusTemp();
    ArrayList<NutritionalStatusBMITemp> arrNutritionalStatusBMITemp;

    final private StringBuilder out = new StringBuilder(65536);
    final private SimpleDateFormat sdf;
    final private HSSFWorkbook book;
    final private HSSFPalette palette;
    final private FormulaEvaluator evaluator;
    private int colIndex;
    private int rowIndex, mergeStart, mergeEnd;
    // Row -> Column -> Pictures
    private Map<Integer, Map<Short, List<HSSFPictureData>>> pix = new HashMap<Integer, Map<Short, List<HSSFPictureData>>>();
    private Boolean isSecond = false;
    private boolean isMerged = false;
    private HSSFSheet sheet;
    String errors;
    String grade = "All";
    String location = "Caloocan City";
    String city = "Caloocan City";

    /**
     * Generates HTML from the InputStream of an Excel file. Generates sheet
     * name in HTML h1 element.
     *
     * @param in InputStream of the Excel file.
     * @throws IOException When POI cannot read from the input stream.
     */
    public ExcelNutritionalStatus(final InputStream in, int sheetNumber) throws IOException {
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

        arrNutritionalStatus = new ArrayList<NutritionalStatusTemp>();
        table(sheet);

        setArrayError(new NutritionalStatusChecker(arrNutritionalStatus).getArrayError());
        setArrayNoError(new NutritionalStatusChecker(arrNutritionalStatus).getArrayNoError());
    }

    public ExcelNutritionalStatus(final HSSFWorkbook book, int sheetNumber) throws IOException {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.book = book;
        palette = book.getCustomPalette();
        evaluator = book.getCreationHelper().createFormulaEvaluator();
        sheet = book.getSheetAt(sheetNumber);

        arrNutritionalStatus = new ArrayList<NutritionalStatusTemp>();
        table(sheet);

        setArrayError(new NutritionalStatusChecker(arrNutritionalStatus).getArrayError());
        setArrayNoError(new NutritionalStatusChecker(arrNutritionalStatus).getArrayNoError());
    }

    public String getErrors() {
        return errors;
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
        for (rowIndex = 0; rowIndex < sheet.getLastRowNum(); ++rowIndex) {
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

        if (row.getCell(0) != null && row.getCell(0).getCellType() == CELL_TYPE_STRING) {

            //gets the grade before the header
            if (row.getCell(0).getStringCellValue().equals("PRE ELEM")
                    || row.getCell(0).getStringCellValue().equals("Grade I")
                    || row.getCell(0).getStringCellValue().equals("GRADE II")
                    || row.getCell(0).getStringCellValue().equals("GRADE III")
                    || row.getCell(0).getStringCellValue().equals("GRADE IV")
                    || row.getCell(0).getStringCellValue().equals("GRADE V")
                    || row.getCell(0).getStringCellValue().equals("Grade VI")
                    || row.getCell(0).getStringCellValue().equals("PRE ELEM - GRADE VI")) {
                grade = row.getCell(0).getStringCellValue();
                return;
            }

            if (row == null) {
                return;
            }

            //removes column headers
            if (row.getCell(0).getStringCellValue().contains("DISTRICT")) {
                rowIndex += 2;
                return;
            }
            //removes the headers
            if (row.getCell(0).getStringCellValue().equals("Department of Education")) {
                return;
            } else if (row.getCell(0).getStringCellValue().contains("PERCENTAGE DISTRIBUTION OF ELEMENTARY SCHOOL CHILDREN IN EACH DISTRICT")) {
                return;
            } else if (row.getCell(0).getStringCellValue().contains("Baseline")) {
                return;
            }
        }

        if (sheet.getRow(rowIndex + 1).getCell(0) != null && sheet.getRow(rowIndex + 1).getCell(0).getCellType() == CELL_TYPE_STRING) {
            //gets the grade in the first table
            if (sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("I")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("II")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("III")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("IV")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("V")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("VI")) {
                grade = "GRADE " + sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue();
            }
            if (sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("SPED")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("PRE ELEM")) {
                grade = sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue();
            }
            //gets the location
            if (sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("AROMAR")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("POBCARAN")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("TANQUE")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("NORTH I")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("NORTH II")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("NORTH III")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("NORTH IV")
                    || sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("GRAND TOTAL")) {
                location = sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue();
            }

            if (sheet.getRow(rowIndex + 1).getCell(0).getStringCellValue().equalsIgnoreCase("GRAND TOTAL")) {
                grade = " ";
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
                                            if (row.getCell(9) == null || row.getCell(9).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                if (row.getCell(10) == null || row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                    if (row.getCell(11) == null || row.getCell(11).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                        if (row.getCell(12) == null || row.getCell(12).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                            if (row.getCell(13) == null || row.getCell(13).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                if (row.getCell(14) == null || row.getCell(14).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                    if (row.getCell(15) == null || row.getCell(15).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
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
                                }
                            }
                        }
                    }
                }
            }
        }

        for (colIndex = 0; colIndex < 15; ++colIndex) {
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
        if (cell == null) {
            return;
        }
        String gender = null;

        switch (colIndex) {
            case 0:
                gender = GetFormat(row.getCell(1));
                if (gender.equalsIgnoreCase("M")) {
                    NutritionalStatusTemp = new NutritionalStatusTemp();
                    arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();

                    if (arrNutritionalStatus.size() != 0) {
                        if (arrNutritionalStatus.get(arrNutritionalStatus.size() - 1).getDistrict().equalsIgnoreCase("Caloocan City")
                                && arrNutritionalStatus.get(arrNutritionalStatus.size() - 1).getGradeLevel().equalsIgnoreCase("SPED")) {
                            NutritionalStatusTemp.setDistrict(city);
                            NutritionalStatusTemp.setGradeLevel(location);
                        } else if (arrNutritionalStatus.get(arrNutritionalStatus.size() - 1).getDistrict().equalsIgnoreCase("North IV")
                                || arrNutritionalStatus.get(arrNutritionalStatus.size() - 1).getDistrict().equalsIgnoreCase("SPED")) {
                            if (arrNutritionalStatus.get(arrNutritionalStatus.size() - 1).getGradeLevel().equalsIgnoreCase("SPED")
                                    || arrNutritionalStatus.get(arrNutritionalStatus.size() - 1).getDistrict().equalsIgnoreCase("SPED")) {
                                NutritionalStatusTemp.setDistrict(location);
                                NutritionalStatusTemp.setGradeLevel(arrNutritionalStatus.get(arrNutritionalStatus.size() - 2).getGradeLevel());
                            }
                            else {
                                if(grade.equalsIgnoreCase("SPED")){
                                    NutritionalStatusTemp.setDistrict("SPED");
                                    NutritionalStatusTemp.setGradeLevel(arrNutritionalStatus.get(arrNutritionalStatus.size() - 1).getGradeLevel());
                                } 
                                else{
                                NutritionalStatusTemp.setDistrict(location);
                                NutritionalStatusTemp.setGradeLevel(arrNutritionalStatus.get(arrNutritionalStatus.size() - 1).getGradeLevel());
                                }
                            }
                        }
                        else {
                            NutritionalStatusTemp.setDistrict(location);
                            NutritionalStatusTemp.setGradeLevel(grade);
                        }
                    } else {
                        NutritionalStatusTemp.setDistrict(location);
                        NutritionalStatusTemp.setGradeLevel(grade);
                    }
                } else {
                    return;
                }
                break;

            //removes % column
            case 1:
                gender = GetFormat(cell);
                if (gender.equalsIgnoreCase("M")) {
                    NutritionalStatusTemp.setTotalMale(GetFormat(row.getCell(2)));
                    NutritionalStatusTemp.setPupilsWeighedMale(GetFormat(row.getCell(3)));
                    NutritionalStatusTemp.setTotalFemale(GetFormat(sheet.getRow(rowIndex + 1).getCell(2)));
                    NutritionalStatusTemp.setPupilsWeighedFemale(GetFormat(sheet.getRow(rowIndex + 1).getCell(3)));
                    NutritionalStatusTemp.setTotalCount(GetFormat(sheet.getRow(rowIndex + 2).getCell(2)));
                    NutritionalStatusTemp.setPupilsWeighedTotal(GetFormat(sheet.getRow(rowIndex + 2).getCell(3)));
                } else {
                    return;
                }
                break;
            case 5:
                gender = GetFormat(row.getCell(1));
                if (gender.equalsIgnoreCase("M")) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    NutritionalStatusBMITemp.setBMI("Severly Wasted");
                    NutritionalStatusBMITemp.setMaleCount(GetFormat(cell));
                    NutritionalStatusBMITemp.setFemaleCount(GetFormat(sheet.getRow(rowIndex + 1).getCell(colIndex)));
                    NutritionalStatusBMITemp.setTotalCount(GetFormat(sheet.getRow(rowIndex + 2).getCell(colIndex)));
                    arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                } else {
                    return;
                }
                break;
            case 7:
                gender = GetFormat(row.getCell(1));
                if (gender.equalsIgnoreCase("M")) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    NutritionalStatusBMITemp.setBMI("Wasted");
                    NutritionalStatusBMITemp.setMaleCount(GetFormat(cell));
                    NutritionalStatusBMITemp.setFemaleCount(GetFormat(sheet.getRow(rowIndex + 1).getCell(colIndex)));
                    NutritionalStatusBMITemp.setTotalCount(GetFormat(sheet.getRow(rowIndex + 2).getCell(colIndex)));
                    arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                } else {
                    return;
                }
                break;
            case 9:
                gender = GetFormat(row.getCell(1));
                if (gender.equalsIgnoreCase("M")) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    NutritionalStatusBMITemp.setBMI("Normal");
                    NutritionalStatusBMITemp.setMaleCount(GetFormat(cell));
                    NutritionalStatusBMITemp.setFemaleCount(GetFormat(sheet.getRow(rowIndex + 1).getCell(colIndex)));
                    NutritionalStatusBMITemp.setTotalCount(GetFormat(sheet.getRow(rowIndex + 2).getCell(colIndex)));
                    arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                } else {
                    return;
                }
                break;
            case 11:
                gender = GetFormat(row.getCell(1));
                if (gender.equalsIgnoreCase("M")) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    NutritionalStatusBMITemp.setBMI("Overweight");
                    NutritionalStatusBMITemp.setMaleCount(GetFormat(cell));
                    NutritionalStatusBMITemp.setFemaleCount(GetFormat(sheet.getRow(rowIndex + 1).getCell(colIndex)));
                    NutritionalStatusBMITemp.setTotalCount(GetFormat(sheet.getRow(rowIndex + 2).getCell(colIndex)));
                    arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                } else {
                    return;
                }
                break;
            case 13:
                gender = GetFormat(row.getCell(1));
                if (gender.equalsIgnoreCase("M")) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    NutritionalStatusBMITemp.setBMI("Obese");
                    NutritionalStatusBMITemp.setMaleCount(GetFormat(cell));
                    NutritionalStatusBMITemp.setFemaleCount(GetFormat(sheet.getRow(rowIndex + 1).getCell(colIndex)));
                    NutritionalStatusBMITemp.setTotalCount(GetFormat(sheet.getRow(rowIndex + 2).getCell(colIndex)));
                    arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                    arrNutritionalStatus.add(NutritionalStatusTemp);
                } else {
                    return;
                }
                break;
            default:
                return;
        }
    }

//    NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
//    NutritionalStatusBMITemp.setBMI(BMI[i]);
//    NutritionalStatusBMITemp.setFemaleCount(GetFormat(row.getCell(x)));
//    arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
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
    public ArrayList<NutritionalStatus> getArrayNoError() {
        return arrayNoError;
    }

    /**
     * @param arrayNoError the arrayNoError to set
     */
    public void setArrayNoError(ArrayList<NutritionalStatus> arrayNoError) {
        this.arrayNoError = arrayNoError;
    }

    /**
     * @return the arrayError
     */
    public ArrayList<NutritionalStatusTemp> getArrayError() {
        return arrayError;
    }

    /**
     * @param arrayError the arrayError to set
     */
    public void setArrayError(ArrayList<NutritionalStatusTemp> arrayError) {
        this.arrayError = arrayError;
    }
}
