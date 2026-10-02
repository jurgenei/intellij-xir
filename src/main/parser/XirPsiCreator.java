package main.parser;

import com.intellij.lang.ASTNode;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import main.psi.impl.*;
import main.psi.impl.list.XirList;
import main.psi.util.XirPsiUtil;


public class XirPsiCreator
{
  public PsiElement createElement(ASTNode node)
  {
    IElementType elementType = node.getElementType();

    if (elementType == AST.AST_BAD_CHARACTER)
    {
      return new XirBadCharacter(node);
    }
    else if (elementType == AST.AST_BAD_ELEMENT)
    {
      return new XirBadElement(node);
    }
    else if (elementType == AST.AST_BASIC_ELE_BOOL)
    {
      return new XirEleBoolean(node);
    }
    else if (elementType == AST.AST_BASIC_ELE_CHAR)
    {
      return new XirEleChar(node);
    }
    else if (elementType == AST.AST_BASIC_ELE_KEYWORD)
    {
      return new XirKeyword(node);
    }
    else if (elementType == AST.AST_BASIC_ELE_NUM)
    {
      return new XirEleNumber(node);
    }
    else if (elementType == AST.AST_BASIC_ELE_PROCEDURE)
    {
      return new XirProcedure(node);
    }
    else if (elementType == AST.AST_BASIC_ELE_STR)
    {
      return new XirEleString(node);
    }
    else if (elementType == AST.AST_BASIC_ELE_SYMBOL)
    {
      return new XirSymbol(node);
    }
    else if (elementType == AST.AST_BASIC_ELE_SYMBOL_DEFINE)
    {
      return new XirSymbolDefine(node);
    }
    else if (elementType == AST.AST_ELE_DATUM_COMMENT)
    {
      return new XirEleDatumComment(node);
    }
    else if (elementType == AST.AST_ELE_VECTOR)
    {
      return new XirVector(node);
    }
    else if (elementType == AST.AST_FORM_AND)
    {
      return new XirFormAnd(node);
    }
    else if (elementType == AST.AST_FORM_BEGIN)
    {
      return new XirFormBegin(node);
    }
    else if (elementType == AST.AST_FORM_CALL_PROCEDURE)
    {
      return new XirFormCallProcedure(node);
    }
    else if (elementType == AST.AST_FORM_CAR)
    {
      return new XirFormCar(node);
    }
    else if (elementType == AST.AST_FORM_CDR)
    {
      return new XirFormCdr(node);
    }
    else if (elementType == AST.AST_FORM_COND)
    {
      return new XirFormCond(node);
    }
    else if (elementType == AST.AST_FORM_CONS)
    {
      return new XirFormCons(node);
    }
    else if (elementType == AST.AST_FORM_DEFINE)
    {
      XirFormDefine form = new XirFormDefine(node);
      setupDefineForm(form);
      return form;
    }
    else if (elementType == AST.AST_FORM_DEFINE_RECORD_TYPE)
    {
      XirFormDefineRecordType form = new XirFormDefineRecordType(node);
      setupDefineRecordType(form);
      return form;
    }
    else if (elementType == AST.AST_FORM_DEFINE_SYNTAX)
    {
      XirFormDefineSyntax form = new XirFormDefineSyntax(node);
      setupDefineSyntax(form);
      return form;
    }
    else if (elementType == AST.AST_FORM_DO)
    {
      return new XirFormDo(node);
    }
    else if (elementType == AST.AST_FORM_EXPORT)
    {
      return new XirFormExport(node);
    }
    else if (elementType == AST.AST_FORM_IF)
    {
      return new XirFormIf(node);
    }
    else if (elementType == AST.AST_FORM_IMPORT)
    {
      return new XirFormImport(node);
    }
    else if (elementType == AST.AST_FORM_LET)
    {
      XirFormLet form = new XirFormLet(node);
//      setupLetForm(form);
      return form;
    }
    else if (elementType == AST.AST_FORM_LET_A)
    {
      return new XirFormLetA(node);
    }
    else if (elementType == AST.AST_FORM_LETREC)
    {
      return new XirFormLetrec(node);
    }
    else if (elementType == AST.AST_FORM_LIBRARY)
    {
      return new XirFormLibrary(node);
    }
    else if (elementType == AST.AST_FORM_LIST)
    {
      return new XirFormList(node);
    }
    else if (elementType == AST.AST_FORM_NOT)
    {
      return new XirFormNot(node);
    }
    else if (elementType == AST.AST_FORM_OR)
    {
      return new XirFormOr(node);
    }
    else if (elementType == AST.AST_FORM_PROCEDURE)
    {
      XirFormProcedure form = new XirFormProcedure(node);
//      setupProcedure(form);
      return form;
    }
    else if (elementType == AST.AST_FORM_QUASIQUOTE)
    {
      return new XirFormQuasiquote(node);
    }
    else if (elementType == AST.AST_FORM_QUASISYNTAX)
    {
      return new XirFormQuasisyntax(node);
    }
    else if (elementType == AST.AST_FORM_QUOTE)
    {
      return new XirFormQuote(node);
    }
    else if (elementType == AST.AST_FORM_SET)
    {
      return new XirFormSet(node);
    }
    else if (elementType == AST.AST_FORM_SYNTAX)
    {
      return new XirFormSyntax(node);
    }
    else if (elementType == AST.AST_FORM_UNLESS)
    {
      return new XirFormUnless(node);
    }
    else if (elementType == AST.AST_FORM_UNQUOTE)
    {
      return new XirFormUnquote(node);
    }
    else if (elementType == AST.AST_FORM_UNQUOTE_SPLICING)
    {
      return new XirFormUnquoteSplicing(node);
    }
    else if (elementType == AST.AST_FORM_UNSYNTAX)
    {
      return new XirFormUnsyntax(node);
    }
    else if (elementType == AST.AST_FORM_UNSYNTAX_SPLICING)
    {
      return new XirFormUnsyntaxSplicing(node);
    }
    else if (elementType == AST.AST_FORM_WHEN)
    {
      return new XirFormWhen(node);
    }
    else if (elementType == AST.AST_IN_FORM_BODY)
    {
      return new XirInFormBody(node);
    }
    else if (elementType == AST.AST_IN_FORM_PARAM_LIST)
    {
      return new XirInFormParamList(node);
    }
    else if (elementType == AST.AST_IN_FORM_PARAM_LIST_LET_INNER)
    {
      return new XirInFormParamListLetInner(node);
    }
    else if (elementType == AST.AST_PLAIN_LIST)
    {
      return new XirPlainList(node);
    }
    else if (elementType == AST.AST_UNRECOGNIZED_FORM)
    {
      return new XirUnrecognizedForm(node);
    }
    else
    {
      System.out.println(">>> Unexpected AST Node Type: " + elementType);
      return new XirUnrecognizedForm(node);
    }

//    throw new Error("Unexpected ASTNode: " + node.getElementType());
  }

