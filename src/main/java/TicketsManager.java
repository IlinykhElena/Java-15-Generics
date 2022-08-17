import javax.xml.transform.Result;
import java.util.Arrays;

public class TicketsManager {

    private TicketsRepository repo;

    public TicketsManager(TicketsRepository repo) {
        this.repo = repo;
    }

    public void add(Ticket ticket) {
        repo.save(ticket);
    }

    public boolean matchesAirport(Ticket ticket, String from, String to) {
        if (ticket.getDepartureAirport().contains(from)) {
            return true;
        } if (ticket.getArrivalAirport().contains(to)) {
            return true;
        } else { return false;}
    }

    public Ticket[] searchByAirports(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if (matchesAirport(ticket, from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

}
