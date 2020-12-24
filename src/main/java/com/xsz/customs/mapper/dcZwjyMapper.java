package com.xsz.customs.mapper;

import com.xsz.customs.model.dcZwjy;
import com.xsz.customs.model.dcZwjyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface dcZwjyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    long countByExample(dcZwjyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    int deleteByExample(dcZwjyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    int insert(dcZwjy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    int insertSelective(dcZwjy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    List<dcZwjy> selectByExample(dcZwjyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    dcZwjy selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    int updateByExampleSelective(@Param("record") dcZwjy record, @Param("example") dcZwjyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    int updateByExample(@Param("record") dcZwjy record, @Param("example") dcZwjyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    int updateByPrimaryKeySelective(dcZwjy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_zwjy
     *
     * @mbg.generated Wed Dec 23 18:46:27 CST 2020
     */
    int updateByPrimaryKey(dcZwjy record);
}