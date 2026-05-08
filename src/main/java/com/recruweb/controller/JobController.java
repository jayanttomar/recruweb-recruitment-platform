
package com.recruweb.controller;
import com.recruweb.service.JobService;
import com.recruweb.entity.Job; import com.recruweb.repository.JobRepository; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/jobs") @CrossOrigin("*") public class JobController {
	@Autowired
	private JobService jobService;

	@PostMapping
	public Job addJob(@RequestBody Job job) {
	    return jobService.addJob(job);
	}

	@GetMapping
	public List<Job> getAllJobs() {
	    return jobService.getAllJobs();
	}

	@GetMapping("/search/{keyword}")
	public List<Job> searchJobs(@PathVariable String keyword) {
	    return jobService.searchJobs(keyword);
	}

	@DeleteMapping("/{id}")
	public String deleteJob(@PathVariable Long id) {
	    return jobService.deleteJob(id);
	}

}