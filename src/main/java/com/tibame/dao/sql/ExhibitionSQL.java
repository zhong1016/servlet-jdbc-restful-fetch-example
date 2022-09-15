package com.tibame.dao.sql;

public class ExhibitionSQL {
	// EXHIBITION_ID, EXHIBITION_TYPE, EXHIBITION_NAME, EXHIBITION_START_DATE,
	// EXHIBITION_END_DATE, EXHIBITION_ARTICLE, EXHIBITION_STATUS,
	// LAST_UPDATE_TIME, LOCATION_ID ,EXHIBITION_IMG

	public static final String GET_ALL = "SELECT * FROM eagle_museum.exhibition";

	public static final String GET_BY_ID = "SELECT * FROM eagle_museum.exhibition where EXHIBITION_ID = ?";

	public static final String INSERT = "INSERT INTO `eagle_museum`.`exhibition` (`EXHIBITION_TYPE`, `EXHIBITION_NAME`, `EXHIBITION_START_DATE`, `EXHIBITION_END_DATE`, `EXHIBITION_ARTICLE`, `EXHIBITION_STATUS`, `LAST_UPDATE_TIME`, `LOCATION_ID`, `EXHIBITION_IMG`) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ?)";

	public static final String UPDATE_IMG = "UPDATE `eagle_museum`.`exhibition` SET `EXHIBITION_IMG` = ? WHERE (`EXHIBITION_ID` = ?)";

	public static final String DELETE = "DELETE FROM `eagle_museum`.`exhibition` WHERE (`EXHIBITION_ID` = ?);";

}
