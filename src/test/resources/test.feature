Feature: Test ASOS


  Scenario Outline: Test login with valid creads
    Given Page is opened
    When I click on signIn link
    And I enter valid email "<email>"
    And I click on continue button
    And I enter valid password "<password>"
    And I click on signIn button
    And I click on slip button
    Then I see user page
    Examples:
      | email                | password   |
      | mymailbox099@meta.ua | asd5329367 |

  Scenario: Search
    Given Page is opened
    When I type text ("Jeans")in search field
    And Press Enter
    Then I see result of my request ("Jeans")

  Scenario Outline: Filter by colour
    Given Category "<page>" is opened
    When I set "<color>"
    Then I see only products with "<color>"
    Examples:
    # For Any test may fail (with select white shown products with cream, blue->aqua, etc.) need to contact PM for investigate)
      | color    | page   |
      | Yellow   | Kids   |
      | Any      | Any    |

  Scenario Outline: Filter by price
    Given Brand "<page>" is opened
    When I select sort by price from low to high
    Then Prise of product is lower or equal to the next product
    Examples:
      | page                                                           |
      | https://www.asos.com/men/a-to-z-of-brands/adidas/cat/?cid=7113 |
      | Any                                                            |

  Scenario Outline: Add to cart
    Given Product "<page>" is opened
    When I select "<size>"
    # Any -> choose first available
    And I Click add to Add to bag
    Then Product is placed to my cart
    Examples:
      | size | page                                                                                                                                    |
      | S    | https://www.asos.com/adidas-originals/adidas-originals-essentials-t-shirt-in-pink/prd/201534582?clr=pink&colourWayId=201534618&cid=7113 |
      | Any  | Any                                                                                                                                     |

  Scenario Outline: Check discount prise in cart
    Given Sales product "<page>" opened
    When I select "<size>"
    # Any -> choose first available
    And I Click add to Add to bag
    Then Price in cart equals to product sales price
    Examples:
      | size | page                                                                                                                  |
      | Any  | Any                                                                                                                   |
      | S    | https://www.asos.com/asos-design/asos-design-3-pack-rib-trunks/prd/201161619?clr=multi&colourWayId=201161620&cid=8409 |

  Scenario Outline: Add product to wishlist
    Given Sales product "<page>" opened
    When I press heart
    Then Product add to my wishlist
    Examples:
      | page                                                                                                                  |
      | Any                                                                                                                   |
      | https://www.asos.com/asos-design/asos-design-3-pack-rib-trunks/prd/201161619?clr=multi&colourWayId=201161620&cid=8409 |

  Scenario: Delete item from wishlist
    Given wishlist page with (2) products in list
    When I click remove button on product no(1)
    Then product removed from wishlist


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
