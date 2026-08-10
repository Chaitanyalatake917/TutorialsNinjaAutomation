package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtilities {
	public static Properties loadPropertiesFile() {
		Properties prop = new Properties();

		try (FileReader fr = new FileReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties")) {
			// implemented try with resources approach to close FileReader automatically
			// after use
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String generateDummyMail() {
		Date date = new Date();
		String newMail = date.toString().replaceAll("\\s", "").replaceAll("\\:", "") + "@gmail.com";
		return newMail;
	}

	public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) {
		BufferedImage actualImage = null;
		BufferedImage expectedImage = null;
		try {
			actualImage = ImageIO.read(new File(actualImagePath));
			expectedImage = ImageIO.read(new File(expectedImagePath));
			ImageDiffer differ = new ImageDiffer();
			ImageDiff imageDiff = differ.makeDiff(actualImage, expectedImage);
			return !imageDiff.hasDiff();
		} catch (IOException e) {
			throw new RuntimeException("Unable to compare screenshots", e);
		}
	}

	public static void takeScreenshot(WebDriver driver, String screenshotPath) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(src, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
