
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * Created by Administrator on 2018/7/26.
 */
public class GroupOptimize {

    public static void main(String[] args) throws Exception {

        Connection conn;
        Statement statement = null;
        ResultSet resultSet = null;
        conn = JDBCUtil.getConnection();
        try {
            statement = conn.createStatement();
            //准备sql操作语句
            String sql = "select * from TestGroup where Status=1 ";

            resultSet = statement.executeQuery(sql);
            ResultSetMetaData md = resultSet.getMetaData();
            String columnName;
            Object columnValue;
            while (resultSet.next()) {
                Map<String, Object> rowResult = new HashMap<String, Object>();
                for (int j = 1; j <= md.getColumnCount(); j++) {
                    columnName = md.getColumnName(j);
                    columnValue = resultSet.getObject(j);
                    rowResult.put(columnName, columnValue);
                }
                selectGroupId(rowResult);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, statement, resultSet);
        }
    }


    private static void selectGroupId(Map<String, Object> rowResult) {
        Connection conn;
        Statement statement = null;
        ResultSet resultSet = null;
        conn= JDBCUtil.getConnection();
        PreparedStatement pstmt;

        try {
            String sql= "select getParentIdList(?) as parentids";
            pstmt  = conn.prepareStatement(sql);
            pstmt.setInt(1,(Integer)rowResult.get("Id"));
            resultSet = pstmt.executeQuery();
            ResultSetMetaData md = resultSet.getMetaData();

            String columnName;
            Object columnValue;
            Map<String, Object> result = new HashMap<String, Object>();
            while (resultSet.next()) {
                for (int j = 1; j <= md.getColumnCount(); j++) {
                    columnName = md.getColumnName(j);
                    columnValue = resultSet.getObject(j);
                    result.put(columnName, columnValue);
                }
            }

            String [] parentids = ((String) result.get("parentids")).split(",");

            List<String> resultList= new ArrayList<String>(Arrays.asList(parentids));
            String pids = removeDuplicate(resultList);

            updateGroup(pids,(Integer)rowResult.get("Id"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            JDBCUtil.close(conn, statement, resultSet);
        }
    }

    private static String  removeDuplicate(List<String> list) {
        List<String> listTemp = new ArrayList<String>();
        for(int i=list.size()-1;i>0;i--){
            if(!listTemp.contains(list.get(i))){
                listTemp.add(list.get(i).toString());
            }
        }
        return  StringUtils.join(listTemp.toArray(), ".");

    }

    private static void updateGroup(String parentids, int id) {

        Connection conn;
        Statement statement = null;
        ResultSet resultSet = null;
        conn= JDBCUtil.getConnection();
        PreparedStatement pstmt;

        try {
            String sql= "update TestGroup set AncestralIds = ? where Id = ?";
            pstmt  =conn.prepareStatement(sql);
            pstmt.setString(1,parentids);
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
}
