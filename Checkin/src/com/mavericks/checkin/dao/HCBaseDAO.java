/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABBaseDAO.java
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
package com.mavericks.checkin.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mavericks.checkin.db.HCDAO;
import com.mavericks.checkin.db.HCDbModel;
import com.mavericks.checkin.db.HCStringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseDAO, performs basic CRUD operations.
 * 
 * @param <T>
 *            the generic type of DbModel
 */
public abstract class HCBaseDAO<T extends HCDbModel> implements HCDAO<T> {

    /** The Constant TAG. */
    private static final String TAG = "BaseDAO";

    /** The db. */
    protected final SQLiteDatabase db;
    public static final int TIME_DEFAULT = 10*60*1000;
    /** The context. */
    protected final Context context;

    /**
     * Instantiates a new base dao.
     * 
     * @param context
     *            the context
     * @param db
     *            the db
     */
    public HCBaseDAO(Context context, SQLiteDatabase db) {
	this.context = context;
	this.db = db;
    }

    /**
     * Gets the table name for DAO.
     * 
     * @return the table name
     */
    public abstract String getTableName();
    public abstract boolean isExpired(Context context);
    public abstract void updateTime(Context context);   
    public abstract int getInvalidateTime();
	public abstract boolean isValid(String Colnm,String value);

    /*
     * (non-Javadoc)
     * 
     * @see com.opendroid.db.dao.DAO#fromCursor(android.database.Cursor)
     */
    public abstract T fromCursor(Cursor c);

    /*
     * (non-Javadoc)
     * 
     * @see com.opendroid.db.dao.DAO#values(java.lang.Object)
     */
    public abstract ContentValues values(T t);

    /**
     * Checks if table is not empty.
     * 
     * @return true, if is not empty
     * @throws DAOException
     *             the dAO exception
     */
    public boolean isNotEmpty() {
	Cursor c = null;

	c = db.rawQuery("select " + getPrimaryColumnName() + " from "
		+ getTableName(), null);
	if (c == null)
	    return false;

	boolean bool = c.moveToFirst();
	c.close();

	return bool;

    }

    /**
     * Find resord by primary key.
     * 
     * @param id
     *            the id
     * @return the t
     * @throws DAOException
     *             the dAO exception
     */
    public T findByPrimaryKey(String id) {
	Cursor c = null;
	T t = null;

	c = db.rawQuery("select * from " + getTableName() + " where "
		+ getPrimaryColumnName() + " = ?", whereArgsForId(id));

	if (c == null)
	    return t;

	if (c.moveToFirst()) {
	    t = fromCursor(c);
	}
	return t;
    }

    public List<T> rawQuery(String query) {
	Cursor c = null;
	List<T> result = null;
	try {
	    c = db.rawQuery(query, null);
	    result = new ArrayList<T>();
	    if (c.moveToFirst()) {
		do { 
		    result.add(fromCursor(c));
		} while (c.moveToNext());
	    }
	} finally {
	    if (c != null) {
		c.close();
	    }
	}
	return result;
       }
     
    
    /**
     * Find first by field.
     * 
     * @param fieldName
     *            the field name
     * @param value
     *            the value
     * @return the t
     * @throws DAOException
     *             the dAO exception
     */
    public T findFirstByField(String fieldName, String value) {
	Cursor c = null;
	T t = null;

	String q = "select * from " + getTableName() + " where " + fieldName
		+ " = ?";
	
	c = db.rawQuery(q, new String[] { value });

	if (c == null)
	    return t;

	if (c.moveToFirst()) {
	    t = fromCursor(c);
	}

	return t;
    }

    // public List<T> findAllByFields(String[] fields,String[] values){
    // List<T> lst = null;
    // String selection="";
    // for(String str : fields){
    // if(selection.equalsIgnoreCase("")){
    // selection = str+" = ?";
    // }
    // selection+=" AND "+str+" = ?";
    // }
    // // query(getTableName(), null, fie, selectionArgs, groupBy, having,
    // orderBy)
    // return lst;
    // }
    /**
     * Select all.
     * 
     * @return the list
     */
    public List<T> selectAll() {
	Cursor c = null;
	List<T> result = null;
	try {
	    c = db.rawQuery("select * from " + getTableName(), null);
	    result = new ArrayList<T>();
	    if (c.moveToFirst()) {
		do {		   
		    result.add(fromCursor(c));
		} while (c.moveToNext());
	    }
	} finally {
	    if (c != null) {
		c.close();
	    }
	}
	return result;
    }

