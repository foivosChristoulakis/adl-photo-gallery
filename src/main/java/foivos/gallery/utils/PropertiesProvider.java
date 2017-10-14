package foivos.gallery.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesProvider {

	private static final String propertiesFileName = "/aws.properties";
	private static Properties properties = new Properties();

	static {
		try (InputStream inStream = ClassLoader.class.getResourceAsStream(propertiesFileName)) {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getAWSBucketName() {
		return properties.getProperty("bucket.name");
	}
}
