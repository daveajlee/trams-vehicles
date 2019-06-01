# trams-vehicles
This microservice for the TraMS platform stores vehicle information through REST and displays it to authorised individuals through a web client.

**How to use**

1. To use the microservice you need to specify the user specific configuration parameters in application.properties and application-production.properties. 
2. Create an executable jar using mvn clean install.
3. Run the jar (for example in production mode): java -Dspring.profiles.active=production -jar trams-vehicles.jar

**How to use the integrated Admin Client and Swagger**

The microservice comes with an integrated admin client which allows you to create and view vehicle information. This admin interface is available at https://your-domain/trams-vehicles/admin/ The initial username and password is admin/admin.

You can save and view data to trams-vehicles through any language by calling the REST interface which is documented by Swagger and reachable through this URL:  https://your-domain/trams-vehicles/swagger-ui.html

**Available Profiles**
* local - This profile uses an in-memory database but does not use Eureka for service discovery. This works well for local development.
* dev - This profile uses an in-memory database and Eureka for service discovery. This works well for development and testing.
* production - This profile uses Eureka for service discovery and can be configured to use a database.

**Import/Export Feature**
There is an import/export feature for vehicle data. This is done via JSON and disabled by default. To activate this feature, go to the following URL: https://your-domain/trams-vehicles/togglz/ and activate the feature by clicking twice on the disabled status button. The feature is now activated until the next server restart. 
