@manageEmployees
  Feature: Manage Employees
    @add
    Scenario: Add Employee to the table
      Given The user is on Manage Employee Landing page
      Then The user input required information


      @remove
      Scenario: Remove employee on a table
        Given The user is on Manage Employee Landing page
        Then User select employee on a table
        And Click Remove Employee Button

        @update
        Scenario: updating employee details
          Given The user is on Manage Employee Landing page
          Then User select employee on a table
          And Edit the details and click Add Employee

          @cancel
          Scenario: cancel employee details
            Given The user is on Manage Employee Landing page
            Then User select employee on a table
            And cancel employee details




