package myBeans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class Test
	{
		private static final String NAME_OF_DRIVER = "FE_DB";
		private String testName;
		private static final int NUMBER_OF_UNITS_IN_CURRENT_TEST = 6;
		private Question questionSet[];
		private String Question, Option1, Option2, Option3, Option4;
		public int answerSelected[], answers[], level[];
		private int currentQuestionNumber;
		private int flag = 0;

		public static void addTest(TestConfiguration testConfiguration)
				throws ClassNotFoundException, SQLException, InvalidValue
			{
				int NONE = 0;
				int LEVEL1 = 1, LEVEL2 = 2;
				DatabaseConnection databaseConnection = null;
				try
					{
						databaseConnection = new DatabaseConnection("FE_DB");
						databaseConnection
								.executeUpdate("insert into test_configuration values('"
										+ testConfiguration.getTestName() + "');");
						for (int unitNumber = 1, counter = 0; unitNumber <= testConfiguration
								.getNumberOfUnits(); unitNumber++, counter += 2)
							{
								if (testConfiguration
										.getNumberOfQuestionPerUnitLevelwise()[counter] == NONE
										&& testConfiguration
												.getNumberOfQuestionPerUnitLevelwise()[counter + 1] == NONE)
									continue;
								try
									{
										String Query = "insert into config_details values('"
												+ testConfiguration.getTestName()
												+ "',"
												+ (unitNumber)
												+ ","
												+ LEVEL1
												+ ","
												+ testConfiguration
														.getNumberOfQuestionPerUnitLevelwise()[counter]
												+ ");";
										databaseConnection.executeUpdate(Query);
										Query = "insert into config_details values('"
												+ testConfiguration.getTestName()
												+ "',"
												+ (unitNumber)
												+ ","
												+ LEVEL2
												+ ","
												+ testConfiguration
														.getNumberOfQuestionPerUnitLevelwise()[counter + 1]
												+ ");";
										databaseConnection.executeUpdate(Query);

									}
								catch (SQLException duplicateRecord)
									{
										duplicateRecord.printStackTrace();
										databaseConnection
												.executeUpdate("delete from test_configuration where testName='"
														+ testConfiguration.getTestName()
														+ "';");
										throw new InvalidValue("Duplicate Record.");
									}
								catch (Exception e)
									{
										databaseConnection
												.executeUpdate("delete from test_configuration where testName='"
														+ testConfiguration.getTestName()
														+ "';");
									}
							}
					}
				finally
					{
						if (databaseConnection != null)
							databaseConnection.disconnect();
					}
			}

		public void showQuestionSet() throws ClassNotFoundException, SQLException
			{
				int counter;
				for (counter = 0; counter < questionSet.length; counter++)
					{
						retrieveQuestion(counter);
					}
			}

		public void retrieveQuestion(int index) throws ClassNotFoundException,
				SQLException
			{
				String question[] = questionSet[index].getQuestionDetails();
				setQuestion(question[0]);
				setOption1(question[1]);
				setOption2(question[2]);
				setOption3(question[3]);
				setOption4(question[4]);
				setAnswers(index, Integer.parseInt(question[5]));
				setLevel(index, questionSet[index].getLevel());
			}

		private int[] getTableLimit(int level) throws ClassNotFoundException,
				SQLException
			{
				int limits[];
				limits = new int[NUMBER_OF_UNITS_IN_CURRENT_TEST];
				DatabaseConnection databaseConnection = new DatabaseConnection(
						NAME_OF_DRIVER);
				String query;
				for (int counter = 1; counter <= limits.length; counter++)
					{
						query = "select count(*) from " + testName + counter + "_"
								+ level + ";";
						ResultSet resultSet = databaseConnection.executeQuery(query);
						resultSet.next();
						limits[counter - 1] = resultSet.getInt(1);
					}
				databaseConnection.disconnect();
				return limits;
			}

		public Test(int[] arguments, String testName) throws ClassNotFoundException,
				SQLException
			{

				int counter, anotherCounter, number, limitForTable, unitPointer = 0, noOfDifficultQns, questionCounter = 0;
				this.testName = testName;
				totalNumberOfQuestions = arguments[0] + arguments[2] + arguments[4]
						+ arguments[6] + arguments[8] + arguments[10];
				questionSet = new Question[totalNumberOfQuestions];
				answerSelected = new int[totalNumberOfQuestions];
				level = new int[totalNumberOfQuestions];
				answers = new int[totalNumberOfQuestions];
				marked = new boolean[totalNumberOfQuestions];
				currentQuestionNumber = 0;
				int limitOfLevel1Questions[] = getTableLimit(1);
				int limitOfLevel2Questions[] = getTableLimit(2);

				Random random = new Random();
				for (counter = 0; counter < NUMBER_OF_UNITS_IN_CURRENT_TEST; counter++, unitPointer += 2)
					{
						limitForTable = arguments[unitPointer];
						noOfDifficultQns = arguments[unitPointer + 1];
						for (anotherCounter = 0; anotherCounter < limitForTable; anotherCounter++)
							{
								int questionLevel;
								int selectedTableLimit[];
								if (anotherCounter < limitForTable - noOfDifficultQns)
									{
										questionLevel = 1;
										selectedTableLimit = limitOfLevel1Questions;
									} else
									{
										questionLevel = 2;
										selectedTableLimit = limitOfLevel2Questions;
									}
								number = random.nextInt(selectedTableLimit[counter]);
								if (number == 0
										|| this.checkForDuplicate(questionCounter,
												new Question(number, counter + 1, 1,
														testName)))
									{
										anotherCounter--;
										continue;
									}
								questionSet[questionCounter++] = new Question(number,
										counter + 1, questionLevel, testName);
							}
					}
			}

		public Test(int[] arguments) throws ClassNotFoundException, SQLException
			{
				int counter, anotherCounter, number, limitForTable, unitPointer = 0, noOfDifficultQns, questionCounter = 0;
				totalNumberOfQuestions = arguments[0] + arguments[2] + arguments[4]
						+ arguments[6] + arguments[8] + arguments[10];
				questionSet = new Question[totalNumberOfQuestions];
				answerSelected = new int[totalNumberOfQuestions];
				level = new int[totalNumberOfQuestions];
				answers = new int[totalNumberOfQuestions];
				marked = new boolean[totalNumberOfQuestions];
				currentQuestionNumber = 0;
				int limitOfLevel1Questions[] = getTableLimit(1);
				int limitOfLevel2Questions[] = getTableLimit(2);

				Random random = new Random();
				for (counter = 0; counter < NUMBER_OF_UNITS_IN_CURRENT_TEST; counter++, unitPointer += 2)
					{
						limitForTable = arguments[unitPointer];
						noOfDifficultQns = arguments[unitPointer + 1];
						for (anotherCounter = 0; anotherCounter < limitForTable; anotherCounter++)
							{
								int questionLevel;
								int selectedTableLimit[];
								if (anotherCounter < limitForTable - noOfDifficultQns)
									{
										questionLevel = 1;
										selectedTableLimit = limitOfLevel1Questions;
									} else
									{
										questionLevel = 2;
										selectedTableLimit = limitOfLevel2Questions;
									}
								number = random.nextInt(selectedTableLimit[counter]);
								if (number == 0
										|| this.checkForDuplicate(questionCounter,
												new Question(number, counter + 1, 1,
														testName)))
									{
										anotherCounter--;
										continue;
									}
								questionSet[questionCounter++] = new Question(number,
										counter + 1, questionLevel, testName);
							}
					}
			}

		public int getAnswerSelected(int index)
			{
				return answerSelected[index];
			}

		public void setAnswerSelected(int index, int value)
			{
				answerSelected[index] = value;
			}

		public boolean getMarked(int index)
			{
				return marked[index];
			}

		public void setMarked(int index, boolean value)
			{
				marked[index] = value;
			}

		public boolean checkForDuplicate(int numberOfQuestions, Question question)
			{

				for (int anotherCounter = 0; anotherCounter < numberOfQuestions; anotherCounter++)
					{
						if (question.equals(questionSet[anotherCounter]))
							return true;
					}
				return false;
			}

		public String getTestName()
			{
				return testName;
			}

		public int getLevel(int index)
			{
				return level[index];
			}

		private void setLevel(int index, int level)
			{
				this.level[index] = level;
			}

		public int getAnswers(int index)
			{
				return answers[index];
			}

		private void setAnswers(int index, int answer)
			{
				this.answers[index] = answer;
			}

		public boolean marked[];
		private int totalNumberOfQuestions;

		public int getTotalNumberOfQuestions()
			{
				return totalNumberOfQuestions;
			}

		public int getCurrentQuestionNumber()
			{
				return currentQuestionNumber;
			}

		public void setCurrentQuestionNumber(int currentQuestionNumber)
			{
				this.currentQuestionNumber = currentQuestionNumber;
			}

		public void setTestName(String testName)
			{
				this.testName = testName;
			}

		public String getQuestion()
			{
				return Question;
			}

		private void setQuestion(String question)
			{
				Question = question;
			}

		public String getOption1()
			{
				return Option1;
			}

		private void setOption1(String option1)
			{
				Option1 = option1;
			}

		public String getOption2()
			{
				return Option2;
			}

		private void setOption2(String option2)
			{
				Option2 = option2;
			}

		public String getOption3()
			{
				return Option3;
			}

		private void setOption3(String option3)
			{
				Option3 = option3;
			}

		public String getOption4()
			{
				return Option4;
			}

		private void setOption4(String option4)
			{
				Option4 = option4;
			}

		public void setFlag(int Flag)
			{
				flag = Flag;
			}

		public int getFlag()
			{
				return flag;
			}
	}
