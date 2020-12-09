package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flight {
    @JsonProperty("departs")
    private Date Departs;
    @JsonProperty("tickets")
    private List<Ticket> tickets;
    @JsonIgnore
    private int id;
    @JsonIgnore
    private String destination;

    SimpleDateFormat desiredDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date getDeparts() {
        return Departs;
    }

    public void setDeparts(String dateTime) throws ParseException {
        Date date = desiredDateFormat.parse(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -6);
        Date newDate = calendar.getTime();
        this.Departs = newDate;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    static class Ticket {
        private Passenger passenger;
        private int price;

        public Passenger getPassenger() {
            return passenger;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    static class Passenger {
        private String firstName;
        private String lastName;


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }


}



