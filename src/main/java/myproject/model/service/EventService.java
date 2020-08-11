package myproject.model.service;

import myproject.service.EventServiceModel;

import java.util.List;

public interface EventService {

    List<EventServiceModel> findAll();
    void cleanUpOldEvent();
    void createOrUpdateEvent(EventServiceModel eventServiceModel);
    void delete(String eventId);

}
