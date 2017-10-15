package foivos.gallery.utils.aws;

import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class DirExistenceChecker {

	private String bucket;
	private String eventsPath;
	private AmazonS3 s3Client;

	public boolean exists(String eventName) {
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucket)
				.withPrefix(eventsPath + "/" + eventName + "/").withMaxKeys(1);

		ObjectListing listing = s3Client.listObjects(listObjectsRequest);
		List<S3ObjectSummary> summaries = listing.getObjectSummaries();
		return !summaries.isEmpty();
	}

	public DirExistenceChecker(String bucket, String eventsPath) {
		this.bucket = bucket;
		this.eventsPath = eventsPath;

		AmazonS3ClientBuilder s3ClientBuilder = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_WEST_2); // TODO
																												// make
																												// it
																												// parameter
				//.withCredentials(CredentialsProvider.getInstance());

		AmazonS3 s3Client = s3ClientBuilder.build();
		this.s3Client = s3Client;
	}
}
