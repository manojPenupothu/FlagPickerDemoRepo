package com.flagpicker.FlagPickerDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flagpicker.FlagPickerDemo.beans.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent,Integer>{

	
}