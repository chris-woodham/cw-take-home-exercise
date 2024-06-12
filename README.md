# Take Home Excercise

## How to run the application locally

Requirements: `Java 17`

The application is a `Spring-Boot maven` application that can be run using the command:
- `mvn spring-boot:run`

The api is served on: `http://localhost:8080/`

### Testing the API endpoints

There are sample json files within the `test-json-files` directory that can be used to test the endpoints:

- e.g `curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/treatments/treatment1_scientific/questions --data "@/... absolute_path .../cw-take-home-exercise/test-json-files/post-treatment1-yes.json"`

## Documentation for the API

The Swagger UI can be found at: [Swagger UI](http://localhost:8080/swagger-ui/index.html) and the raw JSON api docs can be found at: [Raw JSON Docs](http://localhost:8080/api-docs)

## Notes

Because of the time constraint - I made a couple of decisions:

- I decided not to consider user accounts (and therefore also did not need to consider user authentication)
- I decided not to implement a `service` package, but would normally have done this.

I'm afraid I also haven't had time to unit test my code or implement logging.
 
