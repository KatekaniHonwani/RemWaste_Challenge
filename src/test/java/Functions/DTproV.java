package Functions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DTproV {
    		static DataFunctions data= new DataFunctions();
			private static XSSFSheet ExcelWSheet;
			private static XSSFWorkbook ExcelWBook;
			
			
		public static Object[][] getTableArray(String sSheetName,String sExcelWorkbook) throws Exception {   
		   String[][] tabArray = null;
		   try {

			   FileInputStream ExcelFile = new FileInputStream(System.getProperty("user.dir") + "\\data\\"+sExcelWorkbook+".xlsx");
			   
			   ExcelWBook = new XSSFWorkbook(ExcelFile);
			   ExcelWSheet = ExcelWBook.getSheet(sSheetName);
			   
			   int startRow = 1;
			   int startCol = 0;
			
			   
			   int ci,cj,rowcount = 0;
			   int statuscolpos = 0;
			   int totalRows = ExcelWSheet.getLastRowNum();

			   int totalCols =ExcelWSheet.getRow(0).getPhysicalNumberOfCells();

			   
			   //find position of status column 
			   for (int c =0; c <= totalCols; c++) {
				  if (getDTProvCell(0,c).toLowerCase().contains("status")){
					   statuscolpos = c;
					   break; //there can only be one
				   }   
			   	}   
			     //get count for rows marked as not run
			   	 int totalr = totalRows;
				 for (int irow=1;irow<=totalr; irow++) { 
					if (getDTProvCell(irow,statuscolpos).toLowerCase().contains("completed")){  
							  totalRows = totalRows - 1;  
					}  
				 }
		 
				  tabArray=new String[totalRows][totalCols];
				  ci=0;
				  int co=0;
				   for (int i=startRow;i<=totalr;i++, ci++) {   
					   if (!getDTProvCell(i,statuscolpos).toLowerCase().contains("completed")){
						   cj=0;
						   for (int j=startCol;j<totalCols;j++, cj++){
							   
							   if (getDTProvCell(i,j).trim().isEmpty()){
								   tabArray[co][cj] = "N/A";
							   }else{
							   
								   tabArray[co][cj]=getDTProvCell(i,j).trim();
							   }
						   }
						   co=co+1;   
					   }
				    }  
		     }
			catch (FileNotFoundException e){
				System.out.println("Could not read the Excel sheet" + e.getMessage());
				e.printStackTrace();
				}
			System.out.println("____________________________"+tabArray.length);

			return(tabArray);

			}
		
		
		
//-----------------------------------------------------------------------------
		public static String getDTProvCell(int RowNum, int ColNum) throws Exception {
			try{
				Cell cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
				DataFormatter formatter = new DataFormatter();
				String CellData = formatter.formatCellValue(cell);
				return CellData;
	
			}catch (Exception e){
				System.out.println(e.getMessage());
				throw (e);
			}
		}
	}