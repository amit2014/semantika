/*
 * Copyright (c) 2013-2015 Josef Hardi <josef.hardi@gmail.com>
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
package com.obidea.semantika.queryanswer.result;

import java.util.ArrayList;
import java.util.List;

public class ListResultHandler implements IQueryResultHandler
{
   private List<Object[]> mObjectList = new ArrayList<Object[]>();

   @Override
   public void start(List<String> selectNames)
   {
      // NO-OP
   }

   @Override
   public void handleResultFragment(IValueArray valueArray)
   {
      mObjectList.add(row(valueArray, valueArray.size()));
   }

   private static Object[] row(IValueArray valueArray, int cols)
   {
      int i = 0;
      final Object[] rowResults = new Object[cols];
      for (IValue value : valueArray) {
         rowResults[i++] = value.getObject();
      }
      return rowResults;
   }

   @Override
   public void stop()
   {
      // NO-OP
   }

   public List<Object[]> getListResult()
   {
      return mObjectList;
   }
}
