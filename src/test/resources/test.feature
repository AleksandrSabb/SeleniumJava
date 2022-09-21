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

  Scenario: Add product to wishlist
    Given Product page is opened
    When I press heart
    Then Product add to my wishlist

  Scenario Outline: Delete item from wishlist
    Given wishlist page with (2) products in list
    When I click remove button on product no(1)
    Then product removed from wishlist
    Examples:
      | before  | after   |
      | shoes   | t-short |
      | t-short |         |

    Scenario: Checkout unavailable for not login users
      Given User not login
      And 1 product in cart
      When I processed checkout
      Then I am navigated to login page

  Scenario: App in Google Play
    Given Page is opened
    When I scroll to the footer
    And I click on link Mobile And ASOS apps
    And I navigate to Google Play logo & click it
    Then Opened new tap Google play -> ASOS app