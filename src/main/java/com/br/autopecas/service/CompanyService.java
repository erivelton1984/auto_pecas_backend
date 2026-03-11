package com.br.autopecas.service;

import com.br.autopecas.dto.CompanyDTO;
import com.br.autopecas.model.Company;
import com.br.autopecas.repository.CompanyRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> getAll() {
        return repository.findAll();
    }

    public Company getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
    }

    public Company save(CompanyDTO dto) {

        Company company = new Company();

        company.setName(dto.getName());
        company.setAddress(dto.getAddress());
        company.setCity(dto.getCity());
        company.setState(dto.getState());
        company.setLatitude(dto.getLatitude());
        company.setLongitude(dto.getLongitude());

        return repository.save(company);
    }

    public Company update(Long id, CompanyDTO dto) {

        Company company = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));

        company.setName(dto.getName());
        company.setAddress(dto.getAddress());
        company.setCity(dto.getCity());
        company.setState(dto.getState());
        company.setLatitude(dto.getLatitude());
        company.setLongitude(dto.getLongitude());

        return repository.save(company);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}