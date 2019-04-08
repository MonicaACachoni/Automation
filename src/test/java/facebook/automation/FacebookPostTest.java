package facebook.automation;

import org.junit.AfterClass;
import org.junit.Test;

import framework.DriverManager;
import framework.FrameworkBase;
import page.facebook.FacebookTimelinePage;
import page.host.FacebookPage;
import rest.RestUtil;
import util.TestUtil;

public class FacebookPostTest extends FrameworkBase {

	@Test
	public void facebookPost() {

		final String postMessage = "Bom dia Facebook : " + System.currentTimeMillis();
		final String updatedPostMessage = "Boa tarde Facebook!";
		final int postWaitMillis = 4000;
		String userAccessToken;

		FacebookPage facebookPage = new FacebookPage();
		facebookPage.openPage();

		facebookPage.clickLoginButton();
		facebookPage.waitAppAuthorizedMessage();
		userAccessToken = facebookPage.getUserAcessToken();

		FacebookTimelinePage fbTimeline = new FacebookTimelinePage();
		fbTimeline.openPage();
		fbTimeline.clickUserProfile();

		String postId = RestUtil.postMessage(postMessage, userAccessToken);
		fbTimeline.clickUserProfile();
		// Allow evaluator to see the post
		TestUtil.sleep(postWaitMillis);

		RestUtil.updatePostText(postId, updatedPostMessage, userAccessToken);
		fbTimeline.clickUserProfile();
		// Allow evaluator to see the post
		TestUtil.sleep(postWaitMillis);
	}

	@AfterClass
	public static void quitWebDriver() {
		DriverManager.quitDriver();
	}

}
