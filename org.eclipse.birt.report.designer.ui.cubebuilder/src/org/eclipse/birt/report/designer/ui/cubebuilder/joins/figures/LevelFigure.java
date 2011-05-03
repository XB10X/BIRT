/*******************************************************************************
 * Copyright (c) 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.designer.ui.cubebuilder.joins.figures;

import org.eclipse.draw2d.IFigure;


public class LevelFigure extends ColumnFigure
{
	public void setDeselectedFonts( )
	{
		( (IFigure) this.getChildren( ).get( 0 ) ).setFont( selectedFont );
		repaint( );
	}
	
}