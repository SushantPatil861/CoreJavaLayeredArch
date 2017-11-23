package com.cg.trg.bankaccount.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.trg.bankaccount.dto.BankAccount;
import com.cg.trg.bankaccount.exception.BankAccountException;
import com.cg.trg.bankaccount.util.DBUtil;


public class BankAcountDaoImpl implements IBankAccoutDao {
	static{
		PropertyConfigurator.configure("log4j.properties");
	}	
		static Logger myLog = Logger.getLogger(BankAcountDaoImpl.class);

	@Override
	public int raiseComplaint(BankAccount obj) {
		int complaintId = 0;
		String seqQuery = "SELECT complaintid.nextval FROM DUAL";
		String insQuery = "INSERT into bankaccount values(?,?,sysdate,?,?)";
		Connection  connection = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement Ps = null;
				try{
			connection = DBUtil.openConnection();
			myLog.debug("Inserting Details in Bank Account table");
			st = connection.createStatement();
			rs = st.executeQuery(seqQuery);
			while(rs.next()){
				complaintId =rs.getInt(1);
			}
			Ps = connection.prepareStatement(insQuery);
			Ps.setInt(1, complaintId);
			Ps.setString(2, obj.getRaiser());
			Ps.setString(3, obj.getStatus());
			Ps.setString(4, obj.getPriority());
			Ps.executeUpdate();
				
			}catch(SQLException e){
				myLog.error(e.getMessage());
			System.out.println(e.getMessage());
		}finally{
			DBUtil.closeConnection();
		}
		return complaintId ;
	}

	@Override
	public BankAccount viewById(int complaintId) 
			throws BankAccountException {
		BankAccount obj = new BankAccount();
		String sql = "SELECT * FROM bankaccount Where complaintId=?";
		Connection  connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			connection = DBUtil.openConnection();
			myLog.debug("Showing Complaint by ID");
			ps = connection.prepareStatement(sql);
			ps.setInt(1, complaintId);
			rs = ps.executeQuery();
			if (rs.next()) {
				// fetch the products inividually
				
				obj.setComplaintId(rs.getInt("complaintid"));
				obj.setRaiser(rs.getString("raiser"));
				obj.setStatus(rs.getString("status"));
				obj.setPriority(rs.getString("priority"));
			}else{
				throw new BankAccountException("Not Valid Complaint Id");
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
			myLog.error(e.getMessage());
			
			
		}finally{
			DBUtil.closeConnection();
		}
		return obj;
	}

}
