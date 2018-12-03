$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/AddContractor.feature");
formatter.feature({
  "name": "Add new contractor",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Add new contractor",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Regression"
    }
  ]
});
formatter.step({
  "name": "I am on the Main page",
  "keyword": "Given "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I go to Add contractor page",
  "keyword": "When "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter contractor name \"Biedronka\"",
  "keyword": "And "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click OK button",
  "keyword": "And "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "new contractor is created",
  "keyword": "Then "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.uri("src/test/resources/features/AddExpense.feature");
formatter.feature({
  "name": "Add new expense",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Add expense for one account",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Regression"
    }
  ]
});
formatter.step({
  "name": "I am on the Main page",
  "keyword": "Given "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I go to Add expense page",
  "keyword": "When "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I create new expense with the following data",
  "rows": [
    {
      "cells": [
        "Name",
        "Date",
        "Amount",
        "Account nr1",
        "Contractor",
        "Subcategory"
      ]
    },
    {
      "cells": [
        "Srodki czystosci",
        "Current date",
        "120",
        "Wydatki wspolne",
        "Biedronka",
        "Dom: Srodki czystosci"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click OK button on Add expense page",
  "keyword": "And "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "new expense is created",
  "keyword": "Then "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.uri("src/test/resources/features/Login.feature");
formatter.feature({
  "name": "Login to application",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Login with password",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Regression"
    }
  ]
});
formatter.step({
  "name": "I open login page",
  "keyword": "Given "
});
formatter.step({
  "name": "I login with user name \u003cusername\u003e and password \u003cpassword\u003e",
  "keyword": "When "
});
formatter.step({
  "name": "I am logged in",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ]
    },
    {
      "cells": [
        "\"asptanya\"",
        "\"12345678\""
      ]
    }
  ]
});
formatter.scenario({
  "name": "Login with password",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Regression"
    }
  ]
});
formatter.step({
  "name": "I open login page",
  "keyword": "Given "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I login with user name \"asptanya\" and password \"12345678\"",
  "keyword": "When "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I am logged in",
  "keyword": "Then "
});
formatter.match({
  "location": "NativeConstructorAccessorImpl.java:-2"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});