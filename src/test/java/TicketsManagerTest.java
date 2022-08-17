import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketsManagerTest {

    Ticket ticket1 = new Ticket(11, 100, "BAX", "DME", 111);
    Ticket ticket2 = new Ticket(22, 200, "VKO", "AER", 222);
    Ticket ticket3 = new Ticket(33, 300, "BAX", "AER", 333);
    Ticket ticket4 = new Ticket(44, 400, "SGC", "LED", 444);
    Ticket ticket5 = new Ticket(55, 5, "BAX", "DME", 555);
    Ticket ticket6 = new Ticket(66, 1, "BAX", "DME", 666);
    Ticket ticket7 = new Ticket(77, 700, "BAX", "DME", 1);


    TicketsRepository repo = new TicketsRepository();
    TicketsManager manager = new TicketsManager(repo);

    @Test
    public void shouldAddNothing() {

        Ticket[] expected = {};
        Ticket[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddOnlyOneTicket() {
        manager.add(ticket1);
        Ticket[] expected = {ticket1};
        Ticket[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsWithOnlyOneResult() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.searchByAirports("VKO", "AER");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsWithNoResults() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchByAirports("JAVA", "NETOLOGY");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSeveralResultsWithCorrectSort() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);

        Ticket[] expected = {ticket6, ticket5, ticket1, ticket7};
        Ticket[] actual = manager.searchByAirports("BAX", "DME");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatch() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        boolean expected = true;
        boolean actual = manager.matchesAirport(ticket1, "BAX", "DME");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotMatch() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        boolean expected = false;
        boolean actual = manager.matchesAirport(ticket5, "JAVA", "NETOLOGY");

        assertEquals(expected, actual);
    }

}

