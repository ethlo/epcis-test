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

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.gs1.epcis.v10.epcglobal.model.XmlAggregationEventType;
import com.gs1.epcis.v10.epcglobal.model.XmlObjectEventType;
import com.gs1.epcis.v10.epcglobal.model.XmlQuantityEventType;
import com.gs1.epcis.v10.epcglobal.model.XmlTransactionEventType;

/**
 * Tests the capture operation by sending a set of EPCIS capture requests to the
 * repository using the {@link CaptureClient}.
 * 
 * @author Marco Steybe
 */
public class CaptureTest extends AbstractEpcisTest
{
    /**
     * The XML files from which the EPCIS capture events are taken.
     */
    private static final String COMMISSION_CASES_XML = "src/test/resources/events/1_case_commissioning_events.xml";
    private static final String COMMISSION_PALLETS_XML = "src/test/resources/events/2_pallet_commissioning_events.xml";
    private static final String PACK_CASES_XML = "src/test/resources/events/3_pack_cases_events.xml";
    private static final String REICEIVE_PALLET_XML = "src/test/resources/events/4_receive_pallets_events.xml";
    private static final String PICK_ORDER_XML = "src/test/resources/events/5_pick_order_events.xml";
    private static final String SHIP_ORDER_XML = "src/test/resources/events/6_ship_order_events.xml";
    private static final String RECEIVE_ORDER_XML = "src/test/resources/events/7_receive_order_events.xml";
    private static final String STORE_INVENTORY_XML = "src/test/resources/events/8_store_inventory_events.xml";

    private void runCaptureTest(String pathToXml) throws Exception
    {
        long t1 = System.currentTimeMillis();
        int response = captureClient.capture(new FileInputStream(pathToXml));
        Assert.assertEquals(200, response);
        System.out.println("events successfully captured in " + (System.currentTimeMillis() - t1) + "ms");
    }

    /**
     * Capture events test 1.
     */
    @Test
    public void testCommissionCases() throws Exception
    {
        String epcToCheck = "urn:epc:id:sgtin:0614141.107340.1";
        XmlObjectEventType event = dbHelper.getObjectEventByEpc(epcToCheck);
        Assert.assertNull(event);

        runCaptureTest(COMMISSION_CASES_XML);

        event = dbHelper.getObjectEventByEpc(epcToCheck);
        Assert.assertNotNull(event);
    }

    /**
     * Capture events test 2.
     */
    @Test
    public void testCommissionPallets() throws Exception
    {
        String epcToCheck = "urn:epc:id:sscc:0614141.0000000001";
        XmlObjectEventType event = dbHelper.getObjectEventByEpc(epcToCheck);
        Assert.assertNull(event);

        runCaptureTest(COMMISSION_PALLETS_XML);

        event = dbHelper.getObjectEventByEpc(epcToCheck);
        Assert.assertNotNull(event);
    }

    /**
     * Capture events test 3.
     */
    @Test
    public void testPackCases() throws Exception
    {
        String epcToCheck = "urn:epc:id:sgtin:0614141.107340.1";
        XmlAggregationEventType event = dbHelper.getAggregationEventByChildEpc(epcToCheck);
        Assert.assertNull(event);

        runCaptureTest(PACK_CASES_XML);

        event = dbHelper.getAggregationEventByChildEpc(epcToCheck);
        Assert.assertNotNull(event);
    }

    /**
     * Capture events test 4.
     */
    @Test
    public void testReceivePallets() throws Exception
    {
        String epcToCheck = "urn:epc:id:sscc:0614142.0000000010";
        XmlObjectEventType event = dbHelper.getObjectEventByEpc(epcToCheck);
        Assert.assertNull(event);

        runCaptureTest(REICEIVE_PALLET_XML);

        event = dbHelper.getObjectEventByEpc(epcToCheck);
        assertNotNull(event);
    }

    /**
     * Capture events test 5.
     */
    @Test
    public void testPickOrder() throws Exception
    {
        String epcToCheck = "urn:epc:id:sgtin:0614141.107341.1";
        XmlObjectEventType event = dbHelper.getObjectEventByEpc(epcToCheck);
        Assert.assertNull(event);

        runCaptureTest(PICK_ORDER_XML);

        event = dbHelper.getObjectEventByEpc(epcToCheck);
        assertNotNull(event);
    }

    /**
     * Capture events test 6.
     */
    @Test
    public void testShipOrder() throws Exception
    {
        String epcToCheck = "urn:epc:id:sgtin:0614141.107343.1";
        XmlTransactionEventType event = dbHelper.getTransactionEventByEpc(epcToCheck);
        Assert.assertNull(event);

        runCaptureTest(SHIP_ORDER_XML);

        event = dbHelper.getTransactionEventByEpc(epcToCheck);
        Assert.assertNotNull(event);
    }

    /**
     * Capture events test 7.
     */
    @Test
    public void testReceiveOrder() throws Exception
    {
        String epcToCheck = "urn:epc:id:sgtin:0614142.107349.10";
        XmlTransactionEventType event = dbHelper.getTransactionEventByEpc(epcToCheck);
        Assert.assertNull(event);

        runCaptureTest(RECEIVE_ORDER_XML);

        event = dbHelper.getTransactionEventByEpc(epcToCheck);
        Assert.assertNotNull(event);
    }

    /**
     * Capture events test 8.
     */
    @Ignore("QuantityEvent query")
    @Test
    public void testStoreInventory() throws Exception
    {
        String epcToCheck = "urn:epc:idpat:sgtin:0614141.107340.*";
        XmlQuantityEventType event = dbHelper.getQuantityEventByEpcClass(epcToCheck);
        Assert.assertNull(event);

        runCaptureTest(STORE_INVENTORY_XML);

        event = dbHelper.getQuantityEventByEpcClass(epcToCheck);
        Assert.assertNotNull(event);
    }
}
