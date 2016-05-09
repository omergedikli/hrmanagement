package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Application;;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

}
