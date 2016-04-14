# DineWithUs
18641 Project 2 Repo for DineWithUs Android App
Authors: Ashish, Niteesh, Tay

Organization: In our java res folder there are a total of five packages: DBLayout, entities, fragment, UI, and ws. We will explain the function of each package below in the scheme of our overall design hierarchy.

	DBLayout: Contains the classes and interface associated with the database for the backend. The database will serve as the intermediary between the front end and server side functionality.

	entities: Contains the files Appointment.java, Store.java, and StoreSet.java. It provides classes which can be used to perform the sort of backend functions needed by some of the fragments. Appointment.java handles the appointment scheduling, and the Store related classes handle the information (location, ratings, etc.) associated with the restaurants.

	fragment: Contains all the classes related to the layout fragments as shown in the wire frame.

	UI: Contains the files LoginActivity.java and MainActivity.java. LoginActitivty handles the user inputs for username and password and sends the info to the database for authentication. MainActivity handles the switching between fragments.

	ws: Contains the classes associated with hardware features (CameraUtil.java, GPSUtil.java, IOUtil.java) and the classes/interfaces needed for authentication of the client with respect to the backend.

Exception Handling: We will use custom exception handlers. They will handle backend related errors (connection timeouts, etc.), and invalid user enteries for prompted fields.