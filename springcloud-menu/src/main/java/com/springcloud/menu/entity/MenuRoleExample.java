package com.springcloud.menu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MenuRoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andMenuRoleIdIsNull() {
            addCriterion("menu_role_id is null");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdIsNotNull() {
            addCriterion("menu_role_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdEqualTo(Integer value) {
            addCriterion("menu_role_id =", value, "menuRoleId");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdNotEqualTo(Integer value) {
            addCriterion("menu_role_id <>", value, "menuRoleId");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdGreaterThan(Integer value) {
            addCriterion("menu_role_id >", value, "menuRoleId");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("menu_role_id >=", value, "menuRoleId");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdLessThan(Integer value) {
            addCriterion("menu_role_id <", value, "menuRoleId");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("menu_role_id <=", value, "menuRoleId");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdIn(List<Integer> values) {
            addCriterion("menu_role_id in", values, "menuRoleId");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdNotIn(List<Integer> values) {
            addCriterion("menu_role_id not in", values, "menuRoleId");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("menu_role_id between", value1, value2, "menuRoleId");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("menu_role_id not between", value1, value2, "menuRoleId");
            return (Criteria) this;
        }

        public Criteria andIsAvailableIsNull() {
            addCriterion("is_available is null");
            return (Criteria) this;
        }

        public Criteria andIsAvailableIsNotNull() {
            addCriterion("is_available is not null");
            return (Criteria) this;
        }

        public Criteria andIsAvailableEqualTo(Boolean value) {
            addCriterion("is_available =", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableNotEqualTo(Boolean value) {
            addCriterion("is_available <>", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableGreaterThan(Boolean value) {
            addCriterion("is_available >", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_available >=", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableLessThan(Boolean value) {
            addCriterion("is_available <", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableLessThanOrEqualTo(Boolean value) {
            addCriterion("is_available <=", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableIn(List<Boolean> values) {
            addCriterion("is_available in", values, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableNotIn(List<Boolean> values) {
            addCriterion("is_available not in", values, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableBetween(Boolean value1, Boolean value2) {
            addCriterion("is_available between", value1, value2, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_available not between", value1, value2, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyIsNull() {
            addCriterion("menu_role_identify is null");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyIsNotNull() {
            addCriterion("menu_role_identify is not null");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyEqualTo(String value) {
            addCriterion("menu_role_identify =", value, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyNotEqualTo(String value) {
            addCriterion("menu_role_identify <>", value, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyGreaterThan(String value) {
            addCriterion("menu_role_identify >", value, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyGreaterThanOrEqualTo(String value) {
            addCriterion("menu_role_identify >=", value, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyLessThan(String value) {
            addCriterion("menu_role_identify <", value, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyLessThanOrEqualTo(String value) {
            addCriterion("menu_role_identify <=", value, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyLike(String value) {
            addCriterion("menu_role_identify like", value, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyNotLike(String value) {
            addCriterion("menu_role_identify not like", value, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyIn(List<String> values) {
            addCriterion("menu_role_identify in", values, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyNotIn(List<String> values) {
            addCriterion("menu_role_identify not in", values, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyBetween(String value1, String value2) {
            addCriterion("menu_role_identify between", value1, value2, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleIdentifyNotBetween(String value1, String value2) {
            addCriterion("menu_role_identify not between", value1, value2, "menuRoleIdentify");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameIsNull() {
            addCriterion("menu_role_name is null");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameIsNotNull() {
            addCriterion("menu_role_name is not null");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameEqualTo(String value) {
            addCriterion("menu_role_name =", value, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameNotEqualTo(String value) {
            addCriterion("menu_role_name <>", value, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameGreaterThan(String value) {
            addCriterion("menu_role_name >", value, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("menu_role_name >=", value, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameLessThan(String value) {
            addCriterion("menu_role_name <", value, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameLessThanOrEqualTo(String value) {
            addCriterion("menu_role_name <=", value, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameLike(String value) {
            addCriterion("menu_role_name like", value, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameNotLike(String value) {
            addCriterion("menu_role_name not like", value, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameIn(List<String> values) {
            addCriterion("menu_role_name in", values, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameNotIn(List<String> values) {
            addCriterion("menu_role_name not in", values, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameBetween(String value1, String value2) {
            addCriterion("menu_role_name between", value1, value2, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andMenuRoleNameNotBetween(String value1, String value2) {
            addCriterion("menu_role_name not between", value1, value2, "menuRoleName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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