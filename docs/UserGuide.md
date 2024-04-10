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

### - Produce a list of all the students who takes a subject

Displays a list of all the students with that associated subject taken in the tuition centre.
If user enters different letter cases from subjects stored, the system will still match strings regardless of letter case for user intuitiveness.

### Produce a list of all students

Displays a list of all the students at the tuition centre with no other parameters shown.

### Produce a list of all students with total classes attended

Displays a list of all the students with their total classes attended at the tuition centre.

### Produce a list of students moved to archive

Displays a list of all the students that are moved to archive.

### Produce a list of students that were recently deleted

Displays a list of all students who were recently deleted.

### Produce a list of students with their phone numbers

Displays a list of all students with their phone numbers.

### - Sort the list lexicographically, from A to Z

Sorts the masterlist of students from A to Z. When the user types list, it will generate the updated sorted list.

### - Sort the list by total classes attended

Sorts the masterlist of students from the lowest number of classes attended to highest. When the user types list, it will generate the updated sorted list.

### - Sort the list by latest fee payment date

Sorts the masterlist of students from the oldest date to the most recent. When the user types list, it will generate the updated sorted list.

### - Undo the most recent delete action

Adds in the most recently deleted student back into the runtime database.

### - Restore a specific deleted student

Finds a specific deleted student and adds it back into the runtime database.

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
  * so, regardless of what the user uses for capitalisation of the name, the program will recognise that uppercase and lowercases should return the same student.

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

Depending on whether the user entered the student's name or not during the command, a field will be printed out in the terminal, awaiting a user input.

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

Depending on whether the user entered the student's name or not during the command, a field will be printed out in the terminal, awaiting a user input.

```
Example:
restore
Enter student name: 
mario
Student has been restored!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### - Undo a delete and restore the deleted student to the working list
Undoes the latest delete. Used by typing `undo`

Format: `undo`

```
Example:
undo
Last delete undone!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```
### - Sort the list of students `sort`
Sorts the list of students based on the following input. Allows the user to do sort [type] or just sort

Format: `sort`, `sort name`, `sort classes` or `sort payment`

Depending on whether the user entered the student's name or not during the command, fields will be printed out in the terminal, awaiting a user input each time.

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

### `view_subject <subject>` 
Lists all the students associated with that subject. If the user specifies the subject in the initial command, the list will be generated and the command will be terminated. If the user does not specify the subject, the programme will continuously prompt the user for a subject, until they choose to terminate the command.

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
