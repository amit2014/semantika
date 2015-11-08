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
package com.obidea.semantika.mapping.parser.r2rml;

import com.obidea.semantika.exception.SemantikaRuntimeException;

public class R2RmlParserException extends SemantikaRuntimeException
{
   private static final long serialVersionUID = 629451L;

   public R2RmlParserException()
   {
      super();
   }

   public R2RmlParserException(String message)
   {
      super(message);
   }

   public R2RmlParserException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public R2RmlParserException(Throwable cause)
   {
      super(cause);
   }
}
