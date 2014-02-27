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
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ethlo.epcis.utils.QueryCallbackListener;
import com.ethlo.gs1.epcis.errors.EpcisNoSuchSubscriptionException;

/**
 * Test for unsubscribing queries (SE44).
 * 
 * @author Marco Steybe
 */
public class CallbackUnsubscribeTest extends AbstractEpcisTest
{
    private static final String PATH = "src/test/resources/queries/webservice/requests/";

    /**
     * Tests if we receive a notification for a subscribed query, and we receive
     * no further notification after the query is unsubscribed.
     */
    @Test
    public void testSE44() throws Exception
    {
        final String query = "Test-EPCIS10-SE44-Request-1-Subscribe.xml";

        // subscribe a query
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
                listener.wait(15000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        String resp1 = listener.fetchResponse();
        Assert.assertNotNull(resp1);

        // parse response to make sure we got back a result
        Document epcis = parseResponse(resp1);
        Node eventList = epcis.getElementsByTagName("EventList").item(0);
        Assert.assertTrue(eventList.hasChildNodes());

        // unsubscribe the query and wait for any response
        queryClient.unsubscribe("QuerySE44");
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
        Assert.assertNull("No response expected, but received: " + resp2, resp2);
        listener.stopRunning();
    }

    /**
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
     */
    private Document parseResponse(final String resp) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource xmlInput = new InputSource(new StringReader(resp));
        return builder.parse(xmlInput);
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        try
        {
            queryClient.unsubscribe("QuerySE44");
        }
        catch (EpcisNoSuchSubscriptionException e)
        {
        }
    }
}
