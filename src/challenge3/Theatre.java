package challenge3;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Theatre {

    class Seat implements Comparable<Seat> {
        private String seatNum;
        private boolean isReserved;

        public Seat(char rowChar, int seatNum) {
            this.seatNum = "%c%03d".formatted(rowChar, seatNum).toUpperCase();
        }

        @Override
        public String toString() {
            return seatNum;
        }

        @Override
        public int compareTo(Seat o) {
            return seatNum.compareTo(o.seatNum);
        }
    }

    private String name;
    private int seatsInRow;
    private NavigableSet<Seat> seats;

    public Theatre(String name, int rows, int totalSeats) {
        this.name = name;
        this.seatsInRow = totalSeats / rows;

        seats = new TreeSet<>();

        for (int i = 0; i < totalSeats; i++) {
            char rowChar = (char) (i / seatsInRow + (int) 'A');
            int seatInRow = i % seatsInRow + 1;

            seats.add(new Seat(rowChar, seatInRow));
        }

    }

    public void printSeatMap() {
        System.out.println("--------------");
        System.out.println(name);
        for (Seat s : seats) {
            System.out.printf(s.seatNum);
            System.out.println(s.isReserved ? " reserved " : " not reserved ");
        }
        System.out.println("--------------");
    }

    public String reserveSeat(char row, int seat) {

        Seat requestedSeat = new Seat(row, seat);
        Seat requested = seats.floor(requestedSeat);

        if (requested == null || !requested.seatNum.equals(requestedSeat.seatNum)) {
            System.out.println("No such seat");
        } else {
            if (!requested.isReserved) {
                requested.isReserved = true;
                return requested.seatNum;
            } else {
                System.out.println("Seat is already reserved");
            }
        }
        return null;
    }

    private boolean validate(int count, char first, char last, int min, int max) {
        boolean result = (min > 0 || seatsInRow >= count || (max - min + 1) >= count);
        result = result && seats.contains(new Seat(first, min));
        if (!result) {
            System.out.println("Invalid!");
        }
        return result;
    }

    public Set<Seat> reserveSeats(int count, char minRow, char maxRow, int minSeat, int maxSeat) {
        char lastValid = seats.last().seatNum.charAt(0);
        maxRow = (maxRow < lastValid) ? maxRow : lastValid;

        if (!validate(count, minRow, maxRow, minSeat, maxSeat)) {
            return null;
        }

        NavigableSet<Seat> selected = null;

        for (char letter = minRow; letter <= maxRow; letter++) {
            NavigableSet<Seat> contiguos = seats.subSet(
                    new Seat(letter, minSeat), true,
                    new Seat(letter, maxSeat), true);

            int index = 0;
            Seat first = null;
            for (Seat current : contiguos) {
                if (current.isReserved) {
                    index = 0;
                    continue;
                }
                first = (index == 0) ? current : first;
                if (++index == count) {
                    selected = contiguos.subSet(first, true, current, true);
                    break;
                }
            }
            if (selected != null) {
                break;
            }

        }
        Set<Seat> reservedSeats = null;
        if (selected != null) {
            selected.forEach(s -> s.isReserved = true);
            reservedSeats = new TreeSet<>(selected);
        }
        return reservedSeats;
    }

}
