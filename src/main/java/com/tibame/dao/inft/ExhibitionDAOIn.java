
package com.tibame.dao.inft;

import java.util.List;

import com.tibame.model.vo.ExhibitionVO;

public interface ExhibitionDAOIn {

	public List<ExhibitionVO> getAll() throws Exception;

	public List<ExhibitionVO> getById(Integer id) throws Exception;

	public boolean insert(ExhibitionVO vo) throws Exception;

	public boolean updateImg(byte[] img, Integer id) throws Exception;

	public boolean delete(Integer id) throws Exception;

}
