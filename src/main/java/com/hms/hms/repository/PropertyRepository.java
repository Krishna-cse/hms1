package com.hms.hms.repository;

import com.hms.hms.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("select p from Property p join p.city c join p.country co where c.name=:name or co.name=:name")
    List<Property> searchHotel(@Param("name") String name);
}