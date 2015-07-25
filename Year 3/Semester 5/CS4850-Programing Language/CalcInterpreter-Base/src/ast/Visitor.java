/**
 * 
 */
package ast;

import util.CalcException;

/**
 * @author carr
 *
 */
public interface Visitor {
	public Object visit(AddNode n) throws CalcException;
	public Object visit(SubNode n) throws CalcException;
	public Object visit(IdNode n) throws CalcException;
	public Object visit(MultiplyNode n) throws CalcException;
	public Object visit(DivideNode n) throws CalcException;
	public Object visit(IntegerNode n) throws CalcException;
	public Object visit(WithNode n) throws CalcException;
	public Object visit(CeilNode n) throws CalcException;
	public Object visit(ExpNode n) throws CalcException;
}
