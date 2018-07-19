package com.jactravel.spring.controller;

import com.jactravel.spring.component.SnapshotJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SnapshotController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SnapshotController.class);

    @PostMapping(path = "/syncstart", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<?> autoSyncTask(@RequestParam(name = "cron-exp") String cronSxp) {
        LOGGER.info("Scheduling Sync with cron " + cronSxp);
        Map<String, String> response = new HashMap<>();


        try {
            JobDetail job = JobBuilder.newJob(SnapshotJob.class)
                    .withIdentity("sync_job", "group1").build();
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(cronSxp))
                    .build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
            response.put("message", "Sync scheduled.");
        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            response.put("error", e.getMessage());
            e.printStackTrace();
        }


        LOGGER.info("Sync scheduled.");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


    @PostMapping(path = "/syncstop", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<?> autoSyncTaskStop() {
        LOGGER.info("Stopping scheduling job");
        Map<String, String> response = new HashMap<>();
            try {
                Scheduler scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.unscheduleJob(new TriggerKey("trigger1", "group1"));
                scheduler.deleteJob(new JobKey("sync_job", "group1"));
                scheduler.shutdown();
                response.put("message", "Sync stopped.");
            } catch (Exception e) {
                LOGGER.error("Error: " + e.getMessage());
                response.put("error", e.getMessage());
                e.printStackTrace();
            }
            LOGGER.info("Sync stopped.");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
