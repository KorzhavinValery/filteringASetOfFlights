package com.gridnine.testing;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.impl.ArrivalDateEarlierDepartureDateFilter;
import com.gridnine.testing.filter.impl.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.filter.impl.TransferMoreThanTwoHoursFilter;
import com.gridnine.testing.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrivalDateEarlierDepartureDateFilter arrivalDateEarlierDepartureDateFilter = new ArrivalDateEarlierDepartureDateFilter();
        DepartureBeforeCurrentTimeFilter departureBeforeCurrentTimeFilter = new DepartureBeforeCurrentTimeFilter();
        TransferMoreThanTwoHoursFilter transferMoreThanTwoHoursFilter = new TransferMoreThanTwoHoursFilter();

        List<Flight> flights = FlightBuilder.createFlights();


        System.out.println("Все авиаперелёты:");
        flights.forEach(System.out::println);

        System.out.println("\n Авиаперелётов с датой прибытия раньше даты отправки");
        arrivalDateEarlierDepartureDateFilter.filter(flights).forEach(System.out::println);

        System.out.println("\n Вылеты до текущего момента времени");
        departureBeforeCurrentTimeFilter.filter(flights).forEach(System.out::println);
        System.out.println("\n Трансфер занимающий больше 2 часов");
        transferMoreThanTwoHoursFilter.filter(flights).forEach(System.out::println);

    }
}
