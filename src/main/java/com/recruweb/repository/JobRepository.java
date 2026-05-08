package com.recruweb.repository;

import com.recruweb.entity.Job; import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {


	List<Job> findByTitleContaining(String keyword);


}
