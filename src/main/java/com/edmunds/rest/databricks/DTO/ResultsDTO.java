/*
 * Copyright 2018 Edmunds.com, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.edmunds.rest.databricks.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 */
@SuppressWarnings("PMD")
public class ResultsDTO implements Serializable {

  @JsonProperty("resultType")
  private String resultType;
  @JsonProperty("data")
  private Object data;
  @JsonProperty("schema")
  private Object schema;
  @JsonProperty("truncated")
  private boolean truncated;
  @JsonProperty("isJsonSchema")
  private boolean isJsonSchema;
  @JsonProperty("summary")
  private String summary;
  @JsonProperty("cause")
  private String cause;

  public String getResultType() {
    return resultType;
  }

  public void setResultType(String resultType) {
    this.resultType = resultType;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Object getSchema() {
    return schema;
  }

  public void setSchema(Object schema) {
    this.schema = schema;
  }

  public boolean getTruncated() {
    return truncated;
  }

  public void setTruncated(boolean truncated) {
    this.truncated = truncated;
  }

  public boolean getIsJsonSchema() {
    return isJsonSchema;
  }

  public void setIsJsonSchema(boolean isJsonSchema) {
    this.isJsonSchema = isJsonSchema;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }
}
