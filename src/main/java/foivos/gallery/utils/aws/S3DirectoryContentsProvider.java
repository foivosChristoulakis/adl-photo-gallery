package foivos.gallery.utils.aws;

import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3DirectoryContentsProvider implements DirectoryContentsProvider {

	private String bucket;
	private String eventsPath;
	private AmazonS3 s3Client;

	@Override
	public List<String> getFilenames(String folder) {
		if (bucket == null)
			return null;

		ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucket)
				.withPrefix(eventsPath + "/" + folder + "/").withDelimiter("/");

		ObjectListing listing = s3Client.listObjects(listObjectsRequest);
		List<S3ObjectSummary> summaries = listing.getObjectSummaries();

		while (listing.isTruncated()) {
			listing = s3Client.listNextBatchOfObjects(listing);
			summaries.addAll(listing.getObjectSummaries());
		}

		String prefixToRemove = eventsPath + "/" + folder + "/";

		List<String> filenames = summaries.stream().map(summary -> {
			return summary.getKey();
		}).map(fullpath -> {
			return fullpath.substring(prefixToRemove.length());
		}).collect(Collectors.toList());

		return filenames;
	}

	@Override
	public void initialize(String bucket, String eventsPath) {
		this.bucket = bucket;
		this.eventsPath = eventsPath;

		AmazonS3ClientBuilder s3ClientBuilder = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_WEST_2) // TODO
																												// make
																												// it
																												// parameter
				.withCredentials(CredentialsProvider.getInstance());

		AmazonS3 s3Client = s3ClientBuilder.build();
		this.s3Client = s3Client;
	}

}
