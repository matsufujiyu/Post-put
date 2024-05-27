package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper

public interface PetMapper {
	Pet findById(long id);
	
	List<Pet> findAll();
    long insert(@Param("name") Pet name);
    long update(@Param("name") Pet name);
    boolean delete(long id);
  
}
