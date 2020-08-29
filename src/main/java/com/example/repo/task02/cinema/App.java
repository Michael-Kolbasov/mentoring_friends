package com.example.repo.task02.cinema;

import com.example.repo.task02.cinema.core.Cinema;
import com.example.repo.task02.cinema.core.FreeSeatsSearcher;
import com.example.repo.task02.cinema.core.Seat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class App {

    public static void main(final String[] args) {
        // ровный и красивый вывод зала на экран только для значений <= 10, на больше - лень
        final Cinema cinema = new Cinema(10);

        // * - свободное место, x - занятое место
        System.out.println(cinema);

        System.out.println("Сколько вас человек?");

        final int guestNumber;
        try (final BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            final String line = input.readLine();
            guestNumber = Integer.parseInt(line);
        } catch (final IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }

        if (guestNumber < 1) {
            throw new RuntimeException("Сам с собой поговорил, бывает");
        }

        final FreeSeatsSearcher seatsSearcher = new FreeSeatsSearcher();
        final List<List<Seat>> availableSeats = seatsSearcher.searchForFreeSeats(cinema, guestNumber);

        if (availableSeats.isEmpty()) {
            System.out.println("К сожалению, свободных мест нет");
        } else {
            printFreeSeats(availableSeats);
        }
    }

    private static void printFreeSeats(final List<List<Seat>> availableSeats) {
        System.out.println("Доступные места:");
        final StringBuilder builder = new StringBuilder();
        availableSeats.forEach(seats -> {
            builder.append("[");
            for (final Seat seat : seats) {
                builder.append(String.format("%s,%s; ", seat.getX(), seat.getY()));
            }
            // удалить последние пробел и ; в конце строки
            builder.deleteCharAt(builder.length() - 1).deleteCharAt(builder.length() - 1);
            builder.append("]").append(System.lineSeparator());
        });

        System.out.println(builder);
    }
}
