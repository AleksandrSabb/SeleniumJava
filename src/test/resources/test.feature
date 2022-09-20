Feature: Test ASOS


 # Scenario: Test login with valid creads
  #  Given Page is opened
   # When I navigate to profile icon & click on signIn link
    #And I enter valid email
 #   And I enter valid password
  #  And I click on signIn button
   # Then I see main page

 # Scenario: Search
  #  Given Page is opened
   # When I type text in search field
   # And Press Enter
    #Then I see resoult of my request

    #Scenario: Filter by colour
    #  Given Brand page is opened
     # When I set colour dropdown
      #Then I see only products with color i set

   #   Scenario: Filter by price
    #    Given Brand page is opened
     #   When I select sort by price from low to high
      #  Then Prise of product is lower or equal to the next product

        Scenario: Add to cart
          Given Product page is opened
          When I select size
          And I Click add to Add to bag
          Then Product is placed to my cart
