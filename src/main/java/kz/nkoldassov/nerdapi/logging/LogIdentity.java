package kz.nkoldassov.nerdapi.logging;

import org.slf4j.MDC;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class LogIdentity {

  private static final String RUN;

  public static String machine;

  private static final AtomicInteger nextThreadId = new AtomicInteger(0);

  static {
    //noinspection SpellCheckingInspection
    var letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    var rnd = new SecureRandom();
    var arr = new char[6];

    for (int i = 0; i < 6; i++) {
      arr[i] = letters[rnd.nextInt(letters.length)];
    }
    RUN = new String(arr);

    initMachine();

  }

  private static void initMachine() {
    try {

      machine = InetAddress.getLocalHost().getHostName();

    } catch (UnknownHostException e) {
      //ignore this error
      machine = null;
    }
  }

  public static void resetThread() {
    var threadId = nextThreadId.incrementAndGet();

    var logId = generateLogId(threadId);
    MDC.put("LID", logId);
  }

  private static String generateLogId(int threadId) {
    var buf = new StringBuilder();

    if (machine != null) {
      buf.append(machine).append('-');
    }
    buf.append(RUN);
    {
      buf.append("000000");
      if (threadId < 10) {
        buf.append(threadId);
      } else if (threadId < 100) {
        buf.setLength(buf.length() - 1);
        buf.append(threadId);
      } else if (threadId < 1000) {
        buf.setLength(buf.length() - 2);
        buf.append(threadId);
      } else if (threadId < 10000) {
        buf.setLength(buf.length() - 3);
        buf.append(threadId);
      } else if (threadId < 100000) {
        buf.setLength(buf.length() - 4);
        buf.append(threadId);
      } else {
        buf.setLength(buf.length() - 6);
        buf.append(threadId);
      }
      return buf.toString();
    }

  }

  @SuppressWarnings("unused")
  public static String logIdentity() {
    String lid = MDC.get("LID");
    if (lid == null) {
      resetThread();
      lid = MDC.get("LID");
    }
    return lid != null ? lid : "(no-log-identity)";
  }

}
