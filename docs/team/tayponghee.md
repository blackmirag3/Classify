# Tay Pong Hee's Project Portfolio Page

## Project: Classify

Classify is a desktop application used for Tuition Centres to allow for easier management of their enrolled students. 
From allowing users to add new students to sorting them based on their last paid date, Classify is the one-stop app that
all Tuition Centres can employ for ease of student management. To use the app, users can interact with it using CLI.
Overall, the code base has more than 3kLoC, and is written in Java.

Given below are my contributions to the project:

- **Feature: Add a student to the master list.**
  * The foundational building block of the Classify project. 
  * Highlights: The User would be allowed to add a student to the Master List in a specified format given by the program, which helps the User understand the proper format for adding them. Moreover, each student would be allowed any number of subjects tagged to their name, which is done through a polling functionality.
  * Justification: This allows the User to add all the relevant information from the get-go, and makes it easy for the User to know what are the necessary details at registration.


- **Feature: View a student from the master list.**
  * Highlights: Differentiates whether a User has input 0 classes attended for a specified subject or simply left it blank. 
  * Justification: Most of the time, people allude to 0 and leaving it blank as the same thing for that field. However, it is important to note down whether the User has accidentally left it blank i.e. may have lessons attended, or meaningfully filled it as 0, which helps with data analytics of a student's progress.


- **Feature: List the students from the master list, based on certain parameters.**
  * Highlights: The User would be allowed to specify whether to generate the whole list of students, display them with the total classes attended across all subjects or only list students taking a certain class at the tuition centre.
  * Justification: Being able to generate a list of students based on certain parameters would make it easier to narrow down to whom the User is looking for in the database.


- **Feature: Sort the students on the master list based on certain parameters.**
  * Highlights: Created the sorting algorithms to sort based on lexicographical order and by total classes attended. 
  * Justification: Being able to sort a student by alphabetical order and total classes attended helps with easily generating a report based on name, and helps with visualising the number of classes taken by all the students, both in ascending order.
  * Credits: Used the java.util.Comparator class to easily sort the students based on the above parameters.


- **Code contributed:** [Link to Reposense contribution graph](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=tayponghee&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


- **Documentation:**
  * [Contributions to UG](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/UserGuide.md): 
    * Features for list, sorting, as well as the examples for deletion and view. Compiled all the commands into the summary sheet at the bottom of the UG.
  * [Contributions to DG](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/DeveloperGuide.md): 
    * References, InputParsing Component, DataStoring Component, AddStudent Component and StudentSorter Component, as well as the non-functional requirements.
    * UMLs contributed: Object diagram for DataStoring, Class diagrams for InputParsing and StudentSorter. Created using the PlantUML software.
    * [DataStoring UML](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/diagrams/src/DataStoring/ObjectDiagram.png)
    * [InputParsing UML](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/diagrams/src/InputParsing/InputParsing.png)
    * [StudentSorter UML](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/diagrams/src/StudentSorter/StudentSorter.png)


- **Contributions to Team-Based tasks:**
  * Management of assignees for issues
  * Updating of non-specific documentation, e.g. non-functional requirements
  * Weekly reminders on deadlines for tasks
  * Handling of pull requests (Examples: [link 1](https://github.com/AY2324S2-CS2113-T13-3/tp/pull/159) [link 2](https://github.com/AY2324S2-CS2113-T13-3/tp/pull/153) [link 3](https://github.com/AY2324S2-CS2113-T13-3/tp/pull/145))
  * Contributions to Junit tests
    * [StudentAttributesTest]
    * [StudentSorterTest]
    * [SubjectGradeTest]
