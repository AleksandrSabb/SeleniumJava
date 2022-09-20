Feature: Test ASOS


  Scenario: Test login with valid creads
    Given Page is opened
    When I navigate to profile icon
    And I click on signIn link
    And I enter valid email ("mymailbox099@meta.ua")
    And I enter valid password ("asd5329367")
    And I click on signIn button
    Then I see main page

  Scenario: Search
    Given Page is opened
    When I type text ("Nike")in search field
    And Press Enter
    Then I see result of my request ("Nike")

  Scenario: Filter by colour
    Given Brand page is opened
    When I set colour dropdown ("Blue")
    Then I see only products with color ("Blue")

  Scenario: Filter by price
    Given Brand page is opened
    When I select sort by price from low to high
    Then Prise of product is lower or equal to the next product

  Scenario: Add to cart
    Given Product page is opened
    When I select size("Any")
    # Any -> choose first available
    And I Click add to Add to bag
    Then Product is placed to my cart

  Scenario: Check discount prise in cart
    Given Sales product page opened
    When I select size("Any")
    # Any -> choose first available
    And I Click add to Add to bag
    Then Price in cart equals to product sales price