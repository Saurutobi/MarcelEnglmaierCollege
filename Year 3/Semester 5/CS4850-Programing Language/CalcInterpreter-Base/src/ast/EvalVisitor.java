package ast;

import util.CalcException;
import util.Environment;
import util.IntValue;
import util.Value;

public class EvalVisitor implements Visitor
{
	private Environment env;	
	
	public EvalVisitor(Environment env)
	{
		this.env = env;
	}

	@Override
	public Object visit(AddNode n) throws CalcException
	{
		IntValue lv = (IntValue)n.getLeftOperand().accept(this);
		IntValue rv = (IntValue)n.getRightOperand().accept(this);
		return new IntValue(lv.getVal()+rv.getVal());
	}

	@Override
	public Object visit(SubNode n) throws CalcException
	{
		IntValue lv = (IntValue)n.getLeftOperand().accept(this);
		IntValue rv = (IntValue)n.getRightOperand().accept(this);
		return new IntValue(lv.getVal()-rv.getVal());
	}

	@Override
	public Object visit(IdNode n) throws CalcException
	{
		if (!env.containsKey(n.getName()))
			throw new CalcException("Undefined variable: "+n.getName());
		return env.get(n.getName());
	}

	@Override
	public Object visit(MultiplyNode n) throws CalcException
	{
		IntValue lv = (IntValue)n.getLeftOperand().accept(this);
		IntValue rv = (IntValue)n.getRightOperand().accept(this);
		return new IntValue(lv.getVal()*rv.getVal());
	}

	@Override
	public Object visit(DivideNode n) throws CalcException
	{
		IntValue lv = (IntValue)n.getLeftOperand().accept(this);
		IntValue rv = (IntValue)n.getRightOperand().accept(this);
		return new IntValue(lv.getVal()/rv.getVal());
	}

	@Override
	public Object visit(IntegerNode integerNode) throws CalcException
	{
		return new IntValue(integerNode.getValue());
	}

	@Override
	public Object visit(WithNode n) throws CalcException
	{
		Environment env = new Environment();
		String var = n.getVar();
		Value val = (Value)n.getDef().accept(this);
		env.put(var, val);
		EvalVisitor wv = new EvalVisitor(env);
		
		return n.getBody().accept(wv);
	}

	@Override
	public Object visit(CeilNode n) throws CalcException
	{
		float x = (float)n.getC1().accept(this);
		float y = (float)n.getC2().accept(this);
		return new IntValue((int)Math.ceil((double)(x/y)));
	}

	@Override
	public Object visit(ExpNode n) throws CalcException
	{
		float x = (float)n.getC1().accept(this);
		float y = (float)n.getC2().accept(this);
		return Math.pow((double)x, (double)y);
	}
}
