package ast;

import util.CalcException;

public class CeilNode implements ASTNode
{
	private ASTNode C1;
	private ASTNode C2;
	
	public CeilNode(ASTNode C1, ASTNode C2)
	{
		super();
		this.C1 = C1;
		this.C2 = C2;
	}
	
	public ASTNode getC1()
	{
		return C1;
	}
	
	public ASTNode getC2()
	{
		return C2;
	}
	
	@Override
	public void acceptDF(Visitor v) throws CalcException
	{
		
	}

	@Override
	public Object accept(Visitor v) throws CalcException 
	{
		return v.visit(this);
	}
}
