/******************************************************************************
 * Copyright (c) 2002 - 2006 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *****************************************************************************/
package com.ibm.wala.cast.js.test;

import java.io.IOException;
import java.net.URL;

import com.ibm.wala.cast.js.translator.JavaScriptTranslatorFactory;
import com.ibm.wala.eclipse.util.CancelException;
import com.ibm.wala.ipa.callgraph.CallGraph;

public class TestSimplePageCallGraphShape extends TestJSCallGraphShape {

  public static void main(String[] args) {
    justThisTest(TestSimplePageCallGraphShape.class);
  }

  public void setUp() {
    Util.setTranslatorFactory(new JavaScriptTranslatorFactory.CAstRhinoFactory());
  }

  private static final Object[][] assertionsForPage1 = new Object[][] {
    new Object[] { ROOT, new String[] { "page1.html" } },
    new Object[] { "page1.html",
        new String[] { "prologue.js/substring",
                       "prologue.js/indexOf",
                       "page1.html/DOMDocument/write_to_dom",
                       "prologue.js/encodeURI"
        }
    }
  };

  public void testPage1() throws IOException, IllegalArgumentException, CancelException {
    URL url = getClass().getClassLoader().getResource("pages/page1.html");
    CallGraph CG = Util.makeHTMLCG(url);
    verifyGraphAssertions(CG, assertionsForPage1);
  }

}
