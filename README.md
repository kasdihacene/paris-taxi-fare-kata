### Paris Taxi Fare API
Backend of Paris Taxi Fare Application
> Technologies
    
    Java 11
    Maven 
    Github Actions for workflow development


> Endpoints
    
    Calculate Price :   HTTP POST 
                        http://localhost:9090/api/ride/calculate
                        body : {
                               "id": 1,
                               "distance": 2,
                               "startTime": "2020-06-19T13:01:17.031Z",
                               "duration": 9000
                               }
                               
    Fetch populated Rides : 
                        HTTP GET
                        http://localhost:9090/api/ride/populate