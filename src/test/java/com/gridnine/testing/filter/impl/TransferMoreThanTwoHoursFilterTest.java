package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.impl.TransferMoreThanTwoHoursFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferMoreThanTwoHoursFilterTest {
    private FlightFilter out;

    @BeforeEach
    public void setUp() {
        out = new TransferMoreThanTwoHoursFilter();
    }

    @Test
    public void segmentFlightsTest() {
        List<Flight> flights = List.of(
                new Flight(List.of(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1))))
        );
        List<Flight> result = out.filter(flights);
        assertEquals(1, result.size());
    }


    @Test
    public void testFlightsWithGroundTimeMoreThanTwoHours() {
        List<Flight> flights = List.of(
                new Flight(Arrays.asList(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                        new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6)))
                ));
        List<Flight> result = out.filter(flights);
        assertEquals(0, result.size());
    }
}
