package com.seavus.calculator;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seavus.domain.Calculation;

@WebServlet("/calculate")
public class WebCalculatorServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doSessionCalculation(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doSessionCalculation(req, resp);
	}

	protected void doSessionCalculation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		ArrayList<Calculation> list = (ArrayList<Calculation>) session.getAttribute("allCalculations");
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(calculate(req, resp));
		session.setAttribute("allCalculations", list);
	}

	protected Calculation calculate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer num1 = Integer.parseInt(req.getParameter("number1"));
		Integer num2 = Integer.parseInt(req.getParameter("number2"));
		Integer result;
		if (req.getParameter("operand").equals("+")) {
			result = num1 + num2;
		} else {
			result = num1 - num2;
		}
		resp.setContentType("text/html");
		resp.getWriter().println("The result is: " + result.toString());
		return new Calculation(num1, num2, req.getParameter("operand"), result);
	}
}
