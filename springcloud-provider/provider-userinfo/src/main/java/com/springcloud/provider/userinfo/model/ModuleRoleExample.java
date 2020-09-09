package com.springcloud.provider.userinfo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModuleRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ModuleRoleExample() {
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

        public Criteria andModuleRoleIdIsNull() {
            addCriterion("module_role_id is null");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdIsNotNull() {
            addCriterion("module_role_id is not null");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdEqualTo(Integer value) {
            addCriterion("module_role_id =", value, "moduleRoleId");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdNotEqualTo(Integer value) {
            addCriterion("module_role_id <>", value, "moduleRoleId");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdGreaterThan(Integer value) {
            addCriterion("module_role_id >", value, "moduleRoleId");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("module_role_id >=", value, "moduleRoleId");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdLessThan(Integer value) {
            addCriterion("module_role_id <", value, "moduleRoleId");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("module_role_id <=", value, "moduleRoleId");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdIn(List<Integer> values) {
            addCriterion("module_role_id in", values, "moduleRoleId");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdNotIn(List<Integer> values) {
            addCriterion("module_role_id not in", values, "moduleRoleId");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("module_role_id between", value1, value2, "moduleRoleId");
            return (Criteria) this;
        }

        public Criteria andModuleRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("module_role_id not between", value1, value2, "moduleRoleId");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameIsNull() {
            addCriterion("module_role_name is null");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameIsNotNull() {
            addCriterion("module_role_name is not null");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameEqualTo(String value) {
            addCriterion("module_role_name =", value, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameNotEqualTo(String value) {
            addCriterion("module_role_name <>", value, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameGreaterThan(String value) {
            addCriterion("module_role_name >", value, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("module_role_name >=", value, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameLessThan(String value) {
            addCriterion("module_role_name <", value, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameLessThanOrEqualTo(String value) {
            addCriterion("module_role_name <=", value, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameLike(String value) {
            addCriterion("module_role_name like", value, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameNotLike(String value) {
            addCriterion("module_role_name not like", value, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameIn(List<String> values) {
            addCriterion("module_role_name in", values, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameNotIn(List<String> values) {
            addCriterion("module_role_name not in", values, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameBetween(String value1, String value2) {
            addCriterion("module_role_name between", value1, value2, "moduleRoleName");
            return (Criteria) this;
        }

        public Criteria andModuleRoleNameNotBetween(String value1, String value2) {
            addCriterion("module_role_name not between", value1, value2, "moduleRoleName");
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