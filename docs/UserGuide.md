---
layout: page
title: User Guide

---

HireHive is an **all-in-one desktop application** designed to streamline recruitment for small businesses and hiring managers, **combining the efficiency of a Command Line Interface (CLI) with the ease of a Graphical User Interface (GUI)**. It simplifies tracking candidates, organizing interview notes, and managing hiring stages—all in one centralized platform. With **fast keyboard-driven commands**, HireHive lets you complete tasks quicker than traditional GUI apps, so you can focus less on administrative work and more on finding the best talent.

- [Quick start](#quick-start-)
- [Command Summary](#command-summary)
- [Features](#features-)
  - [Viewing help: `help`](#viewing-help--help)
  - [Adding a person: `add`](#adding-a-person-add)
  - [Listing all persons: `list`](#listing-all-persons--list)
  - [Editing a person: `edit`](#editing-a-person--edit)
  - [Tagging a person: `tag`](#tagging-a-person--tag)
  - [Filter persons by tag: `filter`](#filter-persons-by-tag--filter)
  - [Locating persons by name `find`](#locating-persons-by-name-find)
  - [Deleting a person: `delete`](#deleting-a-person--delete)
  - [Clearing all entries: `clear`](#clearing-all-entries--clear)
  - [Exiting the program: `exit`](#exiting-the-program--exit)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Known Issues](#known-issues)

--------------------------------------------------------------------------------------------------------------------


## Quick start 
1. Ensure you have Java `17` or above installed in your Computer.<br>
   - **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103T-F13-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for HireHive.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hirehive.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui-2.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all applicants.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/intern`: Adds an applicant named `John Doe` to HireHive, with his relevant information

   * `delete n/John Doe` : Deletes the applicant `John Doe` from the current list.
   
  * `edit 3 n/Josef` : Edits the name of the 3rd applicant in the list to Josef

  * `find John Doe`: Searches for John Doe in the current list.

  * `tag n/John Doe t/interviewee` : Tags John Doe with the 'interviewee' tag

  * `clear` : Deletes all contacts.

  * `exit` : Exits the app.

1. Refer to the [Features](#features) section below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS r/ROLE [i/INFO]` <br> e.g., `add n/James Ho p/87654321 e/jamesho@example.com a/123, Clementi Rd, 1234665 r/software engineer intern i/26 years old`
**List** | `list`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Tag** | `tag n/NAME [t/TAG]…​`
**Filter** |
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Delete** | `delete n/NAME`<br> e.g., `delete n/John Doe`
**Clear** | `clear`
**Exit** | `exit`


--------------------------------------------------------------------------------------------------------------------

## Features 

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [i/INFO]` can be used as `n/John Doe i/27 years old` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/interviewee`, `t/shortlisted` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to HireHive.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS r/ROLE [i/INFO]`

**:information_source: Note!**<br>
* Phone numbers must **start with 9/8/6**, must not contain spaces and must be **exactly 8 digits** long.
* Dashes(-), commas(,), slashes(/) and periods (.) in names should be **omitted**.
    - Example: `Doe, John` or `Doe/John` should be entered as `Doe John`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An 'Applicant' tag is automatically assigned to each person when they are added to HireHive.
</div>

- Optional to provide INFO of a person. INFO has a maximum length of 500 characters.
- The parameter NAME is **unique** and **case insensitive**, and is displayed as how the user types it.
- Applicants in HireHive can share the same phone number, email, address and role.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/Software Engineer intern`
* `add n/Betsy Crowe e/betsycrowe@example.com a/Ang Mo Kio Street 22 p/87654321 r/senior consulting analyst i/32 years old`

![help message](images/Ui-AddCommand.png)

### Listing all persons : `list`

Shows a list of all applicants in HireHive.

Format: `list`

### Editing a person : `edit`

Edits an existing applicant in HireHive.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Tagging a person : `tag`

Changes or adds tags to the specified person from HireHive. 

Format: `tag n/NAME t/TAG`

* Tags a person if the given name partially matches **exactly one person** in the list (case-insensitive).
* If multiple matches are found, a list of all matching names will be displayed. The user must then enter the **full name** of the person they want to delete.

Examples:
* `tag n/John Doe t/Applicant` will tag John Doe as "Applicant" in HireHive

### Filter persons by tag : `filter`

Shows a list of all applicants with a certain tag in HireHive.

Format: `filter t/TAG`

* The search is case-insensitive. e.g `applicant` will match `Applicant`
* Only full words will be matched e.g. `Reject` will not match `Rejected`

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from HireHive.

Format: `delete n/NAME`

* Deletes the person if the given name partially matches **exactly one person** in the list (case-insensitive).
* If multiple matches are found, a list of all matching names will be displayed. The user must then enter the **full name** of the person they want to delete.

Examples:
* `delete n/John Doe` deletes John Doe's contact from HireHive

![delete message](images/Ui-DeleteCommand.png)

### Clearing all entries : `clear`

Clears all entries from HireHive.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HireHive data are saved in the hard disk automatically after any command that changes the data. If successfully saved, the output following the command will display the success message. There is no need to save manually.

### Editing the data file

HireHive data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HireHive will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause HireHive to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HireHive home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

