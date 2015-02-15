/*
 * Copyright 2015 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.common.json;

import org.sourcepit.common.json.JacksonBuilder;
import org.sourcepit.common.json.JsonBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JacksonBuilderTest extends AbstractJsonBuilderTest<ObjectNode, ArrayNode> {
   @Override
   protected JsonBuilder<ObjectNode, ArrayNode> newJsonBuilder() {
      return new JacksonBuilder();
   }

   @Override
   protected String arrayToString(ArrayNode array) {
      try {
         return new ObjectMapper().writeValueAsString(array);
      }
      catch (JsonProcessingException e) {
         throw new IllegalArgumentException(e);
      }
   }

   @Override
   protected String objectToString(ObjectNode object) {
      try {
         return new ObjectMapper().writeValueAsString(object);
      }
      catch (JsonProcessingException e) {
         throw new IllegalArgumentException(e);
      }
   }
}
