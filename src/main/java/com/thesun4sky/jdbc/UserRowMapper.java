package com.thesun4sky.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class UserRowMapper implements RowMapper<User> {

	// JDBCTemplate 에서 row 응답을 mapRow() 메서드에 rs 파라미터로 넘겨주어 객체에 매핑하기 쉽도록 도와준다.
  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    var user = new User();
    user.setId(rs.getInt("ID"));
    user.setName(rs.getString("NAME"));
    return user;
  }
}
