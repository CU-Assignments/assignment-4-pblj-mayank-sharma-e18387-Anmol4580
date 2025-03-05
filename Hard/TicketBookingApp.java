import java.util.concurrent.locks.*;

class TicketBookingSystem {
    private boolean[] seats;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats];
    }

    public void bookSeat(String customerType, int seatNumber) {
        lock.lock();
        try {
            if (seatNumber < 1 || seatNumber > seats.length) {
                System.out.println("Error: Invalid seat number.");
                return;
            }
            if (!seats[seatNumber - 1]) {
                seats[seatNumber - 1] = true;
                System.out.println(customerType + " Booking: Seat " + seatNumber + " confirmed.");
            } else {
                System.out.println("Error: Seat " + seatNumber + " already booked.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private String customerType;
    private int seatNumber;

    public BookingThread(TicketBookingSystem system, String customerType, int seatNumber, int priority) {
        this.system = system;
        this.customerType = customerType;
        this.seatNumber = seatNumber;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(customerType, seatNumber);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10);

        BookingThread vip1 = new BookingThread(system, "VIP", 1, Thread.MAX_PRIORITY);
        BookingThread regular1 = new BookingThread(system, "Regular", 1, Thread.NORM_PRIORITY);
        BookingThread vip2 = new BookingThread(system, "VIP", 2, Thread.MAX_PRIORITY);
        BookingThread regular2 = new BookingThread(system, "Regular", 2, Thread.NORM_PRIORITY);

        vip1.start();
        regular1.start();
        vip2.start();
        regular2.start();
    }
}