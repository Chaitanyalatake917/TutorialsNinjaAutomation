package register;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_002 {

	@Test
	public void verifyThankYouRegistrationEmail() throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("nav-link-accountList")).click();

		String email = "chaitanyalatake917@gmail.com";
		String link = "";
		String appPassword = "pire ckwj qcwn qmpx";

		driver.get("https://www.amazon.in/");

		driver.findElement(By.id("nav-link-accountList")).click();

		driver.findElement(By.id("ap_email_login")).sendKeys(email);

		driver.findElement(By.className("a-button-input")).click();

		driver.findElement(By.id("auth-fpp-link-bottom")).click();

		driver.findElement(By.id("continue")).click();

		// Wait for Amazon to send email
		Thread.sleep(10000);

		// Gmail IMAP details

		String host = "imap.gmail.com";

		Properties properties = new Properties();

		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", "993");
		properties.put("mail.imap.ssl.enable", "true");

		Session emailSession = Session.getDefaultInstance(properties);

		Store store = emailSession.getStore("imaps");

		store.connect(host, email, appPassword);

		Folder inbox = store.getFolder("INBOX");

		inbox.open(Folder.READ_ONLY);

		boolean found = false;

		// Wait for max 60 seconds

		for (int attempt = 1; attempt <= 6; attempt++) {

			Message[] messages = inbox.getMessages();

			System.out.println("Total emails : " + messages.length);

			// Check latest 20 emails

			int start = Math.max(0, messages.length - 20);

			for (int i = messages.length - 1; i >= start; i--) {

				Message message = messages[i];

				String subject = message.getSubject();

				System.out.println("Checking : " + subject);

				if (subject != null && subject.contains("Password recovery")) {

					System.out.println("Email Found");

					System.out.println("Subject : " + subject);

					System.out.println("From : " + message.getFrom()[0]);

					String emailBody = getTextFromMessage(message);

					System.out.println("=========EMAIL BODY==========");

					System.out.println(emailBody);

					System.out.println("=============================");

					Assert.assertTrue(emailBody.contains("Someone is attempting to reset"));

					// Extract URL using regex

					Pattern pattern = Pattern.compile("https://[^\\s\"<>]+");

					Matcher matcher = pattern.matcher(emailBody);

					while (matcher.find()) {

						String url = matcher.group();

						url = url.replace("&amp;", "&");

						if (url.contains("amazon")) {

							link = url;

							break;
						}
					}

					if (!link.isEmpty()) {

						found = true;

						System.out.println("Extracted Link : " + link);

						break;
					}

				}

			}

			if (found) {

				break;

			}

			System.out.println("Email not found yet. Waiting 10 sec...");

			Thread.sleep(10000);

			inbox.close(false);

			inbox.open(Folder.READ_ONLY);

		}

		inbox.close(false);

		store.close();

		Assert.assertTrue(found, "Password recovery email not found");

		Assert.assertFalse(link.isEmpty(), "Password reset link could not be extracted");
		
		link = link.replace(")", "");

		driver.navigate().to(link);

//		Assert.assertTrue(driver.findElement(By.name("customerResponseDenyButton")).isDisplayed());

//		driver.quit();

	}

	private String getTextFromMessage(Message message) throws Exception {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("text/html")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
		StringBuilder result = new StringBuilder();
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result.append(bodyPart.getContent());
			} else if (bodyPart.isMimeType("text/html")) {
				result.append(bodyPart.getContent());
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
			}
		}
		return result.toString();
	}
}
