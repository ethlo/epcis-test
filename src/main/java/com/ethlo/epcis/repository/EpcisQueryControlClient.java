package com.ethlo.epcis.repository;

import java.io.InputStream;
import java.util.List;

import com.ethlo.gs1.epcis.errors.EpcisDuplicateSubscriptionException;
import com.ethlo.gs1.epcis.errors.EpcisImplementationException;
import com.ethlo.gs1.epcis.errors.EpcisInvalidURIException;
import com.ethlo.gs1.epcis.errors.EpcisNoSuchNameException;
import com.ethlo.gs1.epcis.errors.EpcisNoSuchSubscriptionException;
import com.ethlo.gs1.epcis.errors.EpcisQueryParameterException;
import com.ethlo.gs1.epcis.errors.EpcisQueryTooComplexException;
import com.ethlo.gs1.epcis.errors.EpcisQueryTooLargeException;
import com.ethlo.gs1.epcis.errors.EpcisSubscribeNotPermittedException;
import com.ethlo.gs1.epcis.errors.EpcisSubscriptionControlsException;
import com.gs1.epcis.v10.query.XmlQueryResults;

/**
 * 
 * @author Morten Haraldsen
  */
public interface EpcisQueryControlClient
{
    List<String> getQueryNames();

    XmlQueryResults poll(InputStream fileInputStream) throws EpcisImplementationException, EpcisNoSuchNameException, EpcisQueryParameterException, EpcisQueryTooComplexException, EpcisQueryTooLargeException;

    void subscribe(InputStream fis) throws EpcisSubscriptionControlsException, EpcisDuplicateSubscriptionException, EpcisInvalidURIException, EpcisSubscribeNotPermittedException;

    void unsubscribe(String queryName) throws EpcisNoSuchSubscriptionException, EpcisInvalidURIException;

    List<String> getSubscriptionIds(String string);
    
    String getStandardVersion();

    String getVendorVersion();
}
