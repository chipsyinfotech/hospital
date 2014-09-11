/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABDbModel.java
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


/**
 * The Interface DbModel, to be implemented for mapping table with db table .
 */
public interface HCDbModel {
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId();

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id);
	
	/**
	 * returns table name to which the ordersModel corresponds.
	 *
	 * @return the table name
	 */
	public String getTableName();

	/**
	 * Gets the creates the statement for table.
	 *
	 * @return the creates the statement
	 */
	public String getCreateStatement();
	
	
	public String getPrimaryKey();
}