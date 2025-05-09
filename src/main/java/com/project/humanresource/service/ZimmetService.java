package com.project.humanresource.service;

import com.project.humanresource.entity.Zimmet;
import com.project.humanresource.repository.ZimmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ZimmetService {
    private final ZimmetRepository zimmetRepository;

    @Autowired
    public ZimmetService(ZimmetRepository zimmetRepository) {
        this.zimmetRepository = zimmetRepository;
    }

    public Zimmet save(Zimmet zimmet) {
        return zimmetRepository.save(zimmet);
    }

    public Optional<Zimmet> findById(Long id) {
        return zimmetRepository.findById(id);
    }
} 