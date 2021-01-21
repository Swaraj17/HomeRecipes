package com.abnamro.dkw.recipes.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Component;

/** This class perform DB operations for the unique token
 */
@Component
public class SecurityConfigDao {


	/**This function inserts the unique key and value in the Table.
	 */
	public void getConnection() {
		String query = "INSERT INTO TBL_AUTHORIZATION (id,key, value) VALUES (2,'token_value','Bearer zxzxgjeufbskn89gfd')";
		try {
			Class.forName ("org.h2.Driver"); 
			Connection conn = DriverManager.getConnection ("jdbc:h2:file:~/testdatabase", "sa","");
			Statement st = conn.createStatement(); 
			st.executeUpdate(query); 
			conn.close(); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**This function gets the unique key and value in the Table.
	 * @return token value
	 */
	public String getTokenValue() {
		String query = "SELECT * FROM TBL_AUTHORIZATION WHERE key = 'token_value'";
		String token = "";
		try {
			Class.forName ("org.h2.Driver"); 
			Connection conn = DriverManager.getConnection ("jdbc:h2:file:~/testdatabase", "sa","");
			Statement st = conn.createStatement(); 
			ResultSet resultSet = st.executeQuery(query);
			if(resultSet.next()){
				token = resultSet.getString("VALUE");
			}
			conn.close(); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return token;
	}

}
