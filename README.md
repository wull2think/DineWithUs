# DineWithUs
18641 Project 2 Repo for DineWithUs Android App
Authors: Ashish, Niteesh, Tay

Organization: In our java res folder there are a total of four packages: entities, fragment, UI, and util. We will explain the function of each package below in the scheme of our overall design hierarchy.

	entities: Contains the files Appointment.java, Store.java, and StoreSet.java. It provides classes which can be used to perform the sort of backend functions needed by some of the fragments. Appointment.java handles the appointment scheduling, and the Store related classes handle the information (location, ratings, etc.) associated with the restaurants.

	fragment: Contains all the classes related to the layout fragments as shown in the wire frame.

	UI: Contains the files LoginActivity.java and MainActivity.java. LoginActitivty handles the user inputs for username and password and sends the info to the database for authentication. MainActivity handles the switching between fragments.

	Util: Contains the files DatabaseConnector.java and IOUtil.java. This is the package responsible for the backend of the project. It stores the user data and interfaces with the server and the front end.