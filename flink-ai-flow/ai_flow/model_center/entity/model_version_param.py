#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#
from ai_flow.model_center.entity._model_repo_entity import _ModelRepoEntity
from ai_flow.rest_endpoint.protobuf.message_pb2 import ModelVersionStage


class ModelVersionParam(_ModelRepoEntity):
    """
    AIFlow entity for Model Version Parameter.
    """

    def __init__(self, model_path, model_metric, model_flavor, version_desc, current_stage):
        self._model_path = model_path
        self._model_metric = model_metric
        self._model_flavor = model_flavor
        self._version_desc = version_desc
        self._current_stage = current_stage

    @property
    def model_path(self):
        """String. Model source path for the model."""
        return self._model_path

    @property
    def model_metric(self):
        """String. Model metric address for the model."""
        return self._model_metric

    @property
    def model_flavor(self):
        """String. Model flavor feature for the model."""
        return self._model_flavor

    @property
    def version_desc(self):
        """String. Description of the model version."""
        return self._version_desc

    @property
    def current_stage(self):
        """String. Stage of the model version"""
        return self._current_stage

    # proto mappers
    @classmethod
    def from_proto(cls, proto):
        model_version = proto.model_version
        return cls(model_version.model_path.value if model_version.HasField("model_path") else None,
                   model_version.model_metric.value if model_version.HasField("model_metric") else None,
                   model_version.model_flavor.value if model_version.HasField("model_flavor") else None,
                   model_version.version_desc.value if model_version.HasField("version_desc") else None,
                   ModelVersionStage.Name(model_version.current_stage))
