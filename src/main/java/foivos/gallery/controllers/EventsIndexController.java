package foivos.gallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import foivos.gallery.utils.PropertiesProvider;

@Controller
public class EventsIndexController {

	// @RequestMapping(value = { "/" })
	// public String home() {
	// return "redirect:/events/index.html";
	// }

	@RequestMapping(value = { "/events" })
	public String eventsIndexRedir() {
		return "redirect:/events/index.html";
	}

	@RequestMapping(value = { "/events/index.html" })
	public String eventsIndex(Model model) {

		String assetsURI = "https://s3." + PropertiesProvider.getS3Region() + ".amazonaws.com/"
				+ PropertiesProvider.getAWSBucketName() + "/" + PropertiesProvider.getAssetsPath();

		String eventsURI = "https://s3." + PropertiesProvider.getS3Region() + ".amazonaws.com/"
				+ PropertiesProvider.getAWSBucketName() + "/" + PropertiesProvider.getEventsPath();

		model.addAttribute("assets", assetsURI);
		model.addAttribute("events", eventsURI);

		return "eventsIndex";
	}

}
