/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		HCIRequestListener.java
 * @Project:
 *		Checkin
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright © 2014, 101 Mavericks.
 *		Written under contract by Chipsy Information Technology.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by vijayalaxmi on 22-Aug-2014
 */
package com.mavericks.checkin.client;

public interface HCIRequestListener {
	public void onComplete(int req_type,int status);
}
