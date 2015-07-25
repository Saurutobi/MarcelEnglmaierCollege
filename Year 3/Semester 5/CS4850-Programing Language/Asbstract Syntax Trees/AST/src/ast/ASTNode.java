/**
 * 
 */
package ast;

/**
 * @author carr
 *
 */
public interface ASTNode {

	public  void acceptDF(Visitor v);
}
