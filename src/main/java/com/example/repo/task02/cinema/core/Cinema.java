package com.example.repo.task02.cinema.core;

import java.util.Arrays;
import java.util.Random;

public class Cinema {
    // для простоты кинотеатр будет квадратным, size - длина и ширина
    private final int size;
    private final Seat[][] seats;

    public Cinema(final int size) {
        this.size = size;
        this.seats = new Seat[size][];
        randomlyFillSeats();
    }

    public long countFreeSeats() {
        return Arrays.stream(seats)
                .flatMap(Arrays::stream)
                .filter(Seat::isFree)
                .count();
    }

    private void randomlyFillSeats() {
        final Random random = new Random(17);

        for (int i = 0; i < seats.length; i++) {
            seats[i] = new Seat[size];
            for (int j = 0; j < seats[i].length; j++) {
                final int randInt = random.nextInt(2);
                // если случайное число кратно двум, место инициализируется свободным, иначе - занятым
                if (randInt % 2 == 0) {
                    seats[i][j] = new Seat(true, i, j);
                } else {
                    seats[i][j] = new Seat(false, i, j);
                }
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("  ");
        for (int i = 0; i < size; i++) {
            builder.append(i).append(" ");
        }
        builder.append(System.lineSeparator());
        for (int i = 0; i < seats.length; i++) {
            builder.append(i).append(" ");
            for (int j = 0; j < seats[i].length; j++) {
                builder.append(seats[i][j]).append(" ");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    public int getSize() {
        return size;
    }

    public Seat[][] getSeats() {
        return seats;
    }
}
