package com.hms.hms.controller;

import com.hms.hms.entity.Property;
import com.hms.hms.repository.PropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping("/search-hotels")
    public List<Property> searchHotels(@RequestParam String name){
        List<Property> properties = propertyRepository.searchHotel(name);
        return properties;
    }
}