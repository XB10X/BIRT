/*******************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.designer.data.ui.providers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.data.adapter.api.DataRequestSession;
import org.eclipse.birt.report.designer.data.ui.actions.NewDataSetAction;
import org.eclipse.birt.report.designer.data.ui.dataset.AppContextPopulator;
import org.eclipse.birt.report.designer.data.ui.dataset.AppContextResourceReleaser;
import org.eclipse.birt.report.designer.data.ui.dataset.ExternalUIUtil;
import org.eclipse.birt.report.designer.internal.ui.data.IDataServiceProvider;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.DataSourceHandle;
import org.eclipse.birt.report.model.api.Expression;
import org.eclipse.birt.report.model.api.olap.CubeHandle;

/**
 * DefaultDataServiceProvider
 */
public class DefaultDataServiceProvider implements IDataServiceProvider
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.report.designer.internal.ui.data.IDataServiceProvider
	 * #createDataSet()
	 */
	public void createDataSet( )
	{
		new NewDataSetAction( ).run( );
	}

	public List getSelectValueList( Expression expression,
			DataSetHandle dataSetHandle, boolean useDataSetFilter )
			throws BirtException
	{
		return DistinctValueSelector.getSelectValueList( expression,
				dataSetHandle,
				useDataSetFilter );
	}

	public List getSelectValueFromBinding( Expression expression,
			DataSetHandle dataSetHandle, Iterator binding,
			Iterator groupIterator, boolean useDataSetFilter )
			throws BirtException
	{
		return DistinctValueSelector.getSelectValueFromBinding( expression,
				dataSetHandle,
				binding,
				groupIterator,
				useDataSetFilter );
	}

	public void registerSession( DataSetHandle handle,
			DataRequestSession session ) throws BirtException
	{
		AppContextPopulator.populateApplicationContext( handle, session );
	}
	
	public void registerSession ( CubeHandle handle,
			DataRequestSession session ) throws BirtException
	{
		if ( session.getDataSessionContext( ).getAppContext( ) == null )
		{
			session.getDataSessionContext( ).setAppContext( new HashMap( ) );
		}
		AppContextPopulator.populateApplicationContext( handle, session.getDataSessionContext( ).getAppContext( ) );
	}
	
	public void registerSession ( DataSourceHandle handle,
			DataRequestSession session ) throws BirtException
	{
		if ( session.getDataSessionContext( ).getAppContext( ) == null )
		{
			session.getDataSessionContext( ).setAppContext( new HashMap( ) );
		}
		AppContextPopulator.populateApplicationContext( handle, session.getDataSessionContext( ).getAppContext( ) );
	}
	
	public void unRegisterSession( DataRequestSession session )
			throws BirtException
	{
		if ( session != null )
		{
			AppContextResourceReleaser.release( session.getDataSessionContext( )
					.getAppContext( ) );
		}
	}
	
	public void updateColumnCache( DataSetHandle dataSetHandle,
			boolean holdEvent ) throws BirtException
	{
		ExternalUIUtil.updateColumnCache( dataSetHandle, holdEvent );
	}
}
