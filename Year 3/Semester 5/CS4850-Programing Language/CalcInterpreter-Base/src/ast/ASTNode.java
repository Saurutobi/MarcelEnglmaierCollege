/**
 * 
 */
package ast;

import util.CalcException;

/**
 * @author carr
 *
 */
public interface ASTNode {

	public void acceptDF(Visitor v) throws CalcException;
	public Object accept(Visitor v) throws CalcException;
}
