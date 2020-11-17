package com.example.controller.check;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Job;
import com.example.service.check.CheckEnService;
import com.example.service.check.CheckPagingService;

/**
 * 求人一覧のコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/check-job-list")
public class CheckJobListController {

	@Autowired
	private CheckEnService enService;
	
	@Autowired
	private CheckPagingService pagingService;

	/**
	 * 求人一覧画面へ.
	 * 
	 * @return 求人一覧
	 */
	@RequestMapping("/to-check-job-list")
	public String toJobList(Integer siteNumber,Integer codingLanguageNumber, Integer page, Model model) {
		
		List<Job> jobList = null;
		
		if(Objects.isNull(siteNumber)) {
			siteNumber = 1;
		}

		if(Objects.isNull(codingLanguageNumber)) {
			codingLanguageNumber = 1;
		}
		
		if(Objects.isNull(page)) {
			page = 1;
		}
		model.addAttribute("page",page);
		
		String codingLanguage = null;
		List<String> codingLanguageNameList = Arrays.asList("Java", "Ruby", "PHP", "C++", "C#", "COBOL", "Go", "Kotlin", "Perl", "Python", "R", "Scala", "Swift", "TypeScript");
		for (int i = 0; i < codingLanguageNameList.size(); i++) {
			if (codingLanguageNumber-1 == i) {
				codingLanguage = codingLanguageNameList.get(i);
				break;
			}
		}
		model.addAttribute("codingLanguageNumber",codingLanguageNumber);
		model.addAttribute("codingLanguageNameList",codingLanguageNameList);

		List<String> siteNameList = Arrays.asList("エン転職", "キャリトレ", "Geekly", "Green", "type", "doda", "マイナビ転職", "リクナビNEXT");
		switch (siteNumber){
		  case 1:
			  jobList = enService.searchJobB(codingLanguage,page);
			  model.addAttribute("siteName",siteNameList.get(0));
			  break;
		  case 2:
			  jobList = enService.searchJobB(codingLanguage,page);
			  model.addAttribute("siteName",siteNameList.get(1));
			  break;
		  case 3:
			  System.out.println("3");
			  break;
		  case 4:
			    System.out.println("4");
			    break;
		  case 5:
				System.out.println("5");
				break;
		  case 6:
				System.out.println("6");
				break;
		  case 7:
			    System.out.println("7");
			    break;
		  case 8:
			    System.out.println("8");
			    break;
		}
		model.addAttribute("siteNumber",siteNumber);
		model.addAttribute("siteNameList",siteNameList);
		
		
		Integer totalPage = pagingService.countTotalPage(codingLanguage,50);
		model.addAttribute("totalPage",totalPage);
	    model.addAttribute("jobList",jobList);
		return "job/check_job_list";
	}	
}
