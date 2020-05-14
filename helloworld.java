package myPackage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class helloworld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("hello");

WebDriver driver;
System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");   
driver=new ChromeDriver(); 
String baseURL="https://www.gmail.com";
driver.get(baseURL); 
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


//enter email and click next
driver.findElement(By.id("identifierId")).sendKeys("bainsdeep101");                      
driver.findElement(By.id("identifierNext")).click();                               

//enter password  
driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("fakegagan");      
//Click on show password icon
//driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/span/div")).click();
// click next button
driver.findElement(By.id("passwordNext")).click();
driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);


//search box 
driver.findElement(By.xpath("//input[@aria-label='Search mail']")).sendKeys("andy");
driver.findElement(By.xpath("//input[@aria-label='Search mail']")).sendKeys(Keys.ENTER);
driver.findElement(By.xpath("//*[@id=\":ml\"]")).click();
driver.findElement(By.xpath("//*[@id=\":4\"]/div[3]/div[1]/div/div[1]/div")).click();

//Take screenshot and store as a file format
File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
try {
//now copy the  screenshot to desired location using copyFile //method
FileUtils.copyFile(src, new File("C:/selenium/error1.png"));
}

catch (IOException e)
{
System.out.println(e.getMessage());
}


//settings >> themes >> select image >> save
driver.findElement(By.xpath("//*[@id=':m6']")).click();
driver.findElement(By.xpath("//div[@id='pbwc']")).click();

//Take screenshot and store as a file format
File src1= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
try {
//now copy the  screenshot to desired location using copyFile //method
FileUtils.copyFile(src1, new File("C:/selenium/error2.png"));
}

catch (IOException e)
{
System.out.println(e.getMessage());

}
driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

driver.findElement(By.xpath("//div[@class='a7U']")).click();
driver.findElement(By.xpath("//div[@class='J-J5-Ji T-I T-I-atl']")).click();


//compose a new mail
driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']")).click();

//Take screenshot and store as a file format
File src11= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
try {
//now copy the  screenshot to desired location using copyFile //method
FileUtils.copyFile(src11, new File("C:/selenium/error3.png"));
}

catch (IOException e)
{
System.out.println(e.getMessage());

}

String childWindowHandle=driver.getWindowHandle();
driver.switchTo().window(childWindowHandle);
driver.findElement(By.xpath("//textarea[@class='vO']")).sendKeys("bainsdeep101@gmail.com");
driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("automation");
driver.findElement(By.xpath("//*[@class='Am Al editable LW-avf']")).sendKeys("This is a test message");
driver.findElement(By.xpath("//*[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();
driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


//click profile logo 
driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a")).click(); 
//clicks Signout 
driver.findElement(By.xpath("//*[@id=\"gb_71\"]")).click();

driver.quit();
}
}
