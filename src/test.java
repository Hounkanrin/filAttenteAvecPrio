import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

	public static void main(String[] args) {
		
					
				//connnexion � la base 
				System.out.println("On charge le DRIVE");
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				//	System.out.println(" drive charg�"); 
					
				// creation d'une table 'mysql:host=localhost;dbname=pcvdb;charset=utf8', 'root', ''
					
					
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/tpbeans","root","");
					
//				 	creation de l'objet Statement 
					
					Statement state = conn.createStatement();
							
				// l'objet result le resultas de la requete SQL
					//String sql="INSERT INTO user (id_user, login, mdp) VALUES ( , 'Viviane', 'anne')";
					
					String sql="INSERT INTO user (id_user, login, mdp) VALUES (2, 'Vivi', 'anne')";
					int result= state.executeUpdate(sql);
				
		    
		if (result !=0) {System.out.println("ins�r�e avec succ�s");
						}
		else {System.out.println("insertion non r�alis�e");
			
		}
					//
					conn.close();
					}
				catch (ClassNotFoundException e) {
					System.err.println("Drive non charg� ! "); 
					e.printStackTrace();
			
		} catch (SQLException e) {
			System.err.println("Probl�me de connexion ou de sql "); 
		}
}
					
				
		
		
	}


