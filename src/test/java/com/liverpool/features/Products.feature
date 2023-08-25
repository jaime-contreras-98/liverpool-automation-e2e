@Liverpool-Tests @Regression
Feature: Multiple product list on Liverpool webpage
  Tests were multiple products are found on Liverpool.com

  Background: Open browser and go to webpage
    Given I initialize my browser
    And I go to https://liverpool.com.mx/

    @Liverpool-Test-1 @Regression-1
    Scenario: Search for PS5 Products
      Given I search for product playstation
      When validate the amount of products is greater than 1
      And I click on the first product with name "PlayStation 5 825 GB"
      When I validate using "css" with locator "div[class='product-header-container liverpool'] > h1" is being displayed
      And validate product "name" txt is equal to "Consola PlayStation 5 825 GB"
      Then validate product "price" txt is equal to "$12,999"
      And I close my browser

    @Liverpool-Test-2 @Regression-2
    Scenario: Search for 55 inch screen tv's under $10,000 MXN
      Given I search for product smart tv
      When I validate the "price section" element is being displayed
      And I validate the "all sizes button" element is being displayed
      When I click on element using css with locator div[class='o-aside d-aside--margin'] > div:nth-child(27) a
      And I click on "55 pulgadas" filter checkbox and validate is checked
      When I wait 4 seconds
      And I click on "prices" filter on "Menos de $10000.0"
      When I confirm element with css using locator div[class='o-aside d-aside--margin'] > div:nth-child(26)  div[class*='m-radioButton'] is not visible
      Then I validate product results number is less or equal than 15
      And I close my browser

    @Liverpool-Test-3 @Regression-3
    Scenario: Search DIOR perfumes for men
      Given I hover on element with text "Categorías"
      And I hover on element with text "Belleza"
      When I click on the first product with name "Perfumes Hombre"
      And I validate using "css" with locator "h2.a-title-section-leftMenu" is being displayed
      When validate product "men perfume" txt is equal to "Perfumes Hombre"
      And I sort products by "Calificaciones"
      When I click on element using css with locator a#Marcas
      And I wait 2 seconds
      When I iterate and click on element DIOR from list
      And I wait 2 seconds
      Then validate product "dior perfume" txt is equal to "DIOR"
      And I close my browser
