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

import java.util.List;

import com.obidea.semantika.database.sql.base.ISqlExpression;
import com.obidea.semantika.database.sql.base.ISqlExpressionVisitor;
import com.obidea.semantika.datatype.DataType;
import com.obidea.semantika.mapping.base.TermType;

/**
 * @author Josef Hardi <josef.hardi@gmail.com>
 * @deprecated since 1.8. Use {@link SqlIriConcat} instead.
 */
@Deprecated
public class SqlUriConcat extends SqlFunction
{
   private static final long serialVersionUID = 629451L;

   public SqlUriConcat(List<ISqlExpression> expressions)
   {
      /*
       * As a logical term, this function has a datatype xsd:anyURI. However,
       * as a mapping term, this function has no datatype since it will
       * construct an object identifier.
       */
      super("URICONCAT", DataType.ANY_URI, expressions); //$NON-NLS-1$
   }

   public SqlUriConcat(ISqlExpression... expressions)
   {
      super("URICONCAT", DataType.ANY_URI, expressions); //$NON-NLS-1$
   }

   @Override
   public int getTermType()
   {
      return TermType.URI_TYPE; // This function constructs an object identifier
   }

   @Override
   public String getStringExpression()
   {
      return "||"; //$NON-NLS-1$
   }

   @Override
   public void accept(ISqlExpressionVisitor visitor)
   {
      visitor.visit(this);
   }
}
