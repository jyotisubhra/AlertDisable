package test.resource.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;


public class ConfigUtils {
	
	public static String propertyFile = "src/conf/config.properties";
	public static Properties properties = new Properties();
	public static String logFolder;
	public static String ScreenshotFolder;
	public static String LogfileName;
	
	
	public static Map map = null;
	public static String comment = null;
	public static String startTime = null;
	public static String endTime = null;
	public static String environment = null;
	public static String browser;
	public static String URL = null;
	public static String enable = null;
	
	static String optBrowser;
	
	static Logger logger = Logger.getLogger("ConfigUtils");
	
	public static void fnReadConfig() {
		String[] intAllHosts = null;
		try {
			InputStream input = new FileInputStream(propertyFile);
			properties.load(input);
			logger.info("Test Run Env : " + properties.getProperty("env.RunEnv"));
			environment = properties.getProperty("ENV");
			URL = properties.getProperty("NAGIOS_URL");
			browser = properties.getProperty("BROWSER");
			comment = properties.getProperty("comment");
			startTime = properties.getProperty("startTime");
			endTime = properties.getProperty("endTime");
			enable = properties.getProperty("ENABLE");
			logger.info("Environment Selected is :" + environment);
			if (null != environment) {
				intAllHosts = properties.getProperty(environment).split(",");
				logger.info("Host Lists size, going to disabled: " + intAllHosts.length);
			}
			logFolder = properties.getProperty("LogFolder");
			ScreenshotFolder = properties.getProperty("ScreenshotFolder");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (null != intAllHosts) {
			List<String> intHosts= new ArrayList<String>(Arrays.asList(intAllHosts));
			map = new HashMap<>();
			map.put(environment, intHosts);
		}
	}
	
	public static void fnLoggerStart() {
		SimpleLayout layout = new SimpleLayout();
		String str_Time = getCurrentDateTimeSS();
		String fiename = "target/logs/" + str_Time + ".log";
		LogfileName = "target/logs/" + str_Time + ".log";
		FileAppender appender;
		try {
			appender = new FileAppender(layout, fiename, false);
			logger.addAppender(appender);
			BasicConfigurator.configure();
			logger.setLevel(Level.INFO);
			logger.info("************* Logger Started ***************");
			ScreenshotFolder = "target/logs/" + str_Time;
			File screenshotFolder = new File(ScreenshotFolder);
			if (!screenshotFolder.exists()) {
				if (screenshotFolder.mkdir()) {
					logger.info("Screenshot folder created");
				} else {
					logger.error("screenshot folder created");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getCurrentDateTimeSS() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void fnLoggerEnd() {
		logger.info("**************Logger Ended ******************");
		
	}
	
	public static void captureScreenshot(String fileName) {
		try {
			Robot robot = new Robot();
			BufferedImage screenshot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ConfigUtils.logger.info("Capturing Screenshots");
			
			LogfileName = StringUtils.replace(LogfileName, ".log","");
			File outputFile = new File(LogfileName + "/" + fileName);
			ImageIO.write(screenshot, "JPG", outputFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ReportLog(boolean event, String message, boolean screenshot) {
		String fileName = getCurrentDateTimeSS() + ".jpg";
		if (screenshot) {
			ConfigUtils.captureScreenshot(fileName);
		}
		
		if (!event) {
			ConfigUtils.logger.error( message + "\n" + "Check error Screenshot - " + fileName);
			//AssertTrue(message, false);
		} else {
			ConfigUtils.logger.info(message);
			//AssertTrue(message, true);
		}
	}

	public static void fnBeforeTestBlock() {
		ConfigUtils.fnLoggerStart();
		ConfigUtils.fnReadConfig();
		BusinessUtils.setUp();
		
	}
}
