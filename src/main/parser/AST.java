package main.parser;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import main.XirLanguage;

public interface AST
{
  IFileElementType AST_FILE = new IFileElementType(XirLanguage.INSTANCE);

    IElementType AST_PLAIN_LIST = new XirElementType("ast plain list");

  // Basic element
  IElementType AST_BASIC_ELE_BOOL = new XirElementType("ast bool");
  IElementType AST_BASIC_ELE_NUM = new XirElementType("ast number");
  IElementType AST_BASIC_ELE_CHAR = new XirElementType("ast character");
  IElementType AST_BASIC_ELE_STR = new XirElementType("ast string");
  IElementType AST_BASIC_ELE_KEYWORD = new XirElementType("ast keyword");
  IElementType AST_BASIC_ELE_PROCEDURE = new XirElementType("ast procedure");
  IElementType AST_BASIC_ELE_SYMBOL = new XirElementType("ast symbol");
  IElementType AST_BASIC_ELE_SYMBOL_DEFINE = new XirElementType("ast symbol define");

  // Other element
  IElementType AST_ELE_VECTOR = new XirElementType("ast vector");
  IElementType AST_ELE_DATUM_COMMENT = new XirElementType("ast datum comment");

  // Forms
  IElementType AST_IN_FORM_BODY = new XirElementType("ast body of form");
  IElementType AST_IN_FORM_PARAM_LIST = new XirElementType("ast parameter list of form");
  IElementType AST_IN_FORM_PARAM_LIST_LET_INNER = new XirElementType("ast inner parameter list of let form");
  IElementType AST_FORM_DEFINE = new XirElementType("ast define");
  IElementType AST_FORM_DEFINE_RECORD_TYPE = new XirElementType("ast define-record-type");
  IElementType AST_FORM_DEFINE_SYNTAX = new XirElementType("ast define-syntax");
  IElementType AST_FORM_PROCEDURE = new XirElementType("ast procedure");   // lambda
  IElementType AST_FORM_CALL_PROCEDURE = new XirElementType("ast call procedure");
  IElementType AST_FORM_LET = new XirElementType("ast let");
  IElementType AST_FORM_LET_A = new XirElementType("ast let*");
  IElementType AST_FORM_LETREC = new XirElementType("ast letrec");
  IElementType AST_FORM_SET = new XirElementType("ast set");
  IElementType AST_FORM_QUOTE = new XirElementType("ast quote");
  IElementType AST_FORM_QUASIQUOTE = new XirElementType("ast quasiquote");
  IElementType AST_FORM_UNQUOTE = new XirElementType("ast unquote");
  IElementType AST_FORM_UNQUOTE_SPLICING = new XirElementType("ast unquote-splicing");
  IElementType AST_FORM_SYNTAX = new XirElementType("ast syntax");
  IElementType AST_FORM_QUASISYNTAX = new XirElementType("ast quasisyntax");
  IElementType AST_FORM_UNSYNTAX = new XirElementType("ast unsyntax");
  IElementType AST_FORM_UNSYNTAX_SPLICING = new XirElementType("ast unsyntax-splicing");

  // Data structure forms
  IElementType AST_FORM_CAR = new XirElementType("ast car");
  IElementType AST_FORM_CDR = new XirElementType("ast cdr");
  IElementType AST_FORM_CONS = new XirElementType("ast cons");
  IElementType AST_FORM_LIST = new XirElementType("ast list");

  // Library form
  IElementType AST_FORM_LIBRARY = new XirElementType("ast library");
  IElementType AST_FORM_IMPORT = new XirElementType("ast import");
  IElementType AST_FORM_EXPORT = new XirElementType("ast export");

  // Running process form
  IElementType AST_FORM_BEGIN = new XirElementType("ast begin");
  IElementType AST_FORM_IF = new XirElementType("ast if");
  IElementType AST_FORM_COND = new XirElementType("ast cond");
  IElementType AST_FORM_WHEN = new XirElementType("ast when");
  IElementType AST_FORM_UNLESS = new XirElementType("ast unless");
  IElementType AST_FORM_DO = new XirElementType("ast do");

  // Logic form
  IElementType AST_FORM_AND = new XirElementType("ast and");
  IElementType AST_FORM_OR = new XirElementType("ast or");
  IElementType AST_FORM_NOT = new XirElementType("ast not");

  // Macro form


  IElementType AST_BAD_CHARACTER = new XirElementType("ast bad character");
  IElementType AST_UNRECOGNIZED_FORM = new XirElementType("ast unrecognized form");
  IElementType AST_BAD_ELEMENT = new XirElementType("ast bad element");

  TokenSet AST_ELEMENTS = TokenSet.create(AST_PLAIN_LIST,
          AST_BASIC_ELE_BOOL, AST_BASIC_ELE_NUM, AST_BASIC_ELE_CHAR, AST_BASIC_ELE_STR,
          AST_BASIC_ELE_KEYWORD, AST_BASIC_ELE_PROCEDURE, AST_BASIC_ELE_SYMBOL, AST_BASIC_ELE_SYMBOL_DEFINE,
          AST_ELE_VECTOR,
          AST_IN_FORM_BODY, AST_IN_FORM_PARAM_LIST, AST_IN_FORM_PARAM_LIST_LET_INNER,
          AST_FORM_DEFINE, AST_FORM_DEFINE_RECORD_TYPE, AST_FORM_DEFINE_SYNTAX,
          AST_FORM_PROCEDURE, AST_FORM_CALL_PROCEDURE,
          AST_FORM_LET, AST_FORM_LET_A, AST_FORM_LETREC,
          AST_FORM_SET, AST_FORM_QUOTE, AST_FORM_QUASIQUOTE,
          AST_FORM_CAR, AST_FORM_CDR, AST_FORM_CONS, AST_FORM_LIST,
          AST_FORM_LIBRARY,
          AST_FORM_BEGIN, AST_FORM_IF, AST_FORM_COND, AST_FORM_WHEN, AST_FORM_UNLESS,
          AST_FORM_AND, AST_FORM_OR, AST_FORM_NOT,
          AST_BAD_CHARACTER, AST_UNRECOGNIZED_FORM, AST_BAD_ELEMENT);

  TokenSet LEAF_ELEMENTS = TokenSet.create(
          AST_BASIC_ELE_BOOL, AST_BASIC_ELE_NUM, AST_BASIC_ELE_CHAR,
          AST_BASIC_ELE_STR, AST_BASIC_ELE_KEYWORD, AST_BASIC_ELE_PROCEDURE, AST_BASIC_ELE_SYMBOL,
          AST_BAD_CHARACTER, AST_BAD_ELEMENT);

  TokenSet DEFINE_FORMS = TokenSet.create(
          AST_FORM_DEFINE, AST_FORM_DEFINE_RECORD_TYPE, AST_FORM_DEFINE_SYNTAX,
          AST_FORM_LIBRARY, AST_FORM_EXPORT);
}
