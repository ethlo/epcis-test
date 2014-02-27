/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Fosstrak (www.fosstrak.org).
 *
 * Fosstrak is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Fosstrak is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Fosstrak; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package com.ethlo.epcis.repository.test;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import com.ethlo.epcis.utils.QueryCallbackListener;
import com.ethlo.gs1.epcis.errors.EpcisQueryTooLargeException;

/**
 * Tests for QueryTooLargeException (SE50, SE68). TODO: this cannot yet be
 * tested automatically, idea: need a repository running with a number of x
 * events in the database and the application property 'maxQueryResultRows' set
 * to less than x.
 * 
 * @author Marco Steybe
 */
public class QueryTooLargeTest extends AbstractEpcisTest
{
    private static final String PATH = "src/test/resources/queries/webservice/requests/";

    /**
     * Tests if QueryTooLargeException is raised.
     */
    @Test(expected=EpcisQueryTooLargeException.class)
    public void _testSE50() throws Exception
    {
        queryClient.poll(getRequestFromFile(50));
    }

    /**
     * Tests if QueryTooLargeException is raised (callback).
     */
    @Test
    public void _testSE68() throws Exception
    {
        // subscribe query
        final String query = "Test-EPCIS10-SE68-Request.xml";
        InputStream fis = new FileInputStream(PATH + query);
        queryClient.subscribe(fis);
        fis.close();

        // start subscription response listener
        QueryCallbackListener listener = QueryCallbackListener.getInstance();
        if (!listener.isRunning())
        {
            listener.start();
        }
        System.out.println("waiting ...");
        synchronized (listener)
        {
            try
            {
                listener.wait(10000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        String resp = listener.fetchResponse();
        assertNotNull(resp);

        queryClient.unsubscribe("QuerySE68"); // clean up
        assertTrue(resp.contains("QueryTooLargeException"));
    }
}
