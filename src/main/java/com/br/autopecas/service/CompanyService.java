package com.br.autopecas.service;

import com.br.autopecas.dto.CategoryDTO;
import com.br.autopecas.dto.CompanyDTO;
import com.br.autopecas.model.Category;
import com.br.autopecas.model.Company;
import com.br.autopecas.repository.CategoryRepository;
import com.br.autopecas.repository.CompanyRepository;
import org.springframework.stereotype.Service;

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

    public Company save(Company comp) {

        Company company = new Company();

        company.setName(comp.getName());
        company.setAddress(comp.getAddress());
        company.setCity(comp.getCity());
        company.setState(comp.getState());
        company.setLatitude(comp.getLatitude());
        company.setLongitude(comp.getLongitude());

        return repository.save(company);
    }

    public Company getById(Long id) {

        return repository.findById(id).orElseThrow();
    }

    public Company update(Long id, CompanyDTO companyDTO){

        Company comp = repository.findById(id)
                .orElseThrow();

        comp.setName(companyDTO.getName());
        comp.setAddress(companyDTO.getAddress());
        comp.setCity(companyDTO.getCity());
        comp.setState(companyDTO.getState());
        comp.setLatitude(companyDTO.getLatitude());
        comp.setLongitude(companyDTO.getLongitude());

        return repository.save(comp);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
