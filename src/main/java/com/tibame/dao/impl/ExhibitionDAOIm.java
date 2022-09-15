package com.tibame.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.dao.inft.ExhibitionDAOIn;
import com.tibame.dao.sql.ExhibitionSQL;
import com.tibame.model.vo.ExhibitionVO;

public class ExhibitionDAOIm implements ExhibitionDAOIn {

	private DataSource ds;

	public ExhibitionDAOIm() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA103");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ExhibitionVO> getAll() throws Exception {
		List<ExhibitionVO> list = new ArrayList<ExhibitionVO>();
		// try with resources
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ExhibitionSQL.GET_ALL);) {
			System.out.println("連線成功");
			try (ResultSet rs = pstmt.executeQuery()) {
				ExhibitionVO vo;
				while (rs.next()) {

					vo = new ExhibitionVO();
					vo.setExhibitionId(rs.getInt("EXHIBITION_ID"));
					vo.setExhibitionType(rs.getInt("EXHIBITION_TYPE"));
					vo.setExhibitionName(rs.getString("EXHIBITION_NAME"));
					vo.setExhibitionStartDate(rs.getString("EXHIBITION_START_DATE"));
					vo.setExhibitionEndDate(rs.getString("EXHIBITION_END_DATE"));
					vo.setExhibitionArticle(rs.getString("EXHIBITION_ARTICLE"));
					vo.setExhibitionStatus(rs.getInt("EXHIBITION_STATUS"));
					vo.setLastUpdateTime(rs.getString("LAST_UPDATE_TIME"));
					vo.setLocationId(rs.getInt("LOCATION_ID"));
					vo.setExhibitionImg(rs.getBytes("EXHIBITION_IMG"));
					list.add(vo);
				}
			}
		}
		return list;
	}

	@Override
	public List<ExhibitionVO> getById(Integer id) throws Exception {
		List<ExhibitionVO> list = new ArrayList<ExhibitionVO>();

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ExhibitionSQL.GET_BY_ID);) {
			pstmt.setInt(1, id);
			System.out.println("連線成功");
			try (ResultSet rs = pstmt.executeQuery()) {
				ExhibitionVO vo;
				while (rs.next()) {

					vo = new ExhibitionVO();
					vo.setExhibitionId(rs.getInt("EXHIBITION_ID"));
					vo.setExhibitionType(rs.getInt("EXHIBITION_TYPE"));
					vo.setExhibitionName(rs.getString("EXHIBITION_NAME"));
					vo.setExhibitionStartDate(rs.getString("EXHIBITION_START_DATE"));
					vo.setExhibitionEndDate(rs.getString("EXHIBITION_END_DATE"));
					vo.setExhibitionArticle(rs.getString("EXHIBITION_ARTICLE"));
					vo.setExhibitionStatus(rs.getInt("EXHIBITION_STATUS"));
					vo.setLastUpdateTime(rs.getString("LAST_UPDATE_TIME"));
					vo.setLocationId(rs.getInt("LOCATION_ID"));
					vo.setExhibitionImg(rs.getBytes("EXHIBITION_IMG"));

					list.add(vo);
				}
			}
			return list;
		}
	}

	@Override
	public boolean insert(ExhibitionVO vo) throws Exception {
		// try with resources
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ExhibitionSQL.INSERT);) {
			System.out.println("連線成功");

			pstmt.setInt(1, vo.getExhibitionType());
			pstmt.setString(2, vo.getExhibitionName());
			pstmt.setString(3, vo.getExhibitionStartDate());
			pstmt.setString(4, vo.getExhibitionEndDate());
			pstmt.setString(5, vo.getExhibitionArticle());
			pstmt.setInt(6, vo.getExhibitionStatus());
			pstmt.setString(7, vo.getLastUpdateTime());
			pstmt.setInt(8, vo.getLocationId());
			pstmt.setBytes(9, vo.getExhibitionImg());
			return pstmt.executeUpdate() != 0;
		}
	}

	@Override
	public boolean updateImg(byte[] img, Integer id) throws Exception {
		// try with resources
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ExhibitionSQL.UPDATE_IMG);) {
			System.out.println("連線成功");

			pstmt.setBytes(1, img);
			pstmt.setInt(2, id);

			return pstmt.executeUpdate() != 0;
		}
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ExhibitionSQL.DELETE);) {
			System.out.println("連線成功");

			pstmt.setInt(1, id);

			return pstmt.executeUpdate() != 0;
		}
	}

}
