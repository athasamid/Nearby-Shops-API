package org.nearbyshops.DAOsPreparedRoles;


import com.zaxxer.hikari.HikariDataSource;
import org.nearbyshops.Globals.Globals;
import org.nearbyshops.JDBCContract;
import org.nearbyshops.Model.Shop;
import org.nearbyshops.ModelEndPoints.ShopEndPoint;
import org.nearbyshops.ModelRoles.Endpoints.ShopAdminEndPoint;
import org.nearbyshops.ModelRoles.ShopAdmin;

import java.sql.*;
import java.util.ArrayList;


public class ShopAdminDAO {

	private HikariDataSource dataSource = Globals.getDataSource();

	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();	
	}


	public int saveShopAdmin(ShopAdmin shopAdmin)
	{

		Connection connection = null;
		PreparedStatement statement = null;
		int rowIdOfInsertedRow = -1;

		String insertItemCategory = "INSERT INTO "
				+ ShopAdmin.TABLE_NAME
				+ "("

				+ ShopAdmin.NAME + ","
//				+ ShopAdmin.SHOP_ID + ","

				+ ShopAdmin.USERNAME + ","
				+ ShopAdmin.PASSWORD + ","

				+ ShopAdmin.ABOUT + ","
				+ ShopAdmin.PROFILE_IMAGE_URL + ","
				+ ShopAdmin.PHONE_NUMBER + ","

				+ ShopAdmin.DESIGNATION + ","

				+ ShopAdmin.IS_ENABLED + ","
				+ ShopAdmin.IS_WAITLISTED + ""

				+ " ) VALUES (?,?,?,?, ?,?,?,? ,?)";
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(insertItemCategory,Statement.RETURN_GENERATED_KEYS);

			statement.setString(1,shopAdmin.getName());
//			statement.setObject(2,shopAdmin.getShopID());

			statement.setString(2,shopAdmin.getUsername());
			statement.setString(3,shopAdmin.getPassword());

			statement.setString(4,shopAdmin.getAbout());
			statement.setString(5,shopAdmin.getProfileImageURL());
			statement.setString(6,shopAdmin.getPhoneNumber());

			statement.setString(7,shopAdmin.getDesignation());

			statement.setObject(8,shopAdmin.getEnabled());
			statement.setObject(9,shopAdmin.getWaitlisted());

			rowIdOfInsertedRow = statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();

			if(rs.next())
			{
				rowIdOfInsertedRow = rs.getInt(1);
			}
			
			
			
			System.out.println("Key autogenerated SaveDistributor: " + rowIdOfInsertedRow);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		return rowIdOfInsertedRow;
	}

	//				+ ShopAdmin.NAME_ADMIN + " = ?,"
