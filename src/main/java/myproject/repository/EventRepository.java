package myproject.repository;

import myproject.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface EventRepository extends JpaRepository<Event,String> {

    void deleteByUpdatedOnBefore(Instant before);

}
