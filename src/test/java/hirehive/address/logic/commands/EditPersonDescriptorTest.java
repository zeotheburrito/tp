package hirehive.address.logic.commands;

import static hirehive.address.logic.commands.CommandTestUtil.DESC_AMY;
import static hirehive.address.logic.commands.CommandTestUtil.DESC_BOB;
import static hirehive.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static hirehive.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static hirehive.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static hirehive.address.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static hirehive.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static hirehive.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static hirehive.address.logic.commands.CommandTestUtil.VALID_TAG_CANDIDATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hirehive.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditPersonDescriptor descriptorWithSameValues = new EditCommand.EditPersonDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditCommand.EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withRole(VALID_ROLE_BOB).build();

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTag(VALID_TAG_CANDIDATE).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different note -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withNote(VALID_NOTE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        String expected = EditCommand.EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", phone="
                + editPersonDescriptor.getPhone().orElse(null) + ", email="
                + editPersonDescriptor.getEmail().orElse(null) + ", address="
                + editPersonDescriptor.getAddress().orElse(null) + ", role="
                + editPersonDescriptor.getRole().orElse(null) + ", tag="
                + editPersonDescriptor.getTag().orElse(null) + ", note="
                + editPersonDescriptor.getNote().orElse(null) + ", interviewDate="
                + editPersonDescriptor.getDate().orElse(null) + "}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}
