package page.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.AbstractPage;

public class FacebookTimelinePage extends AbstractPage {

	public FacebookTimelinePage() {
		url = "https://www.facebook.com";
	}

	@Override
	protected void verifyPage() {
		final By divUserNav = new By.ByCssSelector("#userNav");

		try {
			wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(divUserNav));
		} catch (Exception e) {
			final String message = "Facebook login seem to have failed!";
			RuntimeException e1 = new RuntimeException(message, e);
			LOGGER.error(message, e1);
			throw e1;
		}
	}

	public void clickUserProfile() {
		final By imgUserProfile = new By.ByXPath("//a[@title='Profile']");

		try {
			WebElement weProfilePicture = wdWait.until(ExpectedConditions.elementToBeClickable(imgUserProfile));
			weProfilePicture.click();
		} catch (Exception e) {
			final String message = "Error clicking user profile";
			LOGGER.warn(message);
		}
	}

}
