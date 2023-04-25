package kafkaclone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class ConnectDB {
   public static void main(String[]args){
      Connection con = null;
      try {
         con = DriverManager.
         getConnection("jdbc:mysql://localhost:3306/Kafka?useSSL=false", "root", "^loggeringlogging#2019$");
         System.out.println("Connection is successful !!!!!");
         PreparedStatement ps = con.prepareStatement("INSERT INTO `Kafka`.`topic`(`topicName`,`topicId`) VALUES ('hello',321);");
         int status = ps.executeUpdate();
         if (status != 0) {
         System.out.println("Database was connectedx");
         System.out.println("Record was inserted");
         }
         else
         {
        	 System.out.println("Record NO");
        	 
         }
        	 
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
}