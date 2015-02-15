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

import org.bson.BasicBSONObject;
import org.bson.types.BasicBSONList;
import org.sourcepit.common.json.BSONBuilder;
import org.sourcepit.common.json.JsonBuilder;

import com.mongodb.util.JSONSerializers;

public class BSONBuilderTest extends AbstractJsonBuilderTest<BasicBSONObject, BasicBSONList> {
   @Override
   protected JsonBuilder<BasicBSONObject, BasicBSONList> newJsonBuilder() {
      return new BSONBuilder();
   }

   @Override
   protected String arrayToString(BasicBSONList array) {
      return JSONSerializers.getStrict().serialize(array);
   }

   @Override
   protected String objectToString(BasicBSONObject object) {
      return JSONSerializers.getStrict().serialize(object);
   }
}
