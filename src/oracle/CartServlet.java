package oracle;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.Cart;

public class CartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CartServlet() {
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
		//������빺�ﳵ����,id��ʾ��Ʒ���,number��ʾ��������
		
	    int id = Integer.parseInt(request.getParameter("id"));
		int number = Integer.parseInt(request.getParameter("number"));		
		
		//ȡ��session����Ĺ��ﳵ
		HttpSession session = request.getSession();
		Vector<Cart> cart = (Vector<Cart>)session.getAttribute("cart");
	    if (cart == null)
	    {
	    	cart = new Vector<Cart>();
	    	session.setAttribute("cart", cart);
	    }
	    
	    //���빺�ﳵ���߸�������
	    boolean flag=false;
	    for (int i=0; i<=cart.size()-1;i++)
	    {
	    	if(cart.get(i).getId()== id)
	    	{
	    		//��Ʒ���ڹ��ﳵ�д���
	    		cart.get(i).setNumber(cart.get(i).getNumber()+number);
	    		flag = true;
	    		break;
	    	}
	    	
	    }
	    if (flag==false)
	    {
	    	//��Ʒ���ڹ��ﳵ��
	    	Cart c =new Cart();
	    	c.setId(id);
	    	c.setNumber(number);
	    	cart.add(c);
	    }
	    
	    //���������jsp��ʾ
		String result="1";
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
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
