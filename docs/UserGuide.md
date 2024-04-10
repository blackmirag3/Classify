# User Guide for **Classify**

## Preface
With the increased competitiveness of cohorts in Singapore, tuition centres have become more prevalent than ever before. 

According to a **[newspaper article](https://www.straitstimes.com/singapore/education/families-spent-14b-on-private-tuition-for-kids-last-year-as-parents-fork-out)** in The Straits Times, billions of dollars are spent in the tuition industry in Singapore every single year. 

These are just the numbers in Singapore. If we go to other countries, the situation is much worse. In India, new tuition centres preparing students for competitive entrance exams like JEE and NEET spring up every single day.

In such a fast-growing market, it is important that these tuition centres use better and more efficient student management systems to increase efficiency and automation of manual laborious tasks.

These systems enable tutors and administrative staff to focus on more pressing issues and work on catering to every students' needs instead of being swamped with paperwork. 

## Introduction

Classify is a student management system meant to assist administrative staff of private tuition centres. 

The product is capable of storing students, and generating outputs based on provided parameters, such as grade improvements and payment statuses.

Within our program, a student can be stored with their 
- Subjects taken
- Number grades of those subjects
- Phone Number
- Gender
- Last Payment Date
- Remarks

We have determined these to be the attributes important to running a working tuition centre. Based on these attributes, our program also has functions to sort students based on grades, subjects, improvements since joining the centre.

<div style="page-break-after: always;"></div>

## Quick Start

1. Ensure that you have __Java 11 or above__ installed.
2. Download the provided jar file into an empty folder.
3. Open a terminal and change the working directory to the folder with the jar file.
4. Run it from the terminal using the following command:
```
java -jar ./Classify.jar
```
5. Run the command ```help``` within the program to get a quick view of the available commands.

## Features 

### - Add a student 

Initialises the process of adding in a student. 

### - View details of a student

Prints out the details of a given student in the interface.

### - Edit details of a student

Initialises the process of editing both academic and non-academic details of a student.

### - Delete a student from the working list
Removes a student from the master list of students. 

### - Add a student to the archive
Removes a student from the master list of students and adds them to the archive.

### - Unarchive a student
Removes a student from the archive and adds them to the master list of students.

### - Produce a list of all the students who takes a subject

Displays a list of all the students with that associated subject taken in the tuition centre.
If user enters different letter cases from subjects stored, the system will still match strings regardless of letter case for user intuitiveness.

### - Produce a list of all students

Displays a list of all the students at the tuition centre with no other parameters shown.

### - Produce a list of all students with total classes attended

Displays a list of all the students with their total classes attended at the tuition centre.

### - Produce a list of students in the archive

Displays a list of all the students that have been moved to archive.

### - Produce a list of students that were recently deleted

Displays a list of all students who were recently deleted.

### - Produce a list of students with their phone numbers

Displays a list of all students with their phone numbers.

### - Sort the list lexicographically, from A to Z

Sorts the master list of students from A to Z. When the user types list, it will generate the updated sorted list.

### - Sort the list by total classes attended

Sorts the master  list of students from the lowest number of classes attended to highest. When the user types list, it will generate the updated sorted list.

### - Sort the list by latest fee payment date

Sorts the master list of students from the oldest date to the most recent. When the user types list, it will generate the updated sorted list.

### - Undo the most recent delete action

Adds in the most recently deleted student back into the runtime database.

### - Restore a specific deleted student

Finds a specific deleted student and adds it back into the runtime database.

### - Display help message

Prints a message with instructions on how to use the features of Classify.

### - Exit Classify

Exits the program.


# Usage

**Please note that if any of the following characters are entered in any string prompts, `#, - and ~`, they will be replaced and your command may be deemed invalid.**

### Adding a student: `add`
Initialises an interface for adding a student and their relevant details to the database. Allows the user to do add or add [name]

Students of the same name can be added so long as they possess a different phone number. Students with the same phone number can be added so long as they have different names.

User is expected to add only student's name as registered in NRIC or passport. 

The first letter of the student's name will be capitalised, while the 2nd letter onwards will be lowercase, regardless of initial input.

However, if the name contains multiple parts, each corresponding first letter after every space will be capitalised as well.

Example:
- James wong bing xuan -> James Wong Bing Xuan
- james WONG BING XUAN -> James Wong Bing Xuan
- jaMeS WONG binG Xuan -> James Wong Bing Xuan

Format: `add` or `add NAME`

Depending on whether the user entered the student's name or not during the command, fields will be printed out in the terminal, awaiting a user input each time.

Please note that the user input NUMBER (shown below) must be an *8 digit number starting with 8 or 9*. 
Thus, please be informed and educated that our software only works in countries that have phone numbers that are 8 digits long and start with 8 or 9.
To be specific, this is referring to a city-state republic in South East Asia, that lies in the timezone GMT +8 in the Straits of Johor. 
Some may debate that our time zone is actually GMT+7.5 or GMT+7, but it was changed to facilitate business deals and decisions with other financial hubs in the region thanks to our capitalistic overlords.

Example: 
``` 
add wario

Subject (enter nothing to skip): 
SUBJECT

Current marks out of 100 (blank to skip) : 
100

Enter Classes Attended (blank to skip):
1

Do you want to add another subject and grade? (yes/no) 
no

Please input a valid Phone number: 
NUMBER
.
.
```

### - View details of a student `view`
Views a student's details. Allows the user to do view [name] or just view.

Format: `view` or `view NAME`

Depending on whether the user entered the student's name or not during the command, fields will be printed out in the terminal, awaiting a user input each time.
If user enters different letter cases from name stored data, the system will still match strings regardless of letter case for user intuitiveness.

How it works (cases for input):
- In the list: Stored as `James Wong`
- view James Wong -> James Wong
- view james wong -> james wong
- view JAMES WONG -> JAMES WONG
  * Regardless of what capitalisation the user uses for the name, the program will recognise that upper and lower cases should return the same student.

``` 
Example:
view wario

Student details: 
Name: wario
Phone Number: 97655678
Gender: male
Last Payment Date: 12/12/2023
Remarks: Unknown
Subject: subject
Current marks out of 100: 100.0
Classes Attended: 1
```

### - Edit details of a student `edit`
Edit a student's details and subjects. Allows the user to do edit [student] or just edit.

Format: `edit` or `edit NAME`

If user has not entered a name, they will be prompted to input a name.
If the name input matches that of an existing student, edit mode will be entered for that student.
A list of actions will be printed and user can enter the respective index to select action to take.
Each action has its own step by step prompts for user to follow.

```
Example:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Welcome to Classify!
What can I do for you today?
>> edit
Name of student to edit (blank to exit):
>> vincent
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Student details: 
Name: Vincent

Phone Number: 99999999
Gender: Unknown
Last Payment Date: 2024-04-08
Remarks: Unknown

No subjects and grades found for this student.
How would you like to edit student? Enter index (blank to exit):
1. Add subject
2. Modify subject
3. Delete subject
4. Modify phone number
6. Modify payment date
5. Modify remarks
>> 1
Subject (enter nothing to skip): PE
Current marks out of 100 (blank to skip) : 
>> 1
Enter Classes Attended (blank to skip): 
>> 10
Do you want to add another subject and grade? (yes/no)
>> no
What else can I do for you today?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### - Delete a student from the working list `delete`
Deletes the student from the list. Allows the user to do delete [student] or just delete.

Format: `delete` or `delete NAME`

Depending on whether the user entered the student's name or not during the command, 
a field will be printed out in the terminal, awaiting a user input.

``` 
Example:
delete

Enter student name: 
wario
Student removed successfully!
```
### - Restore a student to the working list `restore`
Restores a student deleted in the current session. Allows the user to do restore [student] or just restore.

Format: `restore` or `restore NAME`

Depending on whether the user entered the student's name or not during the command, 
a field will be printed out in the terminal, awaiting a user input.

```
Example:
restore
Enter student name: 
mario
Student has been restored!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### - Undo a delete and restore the deleted student to the working list `undo`
Undoes the latest delete. Used by typing `undo`

Format: `undo`

```
Example:
undo
Last delete undone!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### - Display the list of students `list`
Displays the list of all students, or students with a specified subject. 
The user can further specify if they would like to display the full student list, 
the list with the total classes attended, as well as the list of students in the archive or in the recently deleted list.

Format: `list`

Allows the user to specify a subject, then displays all students with that subject. 
If the field is left blank, students will be displayed regardless of the subjects they have.
The user can then select by index, from 4 additional options to either display all students in the master list,
all students in the master list as well as the total number of classes they have attended.
Alternatively, the user can choose to display either the students in the recently deleted list or the archive.

```
Example:
list
Enter subject name (leave blank for all students):
math
Students with the subject "math":
- Toad
- Wario
- Mario
- Luigi
- Moyai
- Peach
- Daisy
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

list
Enter subject name (leave blank for all students):

Choose the type of list to display: (index only)
1. Full student list
2. List of students with total classes attended
3. List of students with phone number shown
4. List of students in archive
5. List of students in recently deleted
Enter your choice (1, 2, 3, 4 or 5):
2
List of students with total classes attended:
1.Toad - Total Classes Attended: 13
2.Wario - Total Classes Attended: 14
3.Mario - Total Classes Attended: 15
4.Luigi - Total Classes Attended: 16
5.Moyai - Total Classes Attended: 17
6.Peach - Total Classes Attended: 18
7.Daisy - Total Classes Attended: 19
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

list
Enter subject name (leave blank for all students):

Choose the type of list to display: (index only)
1. Full student list
2. List of students with total classes attended
3. List of students with phone number shown
4. List of students in archive
5. List of students in recently deleted
Enter your choice (1, 2, 3, 4 or 5):
3
List of students with phone numbers:
1.Toad - Phone Number: 91234567
2.Wario - Phone Number: 97654321
3.Mario - Phone Number: 81234567
4.Luigi - Phone Number: 87654321
5.Moyai - Phone Number: 88776655
6.Peach - Phone Number: 99887766
7.Daisy - Phone Number: 88997766
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

list
Enter subject name (leave blank for all students):

Choose the type of list to display: (index only)
1. Full student list
2. List of students with total classes attended
3. List of students with phone number shown
4. List of students in archive
5. List of students in recently deleted
Enter your choice (1, 2, 3, 4 or 5):
5
List of students in recently deleted: 
1.Toad
2.Peach
3.Moyai
4.Mario
Currently, there are 4 students in the list.
```

### - Move a student from the master list to the archive `archive`
Adds a student to the archive and deletes them from the master list. Allows the user to do archive [name] or just archive.

Format: `archive`, `archive NAME`

Depending on whether the user entered the student's name or not during the command, 
a field will be printed out in the terminal, awaiting a user input.

```
Example:
archive wario
Archive List: 
Analysing Inputs...
Directory loaded successfully!
Data update success!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Runtime Database: 
Analysing Inputs...
Directory loaded successfully!
Data update success!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### - Move a student from the archive back to the master list `unarchive`
Removes a student from the archive and adds them back to the master list. Allows the user to do unarchive [name] or just unarchive.

Format: `unarchive`, `unarchive NAME`

Depending on whether the user entered the student's name or not during the command, 
a field will be printed out in the terminal, awaiting a user input.

```
Example:
unarchive wario
Archive List: 
Analysing Inputs...
Directory loaded successfully!
Data update success!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Runtime List: 
Analysing Inputs...
Directory loaded successfully!
Data update success!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### - Sort the list of students `sort`
Sorts the list of students based on the following input. Allows the user to do sort [type] or just sort.

Format: `sort`, `sort name`, `sort classes` or `sort payment`

Depending on whether the user entered the student's name or not during the command, 
fields will be printed out in the terminal, awaiting a user input each time.

```
Example:
sort
Sort by: (Choose index)
1. Name (A to Z)
2. Total number of classes attended:
3. Date of last fee payment: 
1
List of Students:
1. mario
2. toad
3. wario
Currently, there are 3 students in the list.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Sort complete!

sort payment
1.toad - Date of last payment: 2015-10-21
2.mario - Date of last payment: 2024-03-30
3.wario - Date of last payment: 2024-03-30
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Sort complete!
```

### - Display instructions on how to use Classify `help`
Prints instructions which detail how to use all the features of Classify.

Format: `help`

```
Example:
help
add                         Adds a student to the student list, expects a name, grade and lessons attended, can be used directly with a name e.g. add [name]
edit                        Edits a students details, expects a name, can be used directly with a name e.g. edit [name]
view                        Views a students details, expects a name, can be used directly with a name e.g. add [name]
delete                      Deletes a student from the student list, expects a name, can be used directly with a name e.g. add [name]
restore                     Restore a student deleted within the current session, expects a name, can be used directly by restore [name].
undo                        Restores the last student deleted in the current session.
list                        Displays the list of all students
                            Currently available types: Whole student list, with total classes attended, with phone number, the archived list, the recently deleted list or by certain subject only.
bye                         Exits Classify
sort                        Sorts the student list by the input parameter, expects an attribute to sort by, can be used directly by sort [type].
                            Currently available types: name, classes, payment
archive                     Removes the specified student from the list and archives them, can be used directly by archive [name].
unarchive                   Removes the specified student from the archive and adds them to the list, can be used directly by unarchive [name].
help                        Prints this help message
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### - Exit Classify `bye`
Exits the Classify program.

Format: `bye`
```
Example:
bye
Hope you've had a productive day. See you again! Goodbye!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Plug out the corresponding drive from the computer you would like to transfer from, and connect it to either the SATA or m.2 port of the computer you would like to transfer to. Please make sure to reinstall any existing drivers to ensure maximum compatibility.

**Q**: How do I transfer my data to another computer **without** removing any components?

**A**: In the jar directory (folder containing jar file), data is stored in the "data" folder (./data). You can import the contents within this data directory to that of the jar directory on the new computer.

**Q**: What inputs are not allowed?

**A**: The inputs that are banned (which are used for file saving) are `#` and `~`, and in all cases except for dates, `-`. 

**Q**: (Follow up from above) Why are other special characters not banned?

**A**: Due to the ever-changing climate of what is considered a name (and more), we have limited the bans to 3 special characters above only. In the event your name or other identification metrics contains `#`, `~` or `-`, we seek for your understanding.


## Command Summary

### `add <student>` 
Adds a student into the list. If the name of the student is not specified in the initial command, the program will prompt the user for the name.

### `list`, press enter, [subject] 
Displays the list of students that has taken that subject.

### `list`, press enter, press enter, [1]
Displays the list of students that is registered in the tuition centre.

### `list`, press enter, press enter, [2]
Displays the list of students that is registered in the tuition centre with the total number of classes taken.

### `list`, press enter, press enter, [3]
Displays the list of students that is registered in the tuition centre with their phone numbers beside their names

### `list`, press enter, press enter, [4]
Displays the list of students who were archived.

### `list`, press enter, press enter, [5]
Displays the list of students who were recently deleted from the master list.

### `view <student>` 
Views a students details. If the name of the student is not specified in the initial command, the program will prompt the user for the name.

### `delete <student>`
Deletes the student from the list. If the name of the student is not specified in the initial command, the program will prompt the user for the name.

### `edit <student>` 
Allows the user to edit the details of the student. If the name of the student is not specified in the initial command, the program will prompt the user for the name.

### `sort <name>` 
Sorts the list lexicographically. If the word `name` is not specified in the initial command, the program will prompt the user for an appropriate flag.

### `sort <payment>` 
Sorts the list by the last fee payment date of a student. If the word `payment` is not specified in the initial command, the program will prompt the user for an appropriate flag.

### `sort <classes>`
Sorts the list by the number of classes a student has attended. If the word `classes` is not specified in the initial command, the program will prompt the user for an appropriate flag.

### `archive <student>`
Archives specified student. Removes student from current list and adds student to archive.

### `unarchive <student>`
Unarchives specified student. Moves student from archive to current list.

### `undo`
Undoes the last deleted entry.

### `restore <student>`
Restores the student from the current session.

### `help` 
Generates the list of commands.

### `bye`
Terminates the program.
