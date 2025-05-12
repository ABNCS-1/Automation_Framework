Feature: AMS Login

@DEPT
Scenario: Login AMS Standard application

Given Navigate to AMS standard application and login with valid credentials
And Navigate to Department menu under Master module
#And User logout the AMS standard application


@COM
Scenario: Move to Company create screen

Given Navigate to AMS standard application and login with valid credentials
And User update test data set id for COM_001
And Navigate to Company menu under Master Module
And Click the Add icon button under Company screen
And Enter the Company Code in Create Company screen
And Enter the Company Name in Create Company screen
When Click the Save button under Create Company screen
Then Verify company record created successfully in Company index page grid table