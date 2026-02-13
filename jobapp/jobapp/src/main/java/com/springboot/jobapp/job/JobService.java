package com.springboot.jobapp.job;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // private List<Job> jobs = new ArrayList<>();
    //private Long nexrId = 1L;

    public List<Job> findAll() {
        return this.jobRepository.findAll();
    }

    public Job createJob(Job job) {
        return this.jobRepository.save(job);
    }

    public Job findOne(Long id) {
        return this.jobRepository.findById(id).orElse(null);
    }

    public boolean updateJob(Long id, Job job) {

        try {
            Optional<Job> jOptional = this.jobRepository.findById(id);

            if (jOptional.isPresent()) {
                Job n = jOptional.get();
                n.setLocation(job.getLocation());
                n.setDescription(job.getDescription());
                n.setMaxSalary(job.getMaxSalary());
                n.setMinSalary(job.getMinSalary());
                n.setTitle(job.getTitle());
                this.jobRepository.save(job);
            }

            return true;
        } catch(Exception e){
            return false;
        }
    }

    public boolean deleteJob(Long id) {
        try {
            this.jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
