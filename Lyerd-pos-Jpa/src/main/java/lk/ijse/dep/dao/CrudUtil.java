package lk.ijse.dep.dao;

public class CrudUtil {
//    public static <T> T execute(String sql,Object... parameters) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            int i = 0;
////            System.out.println(parameters[0]);
//            for (Object parameter : parameters) {
//                i++;
//                pstm.setObject(i,parameter);
//            }
//            if (sql.startsWith("SELECT")){
//                return (T) pstm.executeQuery();//reference data type
//            }
//            return (T) ((Boolean)(pstm.executeUpdate()>0));//Booleon(Primitive)boxing
//    }

/*    public static boolean executeUpdate(String sql,Object... parameters){
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            int i = 0;
            System.out.println(parameters[0]);
            for (Object parameter : parameters) {
                i++;
                pstm.setObject(i,parameter);
            }
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static ResultSet executeQuery(String sql, Object... parameters){
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            int i = 0;
            System.out.println(parameters[0]);
            for (Object parameter : parameters) {
                i++;
                pstm.setObject(i,parameter);
            }
            return pstm.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }*/
}
