# Purchase Order Service - Spring Java Test

## Background 

ACME co. is developing a sales system and you have been asked to write a service to manage the purchase orders.  A framework Spring Boot application has been provided and you will need to complete the various components to meet the requirements stated below.

You may use any tools at your disposal, Stackoverflow, Google, Baeldung... But any code you use from these sites must be understood and you must be prepared to talk through why you have included it.  Be careful with copy and paste!

You may ask questions both during the initial discussion and at any point during the test.  The questions you ask will be part of the test.  The test is complex and intended to assess your knowledge.  If you cannot complete all of the test, please complete as much as you can.

Finally, it goes without saying this must be your own work.  Clearly you could get someone else to do it, but you will be required to complete a similar test on day one of employment under observation. Anyone unable to replicate their work on this test will be asked to leave immediately.


## Requirements

Broadly speaking, the requirements follow on from each other in logic and complexity.  Please complete them as you can.  Commented answers and pseudo-code may be considered if you are unable to provide a code-based answer.


1. Complete the entities in the domain package so that querying the PurchaseOrder object will also return all of the ProductItem line items.  Hint: PurchaseOrder has a one to many relationship to ProductItem.  ProductItem has a one to none, one or many relationship to PurchaseOrder.


2. Complete and update the repository so that a query can return purchase orders which have been created for a given date.


3. Create HTTP endpoints to allow a client to interact with the service.  Endpoints may accept XML or JSON but may only produce JSON output. Your code will need to perform the following actions:
    * Return a list of all purchase orders in the system
    * Return a single purchase order for a given id
    * Return a list of purchase orders created for a given date (default to today if none is given)
    * Create a new purchase order
    * Add and remove product line items to/from the purchase order
    * Delete a purchase order (Note: this will be handled by setting the status to 'Deleted')


4. Add security to the application so that only validated users may access the end points.  
    * There will be two users.  Peter and Paul.  
    * Peter is an end user, has the role of USER and may read all purchase orders, may create new purchase orders but may only update purchase orders that he owns.  
    * Paul is an admin user, has the role of ADMIN and as well as all of Peter's abilities, may update and delete any purchase order on the system
    * Please define these users in memory.

 
5. Create a service which will return all purchase orders which contains given product items.  Create an endpoint which will return this data.


6. Rationalise the client entity to avoid duplication.


7. Prevent unauthorised clients from accessing your service at all.


8. Encrypt the sensitive information held in the client entity.


9. Build the compiled JAR into a Docker container.  You may use any approach to do this.


11. Split the project into two separate modules, one with the purchase order service, a second with an openFeign client which can be referenced as a dependency in any other services which might need to access the purchase order service.


11. Finally, tidy up the POM and make it more readable.


For all of your code, please comment and test fully.


## How to complete the test

1. You will be given a personalised copy of the test repository.


2. Complete your work and push your changes back up to GitHub.


3. Create a pull request for review.


## Last words.

Feel free to show off, be excellent and show me what you can do.  If you want to add something that isn't covered by the requirements, please add it and explain why you've added it.  If you cannot fully answer any of the requirements, but can explain how you would start investigating a solution, please add those details.
