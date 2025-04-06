package me.simulatan.htl.testautomation

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.testcontainers.perSpec
import io.kotest.matchers.shouldBe
import org.testcontainers.containers.PostgreSQLContainer
import java.sql.Connection
import java.sql.DriverManager
import java.util.Properties


class PostgresTest : FunSpec(
	{
		val postgres = PostgreSQLContainer("postgres:17-alpine")
			.withPassword("mypassword6969")
		listener(postgres.perSpec()) // lifecycle is the same as this FunSpec => shared DB for all tests

		lateinit var conn: Connection
		beforeSpec {
			var dbProps = Properties().apply {
				setProperty("user", postgres.username)
				setProperty("password", postgres.password)
			}
			conn = DriverManager.getConnection(postgres.jdbcUrl, dbProps)
			conn.createStatement()
				.execute("CREATE TABLE test_table (id SERIAL PRIMARY KEY, name VARCHAR(50))")
		}

		val testName = "TestName"
		test("Can insert into database") {
			val statement = conn.prepareStatement("INSERT INTO test_table (name) VALUES (?)")
			statement.setString(1, testName)
			statement.execute()
		}

		test("Can read from database") {
			val statement = conn.createStatement()
			val resultSet = statement.executeQuery("SELECT * FROM test_table")
			resultSet.next()
			val name = resultSet.getString("name")
			name shouldBe testName
		}
	})
