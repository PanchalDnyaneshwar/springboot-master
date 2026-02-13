package com.springboot.jobapp.company;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService ) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAll() throws Exception {
        return this.companyService.getAll();
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody Company company) throws Exception {        
         this.companyService.create(company);
         return new ResponseEntity<String>("Company created successfully", HttpStatus.CREATED); 
    }
    
    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Long id) {
        return this.companyService.getCompany(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        this.companyService.updateCompany(id, company);
        return new ResponseEntity<String>("Company updated successfully", HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
         this.companyService.deleteCompany(id);
         return new ResponseEntity<String>("Company deleted successfully", HttpStatus.OK);
    }
}
