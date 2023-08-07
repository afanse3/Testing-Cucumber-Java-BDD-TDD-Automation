@All
  Feature: Purchase

    @BDD @Positive @Purchase
    Scenario: Purchase
      Given User is already login and on inventory list
      When User click add to cart button to one of the items
      And User click cart icon on top-right
      And User check the items they want to buy
      And User click checkout button
      And User fill first name, last name, and zip code
      And User click continue button
      And User check their order
      And User click finish button
      Then User get the order result

    @BDD @Negative @Purchase
    Scenario: Purchase
      Given User is already login and on inventory list
      When User click add to cart button to one of the items
      And User click cart icon on top-right
      And User check the items they want to buy
      And User click checkout button
      And User doesn't fill first name, last name, and zip code
      And User click continue button
      Then User get error message result
