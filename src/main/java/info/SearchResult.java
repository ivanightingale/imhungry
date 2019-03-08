package info;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable
{
    public List<List<Info>> results;
    public List<String> imageURLs;

    public SearchResult(List<List<Info>> results, List<String> imageURLs)
    {
        this.results = results;
        this.imageURLs = imageURLs;
    }
}
