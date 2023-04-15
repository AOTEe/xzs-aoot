import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PoiTest {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\陈圣兹\\Desktop\\模板(多种题型).xlsx");
        doImportQuestionsFromExcel(file);
    }

    public static void doImportQuestionsFromExcel(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //从第二行开始获取内容
        Sheet sheet = workbook.getSheetAt(0);
        // 获取表中的内容
        int rowCount = sheet.getPhysicalNumberOfRows(); //多少行

        for (int i = 0; i < rowCount; i++) {
            Row rowData = sheet.getRow(i);
            if (rowData != null) {
                // 读取列
                int columns = rowData.getPhysicalNumberOfCells();
                for (int j = 0; j < columns; j++) {
                    XSSFCell cell = (XSSFCell) rowData.getCell(j);
                    if (cell==null)
                        continue;
                    //判断每一个cell中数据类型
                    //拿到单元格数据
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING://String
                            System.out.println(cell.getStringCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC://number
                            Long longVal = Math.round(cell.getNumericCellValue());
                            Double doubleVal = cell.getNumericCellValue();
                            System.out.println(cell.getNumericCellValue());
                            break;
                    }



                }
                System.out.println("=====================");
            }
        }

    }

    @Test
    public void test01() throws Exception{
        File file1 = new File("C:\\Users\\陈圣兹\\Desktop\\模板(多种题型).xlsx");

        File file2 = new File("C:\\Users\\陈圣兹\\Desktop\\模板(多种题型)_copy.xlsx");

        FileInputStream inputStream = new FileInputStream(file1);
        FileOutputStream outputStream = new FileOutputStream(file2);


        byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf))!=-1){
            outputStream.write(buf);
        }
        inputStream.close();
        outputStream.close();
    }
}
