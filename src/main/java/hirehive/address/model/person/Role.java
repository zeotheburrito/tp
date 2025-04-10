package hirehive.address.model.person;

import static java.util.Objects.requireNonNull;

import hirehive.address.commons.util.AppUtil;

/**
 * Represents a Person's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {
    public static final String MESSAGE_CONSTRAINTS =
            "Roles should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String fullRole;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        AppUtil.checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        fullRole = role;
    }

    /**
     * Returns true if a given string is a valid role.
     *
     *  @param test The string to test.
     *  @return true if the string matches the role name format; false otherwise.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullRole;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Role)) {
            return false;
        }

        Role otherRole = (Role) other;
        return fullRole.equals(otherRole.fullRole);
    }

    @Override
    public int hashCode() {
        return fullRole.hashCode();
    }
}
