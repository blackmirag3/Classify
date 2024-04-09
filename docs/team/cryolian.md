# Tan Yi Xin (Cryolian)'s Project Portfolio Page

## Project: Classify

Classify is a desktop application used for Tuition Centres to allow for easier management of their enrolled students. 
From allowing users to add new students to sorting them based on their last paid date, Classify is the one-stop app that
all Tuition Centres can employ for ease of student management. To use the app, users can interact with it using CLI.
Overall, the code base has more than 3kLoC, and is written in Java.

Given below are my contributions to the project:

- **Foundation for the project.**
    * Coded out the foundation for the project, which included the Student, Details, StudentList classes.
    * Directed the vision of the project, by guiding my group as to how they wanted to implement their assigned functions. 
    * In charge of learning the architecture and workings of the full code base, to be able to easily identify bug sources and fix them. This also helped when my fellow group members asked about how to go about implementing their functions.

- **Maintenance of commonly used functions and exceptions.**
    * findStudentByName, which was later heavily refactored when students of the same name were allowed to be added which different phone numbers.
    * readInString, which would detect when characters used in our save files were used. This function was later used when adding and editing students. 
    * checkForInvalidCharacters, a function for checking for validity of strings, was also implemented to ensure maximum compatibility with our saving functions. 

- **Extensive bugfinding and fixing**
    * Took charge of finding and fixing bugs in my teammates' code, especially since they usually coded without reference to existing changes other members have implemented. 
    * Took charge of most of the exception handling and throwing when calling features implemented by other members.

- **Code contributed:** [Link to Reposense contribution graph](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=Cryolian&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=Cryolian&tabRepo=AY2324S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
    * Majority of my code was done to ensure my group member's code worked well with each other. I focused on making sure everything worked smoothly, and fixing bugs and exceptions, rather than adding features. Essentially acted as the scrum master.


- **Documentation:**
  * [Contributions to UG](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/UserGuide.md): 
    * Added introduction, quick list of commands, example code and specifications for feature Add Student.
  * [Contributions to DG](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/DeveloperGuide.md): 
    * Student Details component, Target user profile, User Stories, and majority of the UMLs.
    * UMLs contributed: Class diagrams for Data Storing and Input Parsing, Sequence diagrams for Input Parsing and Student Identification. Created using the PlantUML software.
    * [DataStoring UML](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/diagrams/src/DataStoring/DataStoring.png)
    * [InputParsing UML](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/diagrams/src/InputParsing/InputParsing.png)
    * [InputParsing Sequence Diagram](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/diagrams/src/InputParsing/InputParsingSequenceDiagram.png)
    * [Student Identification UML](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/diagrams/src/DataStoring/StudentIdentification.png)


- **Contributions to Team-Based tasks:**
    * Helped with team administration, ensuring deadlines were met, and doing the team tasks such as enabling assertions and ensuring CI compliance.
    * Helped to regularly review code for my team (Exmaples: [Link 1](https://github.com/AY2324S2-CS2113-T13-3/tp/pull/228) [Link 2](https://github.com/AY2324S2-CS2113-T13-3/tp/pull/156) [Link 3](https://github.com/AY2324S2-CS2113-T13-3/tp/pull/99)). While the comments may seem minimal, majority of changes were requested when coding together on discord, or when asked for help through the telegram group chat with the group.
    * Created the V1.0 and V2.0 releases on github. 

