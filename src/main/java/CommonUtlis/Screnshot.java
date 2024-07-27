package CommonUtlis;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class Screnshot {
	String path = "/Users/ct185-manoj-km/Documents/linux/screenshot/";

	public void TakeScrenshotOfFullScreen(AndroidDriver driver) throws IOException, InterruptedException {
		TakesScreenshot scerenshot = (TakesScreenshot) driver;
		File oldfile = scerenshot.getScreenshotAs(OutputType.FILE);
		BufferedImage photo = ImageIO.read(oldfile);
		String newfilename = path+LocalDateTime.now().toString()+".png";
		ImageIO.write(photo, "png", new File(newfilename));
		//return newfilename;
	}

	public String TakeScrenshotOfElement(WebElement elemwElement) throws IOException, InterruptedException {
		File oldfile = elemwElement.getScreenshotAs(OutputType.FILE);
		BufferedImage photo = ImageIO.read(oldfile);
		String newfilename = path+LocalDateTime.now().toString()+".png";
		ImageIO.write(photo, "png", new File(newfilename));
		return newfilename;
	}

}
