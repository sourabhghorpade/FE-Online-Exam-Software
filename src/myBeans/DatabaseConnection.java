package myBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection
	{
		private Statement statement;
		private Connection connection;

		public ResultSet executeQuery(String Query) throws SQLException,
				NullPointerException
			{
				if (!connection.isClosed())
					{
						return statement.executeQuery(Query);
					} else
					throw new SQLException("Connection is not Open");
			}

		public void executeUpdate(String Query) throws SQLException, NullPointerException
			{
				if (!connection.isClosed())
					{
						statement.execute(Query);
					} 
				else
					throw new SQLException("Connection is not Open");

			}

		private void connect(String nameOfDriver) throws ClassNotFoundException,
				SQLException
			{
				disconnect();
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				connection = DriverManager.getConnection("jdbc:odbc:" + nameOfDriver, "",
						"");
				statement = connection.createStatement();
			}

		public void disconnect() throws SQLException
			{
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

				statement = null;
				connection = null;

			}

		public DatabaseConnection(String nameOfDriver) throws ClassNotFoundException,
				SQLException
			{
				statement = null;
				connection = null;
				this.connect(nameOfDriver);
			}

		@Override
		public void finalize() throws SQLException
			{
				disconnect();
				try
					{
						super.finalize();
					}
				catch (Throwable throwable)
					{
						throwable.printStackTrace();
					}
			}

	}
