package foivos.gallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventsIndexController {

//	@RequestMapping(value = { "/" })
//	public String home() {
//		return "redirect:/events/index.html";
//	}

	@RequestMapping(value = { "/events" })
	public String eventsIndex() {
		return "redirect:/events/index.html";
	}

}
