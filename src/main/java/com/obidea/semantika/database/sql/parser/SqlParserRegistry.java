/*
 * Copyright (c) 2013-2015 Obidea Technology
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.obidea.semantika.database.sql.parser;

import java.util.HashMap;
import java.util.Map;

public class SqlParserRegistry
{
   private Map<String, ISqlParser> mParserMap = new HashMap<String, ISqlParser>();

   private static SqlParserRegistry sRegistry = null;

   public static SqlParserRegistry getInstance()
   {
      if (sRegistry == null) {
         sRegistry = new SqlParserRegistry();
      }
      return sRegistry;
   }

   public void register(ISqlParser parser)
   {
      mParserMap.put(parser.getName(), parser);
   }

   public ISqlParser lookup(String parserName)
   {
      return mParserMap.get(parserName);
   }

   /*
    * Prevent instantiation
    */
   private SqlParserRegistry()
   {
      // NO-OP
   }
}
