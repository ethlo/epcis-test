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

import com.ethlo.gs1.epcis.errors.EpcisQueryTooLargeException;
import com.ethlo.gs1.epcis.errors.EpcisSubscribeNotPermittedException;

/**
 * Tests all the simple masterdata queries (MD1 - 13).
 * 
 * @author Marco Steybe
 */
public class MasterDataQueryTest extends AbstractEpcisTest
{
    /**
     * TEST MD1.
     */
    public void testMD1() throws Exception
    {
        runAndCompareTestMaster(1);
    }

    /**
     * TEST MD2.
     */
    public void testMD2() throws Exception
    {
        runAndCompareTestMaster(2);
    }

    /**
     * TEST MD3.
     */
    public void testMD3() throws Exception
    {
        runAndCompareTestMaster(3);
    }

    /**
     * TEST MD4.
     */
    public void testMD4() throws Exception
    {
        runAndCompareTestMaster(4);    
    }

    /**
     * TEST MD5.
     */
    public void testMD5() throws Exception
    {
        runAndCompareTestMaster(5);
    }

    /**
     * TEST MD6.
     */
    public void testMD6() throws Exception
    {
        runAndCompareTestMaster(6);    
    }

    /**
     * TEST MD7.
     */
    public void testMD7() throws Exception
    {
        runAndCompareTestMaster(7);
    }

    /**
     * TEST MD8.
     */
    @Test(expected=EpcisQueryTooLargeException.class)
    public void testMD8() throws Exception
    {
        queryClient.poll(getMasterRequestFromFile(8));
    }

    /**
     * TEST MD9.
     */
    public void testMD9() throws Exception
    {
        runAndCompareTestMaster(9);
    }

    /**
     * TEST MD10.
     */
    public void testMD10() throws Exception
    {
        runAndCompareTestMaster(10);
    }

    /**
     * TEST MD11.
     */
    public void testMD11() throws Exception
    {
        runAndCompareTestMaster(11);
    }

    /**
     * TEST MD12.
     */
    public void testMD12() throws Exception
    {
        runAndCompareTestMaster(12);
    }

    /**
     * TEST MD13.
     */
    @Test(expected=EpcisSubscribeNotPermittedException.class)
    public void testMD13() throws Exception
    {
       queryClient.subscribe(getMasterRequestFromFile(13));
    }
}
