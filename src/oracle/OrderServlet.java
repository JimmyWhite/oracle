package oracle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.ConnDB;

public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public OrderServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理下单请求和管理手机和地址
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		int version = Integer.parseInt(request.getParameter("version"));
		float price = Float.parseFloat(request.getParameter("price"));
		//计算利润
		float profit = 0;
		Vector<Cart> carts = (Vector<Cart>)request.getSession().getAttribute("cart");
		Vector<Product> products = (Vector<Product>)request.getSession().getAttribute("product");
		for (int i=0; i<=carts.size()-1; i++)
		{
			profit += (products.get(carts.get(i).getId()).getSell_price()
					  -products.get(carts.get(i).getId()).getBuy_price())* carts.get(i).getNumber(); 	
		}
		try {
			ConnDB.addOrder(version, price, profit, phone, address, carts);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("cart",null);	
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("1");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
