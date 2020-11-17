package com.example.controller.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Job;
import com.example.service.job.EnService;
import com.example.service.job.JobRegisterService;

/**
 * 求人登録のコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/job-register")
public class JobRegisterController {

	@Autowired
	private JobRegisterService jobRegisterService;
	@Autowired
	private EnService enService;

	/**
	 * 求人登録画面へ.
	 * 
	 * @return 求人登録
	 */
	@RequestMapping("/to-job-register")
	public String toJobRegister() {

		return "job/job_register";
	}

	/**
	 * 求人登録.
	 * 
	 * @param form 新規の求人
	 * @return
	 */
	@RequestMapping("/job-register")
	public String jobRegister() {
		
		List<Job> enJobList = enService.searchJob();

		List<List<Job>> allSiteList = Arrays.asList(enJobList);
		List<Job> allJobList = new ArrayList<>();
		for (List<Job> siteList : allSiteList) {
			allJobList.addAll(siteList);
		}
		jobRegisterService.insert(allJobList);

		return "redirect:/job-register/to-job-register";
	}
}
