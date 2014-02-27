package com.ethlo.epcis.repository.test;
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
 

package org.fosstrak.epcis.repository.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.fosstrak.epcis.utils.QueryCallbackListener;

import com.google.code.morphia.query.QueryResults;
import com.kezzler.ssp.epcis.query.errorhandling.EpcisNoSuchSubscriptionException;

*//**
 * Test for initialRecordTime (SE66).
 * 
 * @author Marco Steybe
 *//*
public class CallbackRecordTimeTest extends FosstrakInteropTestCase
{
    private static final String PATH = "src/test/resources/queries/webservice/";

    *//**
     * Tests if setting the initialRecordTime parameter has effect.
     *//*
    public void testSE66() throws Exception
    {
        // run first query
        String query = "Test-EPCIS10-SE66-Request-1-Subscribe.xml";
        InputStream fis = new FileInputStream(PATH + "requests/" + query);
        queryClient.subscribe(fis);
        fis.close();

        // wait for response callback
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
                listener.wait(15000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        String resp1 = listener.fetchResponse();
        assertNotNull(resp1);

        // parse and compare response
        Reader r = new StringReader(resp1);
        QueryResults actResults = QueryResultsParser.parseQueryDocResults(r);
        r.close();
        query = "Test-EPCIS10-SE66-Response-1-2-QueryResults.xml";
        fis = new FileInputStream(PATH + "responses/" + query);
        QueryResults expResults = QueryResultsParser.parseResults(fis);
        fis.close();
        assertTrue(QueryResultsComparator.identical(expResults, actResults));
        queryClient.unsubscribe("QuerySE66");

        // run second query
        query = "Test-EPCIS10-SE66-Request-2-Subscribe.xml";
        fis = new FileInputStream(PATH + "requests/" + query);
        queryClient.subscribe(fis);
        fis.close();

        // wait for response callback
        System.out.println("waiting ...");
        synchronized (listener)
        {
            try
            {
                listener.wait(15000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        String resp2 = listener.fetchResponse();
        assertNotNull(resp2);

        // parse and compare response
        r = new StringReader(resp1);
        actResults = QueryResultsParser.parseQueryDocResults(r);
        r.close();
        query = "Test-EPCIS10-SE66-Response-1-3-QueryResults.xml";
        fis = new FileInputStream(PATH + "responses/" + query);
        expResults = QueryResultsParser.parseResults(fis);
        fis.close();
        assertTrue(QueryResultsComparator.identical(expResults, actResults));

        queryClient.unsubscribe("QuerySE66");
        listener.stopRunning();
    }

    *//**
     * Clears all event data from the repository. {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     *//*
    @Override
    protected void tearDown() throws Exception
    {
        try
        {
            queryClient.unsubscribe("QuerySE66");
        }
        catch (EpcisNoSuchSubscriptionException e)
        {
        }
    }
}
*/