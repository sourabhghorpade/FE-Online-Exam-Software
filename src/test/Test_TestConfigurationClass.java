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

		@Test(expected = InvalidValue.class)
		public void shouldNotAcceptDuplicateTestName() throws NullPointerException,
				ClassNotFoundException, SQLException, InvalidValue
			{
				int arr[] =
					{ 2, 2, 2, 2 };
				new TestConfiguration("Phy", arr, 4);
			}

	}
