package multi.fclass.carrot.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartCotroller {
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
