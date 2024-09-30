package com.thesun4sky.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication {

	public static void main(String[] args) throws SQLException {
		// 어플리케이션 실행 컨텍스트 생성
		SpringApplication.run(JdbcApplication.class, args);

		// 데이터베이스 연결정보
		String url = "jdbc:h2:mem:test"; 	// spring.datasource.url
		String username = "sa";				// spring.datasource.username

		// connection 얻어오기
		// try-with-resource : try문 안에서 사용할 자원을 괄호안에 선언해 주면서 try문이 종료되면 자동으로 자원 해제가 됨
		try (Connection connection = DriverManager.getConnection(url, username, null)) {
			try {
				// 테이블 생성 (statement 생성)
				String creatSql = "CREATE TABLE USERS (id SERIAL, username varchar(255))";
				try (PreparedStatement statement = connection.prepareStatement(creatSql)) {
					statement.execute();
				}

				// 데이터 추가 (statement 생성)
				String insertSql = "INSERT INTO USERS (username) VALUES ('sumin kang')";
				try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
					for(int i=0; i<10; i++) {
						statement.execute();
					}
				}

				// 데이터 조회 (statement 생성 후 rs = resultSet 수신 & next() 조회)
				String selectSql = "SELECT * FROM USERS";
				try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
					var rs = statement.executeQuery(); // 쿼리 결과를 rs에 저장함
					while (rs.next()) { // 결과값을 하나씩 받아서 출력해 줌
						System.out.printf("%d, %s\n", rs.getInt("id"), rs.getString("username"));
					}
				}
			} catch (SQLException e) { // SQL 예외 처리
				if (e.getMessage().equals("ERROR: relation \"account\" already exists")) {
					System.out.println("USERS 테이블이 이미 존재합니다.");
				} else {
					throw new RuntimeException();
				}
			}
		}
	}
}
