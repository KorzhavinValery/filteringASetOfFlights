package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureBeforeCurrentTimeFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime currTime = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream().anyMatch(segment -> segment.getDepartureDate().isAfter(currTime)))
                .collect(Collectors.toList());
    }
}
