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

import org.sourcepit.common.json.GsonBuilder;
import org.sourcepit.common.json.JsonBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GsonBuilderTest extends AbstractJsonBuilderTest<JsonObject, JsonArray> {
   @Override
   protected JsonBuilder<JsonObject, JsonArray> newJsonBuilder() {
      return new GsonBuilder();
   }

   @Override
   protected String arrayToString(JsonArray array) {
      return array.toString();
   }

   @Override
   protected String objectToString(JsonObject object) {
      return object.toString();
   }
}
