import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Connection  conn = JDBCUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {


            statement=conn.createStatement();

            //准备sql操作语句
            String sql= "select * from TestCase ";

            resultSet = statement.executeQuery(sql);
            ResultSetMetaData md = resultSet.getMetaData();
            String columnName;
            Object columnValue;
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            while (resultSet.next()) {
                Map<String, Object> rowResult = new HashMap<String, Object>();
                for (int j = 1; j <= md.getColumnCount(); j++) {
                    columnName = md.getColumnName(j);
                    columnValue = resultSet.getObject(j);
                    rowResult.put(columnName, columnValue);
                }
                resultList.add(rowResult);
            }
            updateCase(resultList);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            JDBCUtil.close(conn, statement, resultSet);
        }
    }

    private static void updatesteps(String updatesteps, Long id) {
        Connection  conn = JDBCUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pstmt;
        try {
            String sql= "update TestCase set testSteps=? where Id=?";
            pstmt  =conn.prepareStatement(sql);
            pstmt.setString(1,updatesteps);
            pstmt.setLong(2,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(id);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            JDBCUtil.close(conn, statement, resultSet);
        }
    }

    /**
     * @param resultList
     * @throws Exception
     */
    private static void updateCase(List<Map<String, Object>> resultList) throws Exception {
        for(Map<String, Object> map:resultList){
            String stepsJson = map.get("testSteps").toString();
            JSONObject treeJson=JSON.parseObject(stepsJson);
            getTestTree(treeJson);
            String updatesteps = JSON.toJSONString(treeJson);
            Long id = (Long) map.get("Id");
            updatesteps(updatesteps,id);
        }
    }

    /**
     * @param treeJson
     * @return
     * @throws Exception
     */
    private static void getTestTree(JSONObject treeJson) throws Exception {

        Object pluginId = treeJson.get("pluginId");
        if(pluginId!=null&&"89".equals(pluginId.toString())){
            treeJson.put("pluginId","97");
        }
        if(pluginId!=null&&"91".equals(pluginId.toString())){
            treeJson.put("pluginId","90");
        }


        JSONArray subTreeArray = treeJson.getJSONArray("subTree");
        if(subTreeArray != null) {
            for (int i = 0; i < subTreeArray.size(); i++) {
                getTestTree(subTreeArray.getJSONObject(i));
            }
        }
    }
}
