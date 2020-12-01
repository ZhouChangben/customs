package com.xsz.customs.mapper;

import com.xsz.customs.model.dcUser;
import com.xsz.customs.model.dcUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface dcUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    long countByExample(dcUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    int deleteByExample(dcUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    int insert(dcUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    int insertSelective(dcUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    List<dcUser> selectByExample(dcUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    dcUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    int updateByExampleSelective(@Param("record") dcUser record, @Param("example") dcUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    int updateByExample(@Param("record") dcUser record, @Param("example") dcUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    int updateByPrimaryKeySelective(dcUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_user
     *
     * @mbg.generated Tue Dec 01 15:36:30 CST 2020
     */
    int updateByPrimaryKey(dcUser record);
}