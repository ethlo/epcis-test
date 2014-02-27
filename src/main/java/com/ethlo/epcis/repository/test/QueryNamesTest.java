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

import java.util.List;

import org.junit.Test;

/**
 * Test for getQueryNames() (SE45).
 * 
 * @author Marco Steybe
 */
public class QueryNamesTest extends AbstractEpcisTest
{
    /**
     * Tests if the two query types "SimpleEventQuery" and
     * "SimpleMasterDataQuery" are supported by the implementation.
     * 
     * @throws Exception
     *             Any exception, caught by the JUnit framework.
     */
    @Test
    public void testSE45() throws Exception
    {
        List<String> queryNames = queryClient.getQueryNames();

        // must contain SimpleEventQuery and SimpleMasterDataQuery
        assertTrue(queryNames.size() == 2);

        assertTrue(queryNames.contains("SimpleEventQuery"));
        assertTrue(queryNames.contains("SimpleMasterDataQuery"));
    }
}