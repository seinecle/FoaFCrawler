package net.clementlevallois.foafcrawler;

/**
 *
 * @author C. Levallois
 */
public class Edge {

    private String uriSource;
    private String uriTarget;

    public Edge(String uriSource, String uriTarget) {
        this.uriSource = uriSource;
        this.uriTarget = uriTarget;
    }

    public String getUriSource() {
        return uriSource;
    }

    public void setUriSource(String uriSource) {
        this.uriSource = uriSource;
    }

    public String getUriTarget() {
        return uriTarget;
    }

    public void setUriTarget(String uriTarget) {
        this.uriTarget = uriTarget;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.uriSource != null ? this.uriSource.hashCode() : 0);
        hash = 17 * hash + (this.uriTarget != null ? this.uriTarget.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        return true;
    }
    
    
}
