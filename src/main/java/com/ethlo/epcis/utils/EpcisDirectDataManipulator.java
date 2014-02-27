package com.ethlo.epcis.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.ethlo.epcis.dto.EpcisLocation;
import com.gs1.epcis.v10.epcglobal.model.XmlAggregationEventType;
import com.gs1.epcis.v10.epcglobal.model.XmlObjectEventType;
import com.gs1.epcis.v10.epcglobal.model.XmlQuantityEventType;
import com.gs1.epcis.v10.epcglobal.model.XmlTransactionEventType;

/**
 * 
 * @author mha
 */
public interface EpcisDirectDataManipulator
{
    XmlObjectEventType getObjectEventByEpc(String epc);

    XmlTransactionEventType getTransactionEventByEpc(String epc);

    XmlQuantityEventType getQuantityEventByEpcClass(String epcClass);

    XmlAggregationEventType getAggregationEventByChildEpc(String epc);

    void loadObjectEvents(Iterator<XmlObjectEventType> events);

    void loadCodes(List<String> codes);
    
    void loadLocations(List<EpcisLocation> locations);

    void loadHierarchy(Iterator<Entry<int[], String>> hierarchy);

    boolean containsData(String epc);
}