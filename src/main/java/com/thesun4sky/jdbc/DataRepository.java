package com.thesun4sky.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 테이블 생성
	public void createTable() {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (id SERIAL, name VARCHAR(255))");
	}

	// 사용자 추가 (Create)
	public void insertUser(String name) {
		jdbcTemplate.update("INSERT INTO users (name) VALUES (?)", name);
	}

	// 사용자 ID로 User 조회 (Read)
	public User findUserById(Long id) {
		return jdbcTemplate.queryForObject(
			"SELECT * FROM users WHERE id = ?",
			new UserRowMapper(), // 이자리에 매퍼를 생성자로 넣어주면 됨
			id
		);
	}

	// 사용자 이름 변경 (Update)
	public void updateUser(Long id, String newName) {
		jdbcTemplate.update("UPDATE users SET name = ? WHERE id = ?", newName, id);
	}

	// 사용자 삭제 (Delete)
	public void deleteUser(Long id) {
		jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
	}
}
