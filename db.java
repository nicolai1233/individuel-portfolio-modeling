package university;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db {

private String url = "jdbc:postgresql://hattie.db.elephantsql.com:5432/hsbalsct" ;
private String username;
private String password;
Connection db = null;


public db (String Username, String Password) throws SQLException{
	this.username = Username;
	this.password = Password;
	
	try{
	Class.forName("org.postgresql.Driver");
	db = DriverManager.getConnection(url, Username, Password);
 	}
	catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
     }
}
 


public void addPerson(String email, int age, String name, String type){
	try {
	      Statement st = db.createStatement();
	      DatabaseMetaData dbm = db.getMetaData();
	      ResultSet tables = dbm.getTables(null, null, "Person", null);
	       if(tables.next()){
	         
	        }else{
	          ResultSet rs = st.executeQuery("CREATE TABLE Person ( email  varchar(255)  , age  int ,  name  varchar(255) ,  type  varchar(255)   , PRIMARY KEY(email));");  
	            }
	            st.close();
	            //st2.close();
	            }
	        catch (java.sql.SQLException e) {
	            System.out.println(e.getMessage());
	        }try {
	            Statement st = db.createStatement();
	            if(age > 18 && age < 36 && type == "Student" || type == "Teacher"){
	            ResultSet rs2 = st.executeQuery("INSERT INTO Person(email)VALUES('email');");
	            rs2.close();
	            st.close();
	             }
	            }
	        catch (java.sql.SQLException e) {
	            System.out.println(e.getMessage());
	        } 
	
}

public void addCourse(int CourseID, String CourseName, String teacherName){
	try {
	      Statement st = db.createStatement();
	      DatabaseMetaData dbm = db.getMetaData();
	      ResultSet tables = dbm.getTables(null, null, "Course", null);
	       if(tables.next()){
	         
	        }else{
	          ResultSet rs = st.executeQuery("CREATE TABLE Course ( CourseID  int  , CourseName  varchar(255) ,  teacherName  varchar(255)   , PRIMARY KEY(CourseID));");  
	            }
	            st.close();
	            //st2.close();
	            }
	        catch (java.sql.SQLException e) {
	            System.out.println(e.getMessage());
	        }try {
	            Statement st = db.createStatement();
	            ResultSet rs2 = st.executeQuery("INSERT INTO Course(CourseID , CourseName , teacherName)VALUES(CourseID , 'CourseName' , 'teacherName');");
	            rs2.close();
	            st.close();
	            }
	        catch (java.sql.SQLException e) {
	            System.out.println(e.getMessage());
	        } 
	
}

 
public void selectStudent(){
       try {
           Statement st = db.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM Person WHERE type = 'Student'   ");
	
           while (rs.next()) {
             System.out.print("email returned ");
             System.out.println(rs.getString(1));
             //2
             System.out.print("age returned ");
             System.out.println(rs.getString(2));
             //3
             System.out.print("name returned ");
             System.out.println(rs.getString(3));
             //4
             System.out.print("type returned ");
             System.out.println(rs.getString(4));
             //5
           }
           rs.close();
           st.close();
           }
       catch (java.sql.SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   


 public void removePerson(String email){
        try {
            
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("Delete FROM Person WHERE email  ='"+email+"' " );
          
            rs.close();
            st.close();
            }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

 public void removeStudent(){
        try {
            
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("Delete FROM Person WHERE type = 'Student' " );
          
            rs.close();
            st.close();
            }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    


 public void relationshipPeco(String email, int courseID){
         try {
             Statement st = db.createStatement();
             DatabaseMetaData dbm = db.getMetaData();
             ResultSet tables = dbm.getTables(null, null,"Peco" , null);
             if(tables.next()){
                 
             }else{
              ResultSet rs = st.executeQuery("CREATE TABLE Peco(email varchar(255), courseID int , FOREIGN KEY(email) REFERENCES Person (email),  FOREIGN KEY(courseID) REFERENCES Course (courseID));");
                        
             }
             
             st.close();
             //st2.close();
             }
         catch (java.sql.SQLException e) {
             System.out.println(e.getMessage());
         }try {
             Statement st = db.createStatement();
             ResultSet rs2 = st.executeQuery("INSERT INTO Peco(email, courseID) VALUES('"+email+"', "+courseID+" );");
             rs2.close();
             st.close();
             //st2.close();
             }
         catch (java.sql.SQLException e) {
             System.out.println(e.getMessage());
         }
     }
 
}
