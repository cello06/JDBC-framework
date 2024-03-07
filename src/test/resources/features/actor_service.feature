@regression @db @actorService
Feature: Actor Management Service

  @smoke
  Scenario: Get list of all actors
    Given the actor service is running
    When the user send request to database to get list of all actors
    Then the user should receive a list of all actors

  Scenario Outline: Get an actor by actor id
    Given the actor service is running
    When the user send request to database to get an actor by "<actor_id>"
    Then the user should receive an actor with following details  "<first_name>","<last_name>","<last_update>"
    Examples:
      | actor_id | first_name | last_name | last_update            |
      | 1        | Penelope   | Guiness   | 2013-05-26 14:47:57.62 |
      | 27       | Julia      | Mcqueen   | 2013-05-26 14:47:57.62 |
      | 48       | Frances    | Day-Lewis | 2013-05-26 14:47:57.62 |