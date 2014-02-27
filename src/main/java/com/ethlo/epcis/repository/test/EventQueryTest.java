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

import com.ethlo.gs1.epcis.errors.EpcisQueryParameterException;
import com.ethlo.gs1.epcis.errors.EpcisQueryTooLargeException;

/**
 * Tests all the simple event queries from the interoperability tests (SE10-43,
 * SE73).
 * 
 * @author Marco Steybe
 */
public class EventQueryTest extends AbstractEpcisTest
{
    /**
     * Tests the GE_eventTime attribute.
     */
    @Test
    public void testSE10()
    {
        pollAndCompareTest(10);
    }

    /**
     * Tests the LT_eventTime attribute.
     */
    @Test
    public void testSE11()
    {
        pollAndCompareTest(11);
    }

    /**
     * Tests the GE_recordTime attribute.
     */
    @Test
    public void testSE12()
    {
        pollAndCompareTest(12);    
    }

    /**
     * Tests the LT_recordTime attribute.
     */
    @Test
    public void testSE13()
    {
        pollAndCompareTest(13);
    }

    /**
     * Tests the EQ_action attribute.
     */
    @Test
    public void testSE14()
    {
        pollAndCompareTest(14);
    }

    /**
     * Tests the EQ_disposition attribute.
     */
    @Test
    public void testSE15()
    {
        pollAndCompareTest(15);
    }

    /**
     * Tests the EQ_readPoint attribute.
     */
    @Test
    public void testSE16()
    {
        pollAndCompareTest(16);
    }

    /**
     * Tests the WD_readPoint attribute.
     */
    @Test
    public void testSE17()
    {
        pollAndCompareTest(17);
    }

    /**
     * Tests the EQ_bizLocation attribute.
     */
    @Test
    public void testSE18()
    {
        pollAndCompareTest(18);
    }

    /**
     * Tests the WD_bizLocation attribute.
     */
    @Test
    public void testSE19()
    {
        pollAndCompareTest(19);
    }

    /**
     * Tests the EQ_bizTransaction attribute.
     */
    @Test
    public void testSE20()
    {
        pollAndCompareTest(20);
    }

    /**
     * Tests the MATCH_epc attribute.
     */
    @Test
    public void testSE21()
    {
        pollAndCompareTest(21);    
    }

    /**
     * Tests the MATCH_parentID attribute.
     */
    @Test
    public void testSE22()
    {
        pollAndCompareTest(22);    
    }

    /**
     * Tests the MATCH_anyEPC attribute (old spec version: MATCH_childEPC).
     */
    @Test
    public void testSE23()
    {
        pollAndCompareTest(23);
    }

    /**
     * Tests the MATCH_epcClass attribute.
     */
    @Test
    public void testSE24()
    {
        pollAndCompareTest(24);
    }

    /**
     * Tests the EQ_quantity attribute.
     */
    @Test
    public void testSE25()
    {
        pollAndCompareTest(25);
    }

    /**
     * Tests the GT_quantity attribute.
     */
    @Test
    public void testSE26()
    {
        pollAndCompareTest(26);
    }

    /**
     * Tests the GE_quantity attribute.
     */
    @Test
    public void testSE27()
    {
        pollAndCompareTest(27);
    }

    /**
     * Tests the LT_quantity attribute.
     */
    @Test
    public void testSE28()
    {
        pollAndCompareTest(28);    
    }

    /**
     * Tests the LE_quantity attribute.
     */
    @Test
    public void testSE29()
    {
        pollAndCompareTest(29);    
    }

    /**
     * Tests the EQ_fieldname extension field.
     */
    @Test
    public void testSE30()
    {
        pollAndCompareTest(30);
    }

    /**
     * Tests the GT_fieldname attribute.
     */
    @Test
    public void testSE31()
    {
        pollAndCompareTest(31);    
    }

    /**
     * Tests the LT_fieldname attribute.
     */
    @Test
    public void testSE32()
    {
        pollAndCompareTest(32);
    }

    /**
     * Tests the EXISTS_fieldname attribute.
     */
    @Test
    public void testSE33()
    {
        pollAndCompareTest(33);
    }

    /**
     * Tests the HASATTR_fieldname positive case.
     */
    @Test
    public void testSE34()
    {
        pollAndCompareTest(34);
    }

    /**
     * Tests the EQATTR_fieldname_attrname attribute.
     */
    @Test
    public void testSE35()
    {
        pollAndCompareTest(35);
    }

    /**
     * Tests the orderDirection. TODO test Order direction
     */
    @Test
    public void testSE36()
    {
        pollAndCompareTest(36);
    }

    /**
     * Tests a combination of attributes.
     */
    @Test
    public void testSE37()
    {
        pollAndCompareTest(37);
    }

    /**
     * Tests a combination of attributes.
     */
    @Test
    public void testSE38()
    {
        pollAndCompareTest(38);
    }

    /**
     * Tests the eventCountLimit. TODO assert that cases 1-5 match
     */
    @Test
    public void testSE39()
    {
        pollAndCompareTest(39);
    }

    /**
     * Test the maxEventCounts attribute.
     */
    @Test(expected=EpcisQueryTooLargeException.class)
    public void testSE40()
    {
        queryClient.poll(getRequestFromFile(40));
    }

    /**
     * Test impossible eventCount limits.
     */
    @Test(expected=EpcisQueryParameterException.class)
    public void testSE41()
    {
        queryClient.poll(getRequestFromFile(41));
    }

    /**
     * Test the OR operator of attributes.
     */
    @Test
    public void testSE42()
    {
        pollAndCompareTest(42);
    }

    /**
     * Test the AND + OR operators of attributes.
     */
    @Test
    public void testSE43()
    {
        pollAndCompareTest(43);
    }

    /**
     * Tests empty value.
     */
    @Test
    public void testSE73()
    {
        pollAndCompareTest(73);
    }
}
