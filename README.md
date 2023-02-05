# musala-drones
This project has 5 Rest APIs to do the following
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;

Pre Assumptions
- only a drone in IDLE state can be loaded with medications
- a drone is available for loading if its IDLE and has a battery level of above 25%
- a drone can not be loaded above its max capacity (500grams)

Pre requisites to run the project
- Availability of docker environment and Postman

Commands for running the project in Docker environment
- within the project files in the terminal run "docker compose up" to start the project


