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

import java.math.BigDecimal;
import java.math.BigInteger;

public interface JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray extends Iterable<?>> {
   JsonPropertyBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, JsonObject value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, JsonArray value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setFieldToNull(String name);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, String value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, BigDecimal value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, BigInteger value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Double value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, double value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Float value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, float value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Long value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, long value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Integer value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, int value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Short value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, short value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Boolean value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, boolean value);


   ParentBuilder endObject();
}