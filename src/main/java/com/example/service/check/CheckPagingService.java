package com.example.service.check;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CheckPagingService {
	
	public Integer countTotalPage(String codingLanguage,Integer displayCount) {
		
		Integer totalJobCount = 0;
		
		try {
			String siteUrl = "https://employment.en-japan.com/search/search_list/?occupation_back=400000&caroute=0701&occupation=401000_401500_402000_402500_403000_403500_404000_404500_405000_405500_409000&areaid=2&keywordtext="+codingLanguage;
			
			Document documents = Jsoup.connect(siteUrl).get();
			
			Elements elementTotalJobCount = documents.select(".jobSearchListBase .jobSearchListNumCondition .num em");
			totalJobCount = Integer.parseInt(elementTotalJobCount.first().text());
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		float totalPage = totalJobCount / displayCount ;
		if (totalJobCount / displayCount != 0) {
			totalPage++;
		}
				
		return (int) totalPage;
	}

}
