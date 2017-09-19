1. a) The .gitignore is used to filter which files/directories should be committed to git and which should be ignored. This is a tool to stop unimportant files such as config and bit-code files from being committed into the project.

1. b) Gradle is used in this project as dependency manager and build manager. Project builds will get built on the Gradle run models that we created.

1. c) Travis-Cl is per-build testing tool. Each time a git Push occurs Travis-Cl will run all testing for the project, and provide a report on said tests. It serves the role of running tests frequently.


2. a) A route is the path that a packet will take, running the course from a server to a client, or vice versa. For example part of this projects route is the port number 4567


3) The purpose of the Server class is to handle requests from the user. The UserController class translates Json for the web page and handles the database of users.

3. a) The server gives the client a web page that acts as an interface to query the database using the Spark functions and Javascript. Part of this is running the window.onload function which sets up the javascript event listeners, so that the buttons can be clicked. This class does not handle the button clicks themselves.

3. b) The Server class calls getUsers in the userController with no arguments, returning the whole database of users to the web page

3. c) The Server class calls getUsers in the userController with the "age = 25" argument, which filters the getUsers function to return the database only with users with the age of 25.

3. d) The Server class calls the getUser method and return the json of just that one specific user, as seen in line 40.


4) All of the web pages and CSS that the user will be able to access. The purpose of each HTML file is to act as a proxy to access the database using the methods within the Server class and et cetera.


5) The "?age=25" is read from the web page and a request is sent to server that the getUsers method returns with a json response with all of the data, and it's displayed when the user searches for it on the web page.


6) The client-side JavaScript is defined in the users.js file. It's used in the users.html file.
