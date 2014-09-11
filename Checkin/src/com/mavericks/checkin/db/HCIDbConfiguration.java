/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABIDbConfiguration.java
 * @Project:
 *		 Abharan
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright © 2014, 101 Mavericks.
 *		Written under contract by Chipsy Information Technology.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by vijayalaxmi on 22-Aug-2014
 */
package com.mavericks.checkin.db;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DbConfiguration.
 */
public interface HCIDbConfiguration{
   
	String getDatabaseName();
  
	String getDatabasePath();
  
	List<HCDbModel> getModels();
  
	int getDatabaseVersion();
	
	void setDatabaseName(String databaseName);
	  
	void setDatabasePath(String databasePath);
  
	void setModels(List<HCDbModel> models);
  
	void setDatabaseVersion(int version);
	
}
