/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABDAO.java
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
import android.content.ContentValues;
import android.database.Cursor;

public interface HCDAO<T> {
    
    public static final String ID = "_id";
    
    public T findByPrimaryKey(String id);
    public void create(T object);
    public void update(T object);
    public void createOrUpdate(T object);
    public void delete(String id);
    public boolean exists(String id);
    public String getPrimaryColumnName();
    
    public T fromCursor(Cursor c);
    public ContentValues values(T t);
}