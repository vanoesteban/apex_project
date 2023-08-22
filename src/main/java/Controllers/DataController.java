package Controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;


import java.io.FileReader;
import java.lang.reflect.Method;

public class DataController {


    /**
     * fetchData method to retrieve test data for specified json
     *
     * @throws Exception
     * @return
     */
    @DataProvider(name = "fetchData_JSON")
    public Object[][] fetchData(Method method) throws Exception {

        FileReader reader = new FileReader(Global_Vars.CREDS_PROPS);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        JSONObject dataobj = (JSONObject) obj;
        JSONArray dataArray = (JSONArray) dataobj.get("data");

        String arrayResult[][]= new String[dataArray.size()][2];
        String FinalResult[][] = new String[dataArray.size()][2];

        for (int i = 0; i<dataArray.size(); i++){
            JSONObject blockData = (JSONObject) dataArray.get(i);
            arrayResult[0][i] = (String) blockData.get("email");
            arrayResult[1][i] = (String) blockData.get("password");
        }

        switch(method.getName()){
            case "tc001_create_and_login_account":
                FinalResult[0][1] = arrayResult[0][1];
                FinalResult[1][1] = arrayResult[1][1];

        }

        return  FinalResult;
    }

//    public void connect_to_database() throws SQLException, ClassNotFoundException {
//        //string
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection connection = DriverManager.getConnection(Global_Vars.HOST, Global_Vars.username, Global_Vars.password);
//        Statement stm = connection.createStatement();
//        ResultSet result = stm.executeQuery(Global_Vars.query_example);
//        System.out.println("Database: " + result);
//        connection.close();
//    }

}
