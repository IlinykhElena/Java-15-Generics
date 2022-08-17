import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Ticket implements Comparable<Ticket> {
    protected int id;
    protected int price;
    protected String departureAirport;
    protected String arrivalAirport;
    protected int travelTime;

    public int getId() {
        return id;
    }

    public Ticket(int id, int price, String departureAirport, String arrivalAirport, int travelTime) {
        this.id = id;
        this.price = price;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.travelTime = travelTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && price == ticket.price && travelTime == ticket.travelTime && Objects.equals(departureAirport, ticket.departureAirport) && Objects.equals(arrivalAirport, ticket.arrivalAirport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, departureAirport, arrivalAirport, travelTime);
    }

    @Override
    public int compareTo(@NotNull Ticket o) {
        if (price < o.getPrice()) {
            return -1;
        }
        if (price > o.getPrice()) {
            return 1;
        } else {
            return 0;
        }
    }
}
