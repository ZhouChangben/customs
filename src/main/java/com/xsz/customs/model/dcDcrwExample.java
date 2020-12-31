package com.xsz.customs.model;

import java.util.ArrayList;
import java.util.List;

public class dcDcrwExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public dcDcrwExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcIsNull() {
            addCriterion("dc_renwumc is null");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcIsNotNull() {
            addCriterion("dc_renwumc is not null");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcEqualTo(String value) {
            addCriterion("dc_renwumc =", value, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcNotEqualTo(String value) {
            addCriterion("dc_renwumc <>", value, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcGreaterThan(String value) {
            addCriterion("dc_renwumc >", value, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcGreaterThanOrEqualTo(String value) {
            addCriterion("dc_renwumc >=", value, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcLessThan(String value) {
            addCriterion("dc_renwumc <", value, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcLessThanOrEqualTo(String value) {
            addCriterion("dc_renwumc <=", value, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcLike(String value) {
            addCriterion("dc_renwumc like", value, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcNotLike(String value) {
            addCriterion("dc_renwumc not like", value, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcIn(List<String> values) {
            addCriterion("dc_renwumc in", values, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcNotIn(List<String> values) {
            addCriterion("dc_renwumc not in", values, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcBetween(String value1, String value2) {
            addCriterion("dc_renwumc between", value1, value2, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwumcNotBetween(String value1, String value2) {
            addCriterion("dc_renwumc not between", value1, value2, "dcRenwumc");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhIsNull() {
            addCriterion("dc_renwuxh is null");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhIsNotNull() {
            addCriterion("dc_renwuxh is not null");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhEqualTo(Integer value) {
            addCriterion("dc_renwuxh =", value, "dcRenwuxh");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhNotEqualTo(Integer value) {
            addCriterion("dc_renwuxh <>", value, "dcRenwuxh");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhGreaterThan(Integer value) {
            addCriterion("dc_renwuxh >", value, "dcRenwuxh");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhGreaterThanOrEqualTo(Integer value) {
            addCriterion("dc_renwuxh >=", value, "dcRenwuxh");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhLessThan(Integer value) {
            addCriterion("dc_renwuxh <", value, "dcRenwuxh");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhLessThanOrEqualTo(Integer value) {
            addCriterion("dc_renwuxh <=", value, "dcRenwuxh");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhIn(List<Integer> values) {
            addCriterion("dc_renwuxh in", values, "dcRenwuxh");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhNotIn(List<Integer> values) {
            addCriterion("dc_renwuxh not in", values, "dcRenwuxh");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhBetween(Integer value1, Integer value2) {
            addCriterion("dc_renwuxh between", value1, value2, "dcRenwuxh");
            return (Criteria) this;
        }

        public Criteria andDcRenwuxhNotBetween(Integer value1, Integer value2) {
            addCriterion("dc_renwuxh not between", value1, value2, "dcRenwuxh");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmIsNull() {
            addCriterion("dc_renwugqdm is null");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmIsNotNull() {
            addCriterion("dc_renwugqdm is not null");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmEqualTo(String value) {
            addCriterion("dc_renwugqdm =", value, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmNotEqualTo(String value) {
            addCriterion("dc_renwugqdm <>", value, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmGreaterThan(String value) {
            addCriterion("dc_renwugqdm >", value, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmGreaterThanOrEqualTo(String value) {
            addCriterion("dc_renwugqdm >=", value, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmLessThan(String value) {
            addCriterion("dc_renwugqdm <", value, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmLessThanOrEqualTo(String value) {
            addCriterion("dc_renwugqdm <=", value, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmLike(String value) {
            addCriterion("dc_renwugqdm like", value, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmNotLike(String value) {
            addCriterion("dc_renwugqdm not like", value, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmIn(List<String> values) {
            addCriterion("dc_renwugqdm in", values, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmNotIn(List<String> values) {
            addCriterion("dc_renwugqdm not in", values, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmBetween(String value1, String value2) {
            addCriterion("dc_renwugqdm between", value1, value2, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqdmNotBetween(String value1, String value2) {
            addCriterion("dc_renwugqdm not between", value1, value2, "dcRenwugqdm");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameIsNull() {
            addCriterion("dc_renwugqname is null");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameIsNotNull() {
            addCriterion("dc_renwugqname is not null");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameEqualTo(String value) {
            addCriterion("dc_renwugqname =", value, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameNotEqualTo(String value) {
            addCriterion("dc_renwugqname <>", value, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameGreaterThan(String value) {
            addCriterion("dc_renwugqname >", value, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameGreaterThanOrEqualTo(String value) {
            addCriterion("dc_renwugqname >=", value, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameLessThan(String value) {
            addCriterion("dc_renwugqname <", value, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameLessThanOrEqualTo(String value) {
            addCriterion("dc_renwugqname <=", value, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameLike(String value) {
            addCriterion("dc_renwugqname like", value, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameNotLike(String value) {
            addCriterion("dc_renwugqname not like", value, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameIn(List<String> values) {
            addCriterion("dc_renwugqname in", values, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameNotIn(List<String> values) {
            addCriterion("dc_renwugqname not in", values, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameBetween(String value1, String value2) {
            addCriterion("dc_renwugqname between", value1, value2, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcRenwugqnameNotBetween(String value1, String value2) {
            addCriterion("dc_renwugqname not between", value1, value2, "dcRenwugqname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameIsNull() {
            addCriterion("dc_dcbname is null");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameIsNotNull() {
            addCriterion("dc_dcbname is not null");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameEqualTo(String value) {
            addCriterion("dc_dcbname =", value, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameNotEqualTo(String value) {
            addCriterion("dc_dcbname <>", value, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameGreaterThan(String value) {
            addCriterion("dc_dcbname >", value, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameGreaterThanOrEqualTo(String value) {
            addCriterion("dc_dcbname >=", value, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameLessThan(String value) {
            addCriterion("dc_dcbname <", value, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameLessThanOrEqualTo(String value) {
            addCriterion("dc_dcbname <=", value, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameLike(String value) {
            addCriterion("dc_dcbname like", value, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameNotLike(String value) {
            addCriterion("dc_dcbname not like", value, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameIn(List<String> values) {
            addCriterion("dc_dcbname in", values, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameNotIn(List<String> values) {
            addCriterion("dc_dcbname not in", values, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameBetween(String value1, String value2) {
            addCriterion("dc_dcbname between", value1, value2, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbnameNotBetween(String value1, String value2) {
            addCriterion("dc_dcbname not between", value1, value2, "dcDcbname");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjIsNull() {
            addCriterion("dc_dcbtj is null");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjIsNotNull() {
            addCriterion("dc_dcbtj is not null");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjEqualTo(Integer value) {
            addCriterion("dc_dcbtj =", value, "dcDcbtj");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjNotEqualTo(Integer value) {
            addCriterion("dc_dcbtj <>", value, "dcDcbtj");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjGreaterThan(Integer value) {
            addCriterion("dc_dcbtj >", value, "dcDcbtj");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjGreaterThanOrEqualTo(Integer value) {
            addCriterion("dc_dcbtj >=", value, "dcDcbtj");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjLessThan(Integer value) {
            addCriterion("dc_dcbtj <", value, "dcDcbtj");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjLessThanOrEqualTo(Integer value) {
            addCriterion("dc_dcbtj <=", value, "dcDcbtj");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjIn(List<Integer> values) {
            addCriterion("dc_dcbtj in", values, "dcDcbtj");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjNotIn(List<Integer> values) {
            addCriterion("dc_dcbtj not in", values, "dcDcbtj");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjBetween(Integer value1, Integer value2) {
            addCriterion("dc_dcbtj between", value1, value2, "dcDcbtj");
            return (Criteria) this;
        }

        public Criteria andDcDcbtjNotBetween(Integer value1, Integer value2) {
            addCriterion("dc_dcbtj not between", value1, value2, "dcDcbtj");
            return (Criteria) this;
        }

        public Criteria andDcDcbztIsNull() {
            addCriterion("dc_dcbzt is null");
            return (Criteria) this;
        }

        public Criteria andDcDcbztIsNotNull() {
            addCriterion("dc_dcbzt is not null");
            return (Criteria) this;
        }

        public Criteria andDcDcbztEqualTo(String value) {
            addCriterion("dc_dcbzt =", value, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztNotEqualTo(String value) {
            addCriterion("dc_dcbzt <>", value, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztGreaterThan(String value) {
            addCriterion("dc_dcbzt >", value, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztGreaterThanOrEqualTo(String value) {
            addCriterion("dc_dcbzt >=", value, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztLessThan(String value) {
            addCriterion("dc_dcbzt <", value, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztLessThanOrEqualTo(String value) {
            addCriterion("dc_dcbzt <=", value, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztLike(String value) {
            addCriterion("dc_dcbzt like", value, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztNotLike(String value) {
            addCriterion("dc_dcbzt not like", value, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztIn(List<String> values) {
            addCriterion("dc_dcbzt in", values, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztNotIn(List<String> values) {
            addCriterion("dc_dcbzt not in", values, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztBetween(String value1, String value2) {
            addCriterion("dc_dcbzt between", value1, value2, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcDcbztNotBetween(String value1, String value2) {
            addCriterion("dc_dcbzt not between", value1, value2, "dcDcbzt");
            return (Criteria) this;
        }

        public Criteria andDcBy1IsNull() {
            addCriterion("dc_by1 is null");
            return (Criteria) this;
        }

        public Criteria andDcBy1IsNotNull() {
            addCriterion("dc_by1 is not null");
            return (Criteria) this;
        }

        public Criteria andDcBy1EqualTo(String value) {
            addCriterion("dc_by1 =", value, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1NotEqualTo(String value) {
            addCriterion("dc_by1 <>", value, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1GreaterThan(String value) {
            addCriterion("dc_by1 >", value, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1GreaterThanOrEqualTo(String value) {
            addCriterion("dc_by1 >=", value, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1LessThan(String value) {
            addCriterion("dc_by1 <", value, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1LessThanOrEqualTo(String value) {
            addCriterion("dc_by1 <=", value, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1Like(String value) {
            addCriterion("dc_by1 like", value, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1NotLike(String value) {
            addCriterion("dc_by1 not like", value, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1In(List<String> values) {
            addCriterion("dc_by1 in", values, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1NotIn(List<String> values) {
            addCriterion("dc_by1 not in", values, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1Between(String value1, String value2) {
            addCriterion("dc_by1 between", value1, value2, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy1NotBetween(String value1, String value2) {
            addCriterion("dc_by1 not between", value1, value2, "dcBy1");
            return (Criteria) this;
        }

        public Criteria andDcBy2IsNull() {
            addCriterion("dc_by2 is null");
            return (Criteria) this;
        }

        public Criteria andDcBy2IsNotNull() {
            addCriterion("dc_by2 is not null");
            return (Criteria) this;
        }

        public Criteria andDcBy2EqualTo(String value) {
            addCriterion("dc_by2 =", value, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2NotEqualTo(String value) {
            addCriterion("dc_by2 <>", value, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2GreaterThan(String value) {
            addCriterion("dc_by2 >", value, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2GreaterThanOrEqualTo(String value) {
            addCriterion("dc_by2 >=", value, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2LessThan(String value) {
            addCriterion("dc_by2 <", value, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2LessThanOrEqualTo(String value) {
            addCriterion("dc_by2 <=", value, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2Like(String value) {
            addCriterion("dc_by2 like", value, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2NotLike(String value) {
            addCriterion("dc_by2 not like", value, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2In(List<String> values) {
            addCriterion("dc_by2 in", values, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2NotIn(List<String> values) {
            addCriterion("dc_by2 not in", values, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2Between(String value1, String value2) {
            addCriterion("dc_by2 between", value1, value2, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy2NotBetween(String value1, String value2) {
            addCriterion("dc_by2 not between", value1, value2, "dcBy2");
            return (Criteria) this;
        }

        public Criteria andDcBy3IsNull() {
            addCriterion("dc_by3 is null");
            return (Criteria) this;
        }

        public Criteria andDcBy3IsNotNull() {
            addCriterion("dc_by3 is not null");
            return (Criteria) this;
        }

        public Criteria andDcBy3EqualTo(String value) {
            addCriterion("dc_by3 =", value, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3NotEqualTo(String value) {
            addCriterion("dc_by3 <>", value, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3GreaterThan(String value) {
            addCriterion("dc_by3 >", value, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3GreaterThanOrEqualTo(String value) {
            addCriterion("dc_by3 >=", value, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3LessThan(String value) {
            addCriterion("dc_by3 <", value, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3LessThanOrEqualTo(String value) {
            addCriterion("dc_by3 <=", value, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3Like(String value) {
            addCriterion("dc_by3 like", value, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3NotLike(String value) {
            addCriterion("dc_by3 not like", value, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3In(List<String> values) {
            addCriterion("dc_by3 in", values, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3NotIn(List<String> values) {
            addCriterion("dc_by3 not in", values, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3Between(String value1, String value2) {
            addCriterion("dc_by3 between", value1, value2, "dcBy3");
            return (Criteria) this;
        }

        public Criteria andDcBy3NotBetween(String value1, String value2) {
            addCriterion("dc_by3 not between", value1, value2, "dcBy3");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dc_dcrw
     *
     * @mbg.generated do_not_delete_during_merge Thu Dec 31 12:45:20 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dc_dcrw
     *
     * @mbg.generated Thu Dec 31 12:45:20 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}