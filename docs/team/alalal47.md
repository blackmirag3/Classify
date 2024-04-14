# alalal47 - Project Portfolio Page

## Overview
Classify is a CLI based desktop application used for Tuition Centres to allow for easier management of their enrolled students.
From allowing users to add new students to sorting them based on their last paid date, Classify is the one-stop app that
all Tuition Centres can employ for ease of student management.

### Summary of Contributions
- #### Code contributed - [Link to Reposense contribution graph](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=alalal47&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- #### Enhancements implemented
  - **Feature: Allow User Input to be in the form of command or command [flag]**
    - Ensures that experienced Classify users will be to make use of its features more seamlessly, while beginners are guided through each step.

  - **Feature: Delete a student from the master list**
    - Along with the add feature, the other crucial feature that composes Classify's basic functionality.
    - Above being a simple delete feature, also implements a recently deleted list which is only cleared at the end of every session, allowing the user to restore accidental deletes.


  - **Feature: Restore a student deleted in the current session**
    - Works in tandem with the delete function to ensure that users do not have to re-add all the student information in the case of an accidental or unwanted deletion.


  - **Feature: Undo last deletion in the current session**
    - A more convenient way for users to use the restore function if they immediately want to reverse a deletion before any other deletes have been made.
    

  - **Feature: Sort list of students by last date of payment**
    - Allows the user to conveniently track any students that might have overdue fees.


  - **Feature: Handle corrupted Student_Information.txt files**
    - Prevents invalid inputs in the data file from crashing Classify.
    - Does not allow the user to resume use of Classify before the file is deleted, or the corruption is rectified.

  - **Feature: Create and maintain the help function**
    - Ensures users do not have to bring up the UG at every instance, they can instead refer to the help function for a quick guide on how to use all features.


- #### Contributions to the [UG](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/UserGuide.md)
  - Wrote the Usage guide for the help, edit, delete, restore, undo and sort features.

- #### Contributions to the [DG](https://github.com/AY2324S2-CS2113-T13-3/tp/blob/master/docs/DeveloperGuide.md)
  - Wrote the instructions for manual testing.

- #### Contributions to team based tasks
  - Created issues on GitHub for tasks that I felt needed to be completed.
  - Participated in bug-testing before the releases of jar files.
  - Creating shared google docs for user stories and other deliverables before the start of TP.
  
- #### Review/mentoring contributions
  - **Links to some PRs reviewed**
    - [PR1](https://github.com/AY2324S2-CS2113-T13-3/tp/pull/148)
    - [PR2](https://github.com/AY2324S2-CS2113-T13-3/tp/pull/164)

- #### Contributions beyond the project team
  -  Participated in a mutual debugging session with T13-4 link to GitHub [Discussion](https://github.com/orgs/AY2324S2-CS2113-T13-4/discussions/256)