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
package com.obidea.semantika.mapping.parser.termalxml;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.obidea.semantika.datatype.DataType;
import com.obidea.semantika.datatype.exception.UnsupportedDataTypeException;
import com.obidea.semantika.expression.base.ITerm;
import com.obidea.semantika.mapping.UriTemplate;
import com.obidea.semantika.mapping.base.TermType;
import com.obidea.semantika.mapping.base.sql.SqlColumn;
import com.obidea.semantika.mapping.exception.MappingParserException;
import com.obidea.semantika.util.StringUtils;

public abstract class AbstractMappingElementHandler extends AbstractTermalElementHandler
{
   private TermMap mTermMap;

   protected String mTermType;
   
   private String mValue;
   private String mDatatype;
   private String mLanguage;

   protected boolean bUserDefinedTermType = false;

   protected enum TermMap { COLUMN_VALUE, CONSTANT_VALUE, TEMPLATE_VALUE };

   public AbstractMappingElementHandler(TermalXmlParserHandler handler)
   {
      super(handler);
   }

   @Override
   protected MappingElementHandler getParentElement()
   {
      return (MappingElementHandler) super.getParentElement();
   }

   protected void setTermMap(TermMap termMap)
   {
      mTermMap = termMap;
      if (!bUserDefinedTermType) {
         decideDefaultTermType();
      }
   }

   /**
    * If the term map does not have a rr:termType property, then its term type is:
    * <ol>
    * <li>rr:Literal, if it is an object map and at least one of the following conditions is true:
    *    <ul>
    *    <li>It is a column-based term map.</li>
    *    <li>It has a rr:language property (and thus a specified language tag).</li>
    *    <li>It has a rr:datatype property (and thus a specified datatype).</li>
    *    </ul>
    * </li>
    * <li>rr:IRI, otherwise.</li>
    * </ol>
    * (Reference: http://www.w3.org/TR/r2rml/#termtype)
    */
   protected abstract void decideDefaultTermType();

   protected TermMap getTermMap()
   {
      return mTermMap;
   }

   protected void setTermType(String type)
   {
      mTermType = type;
      bUserDefinedTermType = true;
   }

   protected String getTermType()
   {
      return mTermType;
   }

   protected void setValue(String value)
   {
      mValue = value;
   }

   protected String getValue()
   {
      return mValue;
   }

   protected void setDatatype(String datatype)
   {
      mDatatype = datatype;
   }

   protected String getDatatype()
   {
      return mDatatype;
   }

   /**
    * Returns <code>true</code> if the map has specified explicitly the data
    * type, or <code>false</code> otherwise.
    */
   protected boolean hasDatatype()
   {
      return (StringUtils.isEmpty(mDatatype)) ? false : true;
   }

   protected void setLanguage(String language)
   {
      mLanguage = language;
   }

   protected String getLanguage()
   {
      return mLanguage;
   }

   /**
    * Returns <code>true</code> if the map has language tag specified, or
    * <code>false</code> otherwise.
    */
   protected boolean hasLanguageTag()
   {
      return (StringUtils.isEmpty(mLanguage)) ? false : true;
   }

   protected SqlColumn getColumnTerm(String columnName, String termType, String datatype) throws MappingParserException
   {
      if (termType.equals(R2RmlVocabulary.IRI.getUri())) {
         if (!StringUtils.isEmpty(datatype)) {
            throw illegalTermalMappingException("Cannot use rr:datatype together with term type rr:IRI"); //$NON-NLS-1$
         }
         SqlColumn column = getColumnTerm(columnName);
         column.setTermType(TermType.URI_TYPE);
         return column;
      }
      else if (termType.equals(R2RmlVocabulary.LITERAL.getUri())) {
         SqlColumn column = getColumnTerm(columnName);
         column.setTermType(TermType.LITERAL_TYPE);
         if (!StringUtils.isEmpty(datatype)) {
            overrideColumn(column, datatype); // set as datatype-override RDF literal
         }
         return column;
      }
      else if (termType.equals(R2RmlVocabulary.BLANK_NODE.getUri())) {
         throw unsupportedTermTypeException("rr:BlankNode"); //$NON-NLS-1$
      }
      throw unknownTermTypeException(termType);
   }

