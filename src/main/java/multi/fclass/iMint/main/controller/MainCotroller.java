package multi.fclass.iMint.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Seongil, Yoon
 *
 */
@Controller
public class MainCotroller {

	@GetMapping("main")
	public String mainPage() {
		return "main";
	}
}
