package com.example.repo.task02.cinema.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FreeSeatsSearcher {

    // возвращает список доступных мест или пустой список, если таких не нашлось
    public List<List<Seat>> searchForFreeSeats(final Cinema cinema, final int guestNumber) {
        final int size = cinema.getSize();
        if (guestNumber > size ) {
            return Collections.emptyList();
        }

        if (guestNumber == 1) {
            return addEachFreeSeat(cinema);
        }

        return findAdjacentFreeSeats(cinema.getSeats(), guestNumber);
    }

    private List<List<Seat>> addEachFreeSeat(final Cinema cinema) {
        final List<List<Seat>> freeSeats = new ArrayList<>();
        final Seat[][] seats = cinema.getSeats();
        for (final Seat[] row : seats) {
            for (final Seat seat : row) {
                if (seat.isFree()) {
                    freeSeats.add(Collections.singletonList(seat));
                }
            }
        }

        return freeSeats;
    }

    private List<List<Seat>> findAdjacentFreeSeats(final Seat[][] seats, final int requiredAdjacentNumber) {
        final List<List<Seat>> foundFreeSeats = new ArrayList<>();

        // горизонтальный поиск
        for (final Seat[] seat : seats) {
            foundFreeSeats.addAll(searchInRow(seat, requiredAdjacentNumber));
        }

        // вращаем массив и проводим вертикальный поиск
        final Seat[][] rotated = rotateLeft(seats);
        for (final Seat[] seat : rotated) {
            foundFreeSeats.addAll(searchInRow(seat, requiredAdjacentNumber));
        }

        return foundFreeSeats;
    }

    private static Seat[][] rotateLeft(final Seat[][] a) {
        final Seat[][] ret = new Seat[a[0].length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                ret[j][i] = a[i][a.length - j - 1];
            }
        }
        return ret;
    }

    private List<List<Seat>> searchInRow(final Seat[] seats, final int requiredAdjacentNumber) {
        final List<List<Seat>> foundFreeSeats = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            if (areAdjacentSeatsFree(seats, requiredAdjacentNumber, i)) {
                final List<Seat> freeRow = Arrays.asList(seats).subList(i, i + requiredAdjacentNumber);
                foundFreeSeats.add(freeRow);
            }
        }

        return foundFreeSeats;
    }

    // рекурсивно проверяет текущее место и необходимые последующие
    private boolean areAdjacentSeatsFree(final Seat[] seats, final int requiredAdjacentNumber, final int indexToCheck) {
        if (requiredAdjacentNumber == 0) {
            return true;
        }

        // если мест в строчке/столбце не хватает уже физически, - сразу возвращаемся
        if (seats.length - indexToCheck < requiredAdjacentNumber) {
            return false;
        }

        return seats[indexToCheck].isFree()
                && areAdjacentSeatsFree(seats, requiredAdjacentNumber - 1, indexToCheck + 1);
    }
}
