package com.springboot.jobapp.company;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    List<Company> getAll() throws Exception
    {
        try {
              return this.companyRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Company create(Company company) throws Exception {

        try{
            Company comp = this.companyRepository.save(company);
            return comp;
        } catch( Exception e) {
            throw new Exception(e);
        }
    }

    public Company getCompany(Long id) {
        Optional<Company> comp =  this.companyRepository.findById(id);

        if (comp.isPresent()) {
            return comp.get();
        } else {
            return null;
        }
        
    }

    public Company updateCompany(Long id, Company company) {
        Optional<Company> comp =  this.companyRepository.findById(id);
        if(comp.isPresent())
            {
                Company c1 = comp.get();

                c1.setDescription(company.getDescription());
                c1.setName(company.getName());
                this.companyRepository.save(c1);
                return c1;
            } else {
                return null;
            }   
    }

    public boolean deleteCompany(Long id) {
        try{
            this.companyRepository.deleteById(id);
            return true;
        } catch(Exception e) {
            throw new EntityNotFoundException("Entity not found");            
        }
    } 
}
