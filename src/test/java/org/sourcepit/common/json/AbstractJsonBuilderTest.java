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

import static java.lang.Character.isWhitespace;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;
import org.sourcepit.common.json.JsonBuilder;

public abstract class AbstractJsonBuilderTest<JsonObject, JsonArray extends Iterable<?>> {
   protected abstract JsonBuilder<JsonObject, JsonArray> newJsonBuilder();

   protected abstract String arrayToString(JsonArray array);

   protected abstract String objectToString(JsonObject object);

   @Test
   public void testEmptyObject() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject().endObject();
      assertEquals("{}", normalize(objectToString(object)));
   }

   @Test
   public void testNullField() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject().setField("foo").toNull().endObject();
      assertEquals("{'foo':null}", normalize(objectToString(object)));
   }

   @Test
   public void testBigIntegerField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("big")
         .to(BigInteger.valueOf(42))
         .setField("null")
         .to((BigInteger) null)
         .endObject();
      assertEquals("{'big':42,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testBigDecimalField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("big")
         .to(BigDecimal.valueOf(42))
         .setField("null")
         .to((BigDecimal) null)
         .endObject();
      assertEquals("{'big':42,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testDoubleField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("big")
         .to(Double.valueOf(Double.MAX_VALUE))
         .setField("little")
         .to(Double.MAX_VALUE)
         .setField("null")
         .to((Double) null)
         .endObject();
      assertEquals("{'big':1.7976931348623157E308,'little':1.7976931348623157E308,'null':null}",
         normalize(objectToString(object)));
   }

   @Test
   public void testFloatField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("big")
         .to(Float.valueOf(Float.MAX_VALUE))
         .setField("little")
         .to(Float.MAX_VALUE)
         .setField("null")
         .to((Float) null)
         .endObject();
      assertEquals("{'big':3.4028235E38,'little':3.4028234663852886E38,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testIntegerField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("big")
         .to(Integer.valueOf(Integer.MAX_VALUE))
         .setField("little")
         .to(Integer.MAX_VALUE)
         .setField("null")
         .to((Integer) null)
         .endObject();
      assertEquals("{'big':2147483647,'little':2147483647,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testLongField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("big")
         .to(Long.valueOf(Long.MAX_VALUE))
         .setField("little")
         .to(Long.MAX_VALUE)
         .setField("null")
         .to((Long) null)
         .endObject();
      assertEquals("{'big':9223372036854775807,'little':9.223372036854776E18,'null':null}",
         normalize(objectToString(object)));
   }


   @Test
   public void testShortField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("big")
         .to(Short.valueOf(Short.MAX_VALUE))
         .setField("little")
         .to(Short.MAX_VALUE)
         .setField("null")
         .to((Short) null)
         .endObject();
      assertEquals("{'big':32767,'little':32767.0,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testBooleanField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("big")
         .to(Boolean.TRUE)
         .setField("little")
         .to(false)
         .setField("null")
         .to((Boolean) null)
         .endObject();
      assertEquals("{'big':true,'little':false,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testStringField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("big")
         .to("string")
         .setField("null")
         .to((String) null)
         .endObject();
      assertEquals("{'big':'string','null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testComplexField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("foo")
         .toObject()
         .setField("bar")
         .to(74)
         .endObject()
         .setField("bar")
         .to(jb.beginObject().setField("foo").to(47).endObject())
         .endObject();
      assertEquals("{'foo':{'bar':74},'bar':{'foo':47}}", normalize(objectToString(object)));
   }

   @Test
   public void testArrayField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .setField("foo")
         .toArray()
         .add(1)
         .add(2)
         .endArray()
         .setField("bar")
         .to(jb.beginArray().add(2).add(1).endArray())
         .endObject();
      assertEquals("{'foo':[1,2],'bar':[2,1]}", normalize(objectToString(object)));
   }

   @Test
   public void testEmptyArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray().endArray();
      assertEquals("[]", normalize(arrayToString(array)));
   }

   @Test
   public void testNullArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray().addNull().addNull().endArray();
      assertEquals("[null,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testStringArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray().add("foo").add((String) null).endArray();
      assertEquals("['foo',null]", normalize(arrayToString(array)));
   }

   @Test
   public void testBigDecimalArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray().add(BigDecimal.valueOf(1)).add((BigDecimal) null).endArray();
      assertEquals("[1,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testBigIntegerArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray().add(BigInteger.valueOf(1)).add((BigInteger) null).endArray();
      assertEquals("[1,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testDoubleArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray()
         .add(Double.valueOf(Double.MAX_VALUE))
         .add(Double.MAX_VALUE)
         .add((Double) null)
         .endArray();
      assertEquals("[1.7976931348623157E308,1.7976931348623157E308,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testFloatArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray()
         .add(Float.valueOf(Float.MAX_VALUE))
         .add(Float.MAX_VALUE)
         .add((Float) null)
         .endArray();
      assertEquals("[3.4028235E38,3.4028235E38,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testLongArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray()
         .add(Long.valueOf(Long.MAX_VALUE))
         .add(Long.MAX_VALUE)
         .add((Long) null)
         .endArray();
      assertEquals("[9223372036854775807,9223372036854775807,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testIntegerArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray()
         .add(Integer.valueOf(Integer.MAX_VALUE))
         .add(Integer.MAX_VALUE)
         .add((Integer) null)
         .endArray();
      assertEquals("[2147483647,2147483647,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testShortArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray()
         .add(Short.valueOf(Short.MAX_VALUE))
         .add(Short.MAX_VALUE)
         .add((Short) null)
         .endArray();
      assertEquals("[32767,32767,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testBooleanArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray().add(Boolean.TRUE).add(false).add((Boolean) null).endArray();
      assertEquals("[true,false,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testComplexArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray()
         .addObject()
         .setField("foo")
         .to(1)
         .endObject()
         .add(jb.beginObject().setField("bar").to(2).endObject())
         .endArray();
      assertEquals("[{'foo':1},{'bar':2}]", normalize(arrayToString(array)));
   }

   @Test
   public void testArrayArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray()
         .addArray()
         .add(1)
         .add(2)
         .endArray()
         .add(jb.beginArray().add(2).add(1).endArray())
         .endArray();
      assertEquals("[[1,2],[2,1]]", normalize(arrayToString(array)));
   }

   private static String normalize(String str) {
      final StringBuilder sb = new StringBuilder();
      for (char ch : str.toCharArray()) {
         if (!isWhitespace(ch)) {
            sb.append(ch == '\"' ? '\'' : ch);
         }
      }
      return sb.toString();
   }

}
