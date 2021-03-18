package com.van.SmartHome.mongodb;

import com.google.common.base.Strings;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;
import java.util.regex.Pattern;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 *
 *
 */
public class MongoQueryBuilder {
    protected Query query;

    public MongoQueryBuilder() {
        this.query = new Query();
    }

    public void is(String key, String value) {
        if (!Strings.isNullOrEmpty(value)) {
            query.addCriteria(where(key).is(value));
        }
    }

    public void is(String key, Object value) {
        if (value != null) {
            query.addCriteria(where(key).is(value));
        }
    }

    public void regex(String key, String re) {
        if (!Strings.isNullOrEmpty(re)) {
            query.addCriteria(Criteria.where(key).regex(Pattern.quote(re),"i"));
        }
    }

    public void in(String key, Collection<?> list) {
        if (list != null) {
            query.addCriteria(where(key).in(list));
        }
    }

    public void between(String key, Integer from, Integer to) {
        if (from != null || to != null) {
            Criteria criteria = where(key);
            if (from != null) {
                criteria.gte(from);
            }
            if (to != null) {
                criteria.lte(to);
            }
            query.addCriteria(criteria);
        }
    }

    public void gte(String key, Object value) {
        if (value != null ) {
            Criteria criteria = where(key);
            criteria.gte(value);
            query.addCriteria(criteria);
        }
    }

    public Document getQueryObject() {
        return query.getQueryObject();
    }


}
