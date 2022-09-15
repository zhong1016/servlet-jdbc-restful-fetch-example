package com.tibame.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.bean.BajarjoBean;
import com.tibame.common.Global;
import com.tibame.model.vo.ExhibitionVO;
import com.tibame.service.impl.ExhibitionServiceIm;

@WebServlet("/exhibitions/*")
public class ExhibitionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Gson _gson = new Gson();
	private ExhibitionServiceIm service = new ExhibitionServiceIm();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);

		String pathInfo = request.getPathInfo();
		String id;

		// 判斷是否有 ID
		if (pathInfo != null) {
			id = pathInfo.split("/")[1];
		} else {
			id = request.getParameter("exhibitionId");
		}
		// 資料轉換 JSON 後回傳
		response.getWriter().print(_gson.toJson(id == null ? service.getAll() : service.getById(Integer.parseInt(id))));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);

		ExhibitionVO vo = _gson.fromJson(request.getReader().readLine(), ExhibitionVO.class);

		response.getWriter().print(_gson.toJson(service.insert(vo)));

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		setHeaders(response);

		BajarjoBean bajarjo = _gson.fromJson(request.getReader().readLine(), BajarjoBean.class);

		response.getWriter().print(_gson.toJson(
				service.updateImg(bajarjo.getExhibitionImg().replace(Global.BASE64, ""), bajarjo.getExhibitionId())));
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);

		String pathInfo = request.getPathInfo();
		String id;

		// 判斷是否有 ID
		if (pathInfo != null) {
			id = pathInfo.split("/")[1];
		} else {
			id = request.getParameter("exhibitionId");
		}
		response.getWriter().print(_gson.toJson(service.delete(Integer.parseInt(id))));
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

	/*
	 * 誇域
	 */
	private void setHeaders(HttpServletResponse response) {

		response.setContentType("application/json;charset=UTF-8"); // 重要
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.addHeader("Access-Control-Allow-Origin", "*"); // 重要
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Max-Age", "86400");
	}
}
