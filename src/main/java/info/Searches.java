package info;

public class Searches {
    public String searchTerm;
    public int specifiedRadius;
    public int expectedResults;

    public Searches(String st, int sr, int er){
        searchTerm = st;
        specifiedRadius = sr;
        expectedResults = er;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Searches)) return false;
        Searches other = (Searches)obj;
        return searchTerm.equals(other.searchTerm) && specifiedRadius==other.specifiedRadius && expectedResults==other.expectedResults;
    }
}
