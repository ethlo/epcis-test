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

import org.junit.Test;

import com.ethlo.gs1.epcis.errors.EpcisDuplicateSubscriptionException;
import com.ethlo.gs1.epcis.errors.EpcisInvalidURIException;
import com.ethlo.gs1.epcis.errors.EpcisNoSuchSubscriptionException;
import com.ethlo.gs1.epcis.errors.EpcisQueryParameterException;
import com.ethlo.gs1.epcis.errors.EpcisSubscriptionControlsException;

/**
 * Tests for exceptions and error messages (SE51-SE65, SE68-SE72, SE74).
 * 
 * @author Andrea Gr�ssbauer
 * @author Marco Steybe
 */
public class ErrorMessagesTest extends AbstractEpcisTest
{
    /**
     * Tests if InvalidURIException is raised.
     */
    @Test(expected = EpcisInvalidURIException.class)
    public void testSE52() throws Exception
    {
        queryClient.subscribe(getRequestFromFile(52));
        queryClient.unsubscribe("QuerySE52");
    }

    /**
     * Tests if DuplicateSubscriptionException is raised.
     */
    @Test(expected = EpcisDuplicateSubscriptionException.class)
    public void testSE53() throws Exception
    {
        // subscribe first query
        queryClient.subscribe(super.getRequestFromFile(52, 1));

        // subscribe second query
        queryClient.subscribe(getRequestFromFile(52, 2));
    }

    /**
     * Tests if NoSuchSubscriptionException is raised.
     */
    @Test(expected=EpcisNoSuchSubscriptionException.class)
    public void testSE54() throws Exception
    {
        queryClient.unsubscribe("QuerySE54-1");
    }

    /**
     * Tests if SubscriptionControlsException is raised (second value out of
     * range).
     */
    @Test(expected=EpcisSubscriptionControlsException.class)
    public void testSE55() throws Exception
    {
        queryClient.subscribe(getRequestFromFile(55));
    }

    /**
     * Tests if SubscriptionControlsException is raised (second value out of
     * range).
     */
    @Test(expected=EpcisSubscriptionControlsException.class)
    public void testSE56() throws Exception
    {
        queryClient.subscribe(getRequestFromFile(56));
    }

    /**
     * Tests if SubscriptionControlsException is raised (second value invalid).
     */
    @Test(expected=EpcisSubscriptionControlsException.class)
    public void testSE57() throws Exception
    {
        queryClient.subscribe(getRequestFromFile(57));
    }

    /**
     * Tests if SubscriptionControlsException is raised (dayOfWeek value out of
     * range).
     */
    @Test(expected=EpcisSubscriptionControlsException.class)
    public void testSE58() throws Exception
    {
        queryClient.subscribe(getRequestFromFile(58));
    }

    /**
     * Tests if SubscriptionControlsException is raised (dayOfWeek value
     * invalid).
     */
    @Test(expected = EpcisSubscriptionControlsException.class)
    public void testSE59() throws Exception
    {
        queryClient.subscribe(getRequestFromFile(59));
    }

    /**
     * Tests if SubscriptionControlsException is raised (minute value out of
     * range)
     */
    @Test(expected = EpcisSubscriptionControlsException.class)
    public void testSE60() throws Exception
    {
        queryClient.subscribe(getRequestFromFile(60));
    }

    /**
     * Tests if SubscriptionControlsException is raised (minute value out of
     * range).
     */
    @Test(expected = EpcisSubscriptionControlsException.class)
    public void testSE61() throws Exception
    {
        queryClient.subscribe(getRequestFromFile(61));
    }

    /**
     * Tests if SubscriptionControlsExceptionResponse is raised (minute value
     * invalid).
     */
    @Test(expected = EpcisSubscriptionControlsException.class)
    public void testSE62() throws Exception
    {
        queryClient.subscribe(getRequestFromFile(62));
    }

    /**
     * Tests if SubscriptionControlsExceptionResponse is raised (hour value out
     * of range).
     */
    @Test
    public void testSE63() throws Exception
    {
        subscribeAndCompareTest(63);
    }

    /**
     * Tests if SubscriptionControlsExceptionResponse is raised (hour value out
     * of range).
     */
    @Test(expected=EpcisSubscriptionControlsException.class)
    public void testSE64() throws Exception
    {
        subscribeAndCompareTest(64);
    }

    /**
     * Tests if SubscriptionControlsExceptionResponse is raised (hour value out
     * of range).
     */
    @Test(expected=EpcisSubscriptionControlsException.class)
    public void testSE65() throws Exception
    {
        subscribeAndCompareTest(65);
    }

    /**
     * Tests if QueryParameterExceptionResponse is raised (parameter name not
     * defined).
     */
    @Test(expected=EpcisQueryParameterException.class)
    public void testSE70() throws Exception
    {
        pollAndCompareTest(70);
    }

    /**
     * Tests if QueryParameterExceptionResponse is raised (invalid parameter
     * value).
     */
    @Test(expected=EpcisQueryParameterException.class)
    public void testSE71() throws Exception
    {
        pollAndCompareTest(71);
    }

    /**
     * Tests if QueryParameterExceptionResponse is raised (multiple occurrences
     * of same parameter).
     */
    @Test(expected=EpcisQueryParameterException.class)
    public void testSE72() throws Exception
    {
        pollAndCompareTest(72);
    }
}