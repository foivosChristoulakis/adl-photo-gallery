package foivos.gallery.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import foivos.gallery.utils.PropertiesProvider;
import foivos.gallery.utils.aws.DirectoryContentsProvider;
import foivos.gallery.utils.aws.S3DirectoryContentsProvider;

@Controller
public class PhotoGalleryController {

	private DirectoryContentsProvider dir;

	PhotoGalleryController() {
		dir = new S3DirectoryContentsProvider();
		dir.initialize(PropertiesProvider.getAWSBucketName(), PropertiesProvider.getEventsPath());
	}

	@RequestMapping(value = { "/events/{eventName}/{photographerName:[^.]+}" })
	public String photographerIndex(HttpServletRequest request, @PathVariable String eventName,
			@PathVariable String photographerName, Model model) {

		System.out.println("requested " + eventName + " from " + photographerName);

		// add trailing slash to URL
		if (!request.getRequestURI().endsWith("/"))
			return "redirect:" + request.getRequestURI() + "/";

		// else
		List<String> photosNames = dir.getFilenames(eventName + "/" + photographerName);
		System.out.println(photosNames);

		// if no photos returned
		if (photosNames.isEmpty())
			return "error";

		photosNames.remove("thumb.jpg");

		String assetsURI = "https://s3." + PropertiesProvider.getS3Region() + ".amazonaws.com/"
				+ PropertiesProvider.getAWSBucketName() + "/" + PropertiesProvider.getAssetsPath();

		String eventURI = "https://s3." + PropertiesProvider.getS3Region() + ".amazonaws.com/"
				+ PropertiesProvider.getAWSBucketName() + "/" + PropertiesProvider.getEventsPath() + "/" + eventName;
		// System.out.println(assetsURI);

		model.addAttribute("assets", assetsURI);
		model.addAttribute("event", eventURI);

		model.addAttribute("eventName", eventName);
		model.addAttribute("photographerName", photographerName);
		model.addAttribute("photosNames", photosNames);

		return "photographerGallery";

	}

	@Deprecated
	static List<String> getContentsFileNames(File directory) {
		File[] files = directory.listFiles();
		ArrayList<String> fileNames = new ArrayList<>();
		for (File file : files) {
			fileNames.add(file.getName());
		}
		return fileNames;
	}

}
