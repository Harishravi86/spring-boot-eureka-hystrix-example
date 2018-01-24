package com.example.award.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.award.model.Award;

@RestController
public class AwardController {

	@RequestMapping(value = "/award/list/winner/name/{name}", method = RequestMethod.GET)
	public List<Award> getAwardByWinnerName(@PathVariable(value="name") String name){
		List<Award> awards = createAwards(name);
		return awards;
	}
	
	@RequestMapping(value = "async/award/list/winner/name/{name}", method = RequestMethod.GET)
	public List<Award> getAwardByWinnerNameAsync(@PathVariable(value="name") String name) throws InterruptedException{
		List<Award> awards = createAwards(name);
		Thread.sleep(1000l);
		return awards;
	}
	
	private List<Award> createAwards(String name){
		List<Award> awards = new ArrayList<Award>();
		for(int i = 1; i < 6; i++) {
		    Date date = DateUtils.addYears(Calendar.getInstance().getTime(), -i);
			awards.add(new Award("dazzle sparkle : " + i, name, date));
		}
		return awards;
	}
	
	@Async("myExecutor1")
	public void runTask () {
		System.out.printf("Running task  thread: %s%n",
				Thread.currentThread().getName());
	}
	
}
