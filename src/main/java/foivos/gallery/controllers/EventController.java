package foivos.gallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import foivos.gallery.utils.PropertiesProvider;
import foivos.gallery.utils.aws.DirExistenceChecker;

import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EventController {

	private DirExistenceChecker dirChecker;

	public EventController() {
		dirChecker = new DirExistenceChecker(PropertiesProvider.getAWSBucketName(), PropertiesProvider.getEventsPath());
	}

	@RequestMapping(value = { "/events/{eventName:[^.]+}" }) // regex : a sequence of characters which does not contain
																// a dot"
	public String eventIndexRedir(@PathVariable String eventName) {
		return "redirect:/events/" + eventName + "/index.html";
	}

	@RequestMapping(value = { "/events/{eventName:[^.]+}/index.html" }) // regex : a sequence of characters which does
																		// not contain a dot"
	public String eventIndex(@PathVariable String eventName, Model model) {

		// Validate event name
		if (!dirChecker.exists(eventName))
			return "error";

		String eventURI = "https://s3." + PropertiesProvider.getS3Region() + ".amazonaws.com/"
				+ PropertiesProvider.getAWSBucketName() + "/" + PropertiesProvider.getEventsPath() + "/" + eventName;

		String assetsURI = "https://s3." + PropertiesProvider.getS3Region() + ".amazonaws.com/"
				+ PropertiesProvider.getAWSBucketName() + "/" + PropertiesProvider.getAssetsPath();

		model.addAttribute("assets", assetsURI);
		model.addAttribute("event", eventURI);
		model.addAttribute("eventName", eventName);

		return "eventIndex";
	}

}// end class
