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
package com.obidea.semantika.database.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ForeignKey extends DatabaseObject implements IForeignKey
{
   private static final long serialVersionUID = 629451L;

   private ITable mTableObject;

   private List<IColumnReference> mReferences;

   public ForeignKey(final ITable table, final String name)
   {
      super(name);
      mTableObject = table;
      mReferences = new ArrayList<IColumnReference>();
   }

   /**
    * Returns the associated table of this foreign key object.
    */
   @Override
   public ITable getParentObject()
   {
      return mTableObject;
   }

   public void addReference(int keySequence, IColumn pkColumn, IColumn fkColumn)
   {
      final ColumnReference cr = new ColumnReference();
      cr.setPrimaryKeyColumn(pkColumn);
      cr.setForeignKeyColumn(fkColumn);
      
      preventOutOfBoundException(keySequence);
      mReferences.set(keySequence, cr); // use set to replace any existing null value.
   }

   /*
    * The insertion through keySequence may go right to the middle of the
    * list and therefore IndexOutOfBoundException can occur because index >
    * size. This method is used to prevent such exception by inserting
    * first null values until reaching the keySequence index.
    */
   private void preventOutOfBoundException(int keySequence)
   {
      int size = mReferences.size();
      if (keySequence >= size) {
         for (int i = size; i <= keySequence; i++) {
            mReferences.add(i, null);
         }
      }
   }

   @Override
   public List<IColumnReference> getReferences()
   {
      return Collections.unmodifiableList(mReferences);
   }
}
