Feature: Purchase with TDD

  @TDD @Purchase
   Scenario Outline: Purchase with TDD
   Given User is already login and on inventory list
   When User click add to cart button to one of the items
   And User click cart icon on top-right
   And User check the items they want to buy
   And User click checkout button
   And User fill <firstname>, <lastname>, and <zipcode>
   And User click continue button
   And User check their order <result>
   And User click finish button
   Then User get the order result

    Examples:
    | firstname | lastname | zipcode | result |
    | arfan | ahmad | 17003 | Passed |
    | arfan | ahmad |       | Failed |
    | arfan |       | 19821 | Failed |
    |       | ahmad | 12312 | Failed |