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

public class GenericJsonBuilder<JsonObject, JsonArray extends Iterable<?>>
   implements
      JsonBuilder<JsonObject, JsonArray> {
   private abstract class AbstractBuilder<ParentBuilder> {
      // CSOFF
      final ParentBuilder parentBuilder; // CSON

      public AbstractBuilder(ParentBuilder parentBuilder) {
         this.parentBuilder = parentBuilder;
      }
   }

   private class JsonArrayBuilderImpl<ParentBuilder> extends AbstractBuilder<ParentBuilder>
      implements
         JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> {
      // CSOFF
      final JsonArray array; // CSON

      public JsonArrayBuilderImpl(ParentBuilder parentBuilder) {
         super(parentBuilder);
         array = delegate.createJsonArray();
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(JsonObject value) {
         delegate.addObject(array, value);
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(JsonArray value) {
         delegate.addArray(array, value);
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(String value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(BigDecimal value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(BigInteger value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Double value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(double value) {
         return add(Double.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Float value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(float value) {
         return add(Float.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Long value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(long value) {
         return add(Long.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Integer value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(int value) {
         return add(Integer.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Short value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(short value) {
         return add(Short.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Boolean value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(boolean value) {
         return add(Boolean.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> addNull() {
         delegate.addNull(array);
         return this;
      }

      @Override
      public JsonObjectBuilder<JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> addObject() {
         return new JsonObjectBuilderImpl<JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>>(this) {
            @Override
            public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> endObject() {
               delegate.addObject(array, object);
               return super.endObject();
            }
         };
      }

      @Override
      public JsonArrayBuilder<JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> addArray() {
         return new JsonArrayBuilderImpl<JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>>(this) {
            @Override
            public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> endArray() {
               delegate.addArray(JsonArrayBuilderImpl.this.array, array);
               return super.endArray();
            }
         };
      }

      @Override
      public ParentBuilder endArray() {
         return parentBuilder;
      }
   }

   private class JsonObjectBuilderImpl<ParentBuilder> extends AbstractBuilder<ParentBuilder>
      implements
         JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> {
      // CSOFF
      final JsonObject object; // CSON

      public JsonObjectBuilderImpl(ParentBuilder parentBuilder) {
         super(parentBuilder);
         object = delegate.createJsonObject();
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, String value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonPropertyBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name) {
         return new JsonPropertyBuilderImpl<ParentBuilder>(this, name);
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, JsonObject value) {
         delegate.setObject(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, JsonArray value) {
         delegate.setArray(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setFieldToNull(String name) {
         delegate.setNull(object, name);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, BigDecimal value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, BigInteger value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Double value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, double value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Float value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, float value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Long value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, long value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Integer value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, int value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Short value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, short value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, Boolean value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name, boolean value) {
         delegate.set(object, name, value);
         return this;
      }

      @Override
      public ParentBuilder endObject() {
         return parentBuilder;
      }
   }

   private class JsonPropertyBuilderImpl<ParentBuilder> extends AbstractBuilder<JsonObjectBuilderImpl<ParentBuilder>>
      implements
         JsonPropertyBuilder<ParentBuilder, JsonObject, JsonArray> {
      private final String name;

      public JsonPropertyBuilderImpl(JsonObjectBuilderImpl<ParentBuilder> parentBuilder, String name) {
         super(parentBuilder);
         this.name = name;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(String value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(BigDecimal value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(BigInteger value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Double value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(double value) {
         return to(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Float value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(float value) {
         return to(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Long value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(long value) {
         return to(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Short value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(short value) {
         return to(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Integer value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(int value) {
         return to(Integer.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Boolean value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(boolean value) {
         return to(Boolean.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> toNull() {
         delegate.setNull(parentBuilder.object, name);
         return parentBuilder;
      }

      @Override
      public JsonArrayBuilder<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> toArray() {
         return new JsonArrayBuilderImpl<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>>(parentBuilder) {
            @Override
            public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> endArray() {
               final JsonObject owner = JsonPropertyBuilderImpl.this.parentBuilder.object;
               delegate.setArray(owner, name, array);
               return super.endArray();
            }
         };
      }

      @Override
      public JsonObjectBuilder<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> toObject() {
         return new JsonObjectBuilderImpl<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>>(parentBuilder) {
            @Override
            public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> endObject() {
               final JsonObject owner = JsonPropertyBuilderImpl.this.parentBuilder.object;
               delegate.setObject(owner, name, object);
               return super.endObject();
            }
         };
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(JsonObject value) {
         delegate.setObject(parentBuilder.object, name, value);
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(JsonArray value) {
         delegate.setArray(parentBuilder.object, name, value);
         return parentBuilder;
      }
   }

   private final JsonBuilderDelegate<JsonObject, JsonArray> delegate;

   public GenericJsonBuilder(JsonBuilderDelegate<JsonObject, JsonArray> delegate) {
      this.delegate = delegate;
   }

   @Override
   public JsonObjectBuilder<JsonObject, JsonObject, JsonArray> beginObject() {
      return new JsonObjectBuilderImpl<JsonObject>(null) {
         @Override
         public JsonObject endObject() {
            return object;
         }
      };
   }

   @Override
   public JsonArrayBuilder<JsonArray, JsonObject, JsonArray> beginArray() {
      return new JsonArrayBuilderImpl<JsonArray>(null) {
         @Override
         public JsonArray endArray() {
            return array;
         }
      };
   }
}
