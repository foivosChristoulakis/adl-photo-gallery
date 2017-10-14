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

@Controller
public class PhotoGalleryController {
	
	
	
	PhotoGalleryController(){


	}

	@RequestMapping(value = { "/events/{eventName}/{photographerName:[^.]+}" })
	public String photographerIndex(HttpServletRequest request, @PathVariable String eventName,
			@PathVariable String photographerName, Model model) {

		System.out.println("requested " + eventName + " from " + photographerName);
		System.out.println(PropertiesProvider.getAWSBucketName());
		// add trailing slash to URL
		if (!request.getRequestURI().endsWith("/"))
			return "redirect:" + request.getRequestURI() + "/";

		// else
		File galleryDir = new File("static/events/" + eventName + "/" + photographerName);
		if (!galleryDir.isDirectory())
			return "error";
		else {
			List<String> photosNames = getContentsFileNames(galleryDir);
			photosNames.remove("thumb.jpg");

			model.addAttribute("eventName", eventName);
			model.addAttribute("photographerName", photographerName);
			model.addAttribute("photosNames", photosNames);

			return "photographerGallery";
		}

	}

	static List<String> getContentsFileNames(File directory) {
		File[] files = directory.listFiles();
		ArrayList<String> fileNames = new ArrayList<>();
		for (File file : files) {
			fileNames.add(file.getName());
		}
		return fileNames;
	}

}