  private boolean setupLetForm(XirFormLetBase form)
  {
    ASTNode node = form.getNode();
    form.clareLocalDefinitions();
    ASTNode defNode = XirPsiUtil.getNonLeafChildAt(node, 1);
    if (defNode == null) {
      return false;
    }
    if (defNode.getElementType() == AST.AST_BASIC_ELE_SYMBOL) {
      form.addLocalDefinition(defNode.getPsi());
      defNode = XirPsiUtil.getNodeNextNonLeafSibling(defNode);
      if (defNode == null) {
        return false;
      }
    }
    IElementType defNodeType = defNode.getElementType();
    if (defNodeType != AST.AST_PLAIN_LIST && defNodeType != AST.AST_UNRECOGNIZED_FORM) {
      return false;
    }
    defNode = XirPsiUtil.getNonLeafChildAt(defNode, 0);
    if (defNode == null) {
      return true;
    }
    while (defNode != null) {
      ASTNode localDefinition = XirPsiUtil.getNonLeafChildAt(defNode, 0);
      if (localDefinition != null && localDefinition.getElementType() == AST.AST_BASIC_ELE_SYMBOL) {
        form.addLocalDefinition(localDefinition.getPsi());
      }
      defNode = XirPsiUtil.getNodeNextNonLeafSibling(defNode);
    }
      return true;
  }

  private boolean setupDefineForm(XirFormDefine form) {
    ASTNode node = form.getNode();
    form.clareLocalDefinitions();
    ASTNode defNode = XirPsiUtil.getNonLeafChildAt(node, 1);
    if (defNode == null) {
      return false;
    }
    if (defNode.getElementType() == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
      form.setDeclareName(defNode.getPsi());
      return true;
    }
    defNode = XirPsiUtil.getNonLeafChildAt(defNode, 0);
    if (defNode == null) {
      return false;
    }
    if (defNode.getElementType() == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
      form.setDeclareName(defNode.getPsi());
      return true;
    }
    return false;
  }

  private boolean setupDefineSyntax(XirFormDefineSyntax form) {
    ASTNode node = form.getNode();
    ASTNode declareName = getDeclareName(node);
    if (declareName == null) {
      return false;
    } else {
      form.setDeclareName(declareName.getPsi());
      return true;
    }
  }

  private boolean setupDefineRecordType(XirFormDefineRecordType form) {
    ASTNode node = form.getNode();
    ASTNode declareName = getDeclareName(node);
    if (declareName == null) {
      return false;
    } else {
      form.setDeclareName(declareName.getPsi());
      return true;
    }
  }

  private boolean setupProcedure(XirFormProcedure form) {
    ASTNode node = form.getNode();
    form.clareLocalDefinitions();
    ASTNode defNode = XirPsiUtil.getNonLeafChildAt(node, 1);
    if (defNode == null) {
      return false;
    }
    IElementType defNodeType = defNode.getElementType();
    if (defNodeType == AST.AST_BASIC_ELE_SYMBOL) {
      form.addLocalDefinition(defNode.getPsi());
      return true;
    }
    if (defNodeType != AST.AST_PLAIN_LIST && defNodeType != AST.AST_UNRECOGNIZED_FORM) {
      return false;
    }
    defNode = XirPsiUtil.getNonLeafChildAt(defNode, 0);
    if (defNode == null) {
      return true;
    }
    while (defNode != null) {
      if (defNode.getElementType() == AST.AST_BASIC_ELE_SYMBOL) {
        form.addLocalDefinition(defNode.getPsi());
      }
      defNode = XirPsiUtil.getNodeNextNonLeafSibling(defNode);
    }
    return true;
  }

  private ASTNode getDeclareName(ASTNode node)
  {
    ASTNode defNode = XirPsiUtil.getNonLeafChildAt(node, 1);
    if (defNode == null) {
      return null;
    }
    IElementType defNodeType = defNode.getElementType();
    if (defNodeType == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
      return defNode;
    } else {
      return null;
    }
  }

  public PsiFile createFile(FileViewProvider viewProvider)
  {
    return new XirFile(viewProvider);
  }
}
