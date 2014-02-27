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

import java.io.ByteArrayInputStream;

import org.junit.Test;

/**
 * This Test first inserts two events each containing two fieldname extensions
 * into the EPCIS Repository. It then sends a query asking for all events with a
 * fieldname extension greater the given value. If everything works as expected,
 * we have correctly inserted the event extension with the correct data type
 * into the database.
 * 
 * @author Marco Steybe
 */
public class EventFieldExtensionTest extends AbstractEpcisTest
{
    private static String event1 = null;
    private static String event2 = null;
    private static String query = null;

    /**
     * Tests event fieldname extensions.
     */
    @Test
    public void testExtension() throws Exception
    {
        // send event1
        int resp = captureClient.capture(event1);
        assertEquals(200, resp);

        // send event2
        resp = captureClient.capture(event2);
        assertEquals(200, resp);

        // send query
        queryClient.poll(new ByteArrayInputStream(query.getBytes()));

        // TODO check response!
    }

    static
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<epcis:EPCISDocument xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:epcis=\"urn:epcglobal:epcis:xsd:1\" xmlns:epcglobal=\"urn:epcglobal:xsd:1\" xsi:schemaLocation=\"urn:epcglobal:epcis:xsd:1 EPCglobal-epcis-1_0.xsd\" xmlns:hls=\"http://schema.hls.com/extension\" creationDate=\"2006-06-25T00:00:00Z\" schemaVersion=\"1.0\">");
        sb.append("<EPCISBody>");
        sb.append("<EventList>");
        sb.append("<ObjectEvent>");
        sb.append("<eventTime>2006-06-25T00:01:00Z</eventTime>");
        sb.append("<eventTimeZoneOffset>-06:00</eventTimeZoneOffset>");
        sb.append("<epcList>");
        sb.append("<epc>urn:epc:id:sgtin:0614141.107340.1</epc>");
        sb.append("</epcList>");
        sb.append("<action>ADD</action>");
        sb.append("<bizStep>urn:epcglobal:cbv:bizstep:commissioning</bizStep>");
        sb.append("<disposition>urn:epcglobal:cbv:disp:active</disposition>");
        sb.append("<readPoint>");
        sb.append("<id>urn:epcglobal:fmcg:loc:0614141073467.RP-1</id>");
        sb.append("</readPoint>");
        sb.append("<bizLocation>");
        sb.append("<id>urn:epcglobal:fmcg:loc:0614141073467.1</id>");
        sb.append("</bizLocation>");
        sb.append("<hls:temperature>49</hls:temperature>");
        sb.append("<hls:batchNumber>2</hls:batchNumber>");
        sb.append("</ObjectEvent>");
        sb.append("</EventList>");
        sb.append("</EPCISBody>");
        sb.append("</epcis:EPCISDocument>");
        event1 = sb.toString();

        sb = new StringBuilder();
        sb.append("<epcis:EPCISDocument xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:epcis=\"urn:epcglobal:epcis:xsd:1\" xmlns:epcglobal=\"urn:epcglobal:xsd:1\" xsi:schemaLocation=\"urn:epcglobal:epcis:xsd:1 EPCglobal-epcis-1_0.xsd\" xmlns:hls=\"http://schema.hls.com/extension\" creationDate=\"2006-06-25T00:00:00Z\" schemaVersion=\"1.0\">");
        sb.append("<EPCISBody>");
        sb.append("<EventList>");
        sb.append("<ObjectEvent>");
        sb.append("<eventTime>2006-06-25T00:01:00Z</eventTime>");
        sb.append("<eventTimeZoneOffset>-06:00</eventTimeZoneOffset>");
        sb.append("<epcList>");
        sb.append("<epc>urn:epc:id:sgtin:0614141.107340.1</epc>");
        sb.append("</epcList>");
        sb.append("<action>ADD</action>");
        sb.append("<bizStep>urn:epcglobal:cbv:bizstep:commissioning</bizStep>");
        sb.append("<disposition>urn:epcglobal:cbv:disp:active</disposition>");
        sb.append("<readPoint>");
        sb.append("<id>urn:epcglobal:fmcg:loc:0614141073467.RP-1</id>");
        sb.append("</readPoint>");
        sb.append("<bizLocation>");
        sb.append("<id>urn:epcglobal:fmcg:loc:0614141073467.1</id>");
        sb.append("</bizLocation>");
        sb.append("<hls:temperature>48</hls:temperature>");
        sb.append("<hls:batchNumber>2</hls:batchNumber>");
        sb.append("</ObjectEvent>");
        sb.append("</EventList>");
        sb.append("</EPCISBody>");
        sb.append("</epcis:EPCISDocument>");
        event2 = sb.toString();

        sb = new StringBuilder();
        sb.append("<epcisq:poll xmlns:epcisq=\"urn:epcglobal:epcis-query:xsd:1\">");
        sb.append("<queryName>SimpleEventQuery</queryName>");
        sb.append("<params>");
        sb.append("<param>");
        sb.append("<name>GT_http://schema.hls.com/extension#temperature</name>");
        sb.append("<value>48</value>");
        sb.append("</param>");
        sb.append("</params>");
        sb.append("</epcisq:poll>");
        query = sb.toString();

        sb = new StringBuilder();
        sb.append("<ObjectEvent>\n");
        sb.append("  <eventTime>2006-06-25T00:01:00.000Z</eventTime>\n");
        sb.append("  <recordTime>2006-12-14T13:41:28.000Z</recordTime>\n");
        sb.append("  <eventTimeZoneOffset>-06:00</eventTimeZoneOffset>\n");
        sb.append("  <epcList/>\n");
        sb.append("  <action>ADD</action>\n");
        sb.append("  <bizStep>urn:epcglobal:cbv:bizstep:commissioning</bizStep>\n");
        sb.append("  <disposition>urn:epcglobal:cbv:disp:active</disposition>\n");
        sb.append("  <readPoint><id>urn:epcglobal:fmcg:loc:0614141073467.RP-1</id></readPoint>\n");
        sb.append("  <bizLocation><id>urn:epcglobal:fmcg:loc:0614141073467.1</id></bizLocation>\n");
        sb.append("  <bizTransactionList/>\n");
        sb.append("  <hls:temperature xmlns:hls=\"http://schema.hls.com/extension\">49</hls:temperature>\n");
        sb.append("  <hls:batchNumber xmlns:hls=\"http://schema.hls.com/extension\">2</hls:batchNumber>\n");
        sb.append("</ObjectEvent>");
    }
}
