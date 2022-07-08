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

package com.edmunds.rest.databricks.fixtures;

import com.edmunds.rest.databricks.DatabricksServiceFactory;
import com.edmunds.rest.databricks.DatabricksServiceFactory.Builder;
import com.edmunds.rest.databricks.restclient.DatabricksRestClient;
import com.edmunds.rest.databricks.restclient.DatabricksRestClientImpl;
import com.edmunds.rest.databricks.restclient.DefaultHttpClientBuilderFactory;
import com.edmunds.rest.databricks.restclient.HttpClientBuilderFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

/**
 * Created by shong on 7/21/16.
 */
public class DatabricksFixtures {
  private final static String API_VERSION = "2.0";
  public static String HOSTNAME;
  public static String TOKEN;
  public static String USERNAME;
  public static String PASSWORD;
  private static DatabricksRestClient client;
  private static DatabricksRestClient tokenAuthClient;
  private static DatabricksRestClient db425Client;
  private static DatabricksServiceFactory factory;

  static {
    HOSTNAME = System.getenv("DB_URL");
    TOKEN = System.getenv("DB_TOKEN");
    USERNAME = System.getenv("DB_USERNAME");
    PASSWORD = System.getenv("DB_PASSWORD");
//    ACCOUNT_ID = System.getenv("DB_ACCOUNT_ID");
  }

  public static DatabricksRestClient getDatabricksRestClient() throws IOException {
    if (client == null) {
      DatabricksServiceFactory.Builder builder = DatabricksServiceFactory.Builder
              .createTokenAuthentication(TOKEN, HOSTNAME)
              .withMaxRetries(1)
              .withRetryInterval(10)
              .withApiVersion(API_VERSION);
      HttpClientBuilderFactory factory = new DefaultHttpClientBuilderFactory(builder);
      client = new DatabricksRestClientImpl(builder, factory);
    }

    return client;
  }

  public static DatabricksRestClient createTokenAuthRestClient() {
    if (tokenAuthClient == null) {
      DatabricksServiceFactory.Builder builder = DatabricksServiceFactory.Builder
              .createTokenAuthentication(TOKEN, HOSTNAME)
              .withMaxRetries(1)
              .withRetryInterval(10)
              .withApiVersion(API_VERSION);
      HttpClientBuilderFactory factory = new DefaultHttpClientBuilderFactory(builder);
      tokenAuthClient = new DatabricksRestClientImpl(builder, factory);
    }

    return tokenAuthClient;
  }

  /**
   * This will create a builder
   *
   * @return
   * @throws Exception
   */
  public static DatabricksServiceFactory.Builder createDatabricksServiceBuilder() {

    DatabricksServiceFactory.Builder builder = DatabricksServiceFactory.Builder
            .createTokenAuthentication(TOKEN, HOSTNAME)
            .withMaxRetries(1)
            .withRetryInterval(10)
            .withApiVersion(API_VERSION);
    return builder;
  }

  public static DatabricksServiceFactory getDatabricksServiceFactory() {
    if (factory == null) {
      factory = DatabricksServiceFactory.Builder.createTokenAuthentication(TOKEN, HOSTNAME)
              .withMaxRetries(3)
              .withSoTimeout((int)Duration.ofMinutes(1L).toMillis())
              .withConnectionRequestTimeout((int)Duration.ofMinutes(1L).toMillis())
              .withRetryInterval((int)Duration.ofSeconds(5L).toMillis())
              .build();
    }

    return factory;
  }

  public static DatabricksServiceFactory getDatabricksUserPasswordClientFactory() {
    if (factory == null) {
      Builder builder = DatabricksServiceFactory.Builder
          .createUserPasswordAuthentication(USERNAME, PASSWORD, HOSTNAME)
          .withMaxRetries(3)
          .withSoTimeout((int)Duration.ofMinutes(1L).toMillis())
          .withConnectionRequestTimeout((int)Duration.ofMinutes(1L).toMillis())
          .withRetryInterval((int)Duration.ofSeconds(5L).toMillis());
      factory = builder.build(new DefaultHttpClientBuilderFactory(builder) {
            @Override
            public HttpClientBuilder createClientBuilder() {
              HttpClientBuilder defaultBuilder = createDefaultClientBuilder();

              String userPassword = USERNAME + ":" + PASSWORD;
              String auth =  Base64.getEncoder().encodeToString(userPassword.getBytes(StandardCharsets.UTF_8));
              String basicAuth = "Basic " + auth;
              defaultBuilder.setDefaultHeaders(Arrays.asList(new BasicHeader("Authorization", basicAuth)));
              return defaultBuilder;
            }
          });
    }
    return factory;
  }

}
