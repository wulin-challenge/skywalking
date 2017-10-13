package org.skywalking.apm.collector.agentstream.worker.segment.origin.dao;

import org.skywalking.apm.collector.agentstream.worker.segment.cost.dao.SegmentCostH2DAO;
import org.skywalking.apm.collector.core.stream.Data;
import org.skywalking.apm.collector.storage.define.DataDefine;
import org.skywalking.apm.collector.storage.define.segment.SegmentTable;
import org.skywalking.apm.collector.storage.h2.dao.H2DAO;
import org.skywalking.apm.collector.storage.h2.define.H2SqlEntity;
import org.skywalking.apm.collector.stream.worker.impl.dao.IPersistenceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengys5, clevertension
 */
public class SegmentH2DAO extends H2DAO implements ISegmentDAO, IPersistenceDAO<H2SqlEntity, H2SqlEntity> {
    private final Logger logger = LoggerFactory.getLogger(SegmentCostH2DAO.class);
    @Override public Data get(String id, DataDefine dataDefine) {
        return null;
    }
    @Override public H2SqlEntity prepareBatchInsert(Data data) {
        Map<String, Object> source = new HashMap<>();
        H2SqlEntity entity = new H2SqlEntity();
        source.put("id", data.getDataString(0));
        source.put(SegmentTable.COLUMN_DATA_BINARY, Base64.getEncoder().encode(data.getDataBytes(0)));
        logger.debug("segment source: {}", source.toString());

        String sql = getBatchInsertSql(SegmentTable.TABLE, source.keySet());
        entity.setSql(sql);
        entity.setParams(source.values().toArray(new Object[0]));
        return entity;
    }
    @Override public H2SqlEntity prepareBatchUpdate(Data data) {
        return null;
    }
}
