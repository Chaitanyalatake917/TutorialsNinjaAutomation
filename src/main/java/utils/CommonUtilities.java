package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtilities {

	public static String generateDummyMail() {
		Date date=new Date();
		String newMail=date.toString().replaceAll("\\s","").replaceAll("\\:","")+"@gmail.com";
		return newMail;
	}

	public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) {
		BufferedImage bufferedActualImage = null;
		try {
			bufferedActualImage = ImageIO.read(new File(actualImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage bufferedExpectedImage = null;
		try {
			bufferedExpectedImage = ImageIO.read(new File(expectedImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageDiffer differ = new ImageDiffer();
		ImageDiff imageDiff = differ.makeDiff(bufferedExpectedImage, bufferedActualImage);
		return imageDiff.hasDiff();
	}
}
