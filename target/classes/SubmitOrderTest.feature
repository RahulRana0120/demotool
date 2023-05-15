
@tag
Feature: Purchase Order from ECommerce Website
  I want to use this template for my feature file

Background:
Given that user landed on ECommerce Page
@tag2
  Scenario Outline: Positive test of Submitting order
    Given Logged in with username <name> and password <password>
    When User add a product <productName> to Cart
    And Checks out <productName> and submits the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page
        Examples: 
      | name  | password 			| productName  |
      | rahul |     Sam@12345 | ZARA COAT 3 |
