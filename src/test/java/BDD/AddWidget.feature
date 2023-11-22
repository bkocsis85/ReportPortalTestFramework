Feature: Report Portal
  As a user
  I want to add new widgets to my Dashboard
  So I will get more detailed information on my project

  Background: Report portal
    Given I log in to Report Portal with "superAdmin" and "erebus"

  Scenario Outline: Add new widget to my dashboard
    When I select my dashboard
    And I click on Add new widget button
    Then Add new widget dialog should be displayed
    When I select "<widgetType>"
    And Click on Next step button
    And Select my filter
    And Click on Next step button
    And Fill widget name text field with "<widgetName>"
    And Click on Add button
    Then My widget should be displayed on the dashboard with title: "<widgetName>"

    Examples:
    | widgetType              | widgetName            |
    | Launch statistics chart | My statistics chart    |
    | Launches duration chart | My duration chart     |
    | Overall statistics      | My overall statistics |