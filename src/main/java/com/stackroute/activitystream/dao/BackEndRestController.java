package com.stackroute.activitystream.dao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//This class not required
@RestController
public class BackEndRestController {

	
	@GetMapping("/backend")
	public String getBackend()
	{
		return "Tested";
	}
}
