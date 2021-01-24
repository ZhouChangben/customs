package com.xsz.customs.mapper;

import com.xsz.customs.model.dcDwjy;
import com.xsz.customs.model.dcDwjyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface dcDwjyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    long countByExample(dcDwjyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int deleteByExample(dcDwjyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int insert(dcDwjy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int insertSelective(dcDwjy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    List<dcDwjy> selectByExample(dcDwjyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    dcDwjy selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int updateByExampleSelective(@Param("record") dcDwjy record, @Param("example") dcDwjyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int updateByExample(@Param("record") dcDwjy record, @Param("example") dcDwjyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int updateByPrimaryKeySelective(dcDwjy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dwjy
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    int updateByPrimaryKey(dcDwjy record);
}