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

import org.junit.Assert;
import org.junit.Test;

import com.ethlo.epcis.utils.QueryCallbackListener;
import com.ethlo.gs1.epcis.errors.EpcisImplementationException;

/**
 * Tests for an ImplementationException with severity SEVERE (SE51, SE69). In
 * order to trigger such an ImplementationException we start off with a dataset
 * that contains invalid data.
 * 
 * @author Marco Steybe
 */
public class ImplementationErrorTest extends AbstractEpcisTest
{
    /**
     * Tests if ImplementationException is raised.
     */
    @Test(expected=EpcisImplementationException.class)
    public void testSE51() throws Exception
    {
        pollAndCompareTest(51);
    }

    /**
     * Tests if ImplementationException is raised (callback). TODO
     */
    @Test
    public void _testSE69() throws Exception
    {
        queryClient.subscribe(getRequestFromFile(69));

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
        Assert.assertNotNull(resp);

        queryClient.unsubscribe("QuerySE69"); // clean up
        Assert.assertTrue(resp.contains("ImplementationException"));
    }
}
