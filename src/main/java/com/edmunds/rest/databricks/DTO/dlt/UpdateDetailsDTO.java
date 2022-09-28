package com.edmunds.rest.databricks.DTO.dlt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateDetailsDTO {
    public static final String JSON_PROPERTY_PIPELINE_ID = "pipeline_id";
    public static final String JSON_PROPERTY_UPDATE_ID = "update_id";
    public static final String JSON_PROPERTY_CONFIG = "config";
    public static final String JSON_PROPERTY_CAUSE = "cause";
    public static final String JSON_PROPERTY_STATE = "state";
    public static final String JSON_PROPERTY_CLUSTER_ID = "cluster_id";
    public static final String JSON_PROPERTY_CREATION_TIME = "creation_time";
    public static final String JSON_PROPERTY_FULL_REFRESH = "full_refresh";

    @JsonProperty(JSON_PROPERTY_PIPELINE_ID)
    private String pipelineId;

    @JsonProperty(JSON_PROPERTY_UPDATE_ID)
    private String updateId;

    @JsonProperty(JSON_PROPERTY_CONFIG)
    private PipelineSpecDTO config;

    @JsonProperty(JSON_PROPERTY_CAUSE)
    private String cause;

    @JsonProperty(JSON_PROPERTY_STATE)
    private PipelineUpdateResultStateDTO state;

    @JsonProperty(JSON_PROPERTY_CLUSTER_ID)
    private String clusterId;

    @JsonProperty(JSON_PROPERTY_CREATION_TIME)
    private Long creationTime;

    @JsonProperty(JSON_PROPERTY_FULL_REFRESH)
    private boolean fullRefresh;
}