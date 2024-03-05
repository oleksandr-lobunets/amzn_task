package com.agencyamazon.task;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ScheduledTasks {

    @Scheduled(initialDelay = 30, fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
