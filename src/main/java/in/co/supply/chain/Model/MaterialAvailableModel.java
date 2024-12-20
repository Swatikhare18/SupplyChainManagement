package in.co.supply.chain.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import in.co.supply.chain.Bean.MaterialBean;
import in.co.supply.chain.Bean.ProductBean;
import in.co.supply.chain.Exception.ApplicationException;
import in.co.supply.chain.Exception.DatabaseException;
import in.co.supply.chain.Exception.DuplicateRecordException;
import in.co.supply.chain.Utility.JDBCDataSource;


public class MaterialAvailableModel {

	
	public Integer nextPk() throws Exception {
		
		Connection conn = null;
		
		int pk = 0;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select Max(ID) from material");
			
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

	public long add(MaterialBean bean) throws Exception {
		
		Connection conn = null;
		
		int pk = 0;
		
		
		MaterialBean materialbean= findByProductID(bean.getProductID());
		
		if (materialbean != null) {
			
			throw new DuplicateRecordException("ProductID is Already exists");
			
		}
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			pk = nextPk();
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("Insert into material values(?,?,?,?,?,?,?,?)");
			
			ps.setInt(1, pk);
			
			ps.setString(2, bean.getProductID());
			
			ps.setString(3, bean.getProductName());
			
			ps.setString(4, bean.getTotalCost());
			
			ps.setString(5, bean.getCompanyName());
			
			ps.setString(6, bean.getQuantity());
			
			ps.setLong(7, bean.getUserId());
			
			ps.setString(8, bean.getStatus());
			
			System.out.println("ID :"+bean.getUserId());
			
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
	
	
	private MaterialBean findByProductID(String productId) throws Exception {
		
		StringBuffer sb = new StringBuffer("select * from material where productid=?");
		
		MaterialBean bean = null;
		
		Connection conn = null;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			
			ps.setString(1, productId);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				bean = new MaterialBean();
				
				bean.setId(rs.getLong(1));
				
				bean.setProductID(rs.getString(2));
				
				bean.setProductName(rs.getString(3));
				
				bean.setTotalCost(rs.getString(4));
				
				bean.setCompanyName(rs.getString(5));
				
				bean.setQuantity(rs.getString(6));
				
				bean.setUserId(rs.getLong(7));
				
				bean.setStatus(rs.getString(8));
			}
			
			
			rs.close();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			
			throw new ApplicationException("Exception : Exception in getting User by login");
			
			
		} finally {
		}
		
		return bean;
	}
	
	
	public static long delete(long id) {
    	
		int i = 0;
    	
		try {
    		
			Connection conn = JDBCDataSource.getConnection();
    		
			PreparedStatement ps = conn.prepareStatement("delete from material where id =?");
    		
			ps.setLong(1, id);
    		
			i = ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return i;
    }
	
	
	
	public List list() throws Exception {
		
		System.out.println("List");
		
		ArrayList<MaterialBean> list = new ArrayList<MaterialBean>();
	
		Connection conn = null;
			
		conn = JDBCDataSource.getConnection();
			
		PreparedStatement ps = conn.prepareStatement("select * from material");
			
		ResultSet rs = ps.executeQuery();
			
		while (rs.next()) {
				
			MaterialBean bean = new MaterialBean();
				
			bean.setId(rs.getLong(1));
				
			bean.setProductID(rs.getString(2));
				
			bean.setProductName(rs.getString(3));
				
			bean.setTotalCost(rs.getString(4));
				
			bean.setCompanyName(rs.getString(5));
			
			bean.setQuantity(rs.getString(6));
				
			bean.setUserId(rs.getLong(7));
				
			bean.setStatus(rs.getString(8));
				
			list.add(bean);
			
			}
		
		return list;
		
	}
	
	public List Materallist(long userId) throws Exception {
		
		System.out.println("Material List");
		
		ArrayList<MaterialBean> list = new ArrayList<MaterialBean>();
		
		try {
			
			Connection conn = null;
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from material where userid=?");
			
			ps.setLong(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				MaterialBean bean = new MaterialBean();
				
				bean.setId(rs.getLong(1));
				
				bean.setProductID(rs.getString(2));
				
				bean.setProductName(rs.getString(3));
				
				bean.setTotalCost(rs.getString(4));
				
				bean.setCompanyName(rs.getString(5));
				
				bean.setQuantity(rs.getString(6));
				
				bean.setUserId(rs.getLong(7));
				
				bean.setStatus(rs.getString(8));
				
				list.add(bean);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public long Paid(String status, long id) {
		
		System.out.println("in model 11111 update method");
		
		int pk = 0;
		
		try {
			
			Connection conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("update material set status='" + status + "' where id=?");
			
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return pk;
	}
}
