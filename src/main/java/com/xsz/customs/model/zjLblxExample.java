package com.xsz.customs.model;

import java.util.ArrayList;
import java.util.List;

public class zjLblxExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    public zjLblxExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
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
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
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

        public Criteria andZjlbDmIsNull() {
            addCriterion("zjlb_dm is null");
            return (Criteria) this;
        }

        public Criteria andZjlbDmIsNotNull() {
            addCriterion("zjlb_dm is not null");
            return (Criteria) this;
        }

        public Criteria andZjlbDmEqualTo(String value) {
            addCriterion("zjlb_dm =", value, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmNotEqualTo(String value) {
            addCriterion("zjlb_dm <>", value, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmGreaterThan(String value) {
            addCriterion("zjlb_dm >", value, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmGreaterThanOrEqualTo(String value) {
            addCriterion("zjlb_dm >=", value, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmLessThan(String value) {
            addCriterion("zjlb_dm <", value, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmLessThanOrEqualTo(String value) {
            addCriterion("zjlb_dm <=", value, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmLike(String value) {
            addCriterion("zjlb_dm like", value, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmNotLike(String value) {
            addCriterion("zjlb_dm not like", value, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmIn(List<String> values) {
            addCriterion("zjlb_dm in", values, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmNotIn(List<String> values) {
            addCriterion("zjlb_dm not in", values, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmBetween(String value1, String value2) {
            addCriterion("zjlb_dm between", value1, value2, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbDmNotBetween(String value1, String value2) {
            addCriterion("zjlb_dm not between", value1, value2, "zjlbDm");
            return (Criteria) this;
        }

        public Criteria andZjlbMcIsNull() {
            addCriterion("zjlb_mc is null");
            return (Criteria) this;
        }

        public Criteria andZjlbMcIsNotNull() {
            addCriterion("zjlb_mc is not null");
            return (Criteria) this;
        }

        public Criteria andZjlbMcEqualTo(String value) {
            addCriterion("zjlb_mc =", value, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcNotEqualTo(String value) {
            addCriterion("zjlb_mc <>", value, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcGreaterThan(String value) {
            addCriterion("zjlb_mc >", value, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcGreaterThanOrEqualTo(String value) {
            addCriterion("zjlb_mc >=", value, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcLessThan(String value) {
            addCriterion("zjlb_mc <", value, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcLessThanOrEqualTo(String value) {
            addCriterion("zjlb_mc <=", value, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcLike(String value) {
            addCriterion("zjlb_mc like", value, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcNotLike(String value) {
            addCriterion("zjlb_mc not like", value, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcIn(List<String> values) {
            addCriterion("zjlb_mc in", values, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcNotIn(List<String> values) {
            addCriterion("zjlb_mc not in", values, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcBetween(String value1, String value2) {
            addCriterion("zjlb_mc between", value1, value2, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbMcNotBetween(String value1, String value2) {
            addCriterion("zjlb_mc not between", value1, value2, "zjlbMc");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1IsNull() {
            addCriterion("zjlb_by1 is null");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1IsNotNull() {
            addCriterion("zjlb_by1 is not null");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1EqualTo(String value) {
            addCriterion("zjlb_by1 =", value, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1NotEqualTo(String value) {
            addCriterion("zjlb_by1 <>", value, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1GreaterThan(String value) {
            addCriterion("zjlb_by1 >", value, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1GreaterThanOrEqualTo(String value) {
            addCriterion("zjlb_by1 >=", value, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1LessThan(String value) {
            addCriterion("zjlb_by1 <", value, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1LessThanOrEqualTo(String value) {
            addCriterion("zjlb_by1 <=", value, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1Like(String value) {
            addCriterion("zjlb_by1 like", value, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1NotLike(String value) {
            addCriterion("zjlb_by1 not like", value, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1In(List<String> values) {
            addCriterion("zjlb_by1 in", values, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1NotIn(List<String> values) {
            addCriterion("zjlb_by1 not in", values, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1Between(String value1, String value2) {
            addCriterion("zjlb_by1 between", value1, value2, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy1NotBetween(String value1, String value2) {
            addCriterion("zjlb_by1 not between", value1, value2, "zjlbBy1");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2IsNull() {
            addCriterion("zjlb_by2 is null");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2IsNotNull() {
            addCriterion("zjlb_by2 is not null");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2EqualTo(String value) {
            addCriterion("zjlb_by2 =", value, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2NotEqualTo(String value) {
            addCriterion("zjlb_by2 <>", value, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2GreaterThan(String value) {
            addCriterion("zjlb_by2 >", value, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2GreaterThanOrEqualTo(String value) {
            addCriterion("zjlb_by2 >=", value, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2LessThan(String value) {
            addCriterion("zjlb_by2 <", value, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2LessThanOrEqualTo(String value) {
            addCriterion("zjlb_by2 <=", value, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2Like(String value) {
            addCriterion("zjlb_by2 like", value, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2NotLike(String value) {
            addCriterion("zjlb_by2 not like", value, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2In(List<String> values) {
            addCriterion("zjlb_by2 in", values, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2NotIn(List<String> values) {
            addCriterion("zjlb_by2 not in", values, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2Between(String value1, String value2) {
            addCriterion("zjlb_by2 between", value1, value2, "zjlbBy2");
            return (Criteria) this;
        }

        public Criteria andZjlbBy2NotBetween(String value1, String value2) {
            addCriterion("zjlb_by2 not between", value1, value2, "zjlbBy2");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table zj_lblx
     *
     * @mbg.generated do_not_delete_during_merge Sun Jan 24 14:19:18 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table zj_lblx
     *
     * @mbg.generated Sun Jan 24 14:19:18 CST 2021
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