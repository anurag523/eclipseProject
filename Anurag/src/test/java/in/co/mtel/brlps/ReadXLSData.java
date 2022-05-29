package in.co.mtel.brlps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadXLSData extends functions {
	public String[][] getData(String excelSheetName) throws EncryptedDocumentException, IOException
	{
		File f=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\ProductCategoryMapping.xlsx");
		FileInputStream fis=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sheetName=wb.getSheet(excelSheetName);
		
		totalRows=sheetName.getLastRowNum();
		System.out.println("Total Rows: "+totalRows);
		
		Row rowCells=sheetName.getRow(0);
		int totalCols=rowCells.getLastCellNum();
		System.out.println("Total Columns: "+totalCols);
		
		DataFormatter format=new DataFormatter();
		testData=new String[totalRows+1][totalCols+1];
		
		for(int i=1;i<=totalRows;i++) 
		{
			for(int j=0;j<totalCols;j++)
			{
				testData[i][j]=format.formatCellValue(sheetName.getRow(i).getCell(j));
				System.out.print(testData[i][j]+" ");
			}
			System.out.println();
		}
		return testData;
	}
	
}

















