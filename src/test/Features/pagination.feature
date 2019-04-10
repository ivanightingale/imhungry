Feature: Pagination

  Scenario: User does not specify number of results per page.
    Given User does not pick a number of results per page, and there are more than 10 results.
    Then Display 10 results per page, and however many remaining on the last page.