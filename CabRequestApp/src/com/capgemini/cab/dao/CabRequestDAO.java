package com.capgemini.cab.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.cab.exception.CabException;
import com.capgemini.cab.util.DBUtil;
import com.capgemini.cabs.bean.CabRequest;




public class CabRequestDAO implements ICabRequestDAO{

	static{
		PropertyConfigurator.configure("log4j.properties");
	}
	
	static Logger myLog = Logger.getLogger(CabRequestDAO.class);
	
	@Override
	public int addCabRequestDetails(CabRequest obj, String pinCode) throws CabException {
		// TODO Auto-generated method stub
		int requestId = 0;
		String seqQuery = "SELECT seq_request_id.nextval FROM dual";
		String insQuery = "INSERT INTO cab_request values(?,?,?,?,?,?,?,?)";
		
		PreparedStatement insPs = null;
		Statement st = null;
		ResultSet seqRs = null; 
		Connection connection = null;
		try{
				if("Accepted"==obj.getRequestStatus()){
					connection = DBUtil.openConnection();
					st = connection.createStatement();
					seqRs = st.executeQuery(seqQuery);
					while(seqRs.next()){
						requestId = seqRs.getInt(1);  //seqq number on
						myLog.debug("Sequence number\t" + requestId);
					}
					if(requestId!=0)
					{
						
						insPs = connection.prepareStatement(insQuery);
						insPs.setInt(1, requestId);
						insPs.setString(2,obj.getCustomerName());
						insPs.setString(3, obj.getPhoneNumber());
						LocalDate dt =LocalDate.now();
						insPs.setDate(4,Date.valueOf(dt));
						insPs.setString(5, obj.getRequestStatus());
						insPs.setString(6, obj.getCabNumber());
						insPs.setString(7, obj.getAddress());
						insPs.setString(8, obj.getPinCode());
			
						 insPs.executeUpdate();
					}	
						myLog.info("Insertion done in cab_request");
				}else{
					throw new CabException(" We do not provide service for that PinCode Area!!");
				}
					
				
		}catch(SQLException e){
			throw new CabException(e.getMessage());
		}finally{
			DBUtil.closeConnection();
		}
		
		return requestId;
		
	}



	@Override
	public CabRequest getRequestDetails(int requestId) throws CabException {
		// TODO Auto-generated method stub
		String sql = "SELECT CUSTOMER_NAME,REQUEST_STATUS,CAB_NUMBER,ADDRESS_OF_PICKUP FROM CAB_REQUEST WHERE REQUEST_ID=?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection connection = null;
		CabRequest obj=null;
		
		try {
			connection = DBUtil.openConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, requestId); //local var
			
			myLog.debug("Fetching request by ID");
			rs= pst.executeQuery();
			
			
			if(rs.next()){
				//if record found.....AS only 1 Record is coming as OUTPUT
				obj = new CabRequest();
			
				obj.setCustomerName(rs.getString("CUSTOMER_NAME"));
				obj.setRequestStatus(rs.getString("REQUEST_STATUS"));
				obj.setCabNumber(rs.getString("CAB_NUMBER"));
				obj.setAddress(rs.getString("ADDRESS_OF_PICKUP"));
				
			}
		}catch(SQLException e){
			throw new CabException(e.getMessage());
		}
		return obj;
	}

}
