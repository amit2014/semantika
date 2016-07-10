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
package com.obidea.semantika.database.datatype;

public abstract class AbstractNumericType<T> extends AbstractSqlType<T>
{
   protected AbstractNumericType(String name)
   {
      super(name);
   }

   @Override
   public T getValue(String lexicalForm)
   {
      return parseLexicalForm(lexicalForm);
   }

   /**
    * Parse and validate a lexical form of the literal.
    * 
    * @param lexicalForm
    *           the typing form of the literal.
    * @return An object representation of the literal value.
    */
   protected abstract T parseLexicalForm(String lexicalForm);
}