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
package com.obidea.semantika.mapping.base;

import com.obidea.semantika.expression.base.ITerm;

/**
 * Represent the construction of a mapping language for defining class entities.
 */
public interface IClassMapping extends IMapping
{
   /**
    * Sets the term that is mapped to this class' subject.
    */
   void setSubjectMapValue(ITerm term);

   /**
    * Gets the term that is mapped to this class' subject.
    */
   public ITerm getSubjectMapValue();
}
