package org.cboard.elasticsearch.query;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cboard.util.json.JSONBuilder;

import java.util.List;


/**
 * Created by Peter on 2017/5/7.
 */
public class QueryBuilder extends JSONBuilder {

    public static QueryBuilder queryContent() {
        return new QueryBuilder();
    }

    @Override
    public QueryBuilder put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public QueryBuilder putJSONObject(JSONObject jsonObject) {
        super.putAll(jsonObject);
        return this;
    }

    @Override
    public QueryBuilder getJSONObject(String key) {
        return queryContent().putJSONObject(super.getJSONObject(key));
    }

    public static QueryBuilder termQuery(String fieldName, Object value) {
        return queryContent().put("term", json(fieldName, value));
    }

    public static QueryBuilder termsQuery(String fieldName, List<? extends Object> values) {
        return queryContent().put("terms", json(fieldName, values));
    }

    public static QueryBuilder rangeQuery(String fieldName, Object from, Object to, boolean includeLower, boolean includeUpper) {
        JSONBuilder content = json();
        if (from != null) {
            content.put(includeLower ? "gte" : "gt", from);
        }
        if (to != null) {
            content.put(includeUpper ? "lte" : "lt", to);
        }
        return queryContent().put("range", json(fieldName, content));
    }

    public static QueryBuilder rangeQuery(String fieldName, Object from, Object to) {
        return rangeQuery(fieldName, from, to, false, false);
    }

    public static QueryBuilder boolFilter(BoolType boolType, JSONArray filterArray) {
        return queryContent()
                .put("bool",
                        json(boolType.toString(), filterArray)
                );
    }

    public static enum BoolType {
        MUST, MUST_NOT, FILTER, SHOULD;

        public String toString() {
            String result = "should";
            switch (this) {
                case MUST:
                    result = "must";
                    break;
                case MUST_NOT:
                    result = "must_not";
                    break;
                case SHOULD:
                    result = "should";
                    break;
                case FILTER:
                    result = "filter";
                    break;
            }
            return result;
        }
    }
}