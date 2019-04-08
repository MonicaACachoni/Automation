package page.host;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.AbstractPage;

public class FacebookPage extends AbstractPage {

	final static By BUTTON_LOGIN = new By.ByCssSelector("#login");

	public FacebookPage() {
		url = "http://monicacachoni.wixsite.com/mommy2u";
	}

	@Override
	protected void verifyPage() {
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_LOGIN));
	}

	public void clickLoginButton() {
		LOGGER.info("Clicking Login button");

		WebElement weLoginButton = wdWait.until(ExpectedConditions.elementToBeClickable(BUTTON_LOGIN));
		weLoginButton.click();
	}

	public void waitAppAuthorizedMessage() {
		final By divConnectedText = new By.ByCssSelector("#status");
		final String appAuthorizedString = "Facebook app authorized!";
		final int appLoginTimeout = 60;
		WebDriverWait userLoginWdWait = new WebDriverWait(driver, appLoginTimeout);

		LOGGER.info("Waiting user input...");

		try {
			userLoginWdWait
					.until(ExpectedConditions.textToBePresentInElementLocated(divConnectedText, appAuthorizedString));
		} catch (Exception e) {
			final String message = String.format("App authorization not detected wihtin %s seconds", appLoginTimeout);
			RuntimeException e1 = new RuntimeException(message, e);
			LOGGER.error(message, e1);
			throw e1;
		}

		LOGGER.info("Login sucessfull, app authorized");
	}

	public String getUserAcessToken() {
		final String getAccessTokenScript = "return FB.getAuthResponse().accessToken;";

		String userAcessToken = null;
		try {
			userAcessToken = (String) ((JavascriptExecutor) driver).executeScript(getAccessTokenScript);
		} catch (Exception e) {
			final String message = "Error retrieving user accessToken: " + e.getMessage();
			RuntimeException e1 = new RuntimeException(message, e);
			LOGGER.error(message, e1);
			throw e1;
		}

		return userAcessToken;
	}

}
