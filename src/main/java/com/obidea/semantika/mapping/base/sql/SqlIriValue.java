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
package com.obidea.semantika.mapping.base.sql;

import com.obidea.semantika.database.sql.base.ISqlExpressionVisitor;
import com.obidea.semantika.database.sql.base.ISqlValue;
import com.obidea.semantika.datatype.DataType;
import com.obidea.semantika.mapping.base.TermType;

/**
 * @author Josef Hardi <josef.hardi@gmail.com>
 * @since 1.8
 */
public class SqlIriValue extends ConstantTerm implements ISqlValue
{
   private static final long serialVersionUID = 629451L;

   public SqlIriValue(String value)
   {
      /*
       * As a logical term, this constant has a datatype xsd:anyURI. However,
       * as a mapping term, this constant has no datatype since it is treated
       * as an object identifier.
       */
      super(value, DataType.ANY_URI);
   }

   @Override
   public int getTermType()
   {
      return TermType.IRI_TYPE; // This function constructs an object identifier
   }

   @Override
   public String getValue()
   {
      return super.getLexicalValue();
   }

   @Override
   public String getDatatype()
   {
      return super.getDatatype();
   }

   @Override
   public void accept(ISqlExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
