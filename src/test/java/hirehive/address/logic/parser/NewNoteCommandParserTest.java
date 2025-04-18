package hirehive.address.logic.parser;

import org.junit.jupiter.api.Test;

import hirehive.address.logic.Messages;
import hirehive.address.logic.commands.EditCommand;
import hirehive.address.logic.commands.NewNoteCommand;
import hirehive.address.logic.commands.queries.NameQuery;
import hirehive.address.model.person.NameContainsKeywordsPredicate;
import hirehive.address.model.person.Note;
import hirehive.address.testutil.EditPersonDescriptorBuilder;
import hirehive.address.testutil.TypicalPersons;

public class NewNoteCommandParserTest {

    private NewNoteCommandParser parser = new NewNoteCommandParser();

    @Test
    public void parse_validArgs_returnsNewNoteCommand() {
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        editPersonDescriptor.setNote(new Note("test"));
        NewNoteCommand expectedCommand = new NewNoteCommand("Alice", editPersonDescriptor);

        CommandParserTestUtil.assertParseSuccess(parser, "newnote n/Alice i/test", expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "newnote Alice i/test",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, NewNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noName_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "newnote i/test",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, NewNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noNote_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "newnote n/Alice",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, NewNoteCommand.MESSAGE_USAGE));
    }
}
