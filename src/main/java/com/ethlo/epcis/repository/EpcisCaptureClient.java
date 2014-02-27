package com.ethlo.epcis.repository;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Morten Haraldsen
 */
public interface EpcisCaptureClient
{
    int capture(InputStream request) throws IOException;

    int capture(String xmlRequest) throws IOException;
}
