package com.saas.edu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saas.edu.dto.SchoolDto;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;

@Controller
@RequestMapping("test")
public class TestController {
	@Autowired
	private ActorRef printRouterActorRef;
	@Autowired
	private ActorSystem actorSystem;
	
	
	@ResponseBody @RequestMapping(method=RequestMethod.POST,value="/message",produces="text/xml")
	public void print(@RequestBody String message) {
	
		printRouterActorRef.tell(message,null);
		
	}
	
	@ResponseBody @RequestMapping(method=RequestMethod.GET,value="/school",produces="application/json")
	public SchoolDto getSchool() {
		return getSchoolDto();
	}

	private SchoolDto getSchoolDto() {
		SchoolDto schoolDto = new SchoolDto();
		schoolDto.setAddress("Kulathupuzha UPS");
		schoolDto.setDistrict("Kollam");
		return schoolDto;
	}

	
}


