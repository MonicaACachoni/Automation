package framework;

import org.apache.log4j.Logger;
import org.junit.Before;

public class FrameworkBase {

	public final static Logger LOGGER = Logger.getLogger(FrameworkBase.class);

	@Before
	public void setup() {
		LOGGER.info("Test starting...");
	}

	public void teardown() {

		LOGGER.info("Test has finished");
		LOGGER.info("==========================================");
	}

}
