package com.zzd.api.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TMediaWorkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TMediaWorkExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMediaNameIsNull() {
            addCriterion("media_name is null");
            return (Criteria) this;
        }

        public Criteria andMediaNameIsNotNull() {
            addCriterion("media_name is not null");
            return (Criteria) this;
        }

        public Criteria andMediaNameEqualTo(String value) {
            addCriterion("media_name =", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameNotEqualTo(String value) {
            addCriterion("media_name <>", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameGreaterThan(String value) {
            addCriterion("media_name >", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameGreaterThanOrEqualTo(String value) {
            addCriterion("media_name >=", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameLessThan(String value) {
            addCriterion("media_name <", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameLessThanOrEqualTo(String value) {
            addCriterion("media_name <=", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameLike(String value) {
            addCriterion("media_name like", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameNotLike(String value) {
            addCriterion("media_name not like", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameIn(List<String> values) {
            addCriterion("media_name in", values, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameNotIn(List<String> values) {
            addCriterion("media_name not in", values, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameBetween(String value1, String value2) {
            addCriterion("media_name between", value1, value2, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameNotBetween(String value1, String value2) {
            addCriterion("media_name not between", value1, value2, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkIsNull() {
            addCriterion("media_remark is null");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkIsNotNull() {
            addCriterion("media_remark is not null");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkEqualTo(String value) {
            addCriterion("media_remark =", value, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkNotEqualTo(String value) {
            addCriterion("media_remark <>", value, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkGreaterThan(String value) {
            addCriterion("media_remark >", value, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("media_remark >=", value, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkLessThan(String value) {
            addCriterion("media_remark <", value, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkLessThanOrEqualTo(String value) {
            addCriterion("media_remark <=", value, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkLike(String value) {
            addCriterion("media_remark like", value, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkNotLike(String value) {
            addCriterion("media_remark not like", value, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkIn(List<String> values) {
            addCriterion("media_remark in", values, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkNotIn(List<String> values) {
            addCriterion("media_remark not in", values, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkBetween(String value1, String value2) {
            addCriterion("media_remark between", value1, value2, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaRemarkNotBetween(String value1, String value2) {
            addCriterion("media_remark not between", value1, value2, "mediaRemark");
            return (Criteria) this;
        }

        public Criteria andMediaCoverIsNull() {
            addCriterion("media_cover is null");
            return (Criteria) this;
        }

        public Criteria andMediaCoverIsNotNull() {
            addCriterion("media_cover is not null");
            return (Criteria) this;
        }

        public Criteria andMediaCoverEqualTo(String value) {
            addCriterion("media_cover =", value, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverNotEqualTo(String value) {
            addCriterion("media_cover <>", value, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverGreaterThan(String value) {
            addCriterion("media_cover >", value, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverGreaterThanOrEqualTo(String value) {
            addCriterion("media_cover >=", value, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverLessThan(String value) {
            addCriterion("media_cover <", value, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverLessThanOrEqualTo(String value) {
            addCriterion("media_cover <=", value, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverLike(String value) {
            addCriterion("media_cover like", value, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverNotLike(String value) {
            addCriterion("media_cover not like", value, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverIn(List<String> values) {
            addCriterion("media_cover in", values, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverNotIn(List<String> values) {
            addCriterion("media_cover not in", values, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverBetween(String value1, String value2) {
            addCriterion("media_cover between", value1, value2, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaCoverNotBetween(String value1, String value2) {
            addCriterion("media_cover not between", value1, value2, "mediaCover");
            return (Criteria) this;
        }

        public Criteria andMediaUrlIsNull() {
            addCriterion("media_url is null");
            return (Criteria) this;
        }

        public Criteria andMediaUrlIsNotNull() {
            addCriterion("media_url is not null");
            return (Criteria) this;
        }

        public Criteria andMediaUrlEqualTo(String value) {
            addCriterion("media_url =", value, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlNotEqualTo(String value) {
            addCriterion("media_url <>", value, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlGreaterThan(String value) {
            addCriterion("media_url >", value, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlGreaterThanOrEqualTo(String value) {
            addCriterion("media_url >=", value, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlLessThan(String value) {
            addCriterion("media_url <", value, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlLessThanOrEqualTo(String value) {
            addCriterion("media_url <=", value, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlLike(String value) {
            addCriterion("media_url like", value, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlNotLike(String value) {
            addCriterion("media_url not like", value, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlIn(List<String> values) {
            addCriterion("media_url in", values, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlNotIn(List<String> values) {
            addCriterion("media_url not in", values, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlBetween(String value1, String value2) {
            addCriterion("media_url between", value1, value2, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaUrlNotBetween(String value1, String value2) {
            addCriterion("media_url not between", value1, value2, "mediaUrl");
            return (Criteria) this;
        }

        public Criteria andMediaTypeIsNull() {
            addCriterion("media_type is null");
            return (Criteria) this;
        }

        public Criteria andMediaTypeIsNotNull() {
            addCriterion("media_type is not null");
            return (Criteria) this;
        }

        public Criteria andMediaTypeEqualTo(String value) {
            addCriterion("media_type =", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotEqualTo(String value) {
            addCriterion("media_type <>", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeGreaterThan(String value) {
            addCriterion("media_type >", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeGreaterThanOrEqualTo(String value) {
            addCriterion("media_type >=", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeLessThan(String value) {
            addCriterion("media_type <", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeLessThanOrEqualTo(String value) {
            addCriterion("media_type <=", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeLike(String value) {
            addCriterion("media_type like", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotLike(String value) {
            addCriterion("media_type not like", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeIn(List<String> values) {
            addCriterion("media_type in", values, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotIn(List<String> values) {
            addCriterion("media_type not in", values, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeBetween(String value1, String value2) {
            addCriterion("media_type between", value1, value2, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotBetween(String value1, String value2) {
            addCriterion("media_type not between", value1, value2, "mediaType");
            return (Criteria) this;
        }

        public Criteria andPopularNumIsNull() {
            addCriterion("popular_num is null");
            return (Criteria) this;
        }

        public Criteria andPopularNumIsNotNull() {
            addCriterion("popular_num is not null");
            return (Criteria) this;
        }

        public Criteria andPopularNumEqualTo(Integer value) {
            addCriterion("popular_num =", value, "popularNum");
            return (Criteria) this;
        }

        public Criteria andPopularNumNotEqualTo(Integer value) {
            addCriterion("popular_num <>", value, "popularNum");
            return (Criteria) this;
        }

        public Criteria andPopularNumGreaterThan(Integer value) {
            addCriterion("popular_num >", value, "popularNum");
            return (Criteria) this;
        }

        public Criteria andPopularNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("popular_num >=", value, "popularNum");
            return (Criteria) this;
        }

        public Criteria andPopularNumLessThan(Integer value) {
            addCriterion("popular_num <", value, "popularNum");
            return (Criteria) this;
        }

        public Criteria andPopularNumLessThanOrEqualTo(Integer value) {
            addCriterion("popular_num <=", value, "popularNum");
            return (Criteria) this;
        }

        public Criteria andPopularNumIn(List<Integer> values) {
            addCriterion("popular_num in", values, "popularNum");
            return (Criteria) this;
        }

        public Criteria andPopularNumNotIn(List<Integer> values) {
            addCriterion("popular_num not in", values, "popularNum");
            return (Criteria) this;
        }

        public Criteria andPopularNumBetween(Integer value1, Integer value2) {
            addCriterion("popular_num between", value1, value2, "popularNum");
            return (Criteria) this;
        }

        public Criteria andPopularNumNotBetween(Integer value1, Integer value2) {
            addCriterion("popular_num not between", value1, value2, "popularNum");
            return (Criteria) this;
        }

        public Criteria andRecommendNumIsNull() {
            addCriterion("recommend_num is null");
            return (Criteria) this;
        }

        public Criteria andRecommendNumIsNotNull() {
            addCriterion("recommend_num is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendNumEqualTo(Integer value) {
            addCriterion("recommend_num =", value, "recommendNum");
            return (Criteria) this;
        }

        public Criteria andRecommendNumNotEqualTo(Integer value) {
            addCriterion("recommend_num <>", value, "recommendNum");
            return (Criteria) this;
        }

        public Criteria andRecommendNumGreaterThan(Integer value) {
            addCriterion("recommend_num >", value, "recommendNum");
            return (Criteria) this;
        }

        public Criteria andRecommendNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommend_num >=", value, "recommendNum");
            return (Criteria) this;
        }

        public Criteria andRecommendNumLessThan(Integer value) {
            addCriterion("recommend_num <", value, "recommendNum");
            return (Criteria) this;
        }

        public Criteria andRecommendNumLessThanOrEqualTo(Integer value) {
            addCriterion("recommend_num <=", value, "recommendNum");
            return (Criteria) this;
        }

        public Criteria andRecommendNumIn(List<Integer> values) {
            addCriterion("recommend_num in", values, "recommendNum");
            return (Criteria) this;
        }

        public Criteria andRecommendNumNotIn(List<Integer> values) {
            addCriterion("recommend_num not in", values, "recommendNum");
            return (Criteria) this;
        }

        public Criteria andRecommendNumBetween(Integer value1, Integer value2) {
            addCriterion("recommend_num between", value1, value2, "recommendNum");
            return (Criteria) this;
        }

        public Criteria andRecommendNumNotBetween(Integer value1, Integer value2) {
            addCriterion("recommend_num not between", value1, value2, "recommendNum");
            return (Criteria) this;
        }

        public Criteria andDiscussNumIsNull() {
            addCriterion("discuss_num is null");
            return (Criteria) this;
        }

        public Criteria andDiscussNumIsNotNull() {
            addCriterion("discuss_num is not null");
            return (Criteria) this;
        }

        public Criteria andDiscussNumEqualTo(Integer value) {
            addCriterion("discuss_num =", value, "discussNum");
            return (Criteria) this;
        }

        public Criteria andDiscussNumNotEqualTo(Integer value) {
            addCriterion("discuss_num <>", value, "discussNum");
            return (Criteria) this;
        }

        public Criteria andDiscussNumGreaterThan(Integer value) {
            addCriterion("discuss_num >", value, "discussNum");
            return (Criteria) this;
        }

        public Criteria andDiscussNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("discuss_num >=", value, "discussNum");
            return (Criteria) this;
        }

        public Criteria andDiscussNumLessThan(Integer value) {
            addCriterion("discuss_num <", value, "discussNum");
            return (Criteria) this;
        }

        public Criteria andDiscussNumLessThanOrEqualTo(Integer value) {
            addCriterion("discuss_num <=", value, "discussNum");
            return (Criteria) this;
        }

        public Criteria andDiscussNumIn(List<Integer> values) {
            addCriterion("discuss_num in", values, "discussNum");
            return (Criteria) this;
        }

        public Criteria andDiscussNumNotIn(List<Integer> values) {
            addCriterion("discuss_num not in", values, "discussNum");
            return (Criteria) this;
        }

        public Criteria andDiscussNumBetween(Integer value1, Integer value2) {
            addCriterion("discuss_num between", value1, value2, "discussNum");
            return (Criteria) this;
        }

        public Criteria andDiscussNumNotBetween(Integer value1, Integer value2) {
            addCriterion("discuss_num not between", value1, value2, "discussNum");
            return (Criteria) this;
        }

        public Criteria andMediaGradeIsNull() {
            addCriterion("media_grade is null");
            return (Criteria) this;
        }

        public Criteria andMediaGradeIsNotNull() {
            addCriterion("media_grade is not null");
            return (Criteria) this;
        }

        public Criteria andMediaGradeEqualTo(Double value) {
            addCriterion("media_grade =", value, "mediaGrade");
            return (Criteria) this;
        }

        public Criteria andMediaGradeNotEqualTo(Double value) {
            addCriterion("media_grade <>", value, "mediaGrade");
            return (Criteria) this;
        }

        public Criteria andMediaGradeGreaterThan(Double value) {
            addCriterion("media_grade >", value, "mediaGrade");
            return (Criteria) this;
        }

        public Criteria andMediaGradeGreaterThanOrEqualTo(Double value) {
            addCriterion("media_grade >=", value, "mediaGrade");
            return (Criteria) this;
        }

        public Criteria andMediaGradeLessThan(Double value) {
            addCriterion("media_grade <", value, "mediaGrade");
            return (Criteria) this;
        }

        public Criteria andMediaGradeLessThanOrEqualTo(Double value) {
            addCriterion("media_grade <=", value, "mediaGrade");
            return (Criteria) this;
        }

        public Criteria andMediaGradeIn(List<Double> values) {
            addCriterion("media_grade in", values, "mediaGrade");
            return (Criteria) this;
        }

        public Criteria andMediaGradeNotIn(List<Double> values) {
            addCriterion("media_grade not in", values, "mediaGrade");
            return (Criteria) this;
        }

        public Criteria andMediaGradeBetween(Double value1, Double value2) {
            addCriterion("media_grade between", value1, value2, "mediaGrade");
            return (Criteria) this;
        }

        public Criteria andMediaGradeNotBetween(Double value1, Double value2) {
            addCriterion("media_grade not between", value1, value2, "mediaGrade");
            return (Criteria) this;
        }

        public Criteria andUploadUserIsNull() {
            addCriterion("upload_user is null");
            return (Criteria) this;
        }

        public Criteria andUploadUserIsNotNull() {
            addCriterion("upload_user is not null");
            return (Criteria) this;
        }

        public Criteria andUploadUserEqualTo(String value) {
            addCriterion("upload_user =", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotEqualTo(String value) {
            addCriterion("upload_user <>", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserGreaterThan(String value) {
            addCriterion("upload_user >", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserGreaterThanOrEqualTo(String value) {
            addCriterion("upload_user >=", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLessThan(String value) {
            addCriterion("upload_user <", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLessThanOrEqualTo(String value) {
            addCriterion("upload_user <=", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLike(String value) {
            addCriterion("upload_user like", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotLike(String value) {
            addCriterion("upload_user not like", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserIn(List<String> values) {
            addCriterion("upload_user in", values, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotIn(List<String> values) {
            addCriterion("upload_user not in", values, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserBetween(String value1, String value2) {
            addCriterion("upload_user between", value1, value2, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotBetween(String value1, String value2) {
            addCriterion("upload_user not between", value1, value2, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserIsNull() {
            addCriterion("approval_user is null");
            return (Criteria) this;
        }

        public Criteria andApprovalUserIsNotNull() {
            addCriterion("approval_user is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalUserEqualTo(String value) {
            addCriterion("approval_user =", value, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNotEqualTo(String value) {
            addCriterion("approval_user <>", value, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserGreaterThan(String value) {
            addCriterion("approval_user >", value, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserGreaterThanOrEqualTo(String value) {
            addCriterion("approval_user >=", value, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserLessThan(String value) {
            addCriterion("approval_user <", value, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserLessThanOrEqualTo(String value) {
            addCriterion("approval_user <=", value, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserLike(String value) {
            addCriterion("approval_user like", value, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNotLike(String value) {
            addCriterion("approval_user not like", value, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserIn(List<String> values) {
            addCriterion("approval_user in", values, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNotIn(List<String> values) {
            addCriterion("approval_user not in", values, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserBetween(String value1, String value2) {
            addCriterion("approval_user between", value1, value2, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNotBetween(String value1, String value2) {
            addCriterion("approval_user not between", value1, value2, "approvalUser");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkIsNull() {
            addCriterion("approval_remark is null");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkIsNotNull() {
            addCriterion("approval_remark is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkEqualTo(String value) {
            addCriterion("approval_remark =", value, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkNotEqualTo(String value) {
            addCriterion("approval_remark <>", value, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkGreaterThan(String value) {
            addCriterion("approval_remark >", value, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("approval_remark >=", value, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkLessThan(String value) {
            addCriterion("approval_remark <", value, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkLessThanOrEqualTo(String value) {
            addCriterion("approval_remark <=", value, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkLike(String value) {
            addCriterion("approval_remark like", value, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkNotLike(String value) {
            addCriterion("approval_remark not like", value, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkIn(List<String> values) {
            addCriterion("approval_remark in", values, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkNotIn(List<String> values) {
            addCriterion("approval_remark not in", values, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkBetween(String value1, String value2) {
            addCriterion("approval_remark between", value1, value2, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andApprovalRemarkNotBetween(String value1, String value2) {
            addCriterion("approval_remark not between", value1, value2, "approvalRemark");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andCreateDatetimeIsNull() {
            addCriterion("create_datetime is null");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeIsNotNull() {
            addCriterion("create_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeEqualTo(Date value) {
            addCriterion("create_datetime =", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeNotEqualTo(Date value) {
            addCriterion("create_datetime <>", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeGreaterThan(Date value) {
            addCriterion("create_datetime >", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_datetime >=", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeLessThan(Date value) {
            addCriterion("create_datetime <", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("create_datetime <=", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeIn(List<Date> values) {
            addCriterion("create_datetime in", values, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeNotIn(List<Date> values) {
            addCriterion("create_datetime not in", values, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeBetween(Date value1, Date value2) {
            addCriterion("create_datetime between", value1, value2, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("create_datetime not between", value1, value2, "createDatetime");
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

        public Criteria andUpdateDatetimeIsNull() {
            addCriterion("update_datetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeIsNotNull() {
            addCriterion("update_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeEqualTo(Date value) {
            addCriterion("update_datetime =", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeNotEqualTo(Date value) {
            addCriterion("update_datetime <>", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeGreaterThan(Date value) {
            addCriterion("update_datetime >", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_datetime >=", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeLessThan(Date value) {
            addCriterion("update_datetime <", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("update_datetime <=", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeIn(List<Date> values) {
            addCriterion("update_datetime in", values, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeNotIn(List<Date> values) {
            addCriterion("update_datetime not in", values, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeBetween(Date value1, Date value2) {
            addCriterion("update_datetime between", value1, value2, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("update_datetime not between", value1, value2, "updateDatetime");
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
