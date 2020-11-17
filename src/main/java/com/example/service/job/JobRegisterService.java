package com.example.service.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Job;
import com.example.repository.job.JobRepository;

@Service
@Transactional
public class JobRegisterService {
	
	@Autowired
	private JobRepository jobRepository;
	
	public void insert(List<Job> jobList) {
		jobRepository.insert(jobList);
	}
}