   protected ITerm getLiteralTerm(String value, String termType, String datatype) throws MappingParserException
   {
      if (termType.equals(R2RmlVocabulary.IRI.getUri())) {
         if (!StringUtils.isEmpty(datatype)) {
            throw illegalTermalMappingException("Cannot use rr:datatype together with term type rr:IRI"); //$NON-NLS-1$
         }
         return getExpressionObjectFactory().getUriReference(getUri(value));
      }
      else if (termType.equals(R2RmlVocabulary.LITERAL.getUri())) {
         return (StringUtils.isEmpty(datatype)) ?
            getExpressionObjectFactory().getLiteral(value, DataType.STRING) : // by default
            getExpressionObjectFactory().getLiteral(value, datatype);
      }
      else if (termType.equals(R2RmlVocabulary.BLANK_NODE.getUri())) {
         throw unsupportedTermTypeException("rr:BlankNode"); //$NON-NLS-1$
      }
      throw unknownTermTypeException(termType);
   }

   protected ITerm getTemplateTerm(String value, String termType, String datatype) throws MappingParserException
   {
      if (termType.equals(R2RmlVocabulary.IRI.getUri())) {
         if (!StringUtils.isEmpty(datatype)) {
            throw illegalTermalMappingException("Cannot use rr:datatype together with term type rr:IRI"); //$NON-NLS-1$
         }
         return getUriTemplateFunction(value);
      }
      else if (termType.equals(R2RmlVocabulary.LITERAL.getUri())) {
         throw illegalTermalMappingException("Cannot use rr:template together with term type rr:Literal");
      }
      else if (termType.equals(R2RmlVocabulary.BLANK_NODE.getUri())) {
         throw unsupportedTermTypeException("rr:BlankNode"); //$NON-NLS-1$
      }
      throw unknownTermTypeException(termType);
   }

   protected URI getUri(String abbreviatedUri) throws PrefixNotFoundException
   {
      String normalizedAbbreviatedUri = normalizedAbbreviatedUri(abbreviatedUri);
      int colonPos = normalizedAbbreviatedUri.indexOf(":");
      String prefixName = normalizedAbbreviatedUri.substring(0, colonPos);
      String localName = normalizedAbbreviatedUri.substring(colonPos + 1);
      String namespace = getPrefixMapper().get(prefixName);
      if (!StringUtils.isEmpty(namespace)) {
         return URI.create(namespace + localName);
      }
      else {
         throw prefixNotFoundException(prefixName);
      }
   }

   @Override
   protected void handleChild(MappingElementHandler handler)
   {
      // NO-OP: No child node afterwards
   }

   @Override
   protected void handleChild(LogicalTableElementHandler handler)
   {
      // NO-OP: No child node afterwards
   }

   @Override
   protected void handleChild(SubjectMapElementHandler handler)
   {
      // NO-OP: No child node afterwards
   }

   @Override
   protected void handleChild(PredicateObjectMapElementHandler handler)
   {
      // NO-OP: No child node afterwards
   }

   /*
    * Private helper methods
    */

   private String normalizedAbbreviatedUri(String input)
   {
      return (input.indexOf(":") != -1) ? input : ":" + input; //$NON-NLS-1$ //$NON-NLS-2$
   }

   private UriTemplate getUriTemplateFunction(String functionCall) throws MappingParserException
   {
      try {
         TermalTemplate template = new TermalTemplate(functionCall, getUriTemplateMapper());
         String templateString = template.getTemplateString();
         List<SqlColumn> parameters = getColumnTerms(template.getColumnNames());
         return getMappingObjectFactory().createUriTemplate(templateString, parameters);
      }
      catch (Exception e) {
         throw illegalTemplateCallException(e.getMessage());
      }
   }

   private SqlColumn getColumnTerm(String columnName) throws MappingParserException
   {
      SqlColumn column = (SqlColumn) getParentElement().getSourceQuery().findSelectItemExpression(columnName);
      if (column != null) {
         return column;
      }
      throw columnNotFoundException(columnName);
   }

   private List<SqlColumn> getColumnTerms(List<String> columnNames) throws MappingParserException
   {
      List<SqlColumn> toReturn = new ArrayList<SqlColumn>();
      for (String columnName : columnNames) {
         toReturn.add(getColumnTerm(columnName));
      }
      return toReturn;
   }

   private void overrideColumn(SqlColumn column, String datatype) throws MappingParserException
   {
      try {
         column.overrideDatatype(datatype);
      }
      catch (UnsupportedDataTypeException e) {
         throw unsupportedXmlDataTypeException(e.getMessage());
      }
      catch (IllegalArgumentException e) {
         throw datatypeOverrideException(e.getMessage());
      }
   }
}
