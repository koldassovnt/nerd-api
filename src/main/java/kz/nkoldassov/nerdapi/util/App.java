package kz.nkoldassov.nerdapi.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
  public static Path folderD() {
    return Paths.get(System.getProperty("user.home")).resolve(appCode() + ".d");
  }

  public static String appCode() {
    return "nerd";
  }

  public static Path schedulerD() {
    return folderD().resolve("scheduler");
  }

  public static Path confDir() {
    return folderD().resolve("conf");
  }
}
