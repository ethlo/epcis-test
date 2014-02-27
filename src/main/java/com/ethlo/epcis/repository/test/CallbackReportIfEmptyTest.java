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
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.kezzler.ssp.epcis.query.errorhandling.EpcisNoSuchSubscriptionException;

*//**
 * Test for 'reportIfEmpty' tag (SE48).
 * 
 * @author Marco Steybe
 *//*
public class CallbackReportIfEmptyTest extends FosstrakInteropTestCase
{
    private static final String PATH = "src/test/resources/queries/webservice/requests/";
    private static final String REQUEST_1 = "Test-EPCIS10-SE48-Request-1-Subscribe.xml";
    private static final String REQUEST_2 = "Test-EPCIS10-SE48-Request-2-Subscribe.xml";

    *//**
     * Tests that no response is provided if the reportIfEmpty tag is set to
     * false.
     * 
     * @throws Exception
     *             Any exception, caught by the JUnit framework.
     *//*
    @Test
    public void testSE48() throws Exception
    {
        // subscribe the first query
        InputStream fis = new FileInputStream(PATH + REQUEST_1);
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
                listener.wait(15000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        String resp1 = listener.fetchResponse();
        assertNotNull(resp1);

        // parse the response -> must have an empty EventList tag
        Document epcis = parseResponse(resp1);
        Node eventList = epcis.getElementsByTagName("EventList").item(0);
        assertFalse(eventList.hasChildNodes());

        // unsubscribe first query
        try
        {
            queryClient.unsubscribe("QuerySE48-1");
        }
        catch (EpcisNoSuchSubscriptionException e)
        {
        }

        // subscribe the second query
        fis = new FileInputStream(PATH + REQUEST_2);
        queryClient.subscribe(fis);
        fis.close();

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
        assertNull(resp2);
        listener.stopRunning();
    }

    *//**
     * Parses a string into an XML Document.
     * 
     * @param resp
     *            The string to be parsed.
     * @return The parsed XML Document.
     * @throws ParserConfigurationException
     *             If the parser could not be configured.
     * @throws SAXException
     *             If a parse error occurred.
     * @throws IOException
     *             If an I/O error occurred.
     *//*
    private Document parseResponse(final String resp) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource xmlInput = new InputSource(new StringReader(resp));
        return builder.parse(xmlInput);
    }

    *//**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     *//*
    protected void tearDown() throws Exception
    {
        try
        {
            queryClient.unsubscribe("QuerySE48-1");
        }
        catch (EpcisNoSuchSubscriptionException e)
        {
        }
        try
        {
            queryClient.unsubscribe("QuerySE48-2");
        }
        catch (EpcisNoSuchSubscriptionException e)
        {
        }
    }
}
*/