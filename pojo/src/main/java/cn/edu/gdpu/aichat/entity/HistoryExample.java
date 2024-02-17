package cn.edu.gdpu.aichat.entity;

import java.util.ArrayList;
import java.util.List;

public class HistoryExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    public HistoryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
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
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
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

        public Criteria andHistoryIdIsNull() {
            addCriterion("history_id is null");
            return (Criteria) this;
        }

        public Criteria andHistoryIdIsNotNull() {
            addCriterion("history_id is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryIdEqualTo(Integer value) {
            addCriterion("history_id =", value, "historyId");
            return (Criteria) this;
        }

        public Criteria andHistoryIdNotEqualTo(Integer value) {
            addCriterion("history_id <>", value, "historyId");
            return (Criteria) this;
        }

        public Criteria andHistoryIdGreaterThan(Integer value) {
            addCriterion("history_id >", value, "historyId");
            return (Criteria) this;
        }

        public Criteria andHistoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("history_id >=", value, "historyId");
            return (Criteria) this;
        }

        public Criteria andHistoryIdLessThan(Integer value) {
            addCriterion("history_id <", value, "historyId");
            return (Criteria) this;
        }

        public Criteria andHistoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("history_id <=", value, "historyId");
            return (Criteria) this;
        }

        public Criteria andHistoryIdIn(List<Integer> values) {
            addCriterion("history_id in", values, "historyId");
            return (Criteria) this;
        }

        public Criteria andHistoryIdNotIn(List<Integer> values) {
            addCriterion("history_id not in", values, "historyId");
            return (Criteria) this;
        }

        public Criteria andHistoryIdBetween(Integer value1, Integer value2) {
            addCriterion("history_id between", value1, value2, "historyId");
            return (Criteria) this;
        }

        public Criteria andHistoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("history_id not between", value1, value2, "historyId");
            return (Criteria) this;
        }

        public Criteria andHistoryNameIsNull() {
            addCriterion("history_name is null");
            return (Criteria) this;
        }

        public Criteria andHistoryNameIsNotNull() {
            addCriterion("history_name is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryNameEqualTo(String value) {
            addCriterion("history_name =", value, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameNotEqualTo(String value) {
            addCriterion("history_name <>", value, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameGreaterThan(String value) {
            addCriterion("history_name >", value, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("history_name >=", value, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameLessThan(String value) {
            addCriterion("history_name <", value, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameLessThanOrEqualTo(String value) {
            addCriterion("history_name <=", value, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameLike(String value) {
            addCriterion("history_name like", value, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameNotLike(String value) {
            addCriterion("history_name not like", value, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameIn(List<String> values) {
            addCriterion("history_name in", values, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameNotIn(List<String> values) {
            addCriterion("history_name not in", values, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameBetween(String value1, String value2) {
            addCriterion("history_name between", value1, value2, "historyName");
            return (Criteria) this;
        }

        public Criteria andHistoryNameNotBetween(String value1, String value2) {
            addCriterion("history_name not between", value1, value2, "historyName");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table history
     *
     * @mbggenerated do_not_delete_during_merge Tue Apr 04 19:27:46 CST 2023
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table history
     *
     * @mbggenerated Tue Apr 04 19:27:46 CST 2023
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