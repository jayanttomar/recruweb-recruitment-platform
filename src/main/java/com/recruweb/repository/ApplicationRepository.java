package com.recruweb.repository;

import com.recruweb.entity.Application; import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> { }