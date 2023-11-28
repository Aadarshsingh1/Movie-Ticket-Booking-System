/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.*;
//Movie id
//Title and 
//description

class Movie {
    private int id;
    private String title;
    private String description;
   // set movie id Title and description from input.
    public Movie(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    //return movie id which set from user
    public int getId() {
        return id;
    }
    // return Title of movie whch set by the user

    public String getTitle() {
        return title;
    }
    // return movie description which set by the user
    public String getDescription() {
        return description;
    }
}
// In  Theater set Id name and use mappingfor availbel seat and show time 
class Theater {
    private int id;
    private String name;
    private Map<Integer, List<Boolean>> seatMap; 
    private Map<Integer, String> showtimes;
    // Set Theater id and Theater Name

    public Theater(int id, String name) {
        this.id = id;
        this.name = name;
        this.seatMap = new HashMap<>();
        this.showtimes = new HashMap<>();
    }
   ??show time Using Mapping 
    public void addShowtime(int showtimeId, String showtimeDetails, int seatCount) {
        List<Boolean> seats = new ArrayList<>(Collections.nCopies(seatCount, true));
        seatMap.put(showtimeId, seats);
        showtimes.put(showtimeId, showtimeDetails);
    }
  // for return show Time 
    public Map<Integer, String> getShowtimes() {
        return showtimes;
    }
    // rerturn Availbel Seat 

    public List<Boolean> getAvailableSeats(int showtimeId) {
        return seatMap.getOrDefault(showtimeId, new ArrayList<>());
    }
}
// Booking System By userid Movie id And ShowTime and Selected Seat

class Booking {
    private int userId;
    private int movieId;
    private int showtimeId;
    private List<Integer> selectedSeats;
    // Booking Movie or set  userId ,MovieId ,ShowTime ,SelectedSeat 
    public Booking(int userId, int movieId, int showtimeId, List<Integer> selectedSeats) {
        this.userId = userId;
        this.movieId = movieId;
        this.showtimeId = showtimeId;
        this.selectedSeats = selectedSeats;
    }
    //Make a Booking By using mapping 
    public boolean makeBooking(Theater theater) {
        List<Boolean> availableSeats = theater.getAvailableSeats(showtimeId);
        if (availableSeats != null) {
            for (int seat : selectedSeats) {
                if (seat > 0 && seat <= availableSeats.size() && availableSeats.get(seat - 1)) {
                    availableSeats.set(seat - 1, false);
                } else {
                    // if seat is not availbel in theater return false value 
                    return false; 
                }
            }
            //if seat is availbel in theater Which mean  Booking Sucessfull  return True
            return true; 
        }
        // return Invalid ShowTime
        return false; 
    }
    // Canel Booking System 

    public void cancelBooking(Theater theater) {
        List<Boolean> availableSeats = theater.getAvailableSeats(showtimeId);
        if (availableSeats != null) {
            for (int seat : selectedSeats) {
                if (seat > 0 && seat <= availableSeats.size()) {
                    availableSeats.set(seat - 1, true);
                }
            }
        }
    }
}
/// Class for movie Ticket in Booking Syatem

public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        // Creating a sample movie, theater, and showtime in theater
        Movie movie = new Movie(1, "Movie Title", "Movie Description");
        Theater theater = new Theater(1, "Theater Name");
        theater.addShowtime(1, "9:00 AM", 50); // Showtime ID, Showtime details, Seat count

        // Simulating user actions
        List<Integer> selectedSeats = new ArrayList<>(Arrays.asList(5, 6, 7));
        Booking booking = new Booking(1, movie.getId(), 1, selectedSeats);
        boolean isBookingSuccessful = booking.makeBooking(theater);

        if (isBookingSuccessful) {
            System.out.println("Booking successful!");
            System.out.println("Showtime: " + theater.getShowtimes().get(1));
            System.out.println("Selected Seats: " + selectedSeats);
        } else {
            System.out.println("Booking failed. Please try again.");
        }

        // Simulating ticket cancellation
        booking.cancelBooking(theater);
        System.out.println("Booking cancelled. Seats available again: " + theater.getAvailableSeats(1));
        System.out.println();
    }
}
