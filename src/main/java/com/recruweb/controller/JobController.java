
package com.recruweb.controller;
import com.recruweb.service.JobService;
import com.recruweb.entity.Job; import com.recruweb.repository.JobRepository; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
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

	@PostMapping("/generate-jd")
	public Map<String, String> generateJD(
	        @RequestBody Map<String, String> data
	) {

	    String role = data.get("role");
	    String skills = data.get("skills");
	    String experience = data.get("experience");
	    String salary = data.get("salary");
	    String company = data.get("company");

	    String jd =

	            "COMPANY: " + company + "\n\n" +
	            "ROLE: " + role + "\n\n" +

	            "SKILLS REQUIRED: " + skills + "\n\n" +

	            "EXPERIENCE: " + experience + "\n\n" +

	            "SALARY: " + salary + "\n\n" +

	            "JOB DESCRIPTION:\n" +

	            "We are looking for a talented "
	            + role +
	            " with strong knowledge in "
	            + skills +
	            ". Candidate should have "
	            + experience +
	            " experience and good problem-solving skills.";

	    Map<String, String> response =
	            new HashMap<>();

	    response.put("generatedJD", jd);

	    return response;
	}


}