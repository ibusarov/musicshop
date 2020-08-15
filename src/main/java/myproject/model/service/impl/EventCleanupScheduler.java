package myproject.model.service.impl;

import myproject.model.service.EventService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventCleanupScheduler {

    private final EventService eventService;

    public EventCleanupScheduler(EventService eventService) {
        this.eventService = eventService;
    }

    @Scheduled(cron = "0 0 12 * * SUN")
    public void cleanUpOldEvents() {
        eventService.cleanUpOldEvent();
    }
}
