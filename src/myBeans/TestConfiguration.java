package myBeans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class TestConfiguration
	{
		final private String testName;
		final private int[] numberOfQuestionPerUnitLevelwise;
		final private int numberOfUnits;

		private final static String NAME_OF_DRIVER = "FE_DB";
		private DatabaseConnection databaseConnection;

		public TestConfiguration(String testName, int numberOfQuestionsPerUnitLevelwise[],
				int numberOfUnits) throws NullPointerException, ClassNotFoundException,
				SQLException, InvalidValue
			{
				this.testName = testName;
				this.numberOfQuestionPerUnitLevelwise = numberOfQuestionsPerUnitLevelwise;
				this.numberOfUnits = numberOfUnits;
				validateConfiguration();
			}

		public static TestConfiguration retrieveTestConfiguration(String testName)
				throws ClassNotFoundException, SQLException, NullPointerException, InvalidValue
			{
				int questionPerUnit[] = new int[12], easyQuestions, difficultQuestions;
				DatabaseConnection databaseConnection = new DatabaseConnection(NAME_OF_DRIVER);
				String query = "select number_of_questions from config_details where testName='"
						+ testName + "';";
				ResultSet resultSet;
				try
					{
						resultSet = databaseConnection.executeQuery(query);
					}
				catch (SQLException e)
					{
						databaseConnection.disconnect();
						throw e;
					}
				int i;
				for (i = 0; resultSet.next(); i += 2)
					{
						easyQuestions = resultSet.getInt("number_of_questions");
						resultSet.next();
						difficultQuestions = resultSet.getInt("number_of_questions");
						questionPerUnit[i] = easyQuestions + difficultQuestions;
						questionPerUnit[i + 1] = difficultQuestions;
					}

				databaseConnection.disconnect();
				if(i==0)
					throw new InvalidValue("Invalid TestName");
				return new TestConfiguration(testName, questionPerUnit, i);
			}

		public static void deleteTestConfiguration(String testName) throws ClassNotFoundException,
				SQLException
			{
				DatabaseConnection databaseConnection = new DatabaseConnection(NAME_OF_DRIVER);
				databaseConnection.executeUpdate("delete from test_configuration where testName='"
						+ testName + "';");
				databaseConnection.disconnect();
			}

		public void insertIntoDatabase() throws ClassNotFoundException, SQLException, InvalidValue
			{
				if (notUniqueTestName())
					throw new InvalidValue("Duplicate Test Name");
				final int LEVEL1 = 1, LEVEL2 = 2;
				databaseConnection = null;
				try
					{
						databaseConnection = new DatabaseConnection(NAME_OF_DRIVER);
						databaseConnection.executeUpdate("insert into test_configuration values('"
								+ testName + "');");
						for (int unitNumber = 1, counter = 0; unitNumber <= numberOfUnits; unitNumber++, counter += 2)
							{
								/*
								 * if
								 * (getNumberOfQuestionPerUnitLevelwise()[counter
								 * ] == NONE
								 * 
								 * &&
								 * getNumberOfQuestionPerUnitLevelwise()[counter
								 * + 1] == NONE) continue;
								 */
								try
									{
										String Query = "insert into config_details values('"
												+ testName + "'," + (unitNumber) + "," + LEVEL1
												+ "," + numberOfQuestionPerUnitLevelwise[counter]
												+ ");";
										databaseConnection.executeUpdate(Query);
										Query = "insert into config_details values('" + testName
												+ "'," + (unitNumber) + "," + LEVEL2 + ","
												+ numberOfQuestionPerUnitLevelwise[counter + 1]
												+ ");";
										databaseConnection.executeUpdate(Query);

									}
								catch (SQLException duplicateRecord)
									{
										duplicateRecord.printStackTrace();
										databaseConnection
												.executeUpdate("delete from test_configuration where testName='"
														+ testName + "';");
										throw new InvalidValue("Duplicate Record.");
									}
								catch (Exception e)
									{
										databaseConnection
												.executeUpdate("delete from test_configuration where testName='"
														+ testName + "';");
									}
							}
					}
				finally
					{
						if (databaseConnection != null)
							databaseConnection.disconnect();
					}
			}

		private void validateConfiguration() throws InvalidValue, NullPointerException,
				ClassNotFoundException, SQLException
			{
				if (testName == null || testName.equals(""))
					throw new InvalidValue("Invalid Test Name.");
				// TODO Check if the question set is valid :
				// NoOfQuestions<=totalQuestions,table exists etc
			}

		private boolean notUniqueTestName() throws NullPointerException, SQLException,
				ClassNotFoundException
			{
				boolean notUnique;
				databaseConnection = new DatabaseConnection(NAME_OF_DRIVER);
				String queryToCheckIfTestNameIsUnique = "select testName from test_configuration where testName='"
						+ testName + "';";
				ResultSet resultSet = databaseConnection
						.executeQuery(queryToCheckIfTestNameIsUnique);
				if (resultSet.next())
					notUnique = true;
				else
					notUnique = false;
				databaseConnection.disconnect();
				return notUnique;
			}

		public final String getTestName()
			{
				return testName;
			}

		public final int[] getNumberOfQuestionPerUnitLevelwise()
			{
				return numberOfQuestionPerUnitLevelwise;
			}

		public final int getNumberOfUnits()
			{
				return numberOfUnits;
			}

	}