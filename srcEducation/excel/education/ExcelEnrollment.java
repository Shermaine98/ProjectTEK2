package excel.education;

import checker.education.EnrollmentChecker;
import model.education.Enrollment;
import model.temp.education.EnrollmentDetTemp;
import model.temp.education.EnrollmentTemp;
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
public class ExcelEnrollment {

    private ArrayList<Enrollment> arrayNoError;
    private ArrayList<EnrollmentTemp> arrayError;

    EnrollmentChecker EnrollmentChecker;

    ArrayList<EnrollmentTemp> arrEnrollmentTemp;

    EnrollmentTemp EnrollmentTemp = new EnrollmentTemp();
    ArrayList<EnrollmentDetTemp> arrEnrollmentDetTemp;
    
    EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
     
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

    public ExcelEnrollment(final InputStream in, int sheetNumber) throws IOException {
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

        arrEnrollmentTemp = new ArrayList<EnrollmentTemp>();

        table(sheet);
        setArrayError(new EnrollmentChecker(arrEnrollmentTemp).getArrayError());
        setArrayNoError(new EnrollmentChecker(arrEnrollmentTemp).getArrayNoError());
    }

    public ExcelEnrollment(final HSSFWorkbook book, int sheetNumber) throws IOException {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.book = book;
        palette = book.getCustomPalette();
        evaluator = book.getCreationHelper().createFormulaEvaluator();
        sheet = book.getSheetAt(sheetNumber);
        arrEnrollmentTemp = new ArrayList<EnrollmentTemp>();
        table(sheet);

        setArrayError(new EnrollmentChecker(arrEnrollmentTemp).getArrayError());
        setArrayNoError(new EnrollmentChecker(arrEnrollmentTemp).getArrayNoError());
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

        for (rowIndex = 10; rowIndex <= sheet.getLastRowNum(); ++rowIndex) {
            HSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                if(row.getCell(0).getCellType()==HSSFCell.CELL_TYPE_STRING){
                    if(row.getCell(0).getStringCellValue().toLowerCase().startsWith("generated on")){
                        System.out.println("STOPPED");
                        break;
                    }
                }
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
                                                                        if (row.getCell(16) == null || row.getCell(16).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                            if (row.getCell(17) == null || row.getCell(17).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                if (row.getCell(18) == null || row.getCell(18).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                    if (row.getCell(19) == null || row.getCell(19).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                        if (row.getCell(20) == null || row.getCell(20).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                            if (row.getCell(21) == null || row.getCell(21).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                if (row.getCell(22) == null || row.getCell(22).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                    if (row.getCell(23) == null || row.getCell(23).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                        if (row.getCell(24) == null || row.getCell(24).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                            if (row.getCell(25) == null || row.getCell(25).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                                if (row.getCell(26) == null || row.getCell(26).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                                    if (row.getCell(27) == null || row.getCell(27).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                                        if (row.getCell(28) == null || row.getCell(28).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                                            if (row.getCell(29) == null || row.getCell(29).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                                                if (row.getCell(30) == null || row.getCell(30).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                                                    if (row.getCell(31) == null || row.getCell(31).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                                                        if (row.getCell(32) == null || row.getCell(32).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                                                            if (row.getCell(33) == null || row.getCell(33).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                                                                if (row.getCell(34) == null || row.getCell(34).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                                                                                                                                    if (row.getCell(35) == null || row.getCell(35).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
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

        for (colIndex = 0; colIndex < 36; ++colIndex) {
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
        int colspan = 1;
        //System.out.println("MergeStart: " + mergeStart + "MergeEnd: " + mergeEnd);
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
    
        switch (colIndex) {
            case 0://schoolID
                EnrollmentTemp = new EnrollmentTemp();
                arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                EnrollmentTemp.setSchoolID(GetFormat(cell));
                break;
            case 1://school name
                EnrollmentTemp.setSchoolName(GetFormat(cell));
                break;
            case 2://region
                return;
            case 3://province
                return;
            case 4://municipality
                return;
            case 5://division
                return;
            case 6://district
                EnrollmentTemp.setDistrict(GetFormat(cell));
                break;
            case 7://school type
                EnrollmentTemp.setSchoolType(GetFormat(cell));
                break;
            case 8:
                EnrollmentDetTemp = new EnrollmentDetTemp();
                EnrollmentDetTemp.setGradeLevel("Kinder");
                EnrollmentDetTemp.setMaleCount(GetFormat(cell));
                //kinder male
                break;
            case 9://kidner female
                EnrollmentDetTemp.setFemaleCount(GetFormat(cell));
                break;
            case 10:
                EnrollmentDetTemp.setTotalCount(GetFormat(cell));
                arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                //kinder total
                break;
            case 11:
                EnrollmentDetTemp = new EnrollmentDetTemp();
                EnrollmentDetTemp.setGradeLevel("Grade 1");
                EnrollmentDetTemp.setMaleCount(GetFormat(cell)); //grade 1 male
                break;
            case 12:
                EnrollmentDetTemp.setFemaleCount(GetFormat(cell));//grade 1 female
                break;
            case 13://grade 1 total
                EnrollmentDetTemp.setTotalCount(GetFormat(cell));
                arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                break;
            case 14:
                EnrollmentDetTemp = new EnrollmentDetTemp();
                EnrollmentDetTemp.setGradeLevel("Grade 2");
                EnrollmentDetTemp.setMaleCount(GetFormat(cell)); //grade 2 male
                break;
            case 15:
                EnrollmentDetTemp.setFemaleCount(GetFormat(cell));//grade 2 female
                break;
            case 16:
                EnrollmentDetTemp.setTotalCount(GetFormat(cell));//grade 2 total
                arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                break;
            case 17:
                EnrollmentDetTemp = new EnrollmentDetTemp();
                EnrollmentDetTemp.setGradeLevel("Grade 3");
                EnrollmentDetTemp.setMaleCount(GetFormat(cell));//grade 3 male
                break;
            case 18:
                EnrollmentDetTemp.setFemaleCount(GetFormat(cell));//grade 3 female
                break;
            case 19:
                EnrollmentDetTemp.setTotalCount(GetFormat(cell)); //grade 3 total
                arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                break;
            case 20:
                EnrollmentDetTemp = new EnrollmentDetTemp();
                EnrollmentDetTemp.setGradeLevel("Grade 4");
                EnrollmentDetTemp.setMaleCount(GetFormat(cell));//grade 4 male
                break;
            case 21:
                EnrollmentDetTemp.setFemaleCount(GetFormat(cell)); //grade 4 female
                break;
            case 22:
                EnrollmentDetTemp.setTotalCount(GetFormat(cell));//grade 4 total
                arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                break;
            case 23:
                EnrollmentDetTemp = new EnrollmentDetTemp();
                EnrollmentDetTemp.setGradeLevel("Grade 5");
                EnrollmentDetTemp.setMaleCount(GetFormat(cell)); //grade 5 male
                break;
            case 24:
                EnrollmentDetTemp.setFemaleCount(GetFormat(cell));//grade 5 female
                break;
            case 25:
                EnrollmentDetTemp.setTotalCount(GetFormat(cell)); //grade 5 total
                arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                break;
            case 26:
                EnrollmentDetTemp = new EnrollmentDetTemp();
                EnrollmentDetTemp.setGradeLevel("Grade 6");
                EnrollmentDetTemp.setMaleCount(GetFormat(cell)); //grade 6 male
                break;
            case 27:
                EnrollmentDetTemp.setFemaleCount(GetFormat(cell)); //grade 6 female
                break;
            case 28:
                EnrollmentDetTemp.setTotalCount(GetFormat(cell));
                arrEnrollmentDetTemp.add(EnrollmentDetTemp);//grade 6 total
                break;
            case 29:
                EnrollmentTemp.setTotalMale(GetFormat(cell));//total male
                System.out.println("TOTAL MALE " + EnrollmentTemp.getTotalMale());
                break;
            case 30:
                EnrollmentTemp.setTotalFemale(GetFormat(cell));//total female
                System.out.println("TOTAL MALE "+ EnrollmentTemp.getTotalFemale() +"\n");
                break;
            case 31:
                EnrollmentTemp.setGrandTotal(GetFormat(cell));//grand total
                break;
            case 32:
                EnrollmentDetTemp = new EnrollmentDetTemp();
                EnrollmentDetTemp.setGradeLevel("SPED");
                EnrollmentDetTemp.setMaleCount(GetFormat(cell));//SPED male
                break;
            case 33:
                EnrollmentDetTemp.setFemaleCount(GetFormat(cell));//SPED female
                break;
            case 34:
                EnrollmentDetTemp.setTotalCount(GetFormat(cell));
                arrEnrollmentDetTemp.add(EnrollmentDetTemp);//SPED total
                break;
            case 35:
                EnrollmentTemp.setGenderDisparityIndex(GetFormat(cell));//GenderParityIndex
                EnrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                arrEnrollmentTemp.add(EnrollmentTemp);
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
    public ArrayList<Enrollment> getArrayNoError() {
        return arrayNoError;
    }

    /**
     * @param arrayNoError the arrayNoError to set
     */
    public void setArrayNoError(ArrayList<Enrollment> arrayNoError) {
        this.arrayNoError = arrayNoError;
    }

    /**
     * @return the arrayError
     */
    public ArrayList<EnrollmentTemp> getArrayError() {
        return arrayError;
    }

    /**
     * @param arrayError the arrayError to set
     */
    public void setArrayError(ArrayList<EnrollmentTemp> arrayError) {
        this.arrayError = arrayError;
    }

}
