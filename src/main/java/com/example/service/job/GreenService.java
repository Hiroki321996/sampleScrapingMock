package com.example.service.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Job;

@Service
@Transactional
public class GreenService {

	public List<Job> searchJob(String codingLanguage) {
		Boolean loop = true;
		
		
		Job job;
		List<Job> jobList = new ArrayList<>();
		
//		 下記はJava
//		try {
//			String siteUrl = "https://www.green-japan.com/search_key/01?key=oeg7lvr70tys0cs0ccvm&keyword=" + codingLanguage;
//	    	if (page>=2) {
//	    		siteUrl = "https://www.green-japan.com/search_key/01?key=oeg7lvr70tys0cs0ccvm&keyword="+ codingLanguage +"&page=" + page;
//			}
		
		try {
			String siteUrl = "https://www.green-japan.com/search_key/01?key=2vkn2gx21xjyx34dfj15&keyword=COBOL";
			
			while (loop) {
				Document documents = Jsoup.connect(siteUrl).get();
				
		    	String siteName = "Green";
//		    	Elements languagesOnSite = documents.select(".card-info__wrapper a .card-info__detail-area .job-offer-meta-tags :nth-child(3)");
		    	Elements languagesOnSite = documents.select(".card-info__wrapper a .card-info__detail-area .job-offer-meta-tags :has(.icon-code)");
		    	Elements companyName = documents.select(".card-info__wrapper a .card-info__detail-area .card-info__detail-area__box .card-info__detail-area__box__heading h3");
		    	Elements jobType = documents.select(".card-info__wrapper a .card-info__tag-area .job-offer-icon");
		    	Elements location = documents.select(".card-info__wrapper a .card-info__detail-area .job-offer-meta-tags :has(.icon-site)");
//		    	Elements phoneNumber = documents.select(evaluator);
		    	Elements businessDetails = documents.select(".card-info__wrapper a .card-info__detail-area .card-info__detail-area__text");
		    	Elements url = documents.select(".card-info__wrapper a");
		    	Elements datePublished = documents.select(".card-info__wrapper a .card-info__tag-area .update");
//		    	Elements NumOfResults = jsDoc.select(".search_job_count");
		    	Elements nextPage = documents.select(".next_page");
				
		    	for (int i = 0; i < companyName.size(); i++) {
					job = new Job();
					
					job.setSiteName(siteName);
		        	job.setCompanyName(companyName.get(i).text());
		        	job.setJobType(jobType.get(i).text());
		        	job.setCodingLanguages("Java");
		        	job.setLocation(location.get(i).text());
		        	job.setPhoneNumber("000-2222-222");
		        	job.setBusinessDetails(businessDetails.get(i).text());
		        	job.setUrl("https://www.green-japan.com" + url.get(i).attr("href"));
		        	
		        	jobList.add(job);
				}
		    	
		    	if(nextPage.attr("href").isEmpty()) {
		    		loop = false;
		    	}else {
		    		siteUrl = "https://www.green-japan.com" + nextPage.attr("href");
		    	}
		    	
			}
	    	
//	    	https://www.green-japan.com/search_key/01?key=2vkn2gx21xjyx34dfj15&keyword=COBOL
//	    	https://www.green-japan.com/search_key/01?key=2vkn2gx21xjyx34dfj15&keyword=COBOL&page=8
	    	
	    	

	    	
//	    	List<String> codingLanguages = new ArrayList<>();
//	    	Integer counter = 2;
//	    	System.out.println(languagesOnSite.get(0).text());
//	    	System.out.println("------------------");
//	    	for (Element element : languagesOnSite) {
//	    		if(counter % 2 == 0) {
//	    			codingLanguages.add(element.text());
//	    		}
//	    		counter++;
//			}
	    	
//	    	for (String language : codingLanguages) {
//				System.out.println(language);
//			}
//	    	System.out.println(languagesOnSite.get(0).text());
	    	
//	    	System.out.println("会社名");
//	    	System.out.println(companyName.size());
//	    	for (Element element : companyName) {
//				System.out.println(element.text());
//			}
//	    	System.out.println("募集職種");
//	    	for (Element element : jobType) {
//				System.out.println(element.text());
//			}
//	    	System.out.println("勤務地");
//	    	for (Element element : location) {
//				System.out.println(element.text());
//			}
//	    	System.out.println("電話番号");
//	    	for (Element element : phoneNumber) {
//				System.out.println(element.text());
//			}
//	    	System.out.println("事業内容");
//	    	for (Element element : businessDetails) {
//				System.out.println(element.text());
//			}
//	    	System.out.println("リンク");
//	    	for (Element element : url) {
//	    		System.out.print("https://www.green-japan.com");
//				System.out.println(element.attr("href"));
//			}
//	    	System.out.println("掲載日");
//	    	for (Element element : datePublished) {
//				System.out.println(element.text());
//			}
	    	

//	    	System.out.println("aaaaaaaaaaaaaa");
//	    	if(nextPage.attr("href").isEmpty()) {
//	    		System.out.println("nulldesu");
//	    	}else {
//	    		System.out.println("nutnull desu");
//	    		System.out.println(nextPage.attr("href"));
//	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		https://www.geekly.co.jp/search/detail/604192
		System.out.println("done loading");
		
		return jobList;
	}
}
