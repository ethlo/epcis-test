package com.ethlo.epcis.repository.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import com.ethlo.epcis.repository.EpcisCaptureClient;
import com.ethlo.epcis.repository.EpcisQueryControlClient;
import com.ethlo.epcis.utils.EpcisDirectDataManipulator;
import com.gs1.epcis.v10.query.XmlPoll;
import com.gs1.epcis.v10.query.XmlQueryResults;
import com.gs1.epcis.v10.query.XmlSubscribe;

/**
 * 
 * @author Morten Haraldsen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/epcis-testcontext.xml"})
@Transactional
public abstract class AbstractEpcisTest extends TestCase
{
    private static final String REQ_PATH = "queries/webservice/requests/";
    private static final String REQ_SUFFIX = "-Request.xml";
    private static final String RESP_PATH = "queries/webservice/responses/";
    private static final String RESP_PREFIX = "Test-EPCIS10-SE";
    private static final String RESP_SUFFIX = "-Response.xml";
    
    private final static Unmarshaller unmarshaller;
    private static Marshaller marshaller;
    
    static
    {
        try
        {
            final JAXBContext jc = JAXBContext.newInstance("com.gs1.epcis.v10.query");
            unmarshaller = jc.createUnmarshaller();
            marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        }
        catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
        
        XMLUnit.setNormalizeWhitespace(true);
    }
    
    @Rule 
    public TestName name = new TestName();

    @Resource
    protected EpcisCaptureClient captureClient;
    
    @Resource
    protected EpcisQueryControlClient queryClient;
    
    @Resource
    protected EpcisDirectDataManipulator dbHelper;

    protected void pollAndCompareTest(int testNumber)
    {
        final XmlQueryResults actResults = queryClient.poll(getRequestFromFile(testNumber));
        final String expected = getResponseFromFile(testNumber);
        try
        {
            final String actual = serialize(actResults);
            System.out.println("EXPECTED:\n" + expected);
            System.out.println("ACTUAL:\n" + actual);
            XMLAssert.assertXMLEqual(expected, actual);
        }
        catch (SAXException | IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    protected void subscribeAndCompareTest(int testNumber)
    {
        queryClient.subscribe(getRequestFromFile(testNumber));
    }
    
    private String serialize(XmlQueryResults results)
    {
        final StringWriter w = new StringWriter();
        try
        {
            marshaller.marshal(new JAXBElement<XmlQueryResults>(new QName("urn:epcglobal:epcis-query:xsd:1", "QueryResults", "e"), XmlQueryResults.class, results), w);
            return w.toString();
        }
        catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }

    protected void runAndCompareTestMaster(int testNumber)
    {
        final XmlQueryResults actResults = queryClient.poll(getMasterRequestFromFile(testNumber));
        final XmlQueryResults expResults = getMasterResponseFromFile(testNumber);
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expResults, actResults));
    }
    
    
    protected InputStream getRequestFromFile(int testNumber)
    {
        return getResource(REQ_PATH + "Test-EPCIS10-SE" + padTestNumber(testNumber) + REQ_SUFFIX);
    }
    
    private String padTestNumber(int testNumber)
    {
        return StringUtils.leftPad(Integer.toString(testNumber), 2, '0');
    }

    protected InputStream getMasterRequestFromFile(int testNumber)
    {
        return getResource(REQ_PATH + "Test-EPCIS10-MD" + padTestNumber(testNumber) + REQ_SUFFIX);
    }
    
    private InputStream getResource(String path)
    {
        try
        {
            return new ClassPathResource(path).getInputStream();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    protected XmlQueryResults getResponseObjectFromFile(int testNumber)
    {
        try
        {
            return ((JAXBElement<XmlQueryResults>)unmarshaller.unmarshal(new StringReader(getResponseFromFile(testNumber)))).getValue();
        }
        catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    protected String getResponseFromFile(int testNumber)
    {
        final String path = RESP_PATH + RESP_PREFIX + padTestNumber(testNumber) + RESP_SUFFIX;     
        try
        {
            return IOUtils.toString(getResource(path));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    protected XmlQueryResults getMasterResponseFromFile(int testNumber)
    {
        final String path = RESP_PATH + "Test-EPCIS10-MD" + padTestNumber(testNumber) + RESP_SUFFIX;
        try
        {
            return (XmlQueryResults) unmarshaller.unmarshal(getResource(path));
        }
        catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public InputStream getRequestFromFile(int testNumber, int subNumber)
    {
        return getResource(REQ_PATH + "Test-EPCIS10-SE" + padTestNumber(testNumber) + "-" + subNumber + REQ_SUFFIX);
    }

    public static Object deserialize(InputStream in)
    {
        try
        {
            return unmarshaller.unmarshal(in);
        }
        catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static JAXBElement<XmlPoll> deserializePoll(InputStream in)
    {
        return (JAXBElement<XmlPoll>) deserialize(in);
    }

    @SuppressWarnings("unchecked")
    public static XmlSubscribe deserializeSubscribe(InputStream in)
    {
        return ((JAXBElement<XmlSubscribe>) deserialize(in)).getValue();
    }
    
    protected void pollAndCompareTest()
    {
        final String methodName = name.getMethodName();
        final int testNumber = Integer.parseInt(methodName.substring(methodName.lastIndexOf('_') + 1));
        pollAndCompareTest(testNumber);
    }
}
