Feature: Manage Ethereum API
  As a Talan employee,
  In order to interact with other employees,
  I should be able to exchange talanCoins with them

  Scenario: Employee sends talanCoins
    Given Employees Moby and Dick
    When Moby sends 10 talanCoins to Dick
    Then Dick should receive 10 talanCoins