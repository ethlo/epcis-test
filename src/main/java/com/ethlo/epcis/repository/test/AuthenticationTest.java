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

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Marco Steybe
 */
public class AuthenticationTest extends AbstractEpcisTest
{
    /**
     * Test SE1.
     */
    @Test
    public void testSE1() throws Exception
    {
        pollAndCompareTest(1);
    }

    /**
     * Test SE2.
     */
    @Test
    public void testSE2() throws Exception
    {
        //Assert.fail("Authentication not supported!");
    }

    /**
     * Test SE3.
     */
    @Ignore("Uses partial code matching. Impossible to support")
    @Test
    public void testSE3() throws Exception
    {
        pollAndCompareTest(3);
    }
}
