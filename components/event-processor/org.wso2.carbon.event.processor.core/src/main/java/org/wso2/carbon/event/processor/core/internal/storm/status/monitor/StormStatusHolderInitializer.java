package org.wso2.carbon.event.processor.core.internal.storm.status.monitor;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.wso2.carbon.event.processor.core.internal.ds.EventProcessorValueHolder;
import org.wso2.carbon.event.processor.core.internal.storm.StormTopologyManager;
import org.wso2.carbon.event.processor.core.util.DistributedModeConstants;
import org.wso2.carbon.event.processor.core.util.ExecutionPlanStatusHolder;

/**
 * Utility to initialize the statusHolder.
 */
public class StormStatusHolderInitializer {

    public static void initializeStatusHolder(String executionPlanName, int tenantId, int parallel) {
        String stormTopologyName = StormTopologyManager.getTopologyName(executionPlanName, tenantId);

        HazelcastInstance hazelcastInstance = EventProcessorValueHolder.getHazelcastInstance();
        IMap<String, ExecutionPlanStatusHolder> executionPlanStatusHolderIMap = hazelcastInstance.getMap(DistributedModeConstants.STORM_STATUS_MAP);
        ExecutionPlanStatusHolder executionPlanStatusHolder = new ExecutionPlanStatusHolder(parallel);
        executionPlanStatusHolderIMap.put(stormTopologyName, executionPlanStatusHolder);
    }
}
