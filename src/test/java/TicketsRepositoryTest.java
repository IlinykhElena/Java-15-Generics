import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketsRepositoryTest {

    Ticket ticket1 = new Ticket(11, 100, "BAX", "DME", 111);
    Ticket ticket2 = new Ticket(22, 200, "VKO", "AER", 222);
    Ticket ticket3 = new Ticket(33, 300, "BAX", "AER", 333);
    Ticket ticket4 = new Ticket(44, 400, "SGC", "LED", 444);
    Ticket ticket5 = new Ticket(55, 5, "BAX", "DME", 555);
    //Ticket ticket6 = new Ticket(11, 100, "BAX", "DME", 111);

    @Test
    public void shouldSaveAllTickets() {
        TicketsRepository repo = new TicketsRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldSaveNothing() {
        TicketsRepository repo = new TicketsRepository();
        Ticket[] expected = {};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSaveIfIdAlreadyExists() {
        TicketsRepository repo = new TicketsRepository();
        repo.save(ticket1);
        repo.save(ticket2);

        Assertions.assertThrows(RuntimeException.class, () -> {
            repo.save(ticket1);
        });
    }

    @Test
    public void shouldRemoveOneTicketIfIdCorrect() {
        TicketsRepository repo = new TicketsRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
        repo.removeById(33);

        Ticket[] expected = {ticket1, ticket2, ticket4, ticket5};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveSeveralTicketsIfIdCorrect() {
        TicketsRepository repo = new TicketsRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
        repo.removeById(ticket1.getId());
        repo.removeById(ticket4.getId());

        Ticket[] expected = {ticket2, ticket3, ticket5};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveNothingIfIdNotCorrect() {
        TicketsRepository repo = new TicketsRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);

        Assertions.assertThrows(RuntimeException.class, () -> {
            repo.removeById(999);
        });
    }

    @Test
    public void shouldRemoveNothingIfIdNegative() {
        TicketsRepository repo = new TicketsRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);

        Assertions.assertThrows(RuntimeException.class, () -> {
            repo.removeById(-100);
        });
    }

}

