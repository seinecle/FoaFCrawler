/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.foafcrawler;

/**
 *
 * @author C. Levallois
 */
public class NodeFoaf {

    private String uri;
    private String foafName;

    public NodeFoaf(String uri, String foafName) {
        this.uri = uri;
        this.foafName = foafName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFoafName() {
        return foafName;
    }

    public void setFoafName(String foafName) {
        this.foafName = foafName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.uri != null ? this.uri.hashCode() : 0);
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
        final NodeFoaf other = (NodeFoaf) obj;
        if ((this.uri == null) ? (other.uri != null) : !this.uri.equals(other.uri)) {
            return false;
        }
        return true;
    }
    
    
}
