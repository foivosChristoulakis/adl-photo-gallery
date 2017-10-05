package foivos.gallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EventController {
	


    @RequestMapping(value= {"/events/{eventName:[^.]+}"}) //regex : a sequence of characters which does not contain a dot"
    public String eventIndex(@PathVariable String eventName) {
		return "redirect:/events/"+eventName+"/index.html";
    }
    
 

    
}//end class
