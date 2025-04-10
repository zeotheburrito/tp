package hirehive.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hirehive.address.logic.Messages;
import hirehive.address.testutil.PersonBuilder;

public class UpcomingInterviewPredicateTest {
    public static final String TEST_CURRENT_DAY = "01/01/2025";

    @Test
    public void equals() {
        int firstPredicateDays = 5;
        int secondPredicateDays = 10;

        UpcomingInterviewPredicate firstPredicate = new UpcomingInterviewPredicate(firstPredicateDays, TEST_CURRENT_DAY);
        UpcomingInterviewPredicate secondPredicate = new UpcomingInterviewPredicate(secondPredicateDays, TEST_CURRENT_DAY);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        UpcomingInterviewPredicate firstPredicateCopy = new UpcomingInterviewPredicate(firstPredicateDays, TEST_CURRENT_DAY);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_dateWithinDays_returnsTrue() {
        // Within range
        UpcomingInterviewPredicate predicate = new UpcomingInterviewPredicate(5, TEST_CURRENT_DAY);
        assertTrue(predicate.test(new PersonBuilder().withDate("03/01/2025").build()));

        // Boundary date
        predicate = new UpcomingInterviewPredicate(5, TEST_CURRENT_DAY);
        assertTrue(predicate.test(new PersonBuilder().withDate("06/01/2025").build()));

        // Same date
        predicate = new UpcomingInterviewPredicate(5, TEST_CURRENT_DAY);
        assertTrue(predicate.test(new PersonBuilder().withDate(TEST_CURRENT_DAY).build()));
    }

    @Test
    public void test_dateNotWithinDays_returnsTrue() {
        // Within range
        UpcomingInterviewPredicate predicate = new UpcomingInterviewPredicate(5, TEST_CURRENT_DAY);
        assertFalse(predicate.test(new PersonBuilder().withDate("15/01/2025").build()));

        // Boundary date
        predicate = new UpcomingInterviewPredicate(5, TEST_CURRENT_DAY);
        assertFalse(predicate.test(new PersonBuilder().withDate("07/01/2025").build()));

        // Date is within range before the current date
        predicate = new UpcomingInterviewPredicate(5, TEST_CURRENT_DAY);
        assertFalse(predicate.test(new PersonBuilder().withDate("31/12/2024").build()));

        // No interview date
        predicate = new UpcomingInterviewPredicate(5, TEST_CURRENT_DAY);
        assertFalse(predicate.test(new PersonBuilder().build()));

        // Date is before the current date
        predicate = new UpcomingInterviewPredicate(5, TEST_CURRENT_DAY);
        assertFalse(predicate.test(new PersonBuilder().withDate("01/01/2024").build()));
    }

    @Test
    public void getSuccessString() {
        int days = 5;
        UpcomingInterviewPredicate predicate = new UpcomingInterviewPredicate(5);
        String expected = String.format(Messages.MESSAGE_FILTER_OVERVIEW_DATE, days);
        assertEquals(expected, predicate.getSuccessString());
    }
}
