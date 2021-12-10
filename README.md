# Purchase Order Service

## Background 

ACME co. is developing a sales system and you have been asked to write a service to manage the purchase orders.  A framework Spring Boot application has been provided and you will need to complete the various components to meet the requirements stated below.

You may use any tools at your disposal, Stackoverflow, Google, Baeldung... But any code you use from these sites must be understood and you must be prepared to talk through why you have included it.

It goes without saying this must be your own work.  Clearly you could get someone else to do it, but you will be required to complete a similar test on day one of employment under observation. Anyone unable to replicate their work on this test will be asked to leave immediately.

You may ask questions both during the initial discussion and at any point during the test.  The questions you ask will be part of the test.

## Requirements

1. Complete the entities in the domain package so that querying the PurchaseOrder object will also return all of the ProductItem line items.


2. Complete and update the repository so that as can return purchase orders which have been created after a given date


3. Create HTTP endpoints for all of the relevant methods 


4. Add security to the application so that only validated users may access the end points.  There will be two users.  Peter and Paul.  Peter is an end user, has the role of USER and may read all purchase orders, may create new purchase orders but may only update purchase orders that he owns.  Paul is an admin user, has the role of ADMIN and as well as all of Peter's abilities, may update and delete any purhcase order on the system.  <br/><br/>Please define these users in memory.

 
5. Create a service which will return all purchase orders which contains given product items.


6. Rationalise the client entity to avoid duplication


7. Prevent unauthorised clients from accessing your service at all


8. Encrypt the sensitive information held in the client entity


9. Comment and test your code fully


## How to complete the test

1. You will be given a personalised copy of the test repository 


2. Complete your work and push your changes back up to GitHub


## Last words.

Finally, feel free to show off, be excellent and show me what you can do.  If you want to add something that isn't covered by the requirements, please add it and explain why you've added it.  If you cannot fully answer any of the requirements, but can explain how you would start investigating a solution, please add those details.
