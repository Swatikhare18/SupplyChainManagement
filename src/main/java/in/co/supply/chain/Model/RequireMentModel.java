package in.co.supply.chain.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.co.supply.chain.Bean.RequirmentBean;
import in.co.supply.chain.Exception.ApplicationException;
import in.co.supply.chain.Exception.DuplicateRecordException;
import in.co.supply.chain.Utility.JDBCDataSource;


public class RequireMentModel {
	
	public Integer nextpk() throws Exception {

		
		Connection conn = null;
		
		int pk = 0;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM requirment");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				pk = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return pk + 1;
	}

	
	
	public RequirmentBean findByProductId(String productId) throws Exception {
		
		RequirmentBean bean = null;
		
		Connection conn = null;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM requirment WHERE productId=?");
			
			ps.setString(1, productId);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				bean = new RequirmentBean();
				
				bean.setId(rs.getLong(1));
				
				bean.setProductName(rs.getString(2));
				
				bean.setProductID(rs.getString(3));
				
				bean.setLocation(rs.getString(4));
				
				bean.setQuantity(rs.getString(5));
				
				bean.setDiscription(rs.getString(6));
				
				bean.setStatus(rs.getString(7));
				
				bean.setUserId(rs.getLong(8));
				
				bean.setRequire(rs.getString(9));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return bean;
	}

	public RequirmentBean findByPk(long pk) throws Exception {

		
		RequirmentBean bean = null;
		
		Connection conn = null;
		
		try {
			
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM requirment WHERE id=?");
			
			ps.setLong(1, pk);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				bean = new RequirmentBean();
				
				bean.setId(rs.getLong(1));
				
				bean.setProductName(rs.getString(2));
				
				bean.setProductID(rs.getString(3));
				
				bean.setLocation(rs.getString(4));
				
				bean.setQuantity(rs.getString(5));
				
				bean.setDiscription(rs.getString(6));
				
				bean.setStatus(rs.getString(7));
				
				bean.setUserId(rs.getLong(8));
				
				bean.setRequire(rs.getString(9));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return bean;
	}

	public long add(RequirmentBean bean) throws Exception {
		
		System.out.println("in add method");
		
		Connection conn = null;
		
		int pk = 0;

		RequireMentModel model = new RequireMentModel();
		
		RequirmentBean existbean = findByProductId(bean.getProductID());
		
		if (existbean != null) {
			
			throw new DuplicateRecordException("Product Id already exite");
		}

		try {

			conn = JDBCDataSource.getConnection();
			
			pk = nextpk();
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO requirment VALUES(?,?,?,?,?,?,?,?,?)");
			
			ps.setInt(1, pk);
			
			ps.setString(2, bean.getProductName());
			
			ps.setString(3, bean.getProductID());
			
			ps.setString(4, bean.getLocation());
			
			ps.setString(5, bean.getQuantity());
			
			ps.setString(6, bean.getDiscription());
			
			ps.setString(7, bean.getStatus());
			
			ps.setLong(8, bean.getUserId());
			
			ps.setString(9, bean.getRequire());
			
			ps.executeUpdate();
			
			conn.commit();
			
			ps.close();
			
		} catch (Exception e) {
            
			e.printStackTrace();
			try {
				
				conn.rollback();
				
			} catch (Exception e2) {
				
				e.printStackTrace();
				
				
				throw new ApplicationException("Exception : add rollback exception " + e.getMessage());
			}
		} finally {
			
			JDBCDataSource.closeconnection(conn);
		}
		
		return pk;
	}

	
	
	public List Requirementlist(){
    	
		System.out.println("InRequirement List");
		
		ArrayList<RequirmentBean> list = new ArrayList<RequirmentBean>();
		
		try {
			
			Connection conn = null;
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from requirment");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				RequirmentBean bean = new RequirmentBean();
				
				bean.setId(rs.getLong(1));
				
				bean.setProductName(rs.getString(2));
				
				bean.setProductID(rs.getString(3));
				
				bean.setLocation(rs.getString(4));
				
				bean.setQuantity(rs.getString(5));
				
				bean.setDiscription(rs.getString(6));
				
				bean.setStatus(rs.getString(7));
				
				bean.setUserId(rs.getLong(8));
				
				bean.setRequire(rs.getString(9));
				
				list.add(bean);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
    
	
	
	public List UserRequirementlist(long userid){
    	
		System.out.println("InRequirement List");
		
		ArrayList<RequirmentBean> list = new ArrayList<RequirmentBean>();
		
		try {
			
			Connection conn = null;
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from requirment where userid = ?");
			
			ps.setLong(1, userid);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				RequirmentBean bean = new RequirmentBean();
				
				bean.setId(rs.getLong(1));
				
				bean.setProductName(rs.getString(2));
				
				bean.setProductID(rs.getString(3));
				
				bean.setLocation(rs.getString(4));
				
				bean.setQuantity(rs.getString(5));
				
				bean.setDiscription(rs.getString(6));
				
				bean.setStatus(rs.getString(7));
				
				bean.setUserId(rs.getLong(8));
				
				bean.setRequire(rs.getString(9));
				
				list.add(bean);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
    
    

    public static long delete(long id) {
    	
    	int i = 0;
    	
    	try {
    		
    		Connection conn = JDBCDataSource.getConnection();
    		
    		PreparedStatement ps = conn.prepareStatement("delete from requirment where id =?");
    		
    		ps.setLong(1, id);
    		
    		i = ps.executeUpdate();
    		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
    	return i;
    }
    
   
    public long sendDealer(String status, long Sid) {
		
    	System.out.println("in model 11111 update method");
		
    	int pk = 0;
		
    	try {
			
    		Connection conn = JDBCDataSource.getConnection();
			
    		PreparedStatement ps = conn.prepareStatement("update requirment set status='" + status + "' where id=?");
			
    		ps.setLong(1, Sid);
			
    		ps.executeUpdate();
    		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return pk;
	}
    
    
    public long sendInventery(String status, long id1) {
		
    	System.out.println("in model 11111 update method");
		
    	int pk = 0;
		
    	try {
			
    		Connection conn = JDBCDataSource.getConnection();
			
    		PreparedStatement ps = conn.prepareStatement("update requirment set status='" + status + "' where id=?");
			
    		ps.setLong(1, id1);
			
    		ps.executeUpdate();
    		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return pk;
	}

   
    
    public long Sent(String req, long id) {
		
    	System.out.println("in model 11111 update method");
		
    	int pk = 0;
		
    	try {
			
    		Connection conn = JDBCDataSource.getConnection();
			
    		PreparedStatement ps = conn.prepareStatement("update requirment set req='" + req + "' where id=?");
			
    		ps.setLong(1, id);
			
    		ps.executeUpdate();
    		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
    	return pk;
	}
    

}
