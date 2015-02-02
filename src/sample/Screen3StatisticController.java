package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by radko on 30.1.2015 г..
 */
public class Screen3StatisticController implements Initializable, ControlledScreen{
    public TextArea textView;
    ScreensController myController;
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private void refreshStatistic(ActionEvent event) {
        textView.clear();
        printStatistics(Screen2Controller.winnerName);
    }

    private void printStatistics(String winnerName) {
        try {
            FileInputStream file = new FileInputStream("dataBase\\myTest.xlsx");

            //create workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //iterate trought each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                StringBuilder output = new StringBuilder();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    //check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC :
                            output.append((int)cell.getNumericCellValue());
                            output.append("\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING :
                            String seekingName = cell.getStringCellValue();
                            if (seekingName.toLowerCase().equals(winnerName.toLowerCase())) {
                            output.insert(0,"->");
                            }
                            output.append(seekingName);
                            output.append("\t\t");
                            break;
                    }
                }
                output.append("\n");
                textView.appendText(String.valueOf(output));
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private  void goToScreen1(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen1ID);
    }
    @FXML
    private  void goToScreen2(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen2ID);
    }
}
