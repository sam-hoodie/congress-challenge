## Project Overview
This project lets you run command line queries on current and historical members of Congress.

## Instructions for setup

* Clone the repository (A full tutorial can be found in the [Cloning Tutorial](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository))
* Navigate to the location of the file, right click it, and click open in terminal
* Enter the command `.\gradlew build` to build the project (This may take a while the first time)
* Enter the command `.\gradlew run --console=plain` for an interactive demo

## How to interact with the application
After building and running the project in terminal, the > symbol
should appear, meaning the application is ready to run. The different commands
can be viewed by typing "directions" and hitting the Enter key. You will see an interactive
menu to construct your queries.

* All the commands have a hierarchical structure, separated by a dot to go to
the next sublevel. Here are some examples:
  * `-get congress.serving.current.georgia`
  * `-get all.historic.names.shortest.first`

* Each command starts with the chamber of the congress you want to obtain
data about (house, senate, congress)

* The next part signifies the general time period of the data (serving, historic, all)

* The next part of the command specifies what kind of data you want to get. This includes
name data (names), currently serving (current), and term info (terms)

## Autocorrect Functionality
When a command you entered is not recognized, this will trigger the autocorrect. The autocorrect will try to give you
suggestions based on the query that you have typed. If there are no suggestions, it will say so.

## Dependency Credits
* [Congress Member Information Files](https://github.com/unitedstates/congress-legislators)
* [Apache Commons Levenshtein Distance](https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/similarity/LevenshteinDistance.html#apply-java.lang.CharSequence-java.lang.CharSequence-)
* [Jackson Parsing Library](https://github.com/FasterXML/jackson)