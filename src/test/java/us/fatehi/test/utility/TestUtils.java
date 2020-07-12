/*
========================================================================
Chinook Database
http://www.schemacrawler.com
Copyright (c) 2020, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

This software is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

This software and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

========================================================================
*/
package us.fatehi.test.utility;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class TestUtils
{

  public static void verifyCount(final Connection connection,
                                 final String table,
                                 final int expectedCount)
    throws SQLException
  {
    assertThat(connection, is(not(nullValue())));
    assertThat(connection.isClosed(), is(false));

    final SingleConnectionDataSource dataSource =
      new SingleConnectionDataSource(connection, true);
    final String countSql = "SELECT COUNT(*) FROM " + table;
    final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    final Integer actualCount =
      jdbcTemplate.queryForObject(countSql, Integer.class);
    assertThat(actualCount, is(not(nullValue())));
    assertThat(actualCount, is(expectedCount));
  }

  private TestUtils()
  {
    // Prevent instantiation
  }

}
