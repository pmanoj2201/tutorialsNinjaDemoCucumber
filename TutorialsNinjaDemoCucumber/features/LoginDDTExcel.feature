Feature: Login Data Driven with Excel

  Scenario Outline: Login Data Driven Excel
    Given the user is on TutorialsNinja login page
    Then the user should be redirected to the MyAccount Page by passing username and password with excel row "<row_index>"

    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      |         4 |
      |         5 |
