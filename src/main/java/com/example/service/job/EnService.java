package com.example.service.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Job;

@Service
@Transactional
public class EnService {
	
	public List<Job> searchJob(String codingLanguage, Integer page) {
		
		Job job;
		List<Job> jobList = new ArrayList<>();
		
		try {
	    	String siteUrl = "https://employment.en-japan.com/search/search_list/?occupation_back=400000&caroute=0701&occupation=401000_401500_402000_402500_403000_403500_404000_404500_405000_405500_409000&areaid=2&keywordtext="+codingLanguage;
	    	if (page>=2) {
	    		siteUrl = "https://employment.en-japan.com/search/search_list/?keywordtext="+codingLanguage+"&areaid=2&occupation=401000_401500_402000_402500_403000_403500_404000_404500_405000_405500_409000&pagenum="+page+"&aroute=0&arearoute=1&caroute=0701";
			}
	    	
	    	Document documents = Jsoup.connect(siteUrl).get();
	    	
	        String siteName = "エン転職";
	        Elements companyName = documents.select(".nameSet .companyName .company");
	        Elements jobType = documents.select(".nameSet .jobName .jobNameText");
	        
	        String codingLanguages = "Java";
	        Elements location = documents.select(".dataArea .dataList");
	        String phoneNumber = "";
	        
	        Elements businessDetails = documents.select(".dataArea .dataList");
	        Elements url = documents.select(".buttonArea .toDesc");
	        String firstUrl = "https://employment.en-japan.com"; 
	        String latterUrl = "&aroute=0&caroute=0701"; 
	    	
	    	String published = "";	        
	    	
	        for (int i = 0; i < companyName.size(); i++) {
	        	job = new Job();

	        	job.setSiteName(siteName);
	        	job.setCompanyName(companyName.get(i).text());
	        	job.setJobType(jobType.get(i).text());
	        	
	        	job.setCodingLanguages(codingLanguages);
	        	job.setLocation(location.get(i).children().last().children().last().text());
	        	job.setPhoneNumber(phoneNumber);
	        	
	        	job.setBusinessDetails(businessDetails.get(i).children().first().children().last().text());
	        	
	        	if (url.get(i).attr("href").contains("caroute=0701")) {
	        		job.setUrl(firstUrl + url.get(i).attr("href"));
				}else {
					job.setUrl(firstUrl + url.get(i).attr("href") + latterUrl);
				}
	        	job.setPublished(published);
	        	
	        	jobList.add(job);
	        }	        
	    }catch(IOException e) {
	        e.printStackTrace();
	    }
		return jobList;
	}
}
