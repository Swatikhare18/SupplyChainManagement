package in.co.supply.chain.Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.co.supply.chain.Bean.InventoryBean;
import in.co.supply.chain.Bean.RequirmentBean;
import in.co.supply.chain.Exception.ApplicationException;
import in.co.supply.chain.Exception.DuplicateRecordException;
import in.co.supply.chain.Utility.JDBCDataSource;



public class InventoryModel {

	public Integer nextPK() {
		
		int pk = 0;
		
		try {
			
			Connection conn = null;
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Select MAX(ID) From inventory");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				pk = rs.getInt(1);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return pk + 1;

	}
	
	
	public long add(InventoryBean bean) throws Exception {
		
		System.out.println("in Model");
		
		int pk = 0;
		
		
		InventoryModel model = new InventoryModel();
		
		InventoryBean existbean = findByProductName(bean.getProductName());
		
		if (existbean != null) {
			
			throw new DuplicateRecordException("Product Name already exite");
		}
		
		
		Connection conn = null;
		
		try {
		
			conn = JDBCDataSource.getConnection();
		
			pk = nextPK();
		
			conn.setAutoCommit(false);
		
			PreparedStatement ps = conn.prepareStatement("Insert into inventory values(?,?,?,?,?)");
		
			ps.setLong(1, pk);
		
			ps.setString(2, bean.getProductName());
		
			ps.setString(3, bean.getProductCost());
		
			ps.setString(4, bean.getQuantity());
		
			ps.setString(5, bean.getCompanyName());
		
			ps.executeUpdate();
		
			conn.commit();
		
			ps.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			try {
				conn.rollback();
				
			} catch (Exception ex) {
				
				ex.printStackTrace();
				
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			
			throw new ApplicationException("Exception : Exception in add User");
			
		} finally {
			
			JDBCDataSource.closeconnection(conn);
		}
		
		return pk;
	
	}
	
	public InventoryBean findByProductName(String productName) throws Exception {
		
		InventoryBean bean = null;
		
		Connection conn = null;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM inventory WHERE productname=?");
			
			ps.setString(1, productName);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				bean = new InventoryBean();
				
				bean.setId(rs.getLong(1));
				
				bean.setProductName(rs.getString(2));
				
				bean.setProductCost(rs.getString(3));
				
				bean.setQuantity(rs.getString(4));
				
				bean.setCompanyName(rs.getString(5));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return bean;
	}
	
	
	
	public static long Delete(long id) {
		
		
		int i = 0;
		
		try {
		
			Connection   conn = JDBCDataSource.getConnection();
	    
			PreparedStatement ps = conn.prepareStatement("delete from inventory where id=?");
	    
			ps.setLong(1, id);
	    
			i = ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return i;
		
	}
	
	         public List list() {
	    	
	        	 System.out.println("List");
		
	        	 ArrayList<InventoryBean> list = new ArrayList<InventoryBean>();
		
	        	 Connection conn = null;
		try {
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from inventory");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				InventoryBean bean = new InventoryBean();
				
				bean.setId(rs.getLong(1));
				
				bean.setProductName(rs.getString(2));
				
				bean.setProductCost(rs.getString(3));
				
				bean.setQuantity(rs.getString(4));
				
				bean.setCompanyName(rs.getString(5));
				
				list.add(bean);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return list;
	}
	    
	    public InventoryBean findBypk(long pk) throws Exception {
	    	
	    	System.out.println("FindBy PK");
	    	
	    	InventoryBean bean = null;
	    	
	    	Connection conn = null;
	    	try {
			
	    		conn = JDBCDataSource.getConnection();
				
	    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM inventory where id=?");
				
	    		ps.setLong(1, pk);
				
	    		ResultSet rs = ps.executeQuery();
				
	    		while (rs.next()) {
					
	    			bean = new InventoryBean();
					
	    			bean.setId(rs.getLong(1));
					
	    			bean.setProductName(rs.getString(2));
					
	    			bean.setProductCost(rs.getString(3));
					
	    			bean.setQuantity(rs.getString(4));
					
	    			bean.setCompanyName(rs.getString(5));
				}
				
	    		rs.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return bean;
	    }
	    
	    public InventoryBean findBypNAme(String pName) throws Exception {
	    	
	    	System.out.println("Find BY PName");
	    	
	    	InventoryBean bean = null;
	    	
	    	Connection conn = null;
	    	try {
				
	    		conn = JDBCDataSource.getConnection();
				
	    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM inventory where productname=?");
				
	    		ps.setString(1, pName);
				
	    		ResultSet rs = ps.executeQuery();
				
	    		while (rs.next()) {
					
	    			bean = new InventoryBean();
					
	    			bean.setId(rs.getLong(1));
					
	    			bean.setProductName(rs.getString(2));
					
	    			bean.setProductCost(rs.getString(3));
					
	    			bean.setQuantity(rs.getString(4));
					
	    			bean.setCompanyName(rs.getString(5));
				}
				
	    		rs.close();
	    		
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
	    	return bean;
	    }
	    
	    
	    
	    
	    
	    
	    public long update(InventoryBean bean){
	    	
	    	System.out.println("In Update");
	    	
	    	int pk =0;
	    	
	    	try {
	    		
	    		Connection conn = JDBCDataSource.getConnection();
				
	    		PreparedStatement ps = conn.prepareStatement("update inventory set productname=?,productcost=?,quantity=?,companyName=? where id =?");
				
	    		ps.setString(1, bean.getProductName());
				
	    		ps.setString(2, bean.getProductCost());
				
	    		ps.setString(3, bean.getQuantity());
				
	    		ps.setString(4, bean.getCompanyName());
			    
	    		ps.setLong(5, bean.getId());
			    
	    		ps.executeUpdate();
	    		
	    	} catch (SQLException e) {
				
	    		e.printStackTrace();
	    		
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
	    	return pk;
	    }
	    
	    public long lessQuan(String lessproduct,long id1) {
	    	
	    	InventoryBean bean = null;
			
	    	System.out.println("in model 11111 update method");
			
	    	int pk = 0;
			
	    	try {
				
	    		Connection conn = JDBCDataSource.getConnection();
				
	    		System.out.println("in update Product :"+lessproduct);
				
	    		PreparedStatement ps = conn.prepareStatement("update inventory set quantity='" + lessproduct + "' where id=?");
				//ps.setString(1, lessQuantity);
				
	    		ps.setLong(1, id1);
				
	    		ps.executeUpdate();
	    		
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
	    	return pk;
		}
	    
}
