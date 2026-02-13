package com.springboot.jobapp.job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/job")
public class JobController {

    private  JobService jobService;

    public JobController(JobService jobService ){
        this.jobService = jobService;
    }

    @GetMapping()
    public List<Job> findAll() {
       return this.jobService.findAll();
    }

    @PostMapping()
    public ResponseEntity<String> createJob(@RequestBody Job job) {
         this.jobService.createJob(job);
        return new ResponseEntity<>("Job created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findOne(@PathVariable Long id) {
        try {
            Job job = this.jobService.findOne(id);
            if (job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
            else
                return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) {
        boolean res = this.jobService.updateJob(id, job);

        if (res) {
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(" Failed to update job", HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
         boolean delete = this.jobService.deleteJob(id);

         if (delete)
            return new ResponseEntity<>("Job removed Successfully", HttpStatus.OK);
         else
             return new ResponseEntity<>("Job not Removed ", HttpStatus.NOT_FOUND);
    }
}