//				+ ShopAdmin.USERNAME + " = ?,"
//				+ ShopAdmin.PASSWORD + " = ?,"
//
//				+ ShopAdmin.PROFILE_IMAGE_URL + " = ?,"
//				+ ShopAdmin.PHONE_NUMBER + " = ?,"
//
//				+ ShopAdmin.ADMIN_ENABLED + " = ?,"
//				+ ShopAdmin.ADMIN_WAITLISTED + " = ?"



	public int updateShopAdmin(ShopAdmin shopAdmin)
	{


		String updateStatement = "UPDATE " + ShopAdmin.TABLE_NAME
				+ " SET "

//				+ ShopAdmin.NAME + " =?,"
//				+ ShopAdmin.SHOP_ID + " =?,"

//				+ ShopAdmin.USERNAME + " =?,"
//				+ ShopAdmin.PASSWORD + " =?,"

//				+ ShopAdmin.ABOUT + " =?,"
//				+ ShopAdmin.PROFILE_IMAGE_URL + " =?,"
//				+ ShopAdmin.PHONE_NUMBER + " =?,"

//				+ ShopAdmin.DESIGNATION + " =?,"

				+ ShopAdmin.IS_ENABLED + " =?,"
				+ ShopAdmin.IS_WAITLISTED + " =?"

				+ " WHERE " + ShopAdmin.SHOP_ADMIN_ID + " = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;
		int updatedRows = -1;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(updateStatement);


//			statement.setString(1,shopAdmin.getName());
//			statement.setObject(2,shopAdmin.getShopID());

//			statement.setString(2,shopAdmin.getUsername());
//			statement.setString(3,shopAdmin.getPassword());

//			statement.setString(4,shopAdmin.getAbout());
//			statement.setString(5,shopAdmin.getProfileImageURL());
//			statement.setString(6,shopAdmin.getPhoneNumber());

//			statement.setString(7,shopAdmin.getDesignation());

			statement.setObject(1,shopAdmin.getEnabled());
			statement.setObject(2,shopAdmin.getWaitlisted());
			statement.setObject(3,shopAdmin.getShopAdminID());


//			statement.setString(1,shopAdmin.getName());
//			statement.setString(2,shopAdmin.getUsername());
//			statement.setString(3,shopAdmin.getPassword());
//			statement.setString(4,shopAdmin.getProfileImageURL());
//			statement.setString(5,shopAdmin.getPhoneNumber());
//
//			statement.setObject(6,shopAdmin.getAdminEnabled());
//			statement.setObject(7,shopAdmin.getAdminWaitlisted());
//			statement.setObject(8,shopAdmin.getShopID());

			updatedRows = statement.executeUpdate();
			
			
			System.out.println("Total rows updated: " + updatedRows);	
			
			//conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		
		{
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updatedRows;
		
	}

	public int updateBySelf(ShopAdmin shopAdmin)
	{
		// Shop Admin is not allowed to update Enabled or Waitlisted fields they are reserved for Service Level Admins only.

		String updateStatement = "UPDATE " + ShopAdmin.TABLE_NAME
				+ " SET "

				+ ShopAdmin.NAME + " =?,"
//				+ ShopAdmin.SHOP_ID + " =?,"

				+ ShopAdmin.USERNAME + " =?,"
				+ ShopAdmin.PASSWORD + " =?,"

				+ ShopAdmin.ABOUT + " =?,"
				+ ShopAdmin.PROFILE_IMAGE_URL + " =?,"
				+ ShopAdmin.PHONE_NUMBER + " =?,"

				+ ShopAdmin.DESIGNATION + " =?"

				+ " WHERE " + ShopAdmin.SHOP_ADMIN_ID + " = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		int updatedRows = -1;

		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(updateStatement);


			statement.setString(1,shopAdmin.getName());
//			statement.setObject(2,shopAdmin.getShopID());

			statement.setString(2,shopAdmin.getUsername());
			statement.setString(3,shopAdmin.getPassword());

			statement.setString(4,shopAdmin.getAbout());
			statement.setString(5,shopAdmin.getProfileImageURL());
			statement.setString(6,shopAdmin.getPhoneNumber());

			statement.setString(7,shopAdmin.getDesignation());

			statement.setObject(8,shopAdmin.getShopAdminID());


			updatedRows = statement.executeUpdate();


			System.out.println("Total rows updated: " + updatedRows);

			//conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally

		{

			try {

				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updatedRows;

	}

	public int updateShopID(ShopAdmin shopAdmin)
	{
		// Shop Admin is not allowed to update Enabled or Waitlisted fields they are reserved for Service Level Admins only.

		String updateStatement = "UPDATE " + ShopAdmin.TABLE_NAME
				+ " SET "
				+ ShopAdmin.SHOP_ID + " =?"
				+ " WHERE " + ShopAdmin.SHOP_ADMIN_ID + " = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		int updatedRows = -1;

		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(updateStatement);

			statement.setObject(1,shopAdmin.getShopID());
			statement.setObject(2,shopAdmin.getShopAdminID());

			updatedRows = statement.executeUpdate();


			System.out.println("Total rows updated: " + updatedRows);

			//conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally

		{

			try {

				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updatedRows;

	}
	


	public int deleteShopAdmin(int shopAdminID)
	{
		
		String deleteStatement = "DELETE FROM " + ShopAdmin.TABLE_NAME
				+ " WHERE " + ShopAdmin.SHOP_ADMIN_ID + " = ?";
		
		
		Connection connection= null;
		PreparedStatement statement = null;
		int rowsCountDeleted = 0;
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(deleteStatement);

			statement.setObject(1,shopAdminID);

			rowsCountDeleted = statement.executeUpdate();
			
			System.out.println(" Deleted Count: " + rowsCountDeleted);	
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		
		{
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		return rowsCountDeleted;
	}





	
	
	public ArrayList<ShopAdmin> getShopAdmin(Boolean enabled, Boolean waitlisted,
											 String searchString,
											 String sortBy,
											 Integer limit, Integer offset)
	{
		//+ "count(*) OVER() AS full_count " + ","

		String query = "SELECT "
//						+ "count(*) over() AS full_count " + ","
						+ ShopAdmin.SHOP_ADMIN_ID + ","
						+ ShopAdmin.NAME + ","
//						+ ShopAdmin.SHOP_ID + ","

//						+ ShopAdmin.USERNAME + ","
//						+ ShopAdmin.PASSWORD + ","

						+ ShopAdmin.ABOUT + ","
						+ ShopAdmin.PROFILE_IMAGE_URL + ","
						+ ShopAdmin.PHONE_NUMBER + ","

						+ ShopAdmin.DESIGNATION + ","

						+ ShopAdmin.IS_ENABLED + ","
						+ ShopAdmin.IS_WAITLISTED + ""

						+ " FROM " + ShopAdmin.TABLE_NAME;


//						+ Shop.SHOP_ID + ","
//						+ ShopAdmin.NAME_ADMIN + ","
//						+ ShopAdmin.USERNAME + ","
//						+ ShopAdmin.PASSWORD + ","
//
//						+ ShopAdmin.PROFILE_IMAGE_URL + ","
//						+ ShopAdmin.PHONE_NUMBER + ","
//
//						+ ShopAdmin.ADMIN_ENABLED + ","
//						+ ShopAdmin.ADMIN_WAITLISTED + ""



		boolean isFirst = true;

		if(enabled !=null)
		{
			query = query + " WHERE " + ShopAdmin.IS_ENABLED + " = "  + enabled;

			isFirst = false;
		}


		if(waitlisted !=null)
		{
			if(isFirst)
			{
				query = query + " WHERE " + ShopAdmin.IS_WAITLISTED + " = "  + waitlisted;

				isFirst = false;
			}
			else
			{
				query = query + " AND " + ShopAdmin.IS_WAITLISTED + " = "  + waitlisted;
			}
		}



		if(searchString !=null)
		{
			String queryPartSearch = ShopAdmin.TABLE_NAME + "." + ShopAdmin.NAME +" ilike '%" + searchString + "%'";



			if(isFirst)
			{
//				queryJoin = queryJoin + " WHERE " + queryPartSearch;

				query = query + " WHERE " + queryPartSearch;

				isFirst = false;
			}
			else
			{
				query = query + " AND " + queryPartSearch;
			}
		}



		// Applying Filters


//		query = query + " Group by " +
//				ShopAdmin.SHOP_ADMIN_ID;





		if(sortBy!=null)
		{
			if(!sortBy.equals(""))
			{
				String queryPartSortBy = " ORDER BY " + sortBy;

				query = query + queryPartSortBy;
			}
		}



		if(limit !=null)
		{

			String queryPartLimitOffset = "";

			if(offset!=null)
			{
				queryPartLimitOffset = " LIMIT " + limit + " " + " OFFSET " + offset;

			}else
			{
				queryPartLimitOffset = " LIMIT " + limit + " " + " OFFSET " + 0;
			}

			query = query + queryPartLimitOffset;
		}





//		ShopAdminEndPoint endPoint = new ShopAdminEndPoint();

		ArrayList<ShopAdmin> vehiclesList = new ArrayList<ShopAdmin>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next())
			{

				ShopAdmin shopAdmin = new ShopAdmin();

				shopAdmin.setName(rs.getString(ShopAdmin.NAME));
//				shopAdmin.setShopID((Integer) rs.getObject(ShopAdmin.SHOP_ID));

//				shopAdmin.setUsername(rs.getString(ShopAdmin.USERNAME));
//				shopAdmin.setPassword(rs.getString(ShopAdmin.PASSWORD));

				shopAdmin.setAbout(rs.getString(ShopAdmin.ABOUT));
				shopAdmin.setProfileImageURL(rs.getString(ShopAdmin.PROFILE_IMAGE_URL));
				shopAdmin.setPhoneNumber(rs.getString(ShopAdmin.PHONE_NUMBER));

				shopAdmin.setDesignation(rs.getString(ShopAdmin.DESIGNATION));

				shopAdmin.setEnabled(rs.getBoolean(ShopAdmin.IS_ENABLED));
				shopAdmin.setWaitlisted(rs.getBoolean(ShopAdmin.IS_WAITLISTED));

				shopAdmin.setShopAdminID((Integer) rs.getObject(ShopAdmin.SHOP_ADMIN_ID));


				vehiclesList.add(shopAdmin);
			}
			
//rs.getInt("full_count")
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		finally
		{
			
			try {
					if(rs!=null)
					{rs.close();}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return vehiclesList;
	}


	public ShopAdminEndPoint getEndpointMetadata(Boolean enabled, Boolean waitlisted,
											 String searchString)
	{
		String query = "SELECT "
				+ ShopAdmin.SHOP_ADMIN_ID + ""
				+ " FROM " + ShopAdmin.TABLE_NAME;


//						+ Shop.SHOP_ID + ","
//						+ ShopAdmin.NAME_ADMIN + ","
//						+ ShopAdmin.USERNAME + ","
//						+ ShopAdmin.PASSWORD + ","
//
//						+ ShopAdmin.PROFILE_IMAGE_URL + ","
//						+ ShopAdmin.PHONE_NUMBER + ","
//
//						+ ShopAdmin.ADMIN_ENABLED + ","
//						+ ShopAdmin.ADMIN_WAITLISTED + ""



		boolean isFirst = true;

		if(enabled !=null)
		{
			query = query + " WHERE " + ShopAdmin.IS_ENABLED + " = "  + enabled;

			isFirst = false;
		}


		if(waitlisted !=null)
		{
			if(isFirst)
			{
				query = query + " WHERE " + ShopAdmin.IS_WAITLISTED + " = "  + waitlisted;

				isFirst = false;
			}
			else
			{
				query = query + " AND " + ShopAdmin.IS_WAITLISTED + " = "  + waitlisted;
			}
		}



		if(searchString !=null)
		{
			String queryPartSearch = ShopAdmin.TABLE_NAME + "." + ShopAdmin.NAME +" ilike '%" + searchString + "%'";



			if(isFirst)
			{
//				queryJoin = queryJoin + " WHERE " + queryPartSearch;

				query = query + " WHERE " + queryPartSearch;

				isFirst = false;
			}
			else
			{
				query = query + " AND " + queryPartSearch;
			}
		}



		// Applying Filters


		query = "SELECT COUNT(*) as item_count FROM (" + query + ") AS temp";




		ShopAdminEndPoint endPoint = new ShopAdminEndPoint();

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.createStatement();

			rs = statement.executeQuery(query);

			while(rs.next())
			{
				endPoint.setItemCount(rs.getInt("item_count"));
			}

			System.out.println("Total ShopAndmin Endpoint : Count :  " + endPoint.getItemCount());


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		finally
		{

			try {
				if(rs!=null)
				{rs.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return endPoint;
	}




//	+ Shop.TABLE_NAME + "." + Shop.SHOP_ID + ","
//			+ Shop.TABLE_NAME + "." + ShopAdmin.NAME_ADMIN + ","
//			+ Shop.TABLE_NAME + "." + ShopAdmin.USERNAME + ","
//			+ Shop.TABLE_NAME + "." + ShopAdmin.PASSWORD + ","
//
//			+ Shop.TABLE_NAME + "." + ShopAdmin.PROFILE_IMAGE_URL + ","
//			+ Shop.TABLE_NAME + "." + ShopAdmin.PHONE_NUMBER + ","
//
//			+ Shop.TABLE_NAME + "." + ShopAdmin.ADMIN_ENABLED + ","
//			+ Shop.TABLE_NAME + "." + ShopAdmin.ADMIN_WAITLISTED + ""



	public ShopAdmin getShopAdmin(int shopAdminID)
	{

		String query = "SELECT "

				+ ShopAdmin.SHOP_ADMIN_ID + ","
				+ ShopAdmin.NAME + ","
//				+ ShopAdmin.SHOP_ID + ","

				+ ShopAdmin.USERNAME + ","
				+ ShopAdmin.PASSWORD + ","

				+ ShopAdmin.ABOUT + ","
				+ ShopAdmin.PROFILE_IMAGE_URL + ","
				+ ShopAdmin.PHONE_NUMBER + ","

				+ ShopAdmin.DESIGNATION + ","

				+ ShopAdmin.IS_ENABLED + ","
				+ ShopAdmin.IS_WAITLISTED + ""

				+ " FROM " + ShopAdmin.TABLE_NAME
				+ " WHERE " + ShopAdmin.TABLE_NAME + "." + ShopAdmin.SHOP_ADMIN_ID + " = " + shopAdminID;


		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;


		ShopAdmin shopAdmin = null;
		
		try {
			
			connection = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL
					,JDBCContract.CURRENT_USERNAME
					,JDBCContract.CURRENT_PASSWORD);

			statement = connection.createStatement();

			rs = statement.executeQuery(query);
			
			while(rs.next())
			{
				shopAdmin = new ShopAdmin();

				shopAdmin.setName(rs.getString(ShopAdmin.NAME));
//				shopAdmin.setShopID(rs.getInt(ShopAdmin.SHOP_ID));
//				shopAdmin.setShopID((Integer) rs.getObject(ShopAdmin.SHOP_ID));

				shopAdmin.setUsername(rs.getString(ShopAdmin.USERNAME));
				shopAdmin.setPassword(rs.getString(ShopAdmin.PASSWORD));

				shopAdmin.setAbout(rs.getString(ShopAdmin.ABOUT));
				shopAdmin.setProfileImageURL(rs.getString(ShopAdmin.PROFILE_IMAGE_URL));
				shopAdmin.setPhoneNumber(rs.getString(ShopAdmin.PHONE_NUMBER));

				shopAdmin.setDesignation(rs.getString(ShopAdmin.DESIGNATION));

				shopAdmin.setEnabled(rs.getBoolean(ShopAdmin.IS_ENABLED));
				shopAdmin.setWaitlisted(rs.getBoolean(ShopAdmin.IS_WAITLISTED));

				shopAdmin.setShopAdminID(rs.getInt(ShopAdmin.SHOP_ADMIN_ID));


/*
				shopAdmin.setShopID(rs.getInt(Shop.SHOP_ID));
				shopAdmin.setName(rs.getString(ShopAdmin.NAME_ADMIN));
				shopAdmin.setUsername(rs.getString(ShopAdmin.USERNAME));
				shopAdmin.setPassword(rs.getString(ShopAdmin.PASSWORD));

				shopAdmin.setProfileImageURL(rs.getString(ShopAdmin.PROFILE_IMAGE_URL));
				shopAdmin.setPhoneNumber(rs.getString(ShopAdmin.PHONE_NUMBER));

				shopAdmin.setAdminEnabled(rs.getBoolean(ShopAdmin.ADMIN_ENABLED));
				shopAdmin.setAdminWaitlisted(rs.getBoolean(ShopAdmin.ADMIN_WAITLISTED));
*/

			}
			
			
			//System.out.println("Total itemCategories queried " + itemCategoryList.size());	
	
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		
		{
			
			try {
					if(rs!=null)
					{rs.close();}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return shopAdmin;
	}




	public boolean checkUsernameExists(String username)
	{

		String query = "SELECT " + ShopAdmin.USERNAME
				+ " FROM " + ShopAdmin.TABLE_NAME
				+ " WHERE " + ShopAdmin.USERNAME + " = '" + username + "'";

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

//		System.out.println(query);

		ShopAdmin shopAdmin = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);


			while(rs.next())
			{

				return true;
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {
				if(rs!=null)
				{rs.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}




	public void logMessage(String message)
	{
		System.out.println(message);
	}




	public ShopAdmin checkShopAdmin(String username, String password)
	{


		logMessage("Checking ShopAdmin");


		boolean isFirst = true;

		String query = "SELECT "
						+ ShopAdmin.TABLE_NAME + "." + ShopAdmin.SHOP_ADMIN_ID + ","
//						+ ShopAdmin.TABLE_NAME + "." + ShopAdmin.SHOP_ID + ","
						+ ShopAdmin.USERNAME + ","
						+ ShopAdmin.PASSWORD + ","
						+ ShopAdmin.IS_ENABLED + ""

						+ " FROM " + ShopAdmin.TABLE_NAME;



		/*if(deliveryGuyID!=null)
		{
			query = query + " WHERE " + DeliveryGuySelf.ID + " = " + deliveryGuyID;

			isFirst = false;
		}*/

		if(username!=null)
		{
			String queryPartUsername = ShopAdmin.USERNAME + " = '" + username + "'";

			query = query + " WHERE " + queryPartUsername;

			isFirst = false;
		}


		if(password!=null)
		{
			String queryPartPassword = ShopAdmin.PASSWORD + " = '" + password + "'";

			if(isFirst)
			{
				query = query + " WHERE " + queryPartPassword;
			}
			else
			{
				query = query + " AND " + queryPartPassword;
			}
		}


		logMessage(query);


		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;


		//Distributor distributor = null;
		//Admin admin = null;

		ShopAdmin shopAdmin = null;

		try {

			connection = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL
						,JDBCContract.CURRENT_USERNAME
						,JDBCContract.CURRENT_PASSWORD);

			statement = connection.createStatement();

			rs = statement.executeQuery(query);

			while(rs.next())
			{

				logMessage("Inside While check Shop Admin");

				shopAdmin = new ShopAdmin();

//				shopAdmin.setName(rs.getString(ShopAdmin.NAME));

//				shopAdmin.setAbout(rs.getString(ShopAdmin.ABOUT));
//				shopAdmin.setProfileImageURL(rs.getString(ShopAdmin.PROFILE_IMAGE_URL));
//				shopAdmin.setPhoneNumber(rs.getString(ShopAdmin.PHONE_NUMBER));
//
//				shopAdmin.setDesignation(rs.getString(ShopAdmin.DESIGNATION));
//				shopAdmin.setWaitlisted(rs.getBoolean(ShopAdmin.IS_WAITLISTED));


				shopAdmin.setShopAdminID(rs.getInt(ShopAdmin.SHOP_ADMIN_ID));
//				shopAdmin.setShopID(rs.getInt(ShopAdmin.SHOP_ID));

				shopAdmin.setUsername(rs.getString(ShopAdmin.USERNAME));
				shopAdmin.setPassword(rs.getString(ShopAdmin.PASSWORD));

				shopAdmin.setEnabled(rs.getBoolean(ShopAdmin.IS_ENABLED));



			}


			//System.out.println("Total itemCategories queried " + itemCategoryList.size());



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {
				if(rs!=null)
				{rs.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return shopAdmin;
	}


}
