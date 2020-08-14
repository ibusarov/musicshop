package myproject.model.service.impl;

import myproject.model.entity.Event;
import myproject.model.entity.Product;
import myproject.model.service.EventService;
import myproject.repository.EventRepository;
import myproject.service.EventServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<EventServiceModel> findAll() {
        return this.eventRepository
                .findAll()
                .stream()
                .map(event -> this.modelMapper
                        .map(event,EventServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void cleanUpOldEvent() {

        Instant endTime = Instant.now().minus(20, ChronoUnit.DAYS);
        eventRepository.deleteByUpdatedOnBefore(endTime);
    }

    @Override
    public void createOrUpdateEvent(EventServiceModel eventServiceModel) {

        if (eventServiceModel.getCreatedOn() == null) {
            eventServiceModel.setCreatedOn(Instant.now());
        }
        eventServiceModel.setUpdatedOn(Instant.now());

        this.eventRepository.saveAndFlush
                (this.modelMapper.map(eventServiceModel,Event.class));

    }

    @Override
    public void delete(String eventId) {

        this.eventRepository.deleteById(eventId);
    }
}
