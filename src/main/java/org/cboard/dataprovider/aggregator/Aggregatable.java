package org.cboard.dataprovider.aggregator;

import org.cboard.dataprovider.config.AggConfig;
import org.cboard.dataprovider.result.AggregateResult;

import java.util.Map;

/**
 * Created by yfyuan on 2017/1/13.
 */
public interface Aggregatable {

    /**
     * The data provider that support DataSource side Aggregation must implement this method.
     *
     * @param columnName
     * @return
     */
    String[] queryDimVals(String columnName, AggConfig config) throws Exception;

    /**
     * The data provider that support DataSource side Aggregation must implement this method.
     *
     * @return
     */
    String[] getColumn() throws Exception;

    /**
     * The data provider that support DataSource side Aggregation must implement this method.
     *
     * @param ac aggregate configuration
     * @return
     */
    AggregateResult queryAggData(AggConfig ac) throws Exception;

    default String viewAggDataQuery(AggConfig ac) throws Exception {
        return "Not Support";
    }
}
