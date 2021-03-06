/* Soot - a J*va Optimization Framework
 * Copyright (C) 1999 Patrick Lam
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/*
 * Modified by the Sable Research Group and others 1997-1999.  
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */






package soot.jimple.internal;

import soot.tagkit.*;
import soot.*;
import soot.jimple.*;
import soot.util.*;
import java.util.*;

public class JTableSwitchStmt extends AbstractStmt 
    implements TableSwitchStmt
{
    UnitBox defaultTargetBox;
    ValueBox keyBox;
    int lowIndex;
    int highIndex;
    UnitBox[] targetBoxes;

    List stmtBoxes;

  
    public Object clone() 
    {
        return new JTableSwitchStmt(Jimple.v().newImmediateBox(Jimple.cloneIfNecessary(getKey())), lowIndex, highIndex, getTargetBoxesArray(getTargets()), Jimple.v().newStmtBox(getDefaultTarget()));
    }


    // This method is necessary to deal with constructor-must-be-first-ism.
    private static UnitBox[] getTargetBoxesArray(List targets)
    {
        UnitBox[] targetBoxes = new UnitBox[targets.size()];

        for(int i = 0; i < targetBoxes.length; i++)
            targetBoxes[i] = Jimple.v().newStmtBox((Stmt) targets.get(i));

        return targetBoxes;
    }

    public JTableSwitchStmt(Value key, int lowIndex, int highIndex, List targets, Unit defaultTarget)
    {
        this(Jimple.v().newImmediateBox(key), lowIndex, highIndex, 
             getTargetBoxesArray(targets), 
             Jimple.v().newStmtBox(defaultTarget));
    }
    
    
    
    public JTableSwitchStmt(Value key, int lowIndex, int highIndex, List<Object> targets, UnitBox defaultTarget)
    {
        this(Jimple.v().newImmediateBox(key), lowIndex, highIndex, 
             unitBoxListToArray(targets), 
             defaultTarget);
    }
   
    private static UnitBox[] unitBoxListToArray(List<Object> targets) {
        UnitBox[] targetBoxes = new UnitBox[targets.size()];
        
        for(int i = 0; i < targetBoxes.length; i++)
            targetBoxes[i] = (UnitBox) targets.get(i);
        return targetBoxes;
    }
    

    protected JTableSwitchStmt(ValueBox keyBox, int lowIndex, int highIndex, 
                               UnitBox[] targetBoxes, UnitBox defaultTargetBox)
    {
        this.keyBox = keyBox;
        this.defaultTargetBox = defaultTargetBox;

        if(lowIndex > highIndex)
            throw new RuntimeException("Error creating tableswitch: lowIndex(" 
                                       + lowIndex +  ") can't be greater than highIndex(" + highIndex + ").");

        this.lowIndex = lowIndex;
        this.highIndex = highIndex;

        this.targetBoxes = targetBoxes;

        // Build up stmtBoxes
        {
            stmtBoxes = new ArrayList();

            for (UnitBox element : targetBoxes)
				stmtBoxes.add(element);

            stmtBoxes.add(defaultTargetBox);
            stmtBoxes = Collections.unmodifiableList(stmtBoxes);
        }
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        String endOfLine = " ";
        
        buffer.append(Jimple.TABLESWITCH + "(" +
            keyBox.getValue().toString() + ")" + endOfLine);
            
        buffer.append("{" + endOfLine);
        
        for(int i = lowIndex; i <= highIndex; i++)
        {
            buffer.append(
                          "    " + Jimple.CASE + " " + i + ": " + Jimple.GOTO + 
                          " " + getTarget(i - lowIndex) + ";" + endOfLine);
        }

        buffer.append("    " +  Jimple.DEFAULT + 
                      ": " +  Jimple.GOTO + " " 
                      + getDefaultTarget() + ";" + endOfLine);
        
        buffer.append("}");

        return buffer.toString();
    }
    
    public void toString(UnitPrinter up)
    {
        up.literal(Jimple.TABLESWITCH);
        up.literal("(");
        keyBox.toString(up);
        up.literal(")");
        up.newline();
        up.literal("{");
        up.newline();
        for(int i = lowIndex; i <= highIndex; i++) {
            up.literal("    ");
            up.literal(Jimple.CASE);
            up.literal(" ");
            up.literal(new Integer(i).toString());
            up.literal(": ");
            up.literal(Jimple.GOTO);
            up.literal(" ");
            targetBoxes[i-lowIndex].toString(up);
            up.literal(";");
            up.newline();
        }
        
        up.literal("    ");
        up.literal(Jimple.DEFAULT);
        up.literal(": ");
        up.literal(Jimple.GOTO);
        up.literal(" ");
        defaultTargetBox.toString(up);
        up.literal(";");
        up.newline();
        up.literal("}");
    }


    public Unit getDefaultTarget()
    {
        return defaultTargetBox.getUnit();
    }

    public void setDefaultTarget(Unit defaultTarget)
    {
        defaultTargetBox.setUnit(defaultTarget);
    }

    public UnitBox getDefaultTargetBox()
    {
        return defaultTargetBox;
    }

    public Value getKey()
    {
        return keyBox.getValue();
    }

    public void setKey(Value key)
    {
        keyBox.setValue(key);
    }

    public ValueBox getKeyBox()
    {
        return keyBox;
    }

    public void setLowIndex(int lowIndex)
    {
        this.lowIndex = lowIndex;
    }

    public void setHighIndex(int highIndex)
    {
        this.highIndex = highIndex;
    }

    public int getLowIndex()
    {
        return lowIndex;
    }

    public int getHighIndex()
    {
        return highIndex;
    }

    public List getTargets()
    {
        List targets = new ArrayList();

        for (UnitBox element : targetBoxes)
			targets.add(element.getUnit());

        return targets;
    }

    public Unit getTarget(int index)
    {
        return targetBoxes[index].getUnit();
    }

    public void setTarget(int index, Unit target)
    {
        targetBoxes[index].setUnit(target);
    }

    public void setTargets(List<Unit> targets)
    {
        for(int i = 0; i < targets.size(); i++)
            targetBoxes[i].setUnit(targets.get(i));
    }

    public UnitBox getTargetBox(int index)
    {
        return targetBoxes[index];
    }

    public List getUseBoxes()
    {
        List list = new ArrayList();

        list.addAll(keyBox.getValue().getUseBoxes());
        list.add(keyBox);

        return list;
    }

    public List getUnitBoxes()
    {
        return stmtBoxes;
    }

    public void apply(Switch sw)
    {
        ((StmtSwitch) sw).caseTableSwitchStmt(this);
    }    


  


     
    public boolean fallsThrough() {return false;}
    public boolean branches(){return true;}




}
