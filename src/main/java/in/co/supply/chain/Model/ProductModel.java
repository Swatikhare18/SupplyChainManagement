package in.co.supply.chain.Model;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.co.supply.chain.Bean.ProductBean;
import in.co.supply.chain.Exception.ApplicationException;
import in.co.supply.chain.Exception.DatabaseException;
import in.co.supply.chain.Exception.DuplicateRecordException;
import in.co.supply.chain.Utility.JDBCDataSource;


public class ProductModel {

	public Integer nextPk() throws Exception {
		
		
		Connection conn = null;
		
		int pk = 0;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select Max(ID) from product");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			
				pk = rs.getInt(1);
			}
			
			rs.close();
			
		} catch (Exception e) {
			
			throw new DatabaseException("Exception : Exception in getting PK");
			
		} finally {
			
			JDBCDataSource.closeconnection(conn);
		}
		
		return pk + 1;
	}

	
	public long add(ProductBean bean) throws Exception {
		
		Connection conn = null;
		
		int pk = 0;
		
		
		ProductBean productbean= findByProductName(bean.getProductName());
		
		if (productbean != null) {
			
			throw new DuplicateRecordException("Product is Already exists");
		}
		
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			pk = nextPk();
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("Insert into product values(?,?)");
			
			ps.setInt(1, pk);
			
			ps.setString(2, bean.getProductName());
			
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
	
	
	public long addRequirement(ProductBean bean) throws Exception {
		
		Connection conn = null;
		
		int pk = 0;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			pk = nextPk();
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("Insert into product values(?,?,?,?,?,?,?)");
			
			ps.setInt(1, pk);
			
			ps.setString(2, bean.getProductName());
			
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

	private ProductBean findByProductName(String productName) throws Exception {
		
		StringBuffer sb = new StringBuffer("select * from product where productName=?");
		
		ProductBean bean = null;
		
		Connection conn = null;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			
			ps.setString(1, productName);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				bean = new ProductBean();
				
				bean.setId(rs.getLong(1));
				
				bean.setProductName(rs.getString(2));
			}
			
			rs.close();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			throw new ApplicationException("Exception : Exception in getting User by login");
			
		} finally {
		}
		return bean;
		
	}
	
	public long AddRequirment(ProductBean bean){
    	
		
		System.out.println("In Update");
    	
		int pk =0;
    	
		try {
    		
			Connection conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("update product set productName=? where id =?");
			 
			ps.setString(1, bean.getProductName());
		    
			ps.setLong(2, bean.getId());
		    
			ps.executeUpdate();
			
    	} catch (SQLException e) {
			
    		e.printStackTrace();
    		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return pk;
    }
	
	
	
	public long sendDealer(String status, long Sid) {
		
		System.out.println("in model 11111 update method");
		
		int pk = 0;
		
		try {
			
			Connection conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("update product set status='" + status + "' where id=?");
			
			ps.setLong(1, Sid);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return pk;
	}

	
	
	    public List list() throws Exception {
		
	    	ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		
	    	Connection conn = null;
			
	    	conn = JDBCDataSource.getConnection();
			
	    	PreparedStatement ps = conn.prepareStatement("select distinct id,productName from product");
			
	    	ResultSet rs = ps.executeQuery();
			
	    	while (rs.next()) {
				
	    		ProductBean bean = new ProductBean();
				
	    		bean.setId(rs.getLong(1));
				
	    		bean.setProductName(rs.getString(2));
				
	    		list.add(bean);
			}
		return list;
	}
	    
	    
	    public List Requirementlist() throws Exception {
	    	
	    	System.out.println("InRequirement List");
			
	    	ArrayList<ProductBean> list = new ArrayList<ProductBean>();
			
	    	Connection conn = null;
				
	    	conn = JDBCDataSource.getConnection();
				
	    	PreparedStatement ps = conn.prepareStatement("select * from product");
				
	    	ResultSet rs = ps.executeQuery();
				
	    	while (rs.next()) {
					
	    		ProductBean bean = new ProductBean();
					
	    		bean.setId(rs.getLong(1));
					
	    		bean.setProductName(rs.getString(2));
					
	    		list.add(bean);
				}
				
			return list;
		}
	    
	    
	
	    public static long delete(String pname) {
	    	
	    	int i = 0;
	    	
	    	try {
	    		
	    		Connection conn = JDBCDataSource.getConnection();
	    		
	    		PreparedStatement ps = conn.prepareStatement("delete from product where productName =?");
	    		//ps.setLong(1, id);
	    	   
	    		ps.setString(1, pname);
	    		
	    		i = ps.executeUpdate();
	    		
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return i;
	    }
	    
	    public ProductBean findBypk(long pk) {
	    	
	    	ProductBean bean = null;
	    	
	    	Connection conn = null;
	    	
	    	try {
				
	    		conn = JDBCDataSource.getConnection();
				
	    		PreparedStatement ps = conn.prepareStatement("select * from product where id=?");
				
	    		ps.setLong(1, pk);
				
	    		ResultSet rs = ps.executeQuery();
				
	    		while (rs.next()){
					
	    			bean = new ProductBean();
					
	    			bean.setId(rs.getLong(1));
					
	    			bean.setProductName(rs.getString(2));
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
	    	return bean;
	    	
	    }
	    
	    public long update(ProductBean bean){
	    	
	    	System.out.println("In Update");
	    	
	    	int pk =0;
	    	
	    	try {
	    		
	    		Connection conn = JDBCDataSource.getConnection();
				
	    		PreparedStatement ps = conn.prepareStatement("update product set productName=? where id =?");
				
	    		ps.setString(1, bean.getProductName());
			   
	    		ps.setLong(2, bean.getId());
			    
	    		ps.executeUpdate();
	    		
	    	} catch (SQLException e) {
				
	    		e.printStackTrace();
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
	    	return pk;
	    }
}
