# Developer Guide

## Acknowledgements

1. Reference to AB-3 Developer Guide

- [Source](https://se-education.org/addressbook-level3/DeveloperGuide.html#proposed-undoredo-feature)
- Used as template to structure this Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

---

### Ui component

The `Ui` class handles message output for the user within the terminal.

The `UI`component,
- stores message output strings for user feedback
- holds methods for printing messages to be called by other classes, such as `InputParsing`.

#### Design considerations

Being a CLI application, UI/UX is minimal and user IO is confined within the terminal.

Hence, public methods within the`Ui` class are static for easy access by other classes, without the need to instantiate an instance of the `Ui` class to call.

### InputParsing component

This component ensures that the user parses in commands in a format that makes sense, which will modify the master list.

![InputParsingUML](./diagrams/src/InputParsing/InputParsing.png)

#### Design considerations
- The parser must be effecive in breaking down the user's input into its constitutent commands, with further breakdown if an associated argument is added.
- The parser should be quick and effective in understanding the user's input, with simple prompts given to help the user in correctly parsing the command through the input parser.
- Allowing the user to input optional arguments. For example, the user could type `view <student name>`, which takes in the "student name" as an optional argument. This is to increase the robustness of the program, which accounts for the two types of users, one who just types in `view`, and the other as formerly mentioned above. 
- Error handling must be intuitive for the user, so that appropriate error messages are produced if the user does not input a valid command. The error handling should also be robust, to account for the event a user is incapable in following instructions.

#### Implementation and rationale

![InputParsingSequenceDigram](./diagrams/src/InputParsing/InputParsingSequenceDiagram.png)

The InputParsing class is designed to handle the considerations above by breaking down the input given by the user in a well structured process. Below is how the InputParsing class works:

1. Splitting User Input: The Parser would take on the user's input and split it into 2 parts, which is the command and the argument, if any. this is done by the `UserInput` Class
   
2. Command recognition: Depending on the command and the argument given, if any, the parser would execute the command as per defined by the program. If the command and/or argument given is invalid/undefined, the input parser would generate a message to the user, informing them of the command's invalidity. This is done by the `InputParsing` Class.

3. Argument extraction: Depending on the specific command, if an argument can be parsed into that command, the parser would understand the argument and execute the sub-command specified by the user. Should the argument be invalid, the user would be informed of the argument's invalidity. This is also done by the `InputParsing` Class.

Through structuring the InputParsing class in such a manner, the application ensures that the user input is correctly parsed into executable commands for the subsequent phases of the application.

The InputParsing class uses the method below to achieve its functionality:

- `public static void parseUserCommand(String[] userCommand, ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList, Scanner in)`
  
This method takes in the user's input as the userCommand, the master student list, a list of the recently deleted students and the next input as parameters, which then proceeds with executing the command as associated with the user's input.

Given below is an example of how a user may add a student to the data base. 

- Suppose the user types in the command `add wario`. The parser first splits the input into two, checking for the command and the argument.
- It then recognises the `add` command, which then further checks if there is an argument given.
- Since the `add` command only allows the user to add a student to the list if there were no duplicate names present before, it will run the method `findStudentByName(ArrayList<Student> masterStudentList, String name)`, to check if the name was already present in the masterlist.
- Assuming the name "wario" did not exist in the masterlist beforehand, the parser would grant the user's request to add the student to the masterlist.
- This `add wario` command is equivalent to having the user first type `add`, then after waiting fo the program to respond, type `wario`. In the latter scenario, the input parser deems that an argument was not present, and thus will ask the user to give the name of the student that they would like to add.

All the while these commands are being processed, the runtime database `masterStudentList` is being updated, and subsequently being written to the stored database under the `data/studentInfo/Student_information.txt` file.


### Student Details

To facilitate the management of students within a tuition centre, it is imperative to have easy access and storage of important information regarding said personnel.

Seen below is an UML diagram of the relevant classes dealing with storing a student's details within the program runtime.

This text file would have the details of the student's name and their attributes, in the following order: subject, grade, classes taken for that subject, phone number, last paid date and remarks.

In the following component, the guide would go into detail about the implementation for writing and reading to the text file.

![DataStoringUML](./diagrams/src/DataStoring/DateStoring.png)

#### Design considerations

When building our implementation, we wanted our program to be able to easily access a student's details from a student object, and vice versa. 

#### Implementation and rationale

A `Student` is made up of a list of the subjects they are taking, along with relevant information one might need while managing their schedules and clienthood (maybe not gender, but who knows). Every `Student` object is stored in a public `static StudentList` that any class can call.

Storage of these details are handled via the `StudentsAttributes` class when the user package calls upon its methods. To ensure easy access of information from either a `Student` object, and be able to retrieve the `Student` parent when having access to an `StudentAttributes` object, we linked both `Student` and `StudentAttribute`.

Since both classes are referencing each other, it was easy for us to link it with the input parsing system, where we would just need a student's name, and maybe their number if there are multiple students with the same name, to be able to edit their details. 

Please see the diagram below to see how identifying a student works.

![studentIdentification](./diagrams/src/DataStoring/Student%20Identification.png)

We created a parent `Details` class as those are information not specifically related to students. It can thus be repurposed in future updates should we wish to expand this application into a personnel management system, which would include employees of the tuition centre.

However, our current implementation is not very secure as one can access every field of a `Student` object just by having access to it or the `StudentAttribute` object, which can be done via accessing the `static masterStudentList` variable.In future updates, we could possibly implement a Facade Pattern to better hide sensitive details.

### Data Commands Component

This section refers to `DataHandler.java`, `DataReader.java`, and `DataStorage.java` classes.

We have currently implemented a basic data handler which has the abilities to store a student's name into a text file.

This text file is created locally on the users' computer for easy access and retrieval.

Currently there is a polling system set in place where every change in the list of students (eg, addition, deletion, modification) would override the current working text file on the users' computer.

As stated above, all the names and attributes associated with each student will be saved to the main text file, named Student_Information.txt.

If the user chooses to delete or archive a student, it would be saved to an archive file, named student_archive.txt. If the user chooses to unarchive a student or undo a recent deletion, it will be brought back to the main text file.

The two text files are will be created under a directory called data, in which two separate file paths will be created if it is not already found on the user's desktop. 

![DataStoringObjectDiagramUML](./diagrams/src/DataStoring/ObjectDiagram.png)

#### Design Considerations

In order to ensure the proper usage of OOP principles (such as encapsulation), we have segregated the 3 classes to read,store and handle the data.

Moreover, the regex used to separate the different types of data in the file type have been chosen in a way which would not affect normal user operation needs. If the user chooses to use the banned characters, the input parser would replace them, as shown below:

Banned characters: 
`#`,`~` and `-`

- `#` changes to ` `.
- `~` changes to ` `.
- `-` changes to `_`.

This will prevent the user from messing with the way the program reads and writes to the text files, preventing corruption of the database due to improper usage.

#### Implementation and Rationale

##### DataHandler
Contains the methods to read and write student information which are used in other functions such as InputParser and Main.

##### DataReader
Contains the restore student list method which is used to restore the previously saved information about the students which are part of the tuition centre.

##### DataStorage
Contains the relevant methods to make the required file directories for storing the text file. 
It checks for existing folders. If not found, it makes new folders to store text files properly. 

### AddStudent Component

The `AddStudent` class is responsible for adding a new student to the list of students. It ensures that no two students with the same name are added and checks if the attributes added are in the correct format.

#### Design Considerations

- **Handling Duplicate Names**: The class checks if a student with the same name 
already exists in the master list before adding a new student.
- **Input Validation**: It validates the format of input attributes such as 
the name, phone number, gender, last payment date, and remarks.
- **Modular Approach**: The class is designed with modularity in mind,
allowing easy addition of new attributes or modifications in the future.

#### Implementation and Rationale

- **Name Validation**: The `checkForEmptyName` method ensures that the name provided 
by the user is not empty and does not already exist in the master student list.
- **Attribute Validation**: Various methods such as 
`promptForPhoneNumber`, `promptForGender`, `promptForLastPaymentDate`, and `promptForRemarks` 
validate the format of different attributes.
- **Adding Subjects**: The `addSubject` method allows users to add multiple subjects 
along with their grades and classes attended for each student.
- **Error Handling**: The class provides appropriate error messages if invalid input 
or duplicate names are encountered.

The `AddStudent` class contributes to the overall functionality of the application by providing a streamlined process for adding new students and ensuring data integrity within the student database.

### StudentSorter Component

The `StudentSorter` class facilitates sorting of the list of students based on various criterion.
As of the latest iteration, it sorts the master list of students in the order specified by the user.

![StudentSorterUML](./diagrams/src/StudentSorter/StudentSorter.png) 

#### Design Considerations

- Support for sorting by name, total classes attended, and last payment date.
- Input validation to ensure only valid sorting choices are accepted.
- Clear user feedback on sorting choices and results.

#### Implementation and Rationale

- **Sorting Criteria**: Supports sorting by name, total classes attended, and last payment date.
- **Input Validation**: Validates user input to ensure only valid sorting choices are accepted.
- **Switch Case Structure**: Utilizes switch-case structure for efficient sorting based on user choice.
- **Reusable Comparators**: Utilizes reusable comparators for sorting students by name, 
total classes attended, and last payment date.
- **Clear Output**: Provides clear output of sorted student lists with relevant information.
- **Error Handling**: Provides error message for invalid sorting choices.

The `StudentSorter` class contributes to the overall functionality of the application by 
providing a mechanism to organize and present student information based on user preferences.

---

## Product scope

### Target user profile

We aim to target private tuition centres with our product, specifically smaller ones without a good system in place to track the progress of their students.

### Value proposition

Classify serves as an attempt to modernise administrative tasks in education institutes, such as tuition centres or school environments.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|existing user|add a student to the database|utilise the functions of the program on a new student.
|v1.0|existing user|be able to easily update personal information for specific students such as contact details and emergency contacts|contact them or their parents easily.
|v1.0|existing user|search for students by name in the system|readily pull up data when asked.|
|v2.0|existing user|store my data in a file|transfer data between computers.|
|v2.0|existing user|sort the students by different attributes|compare them at a glance.|

## Non-Functional Requirements

1. **Performance**:
   - The system should respond to user actions within 1 second under normal operating conditions.
   - It should be able to handle a minimum of 100 concurrent users without significant degradation in performance.
   - The system should encompass all basic data types that an administrator would need to know about a student to register them for classes in the tuition centre.

2. **Reliability**:
   - In the event the program crashes, the data that was last updated into the storage should not be destroyed.

3. **Scalability**:
   - The system should be designed to accommodate future growth in the number of students and users.

4. **Usability**:
   - The user interface should be intuitive and easy to navigate, requiring minimal training for users to become proficient.
   - Error messages should be clear and informative, guiding users on how to resolve issues.

5. **Compatibility**:
   - It should be platform-independent, allowing users to access it from different operating systems.

6. **Maintainability**:
   - The codebase should be well-documented and follow coding standards to facilitate future maintenance and enhancements.
   - Logging and monitoring mechanisms should be in place to track system usage and identify potential issues.

7. **Data Management**:
   - File saving should done whenever a change has been made to the Master List.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

### Adding a student to the student list
1. Adding a student with a name and phone number only
   1. Prerequisites: View if a student with the name 'joe' exists by using the `view joe` command.
   2. Test case: `add joe` and when prompted for phone number enter `11111111`, while pressing enter to skip other optional fields.
   <br /> 
   Expected: `view joe` now shows the Student details of a student with Name: joe, Phone Number: 11111111. Other fields that were left blank will reflect 'Unknown' or for date fields, today's date.
   3. Test case: `add` and when prompted for Name, `joe`. `11111111` when prompted for phone number, press enter to skip other fields.
   <br />
   Expected: `view joe` shows the same results as when a student was added via `add joe`.
### Viewing a student's details
1. View a student who has been added to the student list
   1. Prerequisites: Add one student named 'joe' to the list with the `add` command
   2. Test case: `view joe`
   <br />
   Expected: Student's details shown correspond to the details input when `add` was used to add a student.
   3. Test case: `view dogman`
   <br />
   Expected: No details are displayed, an error message stating 'Student not found!' is shown.