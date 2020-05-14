package myPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class CalculateEMI {
	private static DecimalFormat decimalformat = new DecimalFormat("000");
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	@Test
	public void calculateLoanEMI() throws IOException {
		WebDriver driver;
		String projectpath = System.getProperty("user.dir");
		String driverpath = projectpath + "\\Driver\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverpath);
		driver = new FirefoxDriver();
		driver.get("https://emicalculator.net/");
		driver.manage().window().maximize();

		for (int xCount = 0; xCount < 3; xCount++) {

			File src=new File("C:\\Users\\netzwelt\\Desktop\\Gagan\\Automation\\emi.xls");
			FileInputStream fis = new FileInputStream(src);
			workbook = new HSSFWorkbook(fis);
			sheet= workbook.getSheetAt(0);
			
			int rowcount=sheet.getLastRowNum();
			System.out.println("Total rows:"+rowcount);
			for(int i=1; i<=rowcount;i++) { 
			
		
			WebElement TB_amt = driver.findElement(By.id("loanamount"));
			cell=sheet.getRow(i).getCell(0);
			TB_amt.clear();
			TB_amt.sendKeys(cell.getStringCellValue());
			String loanAmt = TB_amt.getAttribute("value");
			String LoanAmt = loanAmt.replaceAll(",", "");
			// System.out.println("value of amount is:" + LoanAmt);
			long A = Long.parseLong(LoanAmt);
			System.out.println("Amoumt:" + A);

			WebElement TB_interest = driver.findElement(By.id("loaninterest"));
			cell=sheet.getRow(i).getCell(1);
			TB_interest.clear();
			TB_interest.sendKeys(cell.getStringCellValue());
			String loanintrst = TB_interest.getAttribute("value");
			// System.out.println("value of rate is:" + loanintrst);
			float rate = Float.parseFloat(loanintrst);
			float R = rate / 12 / 100;
			System.out.println("Rate:" + R);

			WebElement TB_tenure = driver.findElement(By.id("loanterm"));
			cell=sheet.getRow(i).getCell(2);
			TB_tenure.clear();
			TB_tenure.sendKeys(cell.getStringCellValue());
			String loantenure = TB_tenure.getAttribute("value");
			// System.out.println("value of time is:" + loantenure);
			int time = Integer.parseInt(loantenure);
			int T = time * 12;
			System.out.println("Time:" + T);

			Double PR = (double) (A * R);
			// System.out.println(PR);
			Double Rplus = (double) (1 + R);
			// System.out.println(Rplus);
			Double power_n = (Math.pow(Rplus, T));
			// System.out.println(power_n);
			Double minus_one = power_n - 1;
			// System.out.println(minus_one);
			Double divide = power_n / minus_one;
			// System.out.println(divide);
			Double EMI = PR * divide;
			// System.out.println("EMI: "+EMI);
			decimalformat.setRoundingMode(RoundingMode.FLOOR);
			String EMI_ = decimalformat.format(EMI);
			// System.out.println("emi: "+EMI_);
			Long EMI_value = Long.parseLong(EMI_);
			System.out.println("Expected EMI:" + EMI_value);

			WebElement emi = driver.findElement(By.xpath("//*[@id='emiamount']/p/span"));
			String loanemi = emi.getText().replaceAll(",", "");
			Long emi_value = Long.parseLong(loanemi);
			System.out.println("Actual EMI :" + emi_value);
			// Assert.assertEquals(EMI_value, emi_value);

			if (EMI_value.equals(emi_value)) {
				System.out.println("Pass");
			} else {
				System.out.println(EMI_value + " != " + emi_value);
			}

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			System.out.println("------------------");
			}
			driver.close();
			
			}
		driver.quit();
	}
}
