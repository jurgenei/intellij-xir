package main.fold;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import main.lexer.XirTokens;
import main.psi.impl.XirInFormBody;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class XirFoldingBuilder implements FoldingBuilder
{
  public String getPlaceholderText(ASTNode node)
  {
    if (node.getElementType() == XirTokens.LINE_COMMENT) {
      return ";...";
    } else if (node.getElementType() == XirTokens.BLOCK_COMMENT) {
      return "#|...|#";
    } else if (node.getElementType() == XirTokens.DATUM_COMMENT) {
      return "#;...";
    } else {
      return "...";
    }
  }

  @NotNull
  public FoldingDescriptor[] buildFoldRegions(@NotNull ASTNode rootNode, @NotNull Document document)
  {
    List<FoldingDescriptor> descriptors = new ArrayList<>();
    appendDescriptors(rootNode, descriptors);
    return descriptors.toArray(new FoldingDescriptor[0]);
  }

  public boolean isCollapsedByDefault(ASTNode node)
  {
      return false;
  }

  private void appendDescriptors(ASTNode node, List<FoldingDescriptor> descriptors)
  {
    if (isFoldableNode(node)) {
      descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
    }
    ASTNode child = node.getFirstChildNode();
    while (child != null) {
      if (child.getElementType() == XirTokens.LINE_COMMENT) {
        ASTNode firstNode = child;
        ASTNode lastNode = child;
        while (child != null) {
          if (child.getElementType() == XirTokens.LINE_COMMENT) {
            lastNode = child;
          } else if (child.getElementType() == XirTokens.WHITESPACE) {
          } else {
            break;
          }
          child = child.getTreeNext();
        }
        descriptors.add(new FoldingDescriptor(firstNode,
                new TextRange(firstNode.getStartOffset(),
                        lastNode.getTextRange().getEndOffset())));
      } else if (child.getElementType() == XirTokens.WHITESPACE) {
        child = child.getTreeNext();
      } else {
        appendDescriptors(child, descriptors);
        child = child.getTreeNext();
      }
    }
  }

  private boolean isFoldableNode(ASTNode node)
  {
    PsiElement element = node.getPsi();
    return (element instanceof XirInFormBody)
            || XirTokens.COMMENTS.contains(node.getElementType());
  }
}