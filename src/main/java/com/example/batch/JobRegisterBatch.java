package com.example.batch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.domain.Job;
import com.example.service.job.EnService;
import com.example.service.job.JobRegisterService;

/**
 * 求人登録のバッチ.
 * 
 * @author oyamadakenji
 *
 */
@Component
public class JobRegisterBatch {

	@Autowired
	private JobRegisterService jobRegisterService;
	@Autowired
	private EnService enService;

	/**
	 * 求人登録.
	 * 
	 */
	@Scheduled(cron = "0 0 0 * * 1", zone = "Asia/Tokyo")
	public void jobRegister() {
		
		List<Job> enJobList = enService.searchJob();

		List<List<Job>> allSiteList = Arrays.asList(enJobList);
		List<Job> allJobList = new ArrayList<>();
		for (List<Job> siteList : allSiteList) {
			allJobList.addAll(siteList);
		}
		jobRegisterService.insert(allJobList);
		System.out.println("できた");
	}
}