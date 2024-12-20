package in.co.supply.chain.Model;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.co.supply.chain.Bean.PaymentBean;
import in.co.supply.chain.Exception.ApplicationException;
import in.co.supply.chain.Exception.DatabaseException;
import in.co.supply.chain.Exception.DuplicateRecordException;
import in.co.supply.chain.Utility.JDBCDataSource;




public class PaymentModel {

	
	public Integer nextPk() throws Exception {
		
		Connection conn = null;
		
		int pk = 0;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select Max(ID) from payment");
			
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

	
	
	public long add(PaymentBean bean) throws Exception {
		
		Connection conn = null;
		
		int pk = 0;
		
		PaymentBean paymentstbean= findByCardNumber(bean.getCardNo());
		
		if (paymentstbean != null) {
			
			throw new DuplicateRecordException("Card Number is Already exists");
		}
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			pk = nextPk();
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("Insert into payment values(?,?,?,?,?,?,?)");
			
			ps.setInt(1, pk);
			
			ps.setString(2, bean.getAmount());
			
			ps.setString(3, bean.getCardNo());
			
			ps.setString(4, bean.getCvv());
			
			ps.setString(5, bean.getCardExpairy());
			
			ps.setLong(6, bean.getUserId());
			
			ps.setString(7, bean.getStatus());
			
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

	
	private PaymentBean findByCardNumber(String cardNo) throws Exception {
		
		PaymentBean bean = null;
		
		Connection conn = null;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM payment WHERE cardNo=?");
			
			ps.setString(1, cardNo);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				bean = new PaymentBean();
				
				bean.setId(rs.getLong(1));
				
				bean.setAmount(rs.getString(2));
				
				bean.setCardNo(rs.getString(3));
				
				bean.setCvv(rs.getString(4));
				
				bean.setCardExpairy(rs.getString(5));
				
				bean.setUserId(rs.getLong(6));
				
				bean.setStatus(rs.getString(7));
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return bean;
	}
	
	
	
	public List Paymentlist() throws Exception {
    	
		System.out.println("Payment List");
		
		ArrayList<PaymentBean> list = new ArrayList<PaymentBean>();
		
		Connection conn = null;
			
		conn = JDBCDataSource.getConnection();
			
		PreparedStatement ps = conn.prepareStatement("select * from payment");
			
		ResultSet rs = ps.executeQuery();
			
		while (rs.next()) {
			
			PaymentBean bean = new PaymentBean();
				
			bean.setId(rs.getLong(1));
				
			bean.setAmount(rs.getString(2));
               
			bean.setCardNo(rs.getString(3));
               
			bean.setCvv(rs.getString(4));
               
			bean.setCardExpairy(rs.getString(5));
               
			bean.setUserId(rs.getLong(6));
               
			bean.setStatus(rs.getString(7));
				
			list.add(bean);
			
			}
		return list;
	}
	
	
	
	public List UserPaymentlist(long userId) throws Exception {
    	
		System.out.println("User Payment List");
		
		ArrayList<PaymentBean> list = new ArrayList<PaymentBean>();
		
		Connection conn = null;
			
		conn = JDBCDataSource.getConnection();
			
		PreparedStatement ps = conn.prepareStatement("select * from payment where userid=?");
			
		ps.setLong(1, userId);
			
		ResultSet rs = ps.executeQuery();
			
		while (rs.next()) {
				
			PaymentBean bean = new PaymentBean();
			
			bean.setId(rs.getLong(1));
			
			bean.setAmount(rs.getString(2));
              
			bean.setCardNo(rs.getString(3));
              
			bean.setCvv(rs.getString(4));
              
			bean.setCardExpairy(rs.getString(5));
              
			bean.setUserId(rs.getLong(6));
              
			bean.setStatus(rs.getString(7));
				
			list.add(bean);
			
			}
		return list;
	}
    
    

    public static long delete(long id) {
    	
    	int i = 0;
    	
    	try {
    		
    		Connection conn = JDBCDataSource.getConnection();
    		
    		PreparedStatement ps = conn.prepareStatement("delete from payment where id =?");
    		
    		ps.setLong(1, id);
    		
    		i = ps.executeUpdate();
    		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
    	return i;
    }
	
}
