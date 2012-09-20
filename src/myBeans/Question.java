package myBeans;

import java.sql.ResultSet;
import java.sql.SQLException;

class Question
{
    private static final String NAME_OF_DRIVER = "FE_DB";
	private static final int NUMBER_OF_COLUMNS = 6;
    private int index;
    private String tableName;
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (index != question.index) return false;
        if (tableName != null ? !tableName.equals(question.tableName) : question.tableName != null
                ) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = index;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        return result;
    }

    public Question(int index,int unit,int level,String testName)
    {

        this.index=index;
        tableName = testName + unit + "_" + level;
    }
    public String showQuestion()
    {
        return index + " " + tableName;
    }
    public String getQuery()
    {
        return "select Question,O1,O2,O3,O4,Answer from " + tableName + " where number = " + index;
    }
    public String[] getQuestionDetails() throws ClassNotFoundException, SQLException
    {
        DatabaseConnection databaseConnection=new DatabaseConnection(NAME_OF_DRIVER);
        ResultSet resultSet=databaseConnection.executeQuery(getQuery());
        resultSet.next();
        String questionDetails[]=new String[NUMBER_OF_COLUMNS];
        int counter=0;
        do
        {
            questionDetails[counter]=resultSet.getString(counter + 1);
            counter++;
        }while (counter<questionDetails.length);
        databaseConnection.disconnect();
        return questionDetails;
    }
    public int getLevel()
    {
        int level=0;
        char character=getTableName().charAt(getTableName().length()-1);
        level=Integer.parseInt(String.valueOf(character));
        return level;
    }
    public String getTableName() {
        return tableName;
    }
}