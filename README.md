# musala-drones
This project has 5 Rest APIs to do the following
https://api.postman.com/collections/922269-b19acfd5-47cb-4182-afba-49ca57beb763?access_key=PMAT-01GRGPGEXMCYYZ39PTFBRD4D9Y
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;

Pre-assumptions
- only a drone in IDLE state can be loaded with medications
- a drone is available for loading if its IDLE and has a battery level of above 25%
- a drone can not be loaded above its max capacity (500grams)

Pre-requisites to run the project
- Availability of docker environment and Postman

Commands for running the project in Docker environment
- firstly clone the repository and open it with your favourite IDE
- within the project files in the terminal run "docker compose up" to run the project or "docker compose up -d" to run in the background.
- run "docker compose stop" to stop the services if running in the background or just "control c"
- use  the postman collection to test the Restfull APIs


