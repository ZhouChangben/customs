package com.xsz.customs.mapper;

import com.xsz.customs.model.dcDcrw;
import com.xsz.customs.model.dcDcrwExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface dcDcrwMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    long countByExample(dcDcrwExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int deleteByExample(dcDcrwExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int insert(dcDcrw record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int insertSelective(dcDcrw record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    List<dcDcrw> selectByExample(dcDcrwExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    dcDcrw selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int updateByExampleSelective(@Param("record") dcDcrw record, @Param("example") dcDcrwExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int updateByExample(@Param("record") dcDcrw record, @Param("example") dcDcrwExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int updateByPrimaryKeySelective(dcDcrw record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int updateByPrimaryKey(dcDcrw record);
}