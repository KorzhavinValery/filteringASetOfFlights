package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.impl.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartureBeforeCurrentTimeFilterTest {
    private FlightFilter out;

    @BeforeEach
    public void setUp() {
        out = new DepartureBeforeCurrentTimeFilter();
    }

    @Test
    public void flightsDepartureBeforeCurrentTimeTest() {
        LocalDateTime currTime = LocalDateTime.now();
        List<Flight> flights = List.of(
                new Flight(List.of(new Segment(currTime.minusHours(1), currTime.plusHours(11))))
        );
        List<Flight> result = out.filter(flights);
        assertEquals(0, result.size());
    }

    @Test
    public void flightsDepartureAfterCurrentTimeTest() {
        LocalDateTime now = LocalDateTime.now();
        List<Flight> flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(1), now.plusHours(11))))
        );
        List<Flight> result = out.filter(flights);
        assertEquals(1, result.size());
    }

}
