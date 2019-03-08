package info;

import java.io.Serializable;
import java.util.List;

//A simple class to tie together the two responses to a search: the results themselves and the list of URLs for the collage
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
