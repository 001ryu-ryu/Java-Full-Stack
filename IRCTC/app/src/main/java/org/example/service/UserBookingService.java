package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Ticket;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {
    private User user;

    private List<User> userList;

    //Object mapper for serialise and deserialize
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "C:\\Users\\iftik\\OneDrive\\Desktop\\Java Full Stack\\IRCTC\\app\\src\\main\\java\\org\\example\\localDB\\users.json";
    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUsers();
    }

    public UserBookingService() throws IOException {
        loadUsers();
    }

    //Load user
    public List<User> loadUsers() throws IOException {
        File users = new File(USERS_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }


    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user -> {
            return user.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user.getHashedPassword());
        }).findFirst();

        return foundUser.isPresent();
    }

    public Boolean signUp(User user) {
        try {
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    //json --> Object (User) -> Deserialize
    //Object (User) --> json -> Serialise

    public void fetchBooking() {
        user.printTickets();
    }

    //  get trains
    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrain(source, destination);
        } catch (IOException exception) {
            System.out.println("No valid trains found");
            return new ArrayList<>();
        }
    }

    //fetch seats
    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }

    //Method for booking train seats

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; //booking successful
                } else {
                    return false; //already booked
                }
            } else {
                return false; //invalid seat or index
            }
        } catch (IOException exception) {
            return Boolean.FALSE;
        }
    }

    public Boolean cancelBooking(String ticketId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ticked Id to cancel");
        ticketId = scanner.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticked Id cannot be null of empty!");
            return Boolean.FALSE;
        }

        String finalTickedId1 = ticketId;
        boolean removed = user.getTicketBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTickedId1));

        String finalTickedId = ticketId;
        user.getTicketBooked().removeIf(Ticket -> Ticket.getTicketId().equals(finalTickedId));
        if (removed) {
            System.out.println("Ticked Id with " + ticketId + " is canceled");
            return Boolean.TRUE;
        } else {
            System.out.println("No ticked found of Id " + ticketId);
            return Boolean.FALSE;
        }
    }
}
