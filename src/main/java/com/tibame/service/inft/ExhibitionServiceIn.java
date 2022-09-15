package com.tibame.service.inft;

import com.tibame.common.Result;
import com.tibame.model.vo.ExhibitionVO;

public interface ExhibitionServiceIn {

	public Result getAll() throws Exception;

	public Result getById(Integer id);

	public Result insert(ExhibitionVO vo);

	public Result updateImg(String img, Integer id);

	public Result delete(Integer id);

}
