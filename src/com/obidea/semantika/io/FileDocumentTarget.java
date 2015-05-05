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
package com.obidea.semantika.io;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;

public class FileDocumentTarget implements IDocumentTarget
{
   private final File mFile;

   public FileDocumentTarget(File file)
   {
      this.mFile = file;
   }

   @Override
   public boolean isWriterAvailable()
   {
      return true;
   }

   @Override
   public Writer getWriter() throws IOException
   {
      return new BufferedWriter(new FileWriter(mFile, true));
   }

   @Override
   public boolean isOutputStreamAvailable()
   {
      return true;
   }

   @Override
   public OutputStream getOutputStream() throws IOException
   {
      return new BufferedOutputStream(new FileOutputStream(mFile));
   }

   @Override
   public URI getDocumentUri()
   {
      return mFile.toURI();
   }
}
