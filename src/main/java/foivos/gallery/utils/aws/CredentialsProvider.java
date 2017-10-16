package foivos.gallery.utils.aws;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public class CredentialsProvider implements AWSCredentialsProvider {

	private static final String credentialsPropertiesFilename = "AWSCredentials.properties";

	private String accessKeyID = "";
	private String secretAccessKey = "";
	private Properties properties = new Properties();

	// Singleton instance
	private static CredentialsProvider instance = null;

	protected CredentialsProvider() {
		loadProperties(credentialsPropertiesFilename);
	}

	public static CredentialsProvider getInstance() {
		if (instance == null) {
			instance = new CredentialsProvider();
		}
		return instance;
	}

	@Override
	public AWSCredentials getCredentials() {
		return new AWSCredentials() {

			public String getAWSAccessKeyId() {
				return accessKeyID;
			}

			public String getAWSSecretKey() {
				return secretAccessKey;
			}

		};
	}

	@Override
	public void refresh() {
		// no-op
	}

	private void loadProperties(String propertiesFilename) {

		File propFile = new File(propertiesFilename);

		try (FileInputStream iFile = new FileInputStream(propFile)) {

			properties.load(iFile);
			accessKeyID = properties.getProperty("aws.accessKeyID");
			secretAccessKey = properties.getProperty("aws.secretAccessKey");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}