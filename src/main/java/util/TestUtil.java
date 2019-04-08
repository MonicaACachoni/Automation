package util;

import org.apache.log4j.Logger;

public class TestUtil {

	public final static Logger LOGGER = Logger.getLogger(TestUtil.class);

	public static void sleep(long millisencods) {
		try {
			Thread.sleep(millisencods);
		} catch (InterruptedException e) {
			LOGGER.debug("Someone awoke the thread from it's nap time!!!", e);
		}
	}

}
