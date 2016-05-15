package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import oracle.Product;
import oracle.Cart;

import java.util.*;

public class ConnDB {
	public static void addOrder(int version,float price,float profit,String tel,String address,Vector<Cart> cart) throws ClassNotFoundException, SQLException
	{
		/*
		 * ���Ӷ�����д������������ر�
		 */
		//���ݿ����ӱ���
		Connection conn = null;
		PreparedStatement pre =null;
		ResultSet rs = null;
		int no = 0;
		
		//���ݿ����
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
		String username = "scott";
		String password = "tiger";
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���Ӷ�����鿴��ǰ��������
		String sql = "select NO_SEQUENCE.nextval from dual";
		pre = conn.prepareStatement(sql);
		rs = pre.executeQuery();
		while (rs.next())
		{
			no = rs.getInt(1);
		}
		
		//׼�������ύ��ORDERS��
		sql ="insert into ORDERS(NO,VERSION,TIME,ALL_PRICE,PROFIT) values(?,?,?,?,?)";
	    pre = conn.prepareStatement(sql);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pre.setInt(1, no);
			pre.setInt(2, version);
			pre.setTimestamp(3, time);
			pre.setFloat(4,price);
			pre.setFloat(5, profit);
			pre.execute();
			
		int orderno = no;
		//ѭ��׼�������ύ��ORDERS_DETAIL��
		for (int i=0; i<=cart.size()-1; i++)
		{
			sql = "select NO2_SEQUENCE.nextval from dual";
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next())
			{
				no=rs.getInt(1);
			}
		    sql = "insert into ORDERS_DETAIL(NO,ORDERNO,PRODUCTNO,NUMBERS) values(?,?,?,?)";
		    pre = conn.prepareStatement(sql);
		    pre.setInt(1, no);
		    pre.setInt(2, orderno);
		    pre.setInt(3, cart.get(i).getId());
		    pre.setInt(4, cart.get(i).getNumber());
		    pre.execute();
		}
		if (version == 1)
		{
			//�ύ��ϵ��ʽ��CONTACT��
			sql = "select NO3_SEQUENCE.nextval from dual";
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next())
			{
				no=rs.getInt(1);
			}
			sql = "insert into CONTACT(NO,ORDERNO,TEL,ADDRESS) values(?,?,?,?)";
			pre = conn.prepareStatement(sql);
			pre.setInt(1, no);
			pre.setInt(2, orderno);
			pre.setString(3, tel);
			pre.setString(4, address);
			pre.execute();			
		}
	}
  
	public static Vector<Product> main(String[] args) {
		// TODO Auto-generated method stub
		//���ݿ����ӱ���
		Connection conn = null;   
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		//������Ʒ��Ϣ
		Vector<Product> vector = new Vector<Product>();
		try {
			//���ݿ����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
			String username = "scott";
			String password = "tiger";
			try {
				conn = DriverManager.getConnection(url,username,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String sql = "select * from PRODUCT";
		try {
			pre = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = pre.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next())
			{
				//�趨��Ʒ��Ϣ
				Product product = new Product();
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String brand = rs.getString("BRAND");
				String spec = rs.getString("SPEC");
				String photo = rs.getString("PHOTO");
				float buy_price = rs.getFloat("BUY_PRICE");
				float sell_price = rs.getFloat("SELL_PRICE");
				product.setBrand(brand);
				product.setBuy_price(buy_price);
				product.setSell_price(sell_price);
				product.setName(name);
				product.setNo(no);
				product.setSpec(spec);
				product.setPhoto(photo);
				vector.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
		
	}

}
