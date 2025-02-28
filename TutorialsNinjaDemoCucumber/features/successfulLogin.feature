Feature: Successful User login

  @sanity
  Scenario: Successful login
    Given the user is on TutorialsNinja login page
    When the user enters valid credentials (username:"123abc123@gmail.com" password:"Test@123")
    And the user clicks on login button
    Then the user should be redirected to MyAccount page
  
  #@sanity
  #Scenario Outline: Login Data Driven
    #Given the user is on TutorialsNinja login page
    #When the user enters username as "<username>" and password as "<password>"
    #And the user clicks on login button
    #Then the user should be redirected to MyAccount page
#
    #Examples: 
      #| username            | password |
      #| 123abc123@gmail.com | Test@123 |
      #| 1abc123@gmail.com   | Test@123 |
