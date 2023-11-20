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
    Given Category "<page>" is opened
    When I select sort by price from low to high
    Then Prise of product is lower or equal to the next product
    Examples:
      | page        |
      | Men Fashion |
      | Any         |

  Scenario Outline: Add to cart
    Given Product "<page>" is opened
    When I select "<size>"
    # Any -> choose first available
    And I Click add to Cart
    And I open cart
    Then Product is placed to my cart
    Examples:
      | size | page                                                                                                                                    |
      | 6.0  | https://us.shein.com/1pc-Fashionable-Stainless-Steel-C-shaped-Horseshoe-Barbell-Lip-Nose-Piercing-Unisex-Daily-Wear-p-20323682-cat-6208.html?src_identifier=fc%3DMen%20Fashion%60sc%3DMen%60tc%3D0%60oc%3D0%60ps%3Dtab11navbar11%60jc%3DitemPicking_100215777&src_module=topcat&src_tab_page_id=page_real_class1700500359154&mallCode=1&imgRatio=1-1|
      | Any  | Any                                                                                                                                     |

  Scenario Outline: Check discount prise in cart
    Given Sales product "<page>" opened
    When I select "<size>"
    # Any -> choose first available
    And I Click add to Cart
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
