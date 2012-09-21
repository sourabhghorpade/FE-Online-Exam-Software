package myBeans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class TestConfiguration
	{

		final private String testName;
		final private int[] numberOfQuestionPerUnitLevelwise;
		final private int numberOfUnits;

		public TestConfiguration(String testName, int numberOfQuestionsPerUnitLevelwise[], int numberOfUnits)
				throws NullPointerException, ClassNotFoundException, SQLException, InvalidValue
			{
				this.testName = testName;
				this.numberOfQuestionPerUnitLevelwise = numberOfQuestionsPerUnitLevelwise;
				this.numberOfUnits = numberOfUnits;
				validateConfiguration();
			}

		private void validateConfiguration() throws InvalidValue, NullPointerException, ClassNotFoundException, SQLException
			{
				if (testName == null || testName.equals(""))
					throw new InvalidValue("Invalid Test Name.");

				if (notUniqueTestName(testName))
					throw new InvalidValue("Duplicate Test Name");
				
				// TODO Check if the question set is valid :
				// NoOfQuestions<=totalQuestions,table exists etc
			}

		private boolean notUniqueTestName(String testName2) throws NullPointerException, SQLException,
				ClassNotFoundException
			{
				boolean unique;
				DatabaseConnection databaseConnection = new DatabaseConnection("FE_DB");
				String queryToCheckIfTestNameIsUnique = "select testName from test_configuration;";
				ResultSet resultSet = databaseConnection.executeQuery(queryToCheckIfTestNameIsUnique);
				if (resultSet.next())
					unique = true;
				else
					unique = false;
				databaseConnection.disconnect();
				return unique;
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