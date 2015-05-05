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
package com.obidea.semantika.app;

import java.util.Properties;

import com.obidea.semantika.util.StringUtils;

public class SystemProperties extends Properties
{
   private static final long serialVersionUID = 629451L;

   public int getTransactionTimeout()
   {
      return Integer.parseInt(getProperty(Environment.TRANSACTION_TIMEOUT));
   }

   public int getTransactionFetchSize()
   {
      return Integer.parseInt(getProperty(Environment.TRANSACTION_FETCH_SIZE));
   }

   public int getTransactionMaxRows()
   {
      return Integer.parseInt(getProperty(Environment.TRANSACTION_MAX_ROWS));
   }

   public String getApplicationName()
   {
      String name = getProperty(Environment.APPLICATION_FACTORY_NAME);
      if (StringUtils.isEmpty(name)) {
         return "semantika"; //$NON-NLS-1$
      }
      return name;
   }
}