    /**
     * Find all by field.
     * 
     * @param fieldName
     *            - field name to search by
     * @param value
     *            - the value of the field
     * @param orderConditions
     *            - the "order by" sentence. May be <b>null</b>.
     * @return the list
     */
    public List<T> findAllByField(String fieldName, String value,
	    String orderConditions, int limit) {
	Cursor c = null;
	List<T> result = null;

	String squery = "select * from " + getTableName() + " where "
		+ fieldName + " = ? " + (limit > 0 ? "limit " + limit : "")
		+ HCStringUtils.safe(orderConditions);
	try {
	    c = db.rawQuery(squery, new String[] { value });

	    result = new ArrayList<T>();
	    if (c.moveToFirst()) {
		do {
		    result.add(fromCursor(c));
		} while (c.moveToNext());
	    }
	} finally {
	    if (c != null) {
		c.close();
	    }
	}
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opendroid.db.dao.DAO#create(java.lang.Object)
     */
    public void create(T model) {

	int id = (int) db.insert(getTableName(), "0.0", values(model));

	if (id != -1) {
	    model.setId(id);
	}
    }

    public void createAll(List<T> listModels) {

	db.beginTransaction();
	for (T model : listModels) {
	    db.insert(getTableName(), "0.0", values(model));
	}
	db.setTransactionSuccessful();

	db.endTransaction();

    }

    public void update(T model) {	
	db.update(getTableName(), values(model), getPrimaryColumnName()
		+ " = ?", whereArgsForId(String.valueOf(model.getPrimaryKey())));
    }

    public void update(String primaryKeyValue,ContentValues values) {

	db.update(getTableName(), values, getPrimaryColumnName()
		+ " = ?", whereArgsForId(primaryKeyValue));
    }
    
    public int updateAndGetRowsEffected(T model) {
	return db
		.update(getTableName(), values(model), getPrimaryColumnName()
			+ " = ?",
			whereArgsForId(String.valueOf(model.getPrimaryKey())));
    }

    public void createOrUpdate(T model) {
	if (exists(String.valueOf(model.getPrimaryKey()))) {
	    update(model);
	} else {
	    create(model);
	}
    }

    public void createOrUpdateAll(List<T> listModels) {
	db.beginTransaction();

	for (T model : listModels) {	    

	    if (exists(String.valueOf(model.getPrimaryKey()))) {
		update(model);
	    } else {
		create(model);
	    }

	}
	db.setTransactionSuccessful();

	db.endTransaction();
    }

    /**
     * Update.
     * 
     * @param models
     *            the models
     * @throws DAOException
     *             the dAO exception
     */
    public void update(List<T> models) {
	db.beginTransaction();
	for (T model : models) {
	    update(model);
	}
	db.setTransactionSuccessful();

	db.endTransaction();
    }

    /**
     * Delete.
     * 
     * @param id
     *            the id
     * @throws DAOException
     *             the dAO exception
     */
    public void delete(String id) {
	db.delete(getTableName(), " " + getPrimaryColumnName() + " = ?",
		whereArgsForId(id));
    }

    /**
     * Delete all.
     * 
     * @throws DAOException
     *             the dAO exception
     */
    public void deleteAll() {
	db.delete(getTableName(), null, null);
    }

    /**
     * Delete by field.
     * 
     * @param fieldName
     *            the field name
     * @param fieldValue
     *            the field value
     */
    public void deleteByField(String fieldName, String fieldValue) {
	db.delete(getTableName(), " " + fieldName + " = ?",
		new String[] { fieldValue });
    }

    /**
     * Exists.
     * 
     * @param id
     *            the id
     * @return true, if successful
     * @throws DAOException
     *             the dAO exception
     */
    public boolean exists(String id) {
	Cursor c = null;

	c = db.rawQuery("select _id from " + getTableName() + " where "
		+ getPrimaryColumnName() + " = ?", whereArgsForId(id));

	if (c == null)
	    return false;

	boolean bool = c.moveToFirst();
	c.close();
	return bool;

    }

    public boolean exists(String column, String value) {
	Cursor c = null;

	c = db.rawQuery("select " + column + " from " + getTableName()
		+ " where " + column + " = ?",
		whereArgsForId(value));

	if (c == null)
	    return false;

	boolean bool = c.moveToFirst();
	c.close();
	return bool;

    }

    /**
     * Find all.
     * 
     * @return the list
     */
    public List<T> findAll(int limit) {
	Cursor c = null;
	List<T> result = null;
	String query = "SELECT * FROM " + getTableName() + (limit>0?(" limit " + limit):"");

	try {
	    c = db.rawQuery(query, null);
	    result = new ArrayList<T>();
	    if (c.moveToFirst()) {
		do {
		    result.add(fromCursor(c));
		} while (c.moveToNext());
	    }
	} finally {
	    if (c != null) {
		c.close();
	    }
	}
	return result;
    }

    /**
     * Find all.
     * 
     * @param orderConditions
     *            the order conditions
     * @return the list
     */
    protected List<T> findAll(String orderConditions) {
	Cursor c = null;
	List<T> result = null;
	try {
	    c = db.rawQuery("select * from " + getTableName() + ' '
		    + HCStringUtils.safe(orderConditions), null);
	    result = new ArrayList<T>();
	    if (c.moveToFirst()) {
		do {
		    result.add(fromCursor(c));
		} while (c.moveToNext());
	    }
	} finally {
	    if (c != null) {
		c.close();
	    }
	}
	return result;
    }

    public List<T> query(String selection, String[] selectionArgs,
	    String groupBy, String having, String orderBy) {
	Cursor c = null;
	List<T> result = null;

	try {
	    c = db.query(getTableName(), null, selection, selectionArgs,
		    groupBy, having, orderBy);
	    result = new ArrayList<T>();
	    if (c.moveToFirst()) {
		do {
		    result.add(fromCursor(c));
		} while (c.moveToNext());
	    }
	} finally {
	    if (c != null) {
		c.close();
	    }
	}
	return result;
    }

    public Cursor cursorQuery(String selection, String[] selectionArgs,
	    String groupBy, String having, String orderBy) {
	Cursor c = null;
	c = db.query(getTableName(), null, selection, selectionArgs, groupBy,
		having, orderBy);
	return c;
    }

    public Cursor cursorQuery(String table, String[] columns, String selection,
	    String[] selectionArgs, String groupBy, String having,
	    String orderBy) {
	Cursor c = null;
	c = db.query(table, columns, selection, selectionArgs, groupBy, having,
		orderBy);
	return c;
    }

    /**
     * Where args for id.
     * 
     * @param id
     *            the id
     * @return the string[]
     */
    protected String[] whereArgsForId(String id) {
	return new String[] { String.valueOf(id) };
    }

    /**
     * Convert <code>{1, 2, 3}</code> to <code>"1,2,3"</code>.
     * 
     * @param ids
     *            the ids
     * @return the string
     */
    protected String idArrayToString(int[] ids) {
	StringBuilder sqlFragment = new StringBuilder();
	for (int i = 0; i < ids.length; i++) {
	    sqlFragment.append(ids[i]);
	    if (i < ids.length - 1) {
		sqlFragment.append(',');
	    }
	}
	return sqlFragment.toString();
    }

    /**
     * Convert all cursor lines to a list of ordersModel objects.
     * 
     * @param cursor
     *            the cursor
     * @return the list
     */
    public List<T> allFromCursor(Cursor cursor) {
	ArrayList<T> result = new ArrayList<T>();
	if (cursor.moveToFirst()) {
	    do {
		result.add(fromCursor(cursor));
	    } while (cursor.moveToNext());
	}
	return result;
    }

    public Cursor getCursorByField(String fieldName, String value,
	    String orderConditions) {
	Cursor c = null;

	c = db.rawQuery("select * from " + getTableName() + " where "
		+ fieldName + " = ? " + HCStringUtils.safe(orderConditions),
		new String[] { value });

	return c;
    }

    public Cursor getAllCursor() {
	Cursor c = null;

	c = db.rawQuery("SELECT * FROM " + getTableName(), null);

	return c;
    }

    protected Cursor getAllCursor(String orderConditions) {
	Cursor c = null;

	c = db.rawQuery(
		"select * from " + getTableName() + ' '
			+ HCStringUtils.safe(orderConditions), null);

	return c;
    }
}
