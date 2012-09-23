package test;

import java.sql.SQLException;

import myBeans.TestConfiguration;

import org.junit.Test;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class Test_TestConfigurationClass
	{
		@Test(expected = InvalidValue.class)
		public void ShouldNotAcceptNullAsTestName() throws NullPointerException,
				ClassNotFoundException, SQLException, InvalidValue
			{
				int arr[] =
					{ 2, 2, 2, 2 };
				new TestConfiguration(null, arr, 4);
			}

		@Test(expected = InvalidValue.class)
		public void shouldNotAcceptBlankAsTestName() throws NullPointerException,
				ClassNotFoundException, SQLException, InvalidValue
			{
				int arr[] =
					{ 2, 2, 2, 2 };
				new TestConfiguration("", arr, 4);
			}

		@Test
		public void shouldAcceptUniqueTestName() throws NullPointerException,
				ClassNotFoundException, SQLException, InvalidValue
			{
				int arr[] =
					{ 2, 2, 2, 2 };
				new TestConfiguration("Unique", arr, 4);
			}
		@Test
		public void shouldReturnCorrectTestConfiguration() throws NullPointerException,
				ClassNotFoundException, SQLException, InvalidValue
			{
				TestConfiguration.retrieveTestConfiguration("Phy");
			}
		
		@Test(expected=InvalidValue.class)
		public void shouldNotInsertDuplicateTestConfigurationIntoDaabase() throws NullPointerException,
				ClassNotFoundException, SQLException, InvalidValue
			{
				TestConfiguration.retrieveTestConfiguration("Phy").insertIntoDatabase();
			}
		@Test
		public void shouldInsertTestConfigurationIntoDatabase() throws NullPointerException,
				ClassNotFoundException, SQLException, InvalidValue
			{
				int[] numberOfQuestionsPerUnitLevelwise={2,5,5,0,0,0,5,5,0,9,1,3};
				String testName="chem";
				TestConfiguration.deleteTestConfiguration(testName);
				new TestConfiguration(testName, numberOfQuestionsPerUnitLevelwise, 6).insertIntoDatabase();
			}
		@Test(expected=InvalidValue.class)
		public void shouldDetectNonExistingTest() throws NullPointerException,
				ClassNotFoundException, SQLException, InvalidValue
			{
				TestConfiguration.retrieveTestConfiguration("Phy");
			}
	}
