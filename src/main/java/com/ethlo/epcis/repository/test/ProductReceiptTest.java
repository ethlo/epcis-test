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

import com.ethlo.gs1.epcis.errors.EpcisNoSuchNameException;

/**
 * Test product receipt (SE 4-7).
 * 
 * @author Marco Steybe
 */
public class ProductReceiptTest extends AbstractEpcisTest
{
    /**
     * Test SE4. FIXME: this should be a Subscribe, not a Poll!
     */
    @Test
    public void testSE4() throws Exception
    {
        subscribeAndCompareTest(4);
    }

    /**
     * Test SE5.
     */
    @Test
    public void testSE5() throws Exception
    {
        // FIXME: How to test?
        //Assert.fail("Authentication not supported!");
    }

    /**
     * Test SE6. FIXME: this should be a Subscribe, not a Poll!
     */
    @Test
    public void testSE6() throws Exception
    {
        subscribeAndCompareTest(6);
    }

    /**
     * Test SE7. FIXME: this should be a Subscribe, not a Poll!
     */
    @Test(expected = EpcisNoSuchNameException.class)
    public void testSE7() throws Exception
    {
        queryClient.poll(getRequestFromFile(7));
    }
}
