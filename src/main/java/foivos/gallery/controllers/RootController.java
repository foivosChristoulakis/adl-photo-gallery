package foivos.gallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import foivos.gallery.utils.PropertiesProvider;
import foivos.gallery.utils.UserValidator;

@Controller
public class RootController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginPage(Model model) {

		String assetsURI = "https://s3." + PropertiesProvider.getS3Region() + ".amazonaws.com/"
				+ PropertiesProvider.getAWSBucketName() + "/" + PropertiesProvider.getAssetsPath();
		model.addAttribute("assets", assetsURI);

		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String loginPage(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println(username + " - " + password + " - " + UserValidator.isUserAccepted(username, password));
		return (UserValidator.isUserAccepted(username, password) ? "redirect:/events/index.html" : "login-failed");
	}

}
