package util;

import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class StringPair implements Serializable
{
    private static final long serialVersionUID = 0L;
    public String first;
    public String second;

    public StringPair(String s1, String s2)
    {
        first = s1;
        second = s2;
    }

}
