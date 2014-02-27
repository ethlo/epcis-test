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


/**
 * Tests inventory tracking (SE8 & 9).
 * 
 * @author Marco Steybe
 */
public class InventoryTrackingTest extends AbstractEpcisTest
{
    /**
     * TEST SE8.
     * FIXME: this should be a Subscribe, not a Poll!
     */
    @Test
    public void testSE8() throws Exception
    {
        subscribeAndCompareTest(8);
    }

    /**
     * TEST SE9.
     * FIXME: this should be a Subscribe, not a Poll!
     */
    @Test
    public void testSE9() throws Exception
    {
        subscribeAndCompareTest(9);
    }
}
