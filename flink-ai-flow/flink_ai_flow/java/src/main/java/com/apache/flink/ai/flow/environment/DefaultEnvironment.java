/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.apache.flink.ai.flow.environment;

import com.apache.flink.ai.flow.FlinkEnvironment;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class DefaultEnvironment implements FlinkEnvironment {

    private ExecutionEnvironment executionEnvironment;
    private StreamExecutionEnvironment streamExecutionEnvironment;
    private TableEnvironment batchTableEnvironment;
    private TableEnvironment streamTableEnvironment;

    public DefaultEnvironment() {
        this.executionEnvironment = null;
        EnvironmentSettings bbSettings = EnvironmentSettings.newInstance().useBlinkPlanner()
                .inBatchMode().build();
        this.batchTableEnvironment = TableEnvironment.create(bbSettings);

        EnvironmentSettings bsSettings = EnvironmentSettings.newInstance().useBlinkPlanner()
                .inStreamingMode().build();
        this.streamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        this.streamTableEnvironment = StreamTableEnvironment.create(streamExecutionEnvironment, bsSettings);
    }

    @Override
    public ExecutionEnvironment getExecutionEnvironment() {
        return executionEnvironment;
    }

    @Override
    public StreamExecutionEnvironment getStreamExecutionEnvironment() {
        return streamExecutionEnvironment;
    }

    @Override
    public TableEnvironment getBatchTableEnvironment() {
        return batchTableEnvironment;
    }

    @Override
    public TableEnvironment getStreamTableEnvironment() {
        return streamTableEnvironment;
    }
}
