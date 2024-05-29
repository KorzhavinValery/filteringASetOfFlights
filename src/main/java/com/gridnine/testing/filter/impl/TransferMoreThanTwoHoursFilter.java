package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TransferMoreThanTwoHoursFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> calculateTransferTime(flight.getSegments()) <= 2)
                .collect(Collectors.toList());
    }

    private int calculateTransferTime(List<Segment> segments) {
        int transferTime = 0;
        for (int i = 1; i < segments.size(); i++) {
            LocalDateTime arrDate = segments.get(i - 1).getArrivalDate();
            LocalDateTime depDate = segments.get(i).getDepartureDate();
            transferTime += (int) Duration.between(arrDate, depDate).toHours();
        }
        return transferTime;
    }
}
