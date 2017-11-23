package com.cg.trg.mobilepurchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.trg.mobilepurchase.dto.Customer;
import com.cg.trg.mobilepurchase.dto.Mobile;
import com.cg.trg.mobilepurchase.exception.MobileException;
import com.cg.trg.mobilepurchase.util.DBUtil;

public class MobileDAOImpl implements IMobileDao {
	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	static Logger myLog = Logger.getLogger(MobileDAOImpl.class);

	@Override
	public ArrayList<Mobile> showAllMobile() {
		ArrayList<Mobile> list = new ArrayList<Mobile>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT mobileId , Name , price , QUANTITY FROM MOBILES";
		try {
			connection = DBUtil.OpenConnection();
			st = connection.createStatement();
			myLog.debug("I am fetching all details of mobile");
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Mobile obj = new Mobile();
				obj.setMobileId(rs.getInt("MobileId"));
				obj.setMobileName(rs.getString("Name"));
				obj.setPrice(rs.getFloat("Price"));
				obj.setQty(rs.getInt("Quantity"));
				list.add(obj);
			}
		} catch (SQLException e) {
			myLog.error("I am debugging");
			e.printStackTrace();
			// new MobileException("Database is Empty");
		} finally {
			DBUtil.CloseConnection();

		}
		myLog.debug("I have fetched all details");
		return list;
	}

	@Override
	public boolean deleteMobile(int mobileId) {
		boolean flag = false;
		String sql = "DELETE FROM mobiles WHERE mobileId = ?";
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = DBUtil.OpenConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, mobileId);
			myLog.debug("I am deleting details of mobile by accepting mobId");
			int upRows = pst.executeUpdate();
			if (upRows > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			myLog.error("i am debugging");
			new MobileException("Mobile does not exist !!");
		} finally {
			DBUtil.CloseConnection();

		}
		myLog.debug("I have deleted all details");
		return flag;
	}

	@Override
	public ArrayList<Mobile> searchByPriceRange(float start, float end)
			throws MobileException {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Mobile obj = new Mobile();
		ArrayList<Mobile> List = new ArrayList<Mobile>();
		String sql = "SELECT * from mobiles WHERE PRICE BETWEEN ? AND ? ";
		try {
			connection = DBUtil.OpenConnection();
			pst = connection.prepareStatement(sql);
			pst.setFloat(1, start);
			pst.setFloat(2, end);
			myLog.debug("I am Fetching details in selected Price Range");
			rs = pst.executeQuery();
			while (rs.next()) {
				obj.setMobileId(rs.getInt("MOBILEID"));
				obj.setMobileName(rs.getString("NAME"));
				obj.setPrice(rs.getFloat("Price"));
				obj.setQty(rs.getInt("Quantity"));

				List.add(obj);

			}
		} catch (SQLException e) {
			myLog.error("I am debugging");
			throw new MobileException("No Mobile in given price range");
		} finally {
			DBUtil.CloseConnection();

		}
		myLog.debug("I have fetched details of mobile in given price Range");
		return List;
	}

	@Override
	public int purchaseMobile(Customer obj, String mobileName, int qty)
			throws MobileException {

		int purchaseId = -1;
		int mobileId = 0;
		String seqQuery = "SELECT pseq.NEXTVAL FROM DUAL";
		String chkQuery = "SELECT quantity FROM mobiles WHERE name = ? ";
		String updQuery = "UPDATE mobiles SEt quantity = quantity -? WHERE name = ? ";
		String insQuery = "INSERT INTO purchasedetails VALUES(?,?,?,?,?,?)";
		String idQuery = "SELECT mobileId FROM mobiles WHERE name = ?";
		Connection connection = null;
		Statement seqst = null;
		PreparedStatement chkPst = null;
		PreparedStatement uptPst = null;
		PreparedStatement insPst = null;
		PreparedStatement idPst = null;
		ResultSet idRs = null;
		ResultSet seqRs = null;
		ResultSet chkRs = null;
		try {
			connection = DBUtil.OpenConnection();
			chkPst = connection.prepareStatement(chkQuery);
			chkPst.setString(1, mobileName);
			myLog.debug("I am Checking Quantity");
			chkRs = chkPst.executeQuery();
			if (chkRs.next()) {

				int avQty = chkRs.getInt("Quantity");
				if (avQty > qty) {
					uptPst = connection.prepareStatement(updQuery);
					uptPst.setInt(1, qty);
					uptPst.setString(2, mobileName);
					myLog.debug("I am Updating Details in Mobiles");
					uptPst.executeQuery();

					idPst = connection.prepareStatement(idQuery);
					idPst.setString(1, mobileName);
					myLog.debug("I am Inserting Details in PurchaseDetails");
					idRs = idPst.executeQuery();
					if (idRs.next()) {
						mobileId = idRs.getInt(1);

					}

					seqst = connection.createStatement();
					myLog.debug("Generating Seq");
					seqRs = seqst.executeQuery(seqQuery);
					if (seqRs.next()) {
						purchaseId = seqRs.getInt(1);

					}

					insPst = connection.prepareStatement(insQuery);
					insPst.setInt(1, purchaseId);
					insPst.setString(2, obj.getCustName());
					insPst.setString(3, obj.getMailId());
					insPst.setLong(4, obj.getPhoneNo());
					LocalDate pDate = LocalDate.now();
					Date purchaseDate = Date.valueOf(pDate);
					insPst.setDate(5, purchaseDate);
					insPst.setInt(6, mobileId);
					insPst.executeUpdate();

				} else {
					throw new MobileException(
							"Order cannot be placed due to insufficient quantity");
				}

			}

		} catch (SQLException e) {
			myLog.error("i am debugging");
			System.out.println(e.getMessage());
		} finally {
			DBUtil.CloseConnection();

		}
		myLog.debug("I have Checked,updated,inserted Details");
		return purchaseId;
	}

}
