package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.impl.ArrivalDateEarlierDepartureDateFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrivalDateEarlierDepartureDateFilterTest {
    private FlightFilter out;

    @BeforeEach
    public void setUp() {
        out = new ArrivalDateEarlierDepartureDateFilter();
    }

    @Test
    public void trueSegmentsFlightTest() {
        LocalDateTime time = LocalDateTime.now();
        List<Flight> flights = List.of(
                new Flight(List.of(new Segment(time, time.plusHours(2)),
                        new Segment(time.plusHours(4), time.plusHours(6))))
        );
        List<Flight> result = out.filter(flights);
        assertEquals(1, result.size());
    }

    @Test
    public void falseSegmentsFlightTest() {
        LocalDateTime time = LocalDateTime.now();
        List<Flight> flights = List.of(
                new Flight(List.of(new Segment(time, time.minusHours(2))))
        );
        List<Flight> result = out.filter(flights);
        assertEquals(0, result.size());
    }
}
