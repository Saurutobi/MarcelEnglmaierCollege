/**
 * 
 */
package ast;

import util.CalcException;

/**
 * @author carr
 *
 */
public class PrintVisitor implements Visitor
{

	@Override
	public Object visit(AddNode n) throws CalcException
	{
		System.out.print(" + ");
		return null;
	}
	
	@Override
	public Object visit(SubNode n) throws CalcException
	{
		System.out.print(" - ");
		return null;
	}

	@Override
	public Object visit(IdNode n) throws CalcException
	{
		System.out.print(n.getName());
		return null;
	}

	@Override
	public Object visit(MultiplyNode n) throws CalcException
	{
		System.out.print(" * ");
		return null;
	}

	@Override
	public Object visit(DivideNode n) throws CalcException
	{
		System.out.print("/ ");
		return null;
	}

	@Override
	public Object visit(IntegerNode integerNode) throws CalcException {
		System.out.print(integerNode.getValue());
		return null;
	}

	@Override
	public Object visit(WithNode n) throws CalcException {
		System.out.print("with ([" + n.getVar() + " ");
		n.getDef().acceptDF(this);
	    System.out.print("]) {");
		n.getBody().acceptDF(this);
		System.out.print(" }");
		return null;
	}

	@Override
	public Object visit(CeilNode n) throws CalcException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ExpNode n) throws CalcException {
		// TODO Auto-generated method stub
		return null;
	}

}
