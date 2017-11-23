package com.cg.trg.orderproduct1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.trg.orderproduct1.dto.Customer;
import com.cg.trg.orderproduct1.dto.Product;
import com.cg.trg.orderproduct1.exception.ProductException;
import com.cg.trg.orderproduct1.util.DBUtil;

public class ProductDAOImpl implements IProductDAO{
static{
	PropertyConfigurator.configure("log4j.properties");
}
static Logger myLog=Logger.getLogger(ProductDAOImpl.class);
@Override
public ArrayList<Product> srchProductByCategory(String category) throws ProductException {
		
		ArrayList<Product> prodList=new ArrayList<Product>();
		String sql="SELECT prodID,prodName,price,qty,catName FROM prodDetails p,category c WHERE p.catID=c.catID and c.catName=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		Connection connection=null;
		try
		{
			connection=DBUtil.openConnection();
			myLog.info("Fetching Products by category");
			pst=connection.prepareStatement(sql);
			pst.setString(1,category);
			rs=pst.executeQuery();
			myLog.info("");
			while (rs.next()) {
				Product obj=new Product();
				obj.setProdId(rs.getInt("prodID"));
				obj.setProdName(rs.getString("prodName"));
				obj.setProdPrice(rs.getFloat("price"));
				obj.setProdQty(rs.getInt("qty"));
				obj.setCategory(rs.getString("catName"));
				
				prodList.add(obj);
			}
		}catch(SQLException s){
			myLog.error(s.getMessage());
			throw new ProductException(s.getMessage());
		}finally{
			DBUtil.closeConnection();
		}
		myLog.info("I m done fetching");
		return prodList;
}

@Override
public ArrayList<Product> getAllProducts() {
	// TODO Auto-generated method stub
	ArrayList<Product> prodList=new ArrayList<Product>();
	String sql="SELECT prodID,prodName,price,qty,catName FROM prodDetails p,category c WHERE p.catID=c.catID";
	Statement st=null;
	ResultSet rs=null;
	Connection connection=null;
	try
	{
		connection=DBUtil.openConnection();
		st=connection.createStatement();
		rs=st.executeQuery(sql);
		while (rs.next()) {
			Product obj=new Product();
			obj.setProdId(rs.getInt("prodID"));
			obj.setProdName(rs.getString("prodName"));
			obj.setProdPrice(rs.getFloat("price"));
			obj.setProdQty(rs.getInt("qty"));
			obj.setCategory(rs.getString("catName"));
			
			prodList.add(obj);
		}
	}catch(SQLException s){
		System.out.println(s.getMessage());
	}finally{
		DBUtil.closeConnection();
	}
	return prodList;
}

@Override
public Product getProductbyId(int prodId) throws ProductException {
	// TODO Auto-generated method stub
	Product obj=null;
	String sql="SELECT prodID,prodName,price,qty,catName FROM prodDetails p,category c WHERE p.catID=c.catID and p.prodID=?";
	PreparedStatement pst=null;
	ResultSet rs=null;
	Connection connection=null;
	try
	{
		connection=DBUtil.openConnection();
		pst=connection.prepareStatement(sql);
		pst.setInt(1,prodId);
		rs=pst.executeQuery();
		if (rs.next()) {
			obj=new Product();
			obj.setProdId(rs.getInt("prodID"));
			obj.setProdName(rs.getString("prodName"));
			obj.setProdPrice(rs.getFloat("price"));
			obj.setProdQty(rs.getInt("qty"));
			obj.setCategory(rs.getString("catName"));
		}
	}catch(SQLException s){
		throw new ProductException(s.getMessage());
	}finally{
		DBUtil.closeConnection();
	}
	return obj;
}

@Override
public int orderProduct(Customer obj, String prodName, int qty) {
	// TODO Auto-generated method stub
	Connection connection=null;
	int orderid=0;
	String seqQry="SELECT oseq.NEXTVAL FROM DUAL";
	String chkQry="SELECT qty FROM prodDetails WHERE prodName=?";
	String updQry="UPDATE prodDetails SET qty=qty-? WHERE prodName=?";
	String insQry="INSERT INTO customerOrder VALUES(?,?,?,?)";
	Statement st=null;
	PreparedStatement chkPs=null;
	PreparedStatement updPs=null;
	PreparedStatement insPs=null;
	ResultSet chkRs=null;
	ResultSet seqRs=null;
	try
	{
		connection=DBUtil.openConnection();
		chkPs=connection.prepareStatement(chkQry);
		chkPs.setString(1, prodName);
		chkRs=chkPs.executeQuery();
		if(chkRs.next()){
			int avQty=chkRs.getInt("qty");
			if(avQty>=qty)
			{
				st=connection.createStatement();
				seqRs=st.executeQuery(seqQry);
				while(seqRs.next()){
					orderid=seqRs.getInt(1);
				}
				updPs=connection.prepareStatement(updQry);
				updPs.setInt(1, qty);
				updPs.setString(2, prodName);
				
				int upRows=updPs.executeUpdate();
				insPs=connection.prepareStatement(insQry);
				insPs.setInt(1,orderid);
				insPs.setString(2,obj.getCustName());
				insPs.setString(3,obj.getCustAddress());
				insPs.setLong(4,obj.getCustPhone());
				int insRows=insPs.executeUpdate();
			}
			else{
				orderid=-1;
			}
		}
		
		
	}catch(SQLException s){
		System.out.println(s.getMessage());
	}finally{
		DBUtil.closeConnection();
	}
	return orderid;
}
}