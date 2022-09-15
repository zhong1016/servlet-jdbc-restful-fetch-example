package com.tibame.service.impl;

import java.util.Base64;
import java.util.List;

import com.tibame.common.Global;
import com.tibame.common.Result;
import com.tibame.dao.impl.ExhibitionDAOIm;
import com.tibame.model.vo.ExhibitionVO;
import com.tibame.service.inft.ExhibitionServiceIn;

public class ExhibitionServiceIm implements ExhibitionServiceIn {

	private ExhibitionDAOIm DAO;
	// RESTFUL
	private Result R;

	public ExhibitionServiceIm() {
		DAO = new ExhibitionDAOIm();
		R = new Result();
	}

	@Override
	public Result getAll() {
		try {
			return R.success(getBase64(DAO.getAll()));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result getById(Integer id) {
		try {
			return R.success(getBase64(DAO.getById(id)));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result insert(ExhibitionVO vo) {

		try {
			var img = vo.getExhibitionImgBase64().replace(Global.BASE64, "");
			if (img != null) {
				vo.setExhibitionImg(Base64.getDecoder().decode(img));
			}
			return R.success(DAO.insert(vo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result updateImg(String img, Integer id) {

		try {
			return R.success(DAO.updateImg(Base64.getDecoder().decode(img), id));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result delete(Integer id) {
		try {
			return R.success(DAO.delete(id));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	public List<ExhibitionVO> getBase64(List<ExhibitionVO> list) {

		for (ExhibitionVO vo : list) {
			var img = vo.getExhibitionImg();
			if (img != null) {
				vo.setExhibitionImgBase64(Global.BASE64 + Base64.getEncoder().encodeToString(img));
				vo.setExhibitionImg(null);
			}
		}

		return list;
	}

}
