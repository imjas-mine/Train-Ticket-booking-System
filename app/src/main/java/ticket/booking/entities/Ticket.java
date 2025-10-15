package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class Ticket {
    @JsonProperty("ticket_id")
    private String ticketID;
    @JsonProperty("user_id")
    private String userID;
    @JsonProperty("date_of_travel")
    private Date dateOfTravel;
    private String source;
    private String destination;
    private Train train;

    public Ticket(String ticketID, String userID, Date dateOfTravel, String source, String destination, Train train) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.dateOfTravel = dateOfTravel;
        this.source = source;
        this.destination = destination;
        this.train = train;
    }

    public Ticket() {
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(Date dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getTicketInfo() {
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", ticketID, userID, source, destination, dateOfTravel);
    }
}
