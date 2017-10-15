package foivos.gallery.utils.aws;

import java.util.List;

public interface DirectoryContentsProvider {

	public List<String> getFilenames(String folder);

	void initialize(String bucket, String eventsPath);

}
