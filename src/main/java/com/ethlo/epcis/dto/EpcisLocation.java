package com.ethlo.epcis.dto;

/**
 * 
 * @author Morten Haraldsen
 *
 */
public class EpcisLocation
{
    private Long id;
    private String name;
    private String uri;

    public EpcisLocation(String name, String uri)
    {
        this(null, name, uri);
    }
    
    public EpcisLocation(Long id, String name, String uri)
    {
        this.id = id;
        this.name = name;
        this.uri = uri;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getUri()
    {
        return uri;
    }
}
