package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;

import java.sql.*;
import java.util.*;

public class DBUtils {

	private static final Logger LOGGER = LogManager.getLogger(DBUtils.class);

	private static final String URL = ConfigManager.getProperty("db_URL");

	private static final String USERNAME = ConfigManager.getProperty("db_username");

	private static final String PASSWORD = System.getenv("db_password");

	static {
		try {
			Class.forName("org.postgresql.Driver");
			LOGGER.info("PostgreSql JDBC Driver loaded");
		}
		catch (ClassNotFoundException e) {
			LOGGER.error("PostgreSql JDBC Driver not found");
		}
	}

	public static Connection getConnection() throws SQLException {
		try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch (SQLException e) {
			LOGGER.error("Database connection problem occurred!");
			throw new SQLException();
		}
	}

	public static List<Map<String, Object>> executeQuery(String query) {
		List<Map<String, Object>> resultList = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			ResultSetMetaData metaData = resultSet.getMetaData();
			int countOfColumn = metaData.getColumnCount();

			while (resultSet.next()) {
				Map<String, Object> currentMap = new HashMap<>();

				for (int i = 1; i <= countOfColumn; i++) {
					currentMap.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(currentMap);
			}

		}
		catch (SQLException e) {
			LOGGER.error("SQL Exception during executeQueryForMapList method");
		}

		return resultList;

	}

	public static <T> List<T> executeQuery(String query, RowMapper<T> rowMapper) {
		List<T> resultList = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			while (resultSet.next()) {
				resultList.add(rowMapper.mapRow(resultSet));
			}

		}
		catch (SQLException e) {
			LOGGER.error("SQL Exception during executeQueryForMapList method");
		}
		return resultList;
	}

}
