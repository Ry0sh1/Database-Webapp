package com.Ryoshi.DatabaseProgram.repository;

import com.Ryoshi.DatabaseProgram.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    @Query(value = "SELECT e FROM Event e WHERE e.event_day = :event_day AND e.event_month = :event_month AND e.event_year = :event_year")
    List<Event> findAllByDate(@Param("event_day") int event_day, @Param("event_month") int event_month, @Param("event_year") int event_year);

}
