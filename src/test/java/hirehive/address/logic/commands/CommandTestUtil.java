package hirehive.address.logic.commands;

import static hirehive.address.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hirehive.address.commons.core.index.Index;
import hirehive.address.logic.commands.exceptions.CommandException;
import hirehive.address.logic.parser.CliSyntax;
import hirehive.address.model.AddressBook;
import hirehive.address.model.Model;
import hirehive.address.model.person.NameContainsKeywordsPredicate;
import hirehive.address.model.person.Person;
import hirehive.address.testutil.Assert;
import hirehive.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "91224567";
    public static final String VALID_PHONE_BOB = "87664321";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ROLE_AMY = "Software engineer";
    public static final String VALID_ROLE_BOB = "Cybersecurity specialist";
    public static final String VALID_TAG_CANDIDATE = "candidate";
    public static final String VALID_TAG_APPLICANT = "applicant";
    public static final String DEFAULT_TAG_APPLICANT = "Applicant";
    public static final String VALID_NOTE_AMY = "30 years old";
    public static final String VALID_NOTE_BOB = "";
    public static final String VALID_DATE_BOB = "01/01/2025";

    public static final String NAME_DESC_AMY = " " + CliSyntax.PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + CliSyntax.PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + CliSyntax.PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + CliSyntax.PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ROLE_DESC_AMY = " " + CliSyntax.PREFIX_ROLE + VALID_ROLE_AMY;
    public static final String ROLE_DESC_BOB = " " + CliSyntax.PREFIX_ROLE + VALID_ROLE_BOB;
    public static final String TAG_DESC_APPLICANT = " " + CliSyntax.PREFIX_TAG + VALID_TAG_APPLICANT;
    public static final String TAG_DESC_CANDIDATE = " " + CliSyntax.PREFIX_TAG + VALID_TAG_CANDIDATE;
    public static final String NOTE_DESC_AMY = " " + CliSyntax.PREFIX_NOTE + VALID_NOTE_AMY;
    public static final String NOTE_DESC_BOB = " " + CliSyntax.PREFIX_NOTE + VALID_NOTE_BOB;

    public static final String DATE_DESC_BOB = " " + CliSyntax.PREFIX_DATE + VALID_DATE_BOB;

    public static final String INVALID_NAME_DESC = " " + CliSyntax.PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + CliSyntax.PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + CliSyntax.PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + CliSyntax.PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_ROLE_DESC = " " + CliSyntax.PREFIX_ROLE + "Developer&"; // '&' not allowed in names
    public static final String INVALID_TAG_DESC = " " + CliSyntax.PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_NOTE_DESC = " " + CliSyntax.PREFIX_NOTE + "Lorem ipsum dolor sit amet, "
            + "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque "
            + "penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, "
            + "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, "
            + "aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. "
            + "Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus"; // >500 chars not allowed for notes
    public static final String INVALID_DATE_DESC = " " + CliSyntax.PREFIX_DATE + "01012025";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withRole(VALID_ROLE_AMY).withTag(VALID_TAG_APPLICANT).withNote(VALID_NOTE_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withRole(VALID_ROLE_BOB).withTag(VALID_TAG_CANDIDATE)
                .withNote(VALID_NOTE_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String name = person.getName().fullName;
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(name));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
