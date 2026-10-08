import ai.kilocode.client.session.controller.ConnectionDelayTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

/** Replays the unchanged repository test, with its original setup and teardown each time. */
@RunWith(AllTests.class)
public class ConnectionDelayRepeatTest {
  public static Test suite() {
    TestSuite suite = new TestSuite("Unmodified hide-event test replay");
    for (int i = 0; i < 200; i++) {
      TestSuite iteration = new TestSuite("iteration-" + i);
      ConnectionDelayTest test = new ConnectionDelayTest();
      test.setName("test hide event sees updated connection state on EDT");
      iteration.addTest(test);
      suite.addTest(iteration);
    }
    return suite;
  }
}
