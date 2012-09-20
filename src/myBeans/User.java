package myBeans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User
	{
        private static final String REASON = "Username is not Available.Please Try Another one";
        private static final int INITIAL_SCORE = 0;
        private static final int INITIAL_LEVEL = 1;
        private static final int INITIAL_TIME_IN_MINUTES = 20;
        private static final int INITIAL_TIME_IN_SECONDS = 0;

        private String username;
		private String password, name, yr, cno;
		private int min, sec;
		private int score;

		public static void register(String username,String password,String name,String year,
                                    String contactNumber) throws SQLException, ClassNotFoundException {
                DatabaseConnection databaseConnection = null;
                try
                {
                    databaseConnection = new DatabaseConnection("stud");
                    databaseConnection.executeUpdate("insert into student values ('" +
                            username + "','" + password + "','"+
                            name + "'," + INITIAL_SCORE + "," + INITIAL_LEVEL + ","
                            +contactNumber+ ",'"+year+ "'," + INITIAL_TIME_IN_MINUTES + "," +
                            INITIAL_TIME_IN_SECONDS + ");");
                    databaseConnection.disconnect();
                }
                catch (SQLException e)
                    {
                    if(databaseConnection!=null)
                        databaseConnection.disconnect();
                    if(e.getMessage().equals("General error"))
                        throw new SQLException(REASON);
                    else throw e;
                    }
        }
		
		public User(String username,String password)
                throws SQLException, ClassNotFoundException
		{

			ResultSet resultSet;
			DatabaseConnection databaseConnection=new DatabaseConnection("stud");
			resultSet=databaseConnection.executeQuery("select * from student where username='"+ username +"' and password='" + password
					+ "'");
			if(resultSet.next())
			{
				this.username=username;
				setUsername(username);
				this.password=password;
				setMin(Integer.parseInt(resultSet.getString("min")));
				setSec(Integer.parseInt(resultSet.getString("sec")));
				name=resultSet.getString("names");
				score=Integer.parseInt(resultSet.getString("score"));
				cno=resultSet.getString("cno");
				yr=resultSet.getString("yr");
				databaseConnection.disconnect();
            }
            else
                {
                	databaseConnection.disconnect();
                	throw new SQLException("Invalid UserName or Password");
                }
		}
	
		public void clear()
			{
				username=password=name=yr=cno=null;
				min=sec=score=0;
			}
		public void update_info()
			{
				try
					{
						DatabaseConnection databaseConnection = new DatabaseConnection(
								"stud");
						databaseConnection.executeUpdate("update student set score = "
								+ getScore() + ",min = " + getMin() + ",sec = "
								+ getSec() + " where username = '" + getUsername() + "'");
						databaseConnection.disconnect();
					}
				catch (SQLException e1)
					{
						System.out.println("Caught Exception");
						System.out.println(e1.getMessage());
					}
				catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}
			}

		public void check_answer(boolean correct, int level)
			{
				switch (level)
					{
					case INITIAL_LEVEL:
						if (correct)
							score += 2;
						else
							score--;
						break;
					case 2:
						if (correct)
							score += 3;
						else
							score -= 2;
						break;
					case 3:
						if (correct)
							score += 5;
						else
							score -= 3;
						break;
					}
				update_info();
			}

		public String getUsername()
			{
				return username;
			}

		public void setUsername(String username)
			{
				this.username = username;
			}

		public String getPassword()
			{
				return password;
			}

		public void setPassword(String password)
			{
				this.password = password;
			}

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public String gettime()
			{
				return min + ":" + sec;
			}

		public int getMin()
			{
				return min;
			}

		public void setMin(int min)
			{
				this.min = min;
			}

		public int getSec()
			{
				return sec;
			}

		public void setSec(int sec)
			{
				this.sec = sec;
			}

		public void settime(String time)
			{

				String[] array;
				String delimiter = ":";
				array = time.split(delimiter);
				try
					{
						min = Integer.parseInt(array[0]);
						sec = Integer.parseInt(array[INITIAL_LEVEL]);
					}
				catch (NumberFormatException e)
					{
						e.printStackTrace();
					}
			}

		public String getYr()
			{
				return yr;
			}

		public void setYr(String yr)
			{
				this.yr = yr;
			}

		public int getScore()
			{
				return score;
			}

		public void setScore(int score)
			{
				this.score = score;
			}

		public String getCno()
			{
				return cno;
			}

		public void setCno(String cno)
			{
				this.cno = cno;
			}

	}
